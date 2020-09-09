package com.polimi.datacar.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.polimi.datacar.R;
import com.polimi.datacar.activities.adapter.LavoroAdapter;
import com.polimi.datacar.controller.LavoroController;
import com.polimi.datacar.model.Lavoro;
import com.polimi.datacar.network.UpdateLavoroStatus;
import com.polimi.datacar.utilities.EditTextCheck;
import com.polimi.datacar.utilities.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AreaLavoriActivity extends AppCompatActivity implements UpdateLavoroStatus {

    private RecyclerView lavoroRecyleView;
    private SwipeRefreshLayout scrollView;
    private Button goToAddLavoro;
    private LavoroController lavoroController;
    private EditText et_ricercaTarga;
    private AlertDialog dialog;
    private List<Lavoro> lavoroList;
    private LavoroAdapter lavoroAdapter;
    int token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_lavori);
        lavoroList = new ArrayList<>();
        lavoroController = new LavoroController();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());;
        token = sharedPref.getInt(getString(R.string.token), 0);

        initializeViews();
        getLavoroList(lavoroController,token);


    }

    private void initializeViews() {
        lavoroRecyleView = findViewById(R.id.area_lavori_recycle_view_lista_lavori);
        goToAddLavoro = findViewById(R.id.button_add_lavoro);
        dialog = Utility.createWaitingAlertDialog(this,R.layout.layout_loading_items);
        et_ricercaTarga = findViewById(R.id.et_ricerca_targa_rv);
        scrollView = findViewById(R.id.scroll_view_area_lavoro);


        et_ricercaTarga.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                findInRecycleView();
            }
        });

        scrollView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrollView.setRefreshing(true);
                getLavoroList(lavoroController,token);
                scrollView.setRefreshing(false);
            }
        });

    }

    private void getLavoroList(LavoroController lavoroController, int token){
        dialog.show();
        lavoroList = new ArrayList<>();
        lavoroController.getAllLavoro(token).enqueue(new Callback<List<Lavoro>>() {
            @Override
            public void onResponse(Call<List<Lavoro>> call, Response<List<Lavoro>> response) {
                if(response.isSuccessful() && response.body()!=null) {
                    lavoroList.addAll(response.body());
                    buildRecycleView(response.body());
                }
                else {
                    dialog.cancel();
                    Toast.makeText(getBaseContext(), R.string.error_generic, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Lavoro>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void buildRecycleView(List<Lavoro> retriviedData) {
        if(!retriviedData.isEmpty()) {
            dialog.cancel();
            lavoroAdapter = new LavoroAdapter(retriviedData,this, this);
            lavoroRecyleView.setAdapter(lavoroAdapter);
            lavoroRecyleView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            dialog.cancel();
            Toast.makeText(this,R.string.lista_lavoro_empty,Toast.LENGTH_SHORT).show();
        }
    }


    public void findInRecycleView(){
        if(EditTextCheck.checkEditTextView(et_ricercaTarga)) {
            String targaToFind = et_ricercaTarga.getText().toString();
            List<Lavoro> lavoroForTarga = new ArrayList<>();
            for(Lavoro lavoro : lavoroList){
                if(lavoro.getTarga().startsWith(targaToFind))
                    lavoroForTarga.add(lavoro);
            }
            lavoroAdapter.updateListInAdapter(lavoroForTarga);
        } else
            lavoroAdapter.updateListInAdapter(lavoroList);
    }

    public void goToAddLavoroActivity(View view) {
        Intent goToAddLavoroActivity = new Intent(this,AddLavoroActivity.class);
        startActivity(goToAddLavoroActivity);
    }

    @Override
    public void updateLavoroStatus(boolean result) {
       dialog.show();
       if(result)
        getLavoroList(lavoroController,token);
       else
           Toast.makeText(this,R.string.server_error,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLavoroList(lavoroController,token);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getLavoroList(lavoroController,token);
    }
}
