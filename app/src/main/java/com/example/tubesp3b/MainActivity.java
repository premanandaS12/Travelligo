package com.example.tubesp3b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.tubesp3b.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, IMainActivity{
    private ActivityMainBinding binding;
    FragmentManager fragmentManager;
    FragmentTransaction ft;
    private BookNowFragment bookNowFragment;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    private Toolbar toolbar;
    private AppBarConfiguration appBarConfig;
    private BottomNavigationView navView;
    private MainPresenter mainPresenter;
    private HomeFragment home;
    private LoginFragment login;
    private BookNowFragment booking;
    private MyBookingHistoryFragment bookingHist;
    private ProfileFragment profile;
    private LocationFragment location;
    private GopayFragment gopay;
    private OvoFragment ovo;
    private ShopeeFragment shopee;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setTitle("TravellyGo."); //Judul aplikasi di toolbar
        this.setSupportActionBar(this.toolbar);

        this.navView = this.binding.bottomNavigationView;
        this.navView.setOnNavigationItemSelectedListener(this);

        this.fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        Log.d("blom login","msk");

        this.mainPresenter = new MainPresenter(this,this,this);

//        set page awal
//        boolean x=false;

        this.home = HomeFragment.newInstance(this,this);
        this.login = LoginFragment.newInstance(this,this);
        this.booking = BookNowFragment.newInstance(this,this);
//        this.bookingHist = MyBookingHistoryFragment.newInstance(this,this);
        this.profile = ProfileFragment.newInstance(this,this);
        this.location = LocationFragment.newInstance(this,this);
        this.gopay = GopayFragment.newInstance(this, this);
        this.ovo = OvoFragment.newInstance(this, this);
        this.shopee = ShopeeFragment.newInstance(this, this);

        if(this.mainPresenter.isLogin()==true){
            changePage(1);
        }else if(this.mainPresenter.isLogin()==false){
            changePage(0);
            this.binding.bottomNavigationView.setVisibility(View.GONE);
        }



        //Change page
        this.getSupportFragmentManager().setFragmentResultListener("changePage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int page = result.getInt("page");
                changePage(page);
            }
        });
//        NavHostFragment navHostFragment = (NavHostFragment) this.getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
//        navController = Navigation.findNavController(this, R.id.fragmentContainerView);
//        NavigationUI.setupWithNavController(this.binding.bottomNavigationView, navController);

//        BottomNavigationView navbar = findViewById(R.id.bottomNavigationView);
//        this.binding.bottomNavigationView.setVisibility(View.GONE);





    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.nav_home){
            changePage(1);
        }else if(item.getItemId()==R.id.nav_bookNow){
            changePage(2);
        }else if(item.getItemId()==R.id.nav_bookHist){
            changePage(3);
        }else if(item.getItemId()==R.id.nav_tiket){
            changePage(4);
        }else if(item.getItemId()==R.id.nav_profile){
            changePage(5);
        }
        return true;
    }

    public void changePage(int page) {
        FragmentTransaction ft =this.fragmentManager.beginTransaction();
        if(page==0){
            ft.replace(R.id.fragmentContainerView, this.login).addToBackStack(null);
        }else if(page==1){
            ft.replace(R.id.fragmentContainerView, this.home).addToBackStack(null);
            this.binding.bottomNavigationView.setVisibility(View.VISIBLE);
        }else if(page==2){
            ft.replace(R.id.fragmentContainerView, this.booking).addToBackStack(null);
//            this.binding.bottomNavigationView.setSelectedItemId(R.id.nav_bookNow);
            this.binding.bottomNavigationView.setVisibility(View.VISIBLE);

        }else if(page==3){
            ft.replace(R.id.fragmentContainerView, this.bookingHist).addToBackStack(null);
            this.binding.bottomNavigationView.setVisibility(View.VISIBLE);
        }else if(page==4){
//            ft.replace(R.id.fragmentContainerView, this.tiket).addToBackStack(null);
            this.binding.bottomNavigationView.setVisibility(View.VISIBLE);
        }else if(page==5){
            ft.replace(R.id.fragmentContainerView, this.profile).addToBackStack(null);
            this.binding.bottomNavigationView.setVisibility(View.VISIBLE);
        }else if(page==6){
            Log.d("halaman","6");
            ft.replace(R.id.fragmentContainerView, this.location).addToBackStack(null);
            this.binding.bottomNavigationView.setVisibility(View.VISIBLE);
        }else if(page==7){
            ft.replace(R.id.fragmentContainerView, this.gopay).addToBackStack(null);
            this.binding.bottomNavigationView.setVisibility(View.VISIBLE);
        }else if(page==8){
            ft.replace(R.id.fragmentContainerView, this.ovo).addToBackStack(null);
            this.binding.bottomNavigationView.setVisibility(View.VISIBLE);
        }else if(page==9){
            ft.replace(R.id.fragmentContainerView, this.shopee).addToBackStack(null);
            this.binding.bottomNavigationView.setVisibility(View.VISIBLE);
        }
        ft.commit();
    }

    @Override
    public void updateAsal(List<String> asal) {

    }

    @Override
    public void updateTujuan(List<String> tujuan) {

    }

    @Override
    public void updateJamBerangkat(List<String> jam) {

    }

    @Override
    public void updateVehicle(List<String> vehicleType) {

    }

    @Override
    public void ruteDipilih(Payload payload) {

    }

    @Override
    public void updatePoolLocation(List<Shuttle> poolLocation) {

    }

    @Override
    public void toastMessage(String msg) {

    }
}