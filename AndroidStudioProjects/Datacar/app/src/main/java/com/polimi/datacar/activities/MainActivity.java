package com.polimi.datacar.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.polimi.datacar.R;
import com.polimi.datacar.activities.adapter.LavoroAdapter;
import com.polimi.datacar.controller.LavoroController;
import com.polimi.datacar.model.Lavoro;
import com.polimi.datacar.utilities.UtilityUI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    RecyclerView lavoroRecyleView;
    Button client_area, lavoro_area, logout;
    private AlertDialog dialog;
    List<Lavoro> lavoroList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        client_area = findViewById(R.id.main_activity_area_clienti);
        lavoro_area = findViewById(R.id.main_activity_area_lavori);
        logout = findViewById(R.id.main_activity_logout);
        lavoroRecyleView = findViewById(R.id.main_activity_recycle_view_lista_lavori);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());;
        int token = sharedPref.getInt(getString(R.string.token), 0);
        Log.d("lavoro", "" + token);

        LavoroController lavoroController = new LavoroController();

        lavoroList = new ArrayList<>();
        dialog = UtilityUI.createWaitingAlertDialog(this,R.layout.layout_loading_items);
        dialog.show();
        getDataFromServer(lavoroController,token);
        dialog.cancel();



    }




    private void getDataFromServer(LavoroController lavoroController, int token){

        final List<Lavoro> retriviedData = new ArrayList<>();
        lavoroController.getAllLavoro(token).enqueue(new Callback<List<Lavoro>>() {
            @Override
            public void onResponse(Call<List<Lavoro>> call, Response<List<Lavoro>> response) {
                if(response.isSuccessful()) {
                    retriviedData.addAll(response.body());
                    buildRecycleView(retriviedData);
                }
            }

            @Override
            public void onFailure(Call<List<Lavoro>> call, Throwable t) {
                t.printStackTrace();
                Log.e("token", t.getMessage());
            }
        });


    }







    public void buildRecycleView(List<Lavoro> retriviedData) {
        if(!retriviedData.isEmpty()) {
            lavoroList.addAll(retriviedData);
            LavoroAdapter lavoroAdapter = new LavoroAdapter(retriviedData,this);
            lavoroRecyleView.setAdapter(lavoroAdapter);
            lavoroRecyleView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            Toast.makeText(this,R.string.app_name,Toast.LENGTH_SHORT).show();
        }
    }

    public void logoutOfficina(View view) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(getString(R.string.token));
        editor.apply();

        Intent backToLoginActivity = new Intent(this,LoginActivity.class);
        startActivity(backToLoginActivity);
    }

    public void goToAreaClienti(View view) {
        Intent goToAreaClienti= new Intent(this,AreaClientiActivity.class);
        startActivity(goToAreaClienti);
    }

    public void goToAreaLavori(View view) {
        Intent goToAreaLavori = new Intent(this,AreaLavoriActivity.class);
        startActivity(goToAreaLavori);
    }
}
