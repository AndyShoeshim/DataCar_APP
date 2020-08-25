package com.polimi.datacar.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.polimi.datacar.R;
import com.polimi.datacar.controller.ClientController;
import com.polimi.datacar.model.Cliente;
import com.polimi.datacar.utilities.UtilityUI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AreaClientiActivity extends AppCompatActivity {

    RecyclerView clienteRv;
    EditText search_cliente_edit;
    ImageButton searchButton;
    Button addButton;
    ClientController clientController;
    AlertDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_clienti);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());;
        int token = sharedPref.getInt(getString(R.string.token), 0);

        clientController = new ClientController();

        initializeViews();
        getDataFromServer(token);


    }



    public void initializeViews() {
        dialog = UtilityUI.createWaitingAlertDialog(this,R.layout.layout_loading_items);
        clienteRv = findViewById(R.id.area_cliente_recycle_view_lista_cliente);
        search_cliente_edit = findViewById(R.id.area_cliente_search_text);
        searchButton = findViewById(R.id.area_cliente_search_button);
        addButton = findViewById(R.id.area_clienti_register_cliente);
    }


    public void buildRecycleView(List<Cliente> listOfCliente){
        ClienteAdapter adpater = new ClienteAdapter(listOfCliente,this);
        clienteRv.setAdapter(adpater);
        clienteRv.setLayoutManager(new LinearLayoutManager(this));
        dialog.cancel();
    }

    public void getDataFromServer(int token){
        dialog.show();
        clientController.getAllClient(token).enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if(response.isSuccessful()){
                    buildRecycleView(response.body());
                } else {
                    Toast.makeText(getBaseContext(),R.string.error_generic,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Toast.makeText(getBaseContext(),R.string.error_generic,Toast.LENGTH_SHORT).show();
            }
        });
    }


    //TODO search and delete cliente


    public void registerCliente(View view) {
        startActivity(new Intent(this,AddClienteActivity.class));
    }

    public void searchCliente(View view) {
    }
}
