package com.example.tubesp3b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tubesp3b.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity{
    private ActivityMainBinding binding;
    FragmentManager fragmentManager;
    FragmentTransaction ft;
    private BookNowFragment bookNowFragment;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    private Toolbar toolbar;
    private AppBarConfiguration appBarConfig;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setTitle("TravellyGo."); //Judul aplikasi di toolbar
        this.setSupportActionBar(this.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) this.getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
        navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(this.binding.bottomNavigationView, navController);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}