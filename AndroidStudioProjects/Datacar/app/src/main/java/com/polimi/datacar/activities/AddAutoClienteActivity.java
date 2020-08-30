package com.polimi.datacar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.polimi.datacar.R;
import com.polimi.datacar.callbacks.RegisterAutoResult;
import com.polimi.datacar.controller.AutoClienteController;
import com.polimi.datacar.model.Auto;
import com.polimi.datacar.utilities.UtilityUI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAutoClienteActivity extends AppCompatActivity implements RegisterAutoResult {

    EditText targa, modello, marca, motore, cilindrata, carburante, cavalli;
    Button button_auto_cliente;
    AutoClienteController autoClienteController;
    AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_cliente);
        initializeViews();
        autoClienteController = new AutoClienteController();



    }


    private void initializeViews(){
        targa = findViewById(R.id.targa_auto);
        modello = findViewById(R.id.modello_auto);
        marca = findViewById(R.id.marca_auto);
        motore = findViewById(R.id.motore_auto);
        cilindrata = findViewById(R.id.cilindrata_auto);
        carburante = findViewById(R.id.carburante_auto);
        cavalli = findViewById(R.id.cavalli_auto);
        button_auto_cliente = findViewById(R.id.button_auto_cliente);

        dialog = UtilityUI.createWaitingAlertDialog(this,R.layout.layout_loading_items);
    }


    public void registerAutoCliente(View view) {
        String cod_fiscale_proprietario = getIntent().getStringExtra("cod_fiscale_cliente");
        String targa_auto = targa.getText().toString();
        String modello_auto = modello.getText().toString();
        String marca_auto = marca.getText().toString();
        String motore_auto = motore.getText().toString();
        String cilindrata_auto = cilindrata.getText().toString();
        String carburante_auto = carburante.getText().toString();
        String cavalli_auto = String.valueOf(cavalli.getText());

        Auto auto = new Auto(marca_auto,modello_auto,motore_auto,cilindrata_auto,carburante_auto,cavalli_auto);
        auto.setTarga(targa_auto);
        auto.setCod_fiscale(cod_fiscale_proprietario);

        dialog.show();

        autoClienteController.addAutoCliente(auto).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                registerAutoClienteResult(response.isSuccessful());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(AddClienteActivity.class.getSimpleName(), t.getLocalizedMessage());
            }

        });

    }

    @Override
    public void registerAutoClienteResult(boolean result) {
        if(result){
            dialog.cancel();
            Toast.makeText(this,R.string.registra_auto_success,Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,AreaClientiActivity.class));
        } else {
            dialog.cancel();
            Toast.makeText(this,R.string.error_generic,Toast.LENGTH_SHORT).show();
        }
    }

}
