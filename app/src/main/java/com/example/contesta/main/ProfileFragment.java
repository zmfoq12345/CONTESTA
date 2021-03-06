package com.example.contesta.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.contesta.Login.LoginActivity;
import com.example.contesta.R;
import com.example.contesta.util.AddPopupActivity;
import com.example.contesta.util.PopupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;import cn.pedant.SweetAlert.SweetAlertDialog;
import android.graphics.Color;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonSignin,buttonSignup, btn_add;
    TextView User_id;
    FirebaseAuth firebaseAuth;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

//        if(firebaseAuth.getCurrentUser() != null){
//            finish();
//            startActivity(new Intent(getApplicationContext(), ProfileFragment.class)); //추가해 줄 ProfileActivity
//        }
        editTextEmail = (EditText)view.findViewById(R.id.email);
        editTextPassword = (EditText) view.findViewById(R.id.password);
        User_id = (TextView) view.findViewById(R.id.User_id);

        buttonSignup = (Button) view.findViewById(R.id.btn_signin);
        buttonSignup.setOnClickListener(this);

        buttonSignin = (Button) view.findViewById(R.id.btn_Login);
        buttonSignin.setOnClickListener(this);

        btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);

        if (LoginActivity.getLogin()){
            User_id.setVisibility(View.VISIBLE);
//            btn_add.setVisibility(View.VISIBLE);
            editTextEmail.setVisibility(View.GONE);
            editTextPassword.setVisibility(View.GONE);
            buttonSignin.setVisibility(View.GONE);
            buttonSignup.setVisibility(View.GONE);
            User_id.setText(LoginActivity.getId());
        }
        return view;
    }
    @Override
    public void onClick(View view) {

        if(view == btn_add) {
            Intent intent = new Intent(view.getContext(), AddPopupActivity.class);
            startActivity(intent);
        }else{

            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                Toast.makeText(getView().getContext(), "email을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(getView().getContext(), "password를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            if(view == buttonSignin) {
                userLogin(email, password);
            }
            if(view == buttonSignup) {
                registerUser(email,password);
            }
        }

    }
    //firebase userLogin method
    private void userLogin(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) getView().getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        progressDialog.dismiss();
                        if(task.isSuccessful()) {
                            new SweetAlertDialog(getView().getContext())
                                    .setTitleText("로그인되었습니다!")
                                    .show();
//                            Toast.makeText(getView().getContext(), "로그인되었습니다!", Toast.LENGTH_LONG).show();
                            User_id.setVisibility(View.VISIBLE);
//                            btn_add.setVisibility(View.VISIBLE);

                            editTextEmail.setVisibility(View.GONE);
                            editTextPassword.setVisibility(View.GONE);
                            buttonSignin.setVisibility(View.GONE);
                            buttonSignup.setVisibility(View.GONE);
                            LoginActivity.setLogin(true);
                            LoginActivity.setId(editTextEmail.getText().toString());
                            User_id.setText(LoginActivity.getId());
                        } else {
                            new SweetAlertDialog(getView().getContext(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("로그인 실패!")
                                    .setContentText("아이디와 비밀번호를\n제대로 입력했는지 확인해 주세요")
                                    .show();
//                            Toast.makeText(getView().getContext(), "로그인 실패!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    private void registerUser(String email, String password){
        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) getView().getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공
                            new SweetAlertDialog(getView().getContext())
                                    .setTitleText("회원가입 완료!!")
                                    .show();
                        } else {
                            // 회원가입 실패
//                        Toast.makeText(getView().getContext(), "등록 에러!"+task.getResult(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
