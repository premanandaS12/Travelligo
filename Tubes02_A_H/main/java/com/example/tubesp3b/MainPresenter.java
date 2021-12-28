package com.example.tubesp3b;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPresenter {
    protected IMainActivity ui;
    private Context context;
    private MainActivity activity;
    private Gson gson;
    private static final String BASE_URL = "https://devel.loconode.com/pppb/v1/";
    private static final String AUTHENTICATE = "authenticate";
    private static final String ROUTES = "routes";
    private static final String COURSES = "courses?";
    private static final String SOURCE = "source";
    private static final String DESTINATION = "destination";
    private static final String VEHICLE = "vehicle";
    private static final String DATE = "date";
    private static final String HOUR = "hour";
    private static final String ORDERS = "orders?";
    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";
    private static final String GET_ORDER = "orders";
    private RequestQueue requestQueue;
    private LoginMessage loginMessage;
    private List<Payload> rute;
    private List<TravelCourses> currentCourse;
    private List<TravelOrderHist> orderHist;
    private DbHelper dbHelper;
    private List<String> asal;
    private List<String> tujuan;
    private List<String> jam;
    private List<String> vehicleType;
    private List<Shuttle> poolLocation;

    public MainPresenter(IMainActivity view, MainActivity activity, Context context){
        this.ui = view;
        this.activity = activity;
        this.context = activity.getBaseContext();
        this.gson = new Gson();
        this.requestQueue = Volley.newRequestQueue(context);
        this.rute = new ArrayList<Payload>();
        this.orderHist = new ArrayList<TravelOrderHist>();
        this.dbHelper = new DbHelper(context);
        this.tujuan = new ArrayList<>();
        this.asal = new ArrayList<>();
        this.jam = new ArrayList<>(Arrays.asList("00","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"));
        this.vehicleType = new ArrayList<>(Arrays.asList("Small", "Large"));
        this.poolLocation = new ArrayList<>();
        this.currentCourse = new ArrayList<>();
    }
//    Method untuk autentikasi login user
    public void authenticateLogin(String username, String password){
        User user = new User(username,password);
        String json = gson.toJson(user);
        try {
            JSONObject jsonObject = new JSONObject(json);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASE_URL + AUTHENTICATE, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String resp = response.toString();
                    try {
                        if(null!=resp){
                            LoginMessage loginMessage = gson.fromJson(resp,LoginMessage.class);
                            setLoginMessage(loginMessage, username);
                        }else{
                            String err = response.getString("errcode");
                            toastMessage(err);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    toastMessage("Failed to login.");
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

//    Masukkan response yang didapat pada saat login ke obj Login message, kemudian simpan token, username, jumlah order, dan jumlah point user yang baru login
    public void setLoginMessage(LoginMessage loginMessage, String username){
        this.loginMessage = loginMessage;
        this.dbHelper.addUser(this.loginMessage.getToken(),username,0,0);
        this.ui.changePage(1);
    }

//    Method untuk mendapatkan semua rute travel untuk kemudian ditampilkan di view
    public void getRoutes(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL + ROUTES, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arr = response.getJSONArray("payload");
                    for(int i=0;i<arr.length();i++){
                        JSONObject payload = arr.getJSONObject(i);
                        String temp = payload.toString();
                        Payload currPayload = gson.fromJson(temp,Payload.class);
                        addPayload(currPayload);
                    }
                    updateListAsalTujuan();
                    updateJam();
                    updateVehicleType();
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastMessage("Failed to get routes.");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = getTokenFromDb();
                Map<String,String> headers= new HashMap<String,String>();
                headers.put("Authorization", "Bearer "+ token);
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }


//    method untuk mendapatkan course sebelum melakukan order tiket
    public void getCourses(String source, String destination, String vehicleType, String date, String hour) {
        Uri uri = Uri.parse(BASE_URL + COURSES).buildUpon().appendQueryParameter(SOURCE, source)
                .appendQueryParameter(DESTINATION, destination)
                .appendQueryParameter(VEHICLE, vehicleType)
                .appendQueryParameter(DATE, date)
                .appendQueryParameter(HOUR, hour).build();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, uri.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String resp = response.getString("payload");
                    if(null!=resp){
                        JSONArray arr = response.getJSONArray("payload");
                        for(int i=0;i<arr.length();i++){
                            JSONObject payload = arr.getJSONObject(i);
                            String temp=payload.toString();
                            TravelCourses curCourse = gson.fromJson(temp,TravelCourses.class);
                            addCourse(curCourse);
                        }

                    }else if(null==resp){
                        String errCode = response.getString("errcode");
                        toastMessage(errCode);
                    }
                    Log.d("payload",resp);
                    Log.d("msk try","msk");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastMessage("Failed to get course.");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers= new HashMap<String,String>();
                headers.put("Authorization", "Bearer "+getTokenFromDb());
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);
    }
    //Method untuk mendapatkan history
    public void getTravelOrderHist(int limit, int offset) throws MalformedURLException {
        Uri uri = Uri.parse(BASE_URL + ORDERS).buildUpon().appendQueryParameter(LIMIT, String.valueOf(limit)).appendQueryParameter(OFFSET, String.valueOf(offset)).build();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, uri.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arr = response.getJSONArray("payload");
                    for(int i=0;i<arr.length();i++){
                        JSONObject payload = arr.getJSONObject(i);
                        String temp = payload.toString();
                        TravelOrderHist currTO = gson.fromJson(temp, TravelOrderHist.class);
                        addTravelOrderHist(currTO);
                    }

                    getUsername();
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastMessage("Failed to get history.");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers= new HashMap<String,String>();
                headers.put("Authorization", "Bearer "+getTokenFromDb());
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

//    method untuk order tiket
    public void order(String course_id, String seats) throws JSONException {
        Pesan pesan = new Pesan(course_id,seats);
        String json = gson.toJson(pesan);
        JSONObject jsonObject = new JSONObject(json);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASE_URL + GET_ORDER, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String msg= null;
                try {
                    msg = response.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(msg.equals("Order submitted")){
                    JSONArray arr = null;
                    try {
                        arr = response.getJSONArray("payload");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for(int i=0;i<arr.length();i++){
                        JSONObject payload = null;
                        try {
                            payload = arr.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String temp = payload.toString();
                        Order currOrder= gson.fromJson(temp, Order.class);
                        displayTicket(currOrder);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastMessage("Failed to order.");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers= new HashMap<String,String>();
                headers.put("Authorization", "Bearer "+getTokenFromDb());
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

//  Method untuk mengrim toast ke view
    public void toastMessage(String msg){
        this.ui.toastMessage(msg);
    }

//  Add payload ke atribut mainPresenter
    public void addPayload(Payload currPayload){
        this.rute.add(currPayload);
    }

//    add course obj ke atribut mainPresenter
    public void addCourse(TravelCourses course){
        this.currentCourse.add(course);
        updateStatusKursi();
    }

//    add objek history yang didapat dari response ke list orderhist (atribut mainPresenter)
    public void addTravelOrderHist(TravelOrderHist travelOrder){
        this.orderHist.add(travelOrder);
        updateHistory();
    }

//    Check apakah user sudah login atau belum
    public Boolean isLogin(){
        String token = this.dbHelper.getToken();
        if(null==token || token.equals("")){
            return false;
        }else{
            return true;
        }
    }

//    Method untuk get token dari db
    public String getTokenFromDb(){
        String token = this.dbHelper.getToken();
        return token;
    }

//    Sort untuk list asal dan tujuan agar kota tidak double
    public void updateListAsalTujuan(){
        for(int i=0;i<this.rute.size()-1;i++){
            if(!this.asal.contains(this.rute.get(i).getSource())){
                this.asal.add(this.rute.get(i).getSource());
            }
        }
        for(int i=0;i<this.rute.size()-1;i++){
            if(!this.tujuan.contains(this.rute.get(i).getDestination())){
                this.tujuan.add(this.rute.get(i).getDestination());
            }
        }
        Collections.sort(this.asal);
        Collections.sort(this.tujuan);

        this.ui.updateAsal(this.asal);
        this.ui.updateTujuan(this.tujuan);
    }

//    kirim String jam berangkat melalui ui untuk ditampilkan di view
    public void updateJam(){
        this.ui.updateJamBerangkat(this.jam);
    }

//    Kirim String vehicleType melalui ui untuk ditampilkan di view
    public void updateVehicleType(){
        this.ui.updateVehicle(this.vehicleType);
    }

    public void getObjPesanan(String source, String destination){
        for(int i=0;i<this.rute.size();i++){
            if(this.rute.get(i).getSource()==source && this.rute.get(i).getDestination()== destination){
                this.ui.ruteDipilih(this.rute.get(i));
            }else{
                this.toastMessage("Invalid destination.");
            }
        }
    }

//    Add lokasi pool shuttle ke dalam list kemudian dikirim ke listview untuk lokasishuttle
    public void getLocation(){
        Shuttle shuttlelocBdg = new Shuttle("Jalan Pasteur No. 16A","Bandung");
        Shuttle shuttleLocJkt = new Shuttle("Jalan Pademangan Indah 5 No. 12","Jakarta");
        Shuttle shuttleLocBek = new Shuttle("Jalan Pangeran Diponegoro No. 19F","Bekasi");
        Shuttle shuttleLocCik = new Shuttle("Jalan Mataram No. 39A","Cikarang");
        this.poolLocation.add(shuttlelocBdg);
        this.poolLocation.add(shuttleLocJkt);
        this.poolLocation.add(shuttleLocBek);
        this.poolLocation.add(shuttleLocCik);
        this.ui.updatePoolLocation(this.poolLocation);
    }

//    kirim username melalui ui untuk ditampilkan di view
    public void getUsername(){
        this.ui.updateUname(this.dbHelper.getUsername());
    }

//    Update object ke atribut kelas mainPresenter
    public void updateHistory(){
        this.ui.updateHistory(this.orderHist);
    }

//    Untuk konversi hasil kursi yang telah dipesan menjadi array of boolean
//    Kursi yang telah dipesan oleh orang lain didapat pada saat melakukan request ke api
    public void updateStatusKursi(){
        TravelCourses tc = this.currentCourse.get(0);
        if(tc.getVehicleType().equals("Small")){
            boolean[] booked=new boolean[6];
            boolean[] dipencet=new boolean[6];
            for(int i=0;i<tc.getSeats().length;i++){
                booked[tc.getSeats()[i]-1]=true;
                dipencet[tc.getSeats()[i]-1]=true;
            }
            ui.updateCourse(tc,booked,dipencet,11);
        }else if(tc.getVehicleType().equals("Large")){
            boolean[] booked=new boolean[10];
            boolean[] dipencet=new boolean[10];
            for(int i=0;i<tc.getSeats().length;i++){
                booked[tc.getSeats()[i]-1]=true;
                dipencet[tc.getSeats()[i]-1]=true;
            }
            ui.updateCourse(tc,booked,dipencet,10);
        }

    }

//    Untuk mendapatkan nomor kursi yang dipesan dengan menekan kanvas
    public String getSeatNumber(boolean[] booked, boolean[] dipencet){
        String seats="";
        int counter=0;
        for(int i=0;i<booked.length;i++){
            if(booked[i]==false && dipencet[i]==true){
                if(counter==0){
                    seats+=String.valueOf(i+1);
                }else if(counter>0){
                    seats=seats+","+String.valueOf(i+1);
                }
                counter++;
            }
        }
        return seats;
    }

//    Untuk konversi jumlah total seat dipesan dari kanvas yang ditekan
    public int jumlahSeatDipesan(boolean[] booked, boolean[] dipencet){
        int counter=0;
        for(int i=0;i<booked.length;i++){
            if(booked[i]!=true && dipencet[i]==true){
                counter++;
            }
        }
        return counter;
    }

//    Untuk mendapatkan username yang disimpan di db
    public String getUsernameFromDb(){
        return this.dbHelper.getUsername();
    }

//    Untuk menampilkan tiket yang didapat ketika user sudah selesai melakukan transaksi pembelian tiket
    public void displayTicket(Order order){
        String username = getUsernameFromDb();
        updateOrderAndPoint();
        this.ui.displayTicket(order, username);
    }

//    Method untuk logout di mana ketika user menekan tombol logout token akan dihapus
    public void logout(){
        this.dbHelper.deleteToken(this.dbHelper.getToken());
        activity.finish();
    }

//    Method untuk update jumlah order dan point ketika user memencet tombol order untuk membeli tiket. Setiap pembelian tiket oleh user akan menambah 100 poin dan 1 order
    public void updateOrderAndPoint(){
        int point = this.dbHelper.getJumlahPoint();
        int orderCount = this.dbHelper.getJumlahOrder();
        String token = this.dbHelper.getToken();
        this.dbHelper.updatePoinAndOrder(orderCount+1,point+100,token);
    }

//    Method untuk get jumlah total order yang telah dilakukan user dari db
    public int getOrderCount(){
        return this.dbHelper.getJumlahOrder();
    }

//    Method untuk get point yang dikumpulkan user dari db
    public int getPoint(){
        return this.dbHelper.getJumlahPoint();
    }


//    Method untuk membuka phone/call di hp
    public void dial(String telp){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+telp));
        activity.startActivity(intent);
    }

}
