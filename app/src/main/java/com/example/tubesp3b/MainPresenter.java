package com.example.tubesp3b;

import android.content.Context;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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
    private TravelCourses currentCourse;
    private List<TravelOrderHist> orderHist;
    private DbHelper dbHelper;
    private Order order;
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
    }

    public void authenticateLogin(String username, String password){
        Log.d("login","msk login");
        User user = new User(username,password);
        String json = gson.toJson(user);
        this.dbHelper.addUername(username);
        try {
            JSONObject jsonObject = new JSONObject(json);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASE_URL + AUTHENTICATE, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String resp = response.toString();
                    try {
                        if(null!=resp){
                            LoginMessage loginMessage = gson.fromJson(resp,LoginMessage.class);
                            setLoginMessage(loginMessage);
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

    public void setLoginMessage(LoginMessage loginMessage){
        this.loginMessage = loginMessage;
        this.dbHelper.addToken(this.loginMessage.getToken());
        this.ui.changePage(1);
    }

    public void getRoutes(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL + ROUTES, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("route", response.toString());
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
//                return super.getHeaders();
            }
        };
        requestQueue.add(jsonObjectRequest);
    }


    //cek apakah hour beneran int ato time takutnya salah
    //date juga takut salah klo tipe nya date
    //klo g jalan coba pake try catch si throws exceptionnya
    public void getCourses(String source, String destination, String vehicleType, Date date, int hour) throws MalformedURLException {
        Uri uri = Uri.parse(BASE_URL + COURSES).buildUpon().appendQueryParameter(SOURCE, source)
                .appendQueryParameter(DESTINATION, destination)
                .appendQueryParameter(VEHICLE, vehicleType)
                .appendQueryParameter(DATE, date.toString())
                .appendQueryParameter(HOUR, String.valueOf(hour)).build();

        URL url = new URL(uri.toString());


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String resp = response.getString("payload");
                    TravelCourses curCourse = gson.fromJson(resp,TravelCourses.class);
                    addCourse(curCourse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers= new HashMap<String,String>();
                headers.put("Authorization", "Bearer "+loginMessage.getToken());
                return headers;
//                return super.getHeaders();
            }
        };

    }
//klo g jalan coba pake try catch si throws exceptionnya
    public void getTravelOrderHist(int limit, int offset) throws MalformedURLException {
        Uri uri = Uri.parse(BASE_URL + ORDERS).buildUpon().appendQueryParameter(LIMIT, String.valueOf(limit)).appendQueryParameter(OFFSET, String.valueOf(offset)).build();
        URL url =  new URL(uri.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url.toString() + ROUTES, null, new Response.Listener<JSONObject>() {
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
                Map<String,String> headers= new HashMap<String,String>();
                headers.put("Authorization", "Bearer "+loginMessage.getToken());
                return headers;
//                return super.getHeaders();
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void order(String course_id, String seats){
        Pesan pesan = new Pesan(course_id,seats);
        String json = gson.toJson(pesan);
        try {
            JSONObject jsonObject = new JSONObject(json);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASE_URL + GET_ORDER, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String msg="";
                    try {
                        msg = response.getString("message");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(msg=="Order submitted"){
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
                            addTempOrder(currOrder);
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    toastMessage("Failed to order.");
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }


    public void toastMessage(String msg){
        this.ui.toastMessage(msg);
    }

    public void addPayload(Payload currPayload){
        this.rute.add(currPayload);
    }

    public void addCourse(TravelCourses course){
        this.currentCourse = course;
    }

    public void addTravelOrderHist(TravelOrderHist travelOrder){
        this.orderHist.add(travelOrder);
    }

    public Boolean isLogin(){
//        Log.d("pjgToken",String.valueOf(this.loginMessage.getToken().length()));
        String token = this.dbHelper.getToken();
        if(null==token || token.equals("")){
            return false;
        }else{
            return true;
        }
    }

    public String getTokenFromDb(){
        Log.d("token", this.dbHelper.getToken());
        String token = this.dbHelper.getToken();
        return token;
//        this.loginMessage.setToken(this.dbHelper.getToken());
    }

    public void addTempOrder(Order currOrder){
        this.order = currOrder;
    }


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

        Log.d("asal",this.asal.toString());
        Log.d("tujuan",this.tujuan.toString());
    }

    public void updateJam(){
        this.ui.updateJamBerangkat(this.jam);
    }

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

    public void getLocation(){
        Shuttle shuttlelocBdg = new Shuttle("Jalan Pasteur No. 16A","Bandung");
        Shuttle shuttleLocJkt = new Shuttle("Jalan Pademangan Indah 5 No. 12","Jakarta");
        Shuttle shuttleLocBek = new Shuttle("Jalan Pangeran Diponegoro No. 19F","Bekasi");
        Shuttle shuttleLocCik = new Shuttle("Jalan Mataram No. 39A","Cikarang");
        this.poolLocation.add(shuttlelocBdg);
        this.poolLocation.add(shuttleLocJkt);
        this.poolLocation.add(shuttleLocBek);
        this.poolLocation.add(shuttleLocCik);
        Log.d("di mp",this.poolLocation.toString());
        this.ui.updatePoolLocation(this.poolLocation);
    }

}
