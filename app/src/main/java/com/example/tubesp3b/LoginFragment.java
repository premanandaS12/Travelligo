package com.example.tubesp3b;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tubesp3b.databinding.FragmentLoginBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

//        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
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
}