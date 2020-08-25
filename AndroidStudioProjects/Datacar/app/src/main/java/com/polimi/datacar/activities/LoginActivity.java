package com.polimi.datacar.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.polimi.datacar.R;
import com.polimi.datacar.controller.OfficinaController;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    OfficinaController officinaController;
    EditText email_text;
    EditText password_text;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        officinaController = new OfficinaController();
        email_text = findViewById(R.id.login_activity_email);
        password_text = findViewById(R.id.login_activity_password);
        button = findViewById(R.id.login_activity_button_login);
    }


    public void officinaLogin(View view) {
        String email = email_text.getText().toString();
        String password = password_text.getText().toString();
        officinaController.officinaLogin(email,password).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()) {
                    int token = response.body().intValue();
                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(getString(R.string.token), token);
                    editor.apply();
                    Log.d("lavoro", "" + token);
                    Intent changeActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(changeActivity);
                } else
                    Toast.makeText(getBaseContext(),"Credenziali sbagliate o non esistenti",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                t.printStackTrace();
                Log.e("token", t.getMessage());
            }
        });
    }

    public void goToRegisterActivity(View view) {
        Intent changeActivity = new Intent(this,RegisterActivity.class);
        startActivity(changeActivity);
    }
}
