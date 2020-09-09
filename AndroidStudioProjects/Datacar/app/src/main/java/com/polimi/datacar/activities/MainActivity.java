package com.polimi.datacar.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.polimi.datacar.R;
import com.polimi.datacar.activities.adapter.LavoroAdapter;
import com.polimi.datacar.controller.LavoroController;
import com.polimi.datacar.model.Lavoro;
import com.polimi.datacar.network.UpdateLavoroStatus;
import com.polimi.datacar.utilities.Utility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UpdateLavoroStatus {


    private RecyclerView lavoroRecyleView;
    private Button client_area, lavoro_area, logout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AlertDialog dialog;
    private List<Lavoro> lavoroList;
    int token;
    private LavoroController lavoroController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        client_area = findViewById(R.id.main_activity_area_clienti);
        lavoro_area = findViewById(R.id.main_activity_area_lavori);
        logout = findViewById(R.id.main_activity_logout);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_main);
        lavoroRecyleView = findViewById(R.id.main_activity_recycle_view_lista_lavori);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());;
        token = sharedPref.getInt(getString(R.string.token), 0);
        lavoroController = new LavoroController();
        lavoroList = new ArrayList<>();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getDataFromServer(lavoroController,token);
                swipeRefreshLayout.setRefreshing(false);
            }
        });




        dialog = Utility.createWaitingAlertDialog(this,R.layout.layout_loading_items);
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
                    buildRecycleView(getTodaysWork(retriviedData));
                }
            }

            @Override
            public void onFailure(Call<List<Lavoro>> call, Throwable t) {
                errorWithServer();
            }
        });
    }


    public void errorWithServer() {
        Utility.retrofitOnFailure(this);
    }

    public void buildRecycleView(List<Lavoro> retriviedData) {
        if(!retriviedData.isEmpty()) {
            lavoroList.addAll(retriviedData);
            LavoroAdapter lavoroAdapter = new LavoroAdapter(retriviedData,this, this);
            lavoroRecyleView.setAdapter(lavoroAdapter);
            lavoroRecyleView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            Toast.makeText(this,R.string.lista_lavoro_empty,Toast.LENGTH_SHORT).show();
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

    private List<Lavoro> getTodaysWork(List<Lavoro> lavoros){
        LocalDate todaysDate = LocalDate.now();
        java.sql.Date todaysDateFormatted = java.sql.Date.valueOf(todaysDate.toString());
        List<Lavoro> todaysWork = new ArrayList<>();
        for(Lavoro lavoroRetrivied : lavoros){
            Date dateOfLavoro = lavoroRetrivied.getDataScandenza();
            if(dateOfLavoro.compareTo(todaysDateFormatted)==0)
                todaysWork.add(lavoroRetrivied);
        }

        return todaysWork;
    }

    public void goToAreaClienti(View view) {
        Intent goToAreaClienti= new Intent(this,AreaClientiActivity.class);
        startActivity(goToAreaClienti);
    }

    public void goToAreaLavori(View view) {
        Intent goToAreaLavori = new Intent(this,AreaLavoriActivity.class);
        startActivity(goToAreaLavori);
    }

    @Override
    public void updateLavoroStatus(boolean result) {
        dialog.show();
        if(result) {
            dialog.show();
            getDataFromServer(lavoroController,token);
            dialog.cancel();
        }
        else
            Toast.makeText(this,R.string.server_error,Toast.LENGTH_SHORT).show();
    }
}
