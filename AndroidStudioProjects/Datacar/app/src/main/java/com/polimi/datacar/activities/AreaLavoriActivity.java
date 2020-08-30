package com.polimi.datacar.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.polimi.datacar.R;
import com.polimi.datacar.activities.adapter.LavoroAdapter;
import com.polimi.datacar.controller.LavoroController;
import com.polimi.datacar.model.Lavoro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AreaLavoriActivity extends AppCompatActivity {

    private RecyclerView lavoroRecyleView;
    private Button goToAddLavoro;
    private LavoroController lavoroController;
    int token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_lavori);
        lavoroController = new LavoroController();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());;
        token = sharedPref.getInt(getString(R.string.token), 0);
        initializeViews();



    }

    private void initializeViews() {
        lavoroRecyleView = findViewById(R.id.area_lavori_recycle_view_lista_lavori);
        goToAddLavoro = findViewById(R.id.button_add_lavoro);

        lavoroController.getAllLavoro(token).enqueue(new Callback<List<Lavoro>>() {
            @Override
            public void onResponse(Call<List<Lavoro>> call, Response<List<Lavoro>> response) {
                if(response.isSuccessful() && response.body()!=null) {
                    buildRecycleView(response.body());
                }
                else
                    Toast.makeText(getBaseContext(),R.string.error_generic,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Lavoro>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void buildRecycleView(List<Lavoro> retriviedData) {
        if(!retriviedData.isEmpty()) {
            LavoroAdapter lavoroAdapter = new LavoroAdapter(retriviedData,this);
            lavoroRecyleView.setAdapter(lavoroAdapter);
            lavoroRecyleView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            Toast.makeText(this,R.string.lista_lavoro_empty,Toast.LENGTH_SHORT).show();
        }
    }

    public void goToAddLavoroActivity(View view) {
        Intent goToAddLavoroActivity = new Intent(this,AddLavoroActivity.class);
        startActivity(goToAddLavoroActivity);
    }
}
