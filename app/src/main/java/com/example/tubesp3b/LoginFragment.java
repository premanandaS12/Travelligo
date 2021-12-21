package com.example.tubesp3b;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tubesp3b.databinding.FragmentLoginBinding;

import java.util.List;

public class LoginFragment extends Fragment implements View.OnClickListener, IMainActivity {
    private FragmentLoginBinding binding;
    private MainActivity activity;
    private MainPresenter presenter;
    private Context context;
    public LoginFragment(MainActivity activity, Context context) {
        this.activity = activity;
        this.context = context;

    }

    public static LoginFragment newInstance(MainActivity activity, Context context){
        LoginFragment fragment = new LoginFragment(activity, context);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentLoginBinding.inflate(inflater, container, false);
        this.presenter = new MainPresenter(this,activity,context);
        this.binding.btnSignIn.setOnClickListener(this);

        return this.binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if(view==this.binding.btnSignIn){
            String username = this.binding.etUsername.getText().toString();
            String password = this.binding.etPassword.getText().toString();
            if(username.length()!=0 && password.length() != 0){
                this.presenter.authenticateLogin(username, password);

            }else if(username.length()==0 || password.length()==0){
                Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void toastMessage(String msg) {

    }

    @Override
    public void changePage(int page) {
        Bundle args = new Bundle();
        args.putInt("page",page);
        this.getParentFragmentManager().setFragmentResult("changePage",args);
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
    public void updateUname(String username) {

    }

    @Override
    public void updateHistory(List<TravelOrderHist> history) {

    }

    @Override
    public void updateCourse(TravelCourses travelCourses, boolean[] booked, boolean[] dipencet, int page) {

    }

    @Override
    public void updateTiket(String username, TravelOrderHist history) {

    }

    @Override
    public void displayTicket(Order order, String username) {

    }
}