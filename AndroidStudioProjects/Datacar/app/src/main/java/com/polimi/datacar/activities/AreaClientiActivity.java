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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.polimi.datacar.R;
import com.polimi.datacar.activities.adapter.ClienteAdapter;
import com.polimi.datacar.controller.ClientController;
import com.polimi.datacar.model.Cliente;
import com.polimi.datacar.utilities.Utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AreaClientiActivity extends AppCompatActivity  {

    RecyclerView clienteRv;
    EditText search_cliente_edit;
    ImageButton searchButton;
    Button addButton;
    ClientController clientController;
    AlertDialog dialog;
    ClienteAdapter clienteAdapter;
    SwipeRefreshLayout scrollView;
    int token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_clienti);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());;
        token = sharedPref.getInt(getString(R.string.token), 0);

        clientController = new ClientController();

        initializeViews();
        getDataFromServer(token);


    }



    private void initializeViews() {
        dialog = Utility.createWaitingAlertDialog(this,R.layout.layout_loading_items);
        clienteRv = findViewById(R.id.area_cliente_recycle_view_lista_cliente);
        search_cliente_edit = findViewById(R.id.area_cliente_search_text);
        searchButton = findViewById(R.id.area_cliente_search_button);
        addButton = findViewById(R.id.area_clienti_register_cliente);
        scrollView = findViewById(R.id.scroll_view_area_clienti);

        scrollView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrollView.setRefreshing(true);
                getDataFromServer(token);
                scrollView.setRefreshing(false);
            }
        });
    }



    public void buildRecycleView(List<Cliente> listOfCliente){
        clienteAdapter = new ClienteAdapter(listOfCliente,this,clientController,token);
        clienteRv.setAdapter(clienteAdapter);
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
                Utility.retrofitOnFailure(getBaseContext());
            }
        });
    }


    public void registerCliente(View view) {
        startActivity(new Intent(this,AddClienteActivity.class));
    }


    public void searchCliente(View view) {
        String cod_fiscale_cliente = search_cliente_edit.getText().toString();
        String id = String.valueOf(clienteAdapter.getIdOfClienteByCodFiscale(cod_fiscale_cliente));
        clientController.getSpecificClient(token,id).enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(response.isSuccessful())
                    getClientDataAndPassToActivity(response.body());
                else
                    Toast.makeText(getBaseContext(),R.string.error_search_client,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                    Utility.retrofitOnFailure(getBaseContext());
            }
        });
    }


    public void getClientDataAndPassToActivity(Cliente clienteFound){
        Intent seeClientDetail = new Intent(this, ClientDetailActivity.class);

        seeClientDetail.putExtra("nome", clienteFound.getNome());
        seeClientDetail.putExtra("cognome", clienteFound.getCognome());
        seeClientDetail.putExtra("cap", clienteFound.getCap());
        seeClientDetail.putExtra("cod_fiscale", clienteFound.getCod_fiscale());
        seeClientDetail.putExtra("citta", clienteFound.getCitta());
        seeClientDetail.putExtra("telefono", clienteFound.getTelefono());
        seeClientDetail.putExtra("indirizzo",clienteFound.getIndirizzo());
        seeClientDetail.putExtra("sesso",clienteFound.getSesso());

        startActivity(seeClientDetail);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getDataFromServer(token);
    }
}
