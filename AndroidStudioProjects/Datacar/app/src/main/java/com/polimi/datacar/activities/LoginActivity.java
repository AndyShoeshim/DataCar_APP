package com.polimi.datacar.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.polimi.datacar.R;
import com.polimi.datacar.callbacks.OfficinaLoginCallback;
import com.polimi.datacar.controller.OfficinaController;
import com.polimi.datacar.utilities.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements OfficinaLoginCallback {

    OfficinaController officinaController;
    EditText email_text;
    EditText password_text;
    Button button;
    AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        officinaController = new OfficinaController();
        email_text = findViewById(R.id.login_activity_email);
        password_text = findViewById(R.id.login_activity_password);
        button = findViewById(R.id.login_activity_button_login);
        dialog = Utility.createWaitingAlertDialog(this,R.layout.layout_loading_items);
    }


    public void officinaLogin(View view) {
        dialog.show();
        String email = email_text.getText().toString();
        String password = password_text.getText().toString();
        final Context context = email_text.getContext();
        officinaController.officinaLogin(email,password).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful() && response.body()!=null) {
                    loginOperation(response.body());
                } else {
                    loginOperation(0);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                dialog.cancel();
                Utility.retrofitOnFailure(context);
            }
        });
    }

    public void goToRegisterActivity(View view) {
        Intent changeActivity = new Intent(this,RegisterActivity.class);
        startActivity(changeActivity);
    }



    @Override
    public void loginOperation(int token) {
        if(token!=0){
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.token), token);
            editor.apply();
            dialog.cancel();
            Intent changeActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(changeActivity);
        } else {
            dialog.cancel();
            Toast.makeText(this, R.string.login_error,Toast.LENGTH_SHORT).show();
        }

    }
}
