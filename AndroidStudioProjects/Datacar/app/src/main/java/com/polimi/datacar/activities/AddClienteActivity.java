package com.polimi.datacar.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.polimi.datacar.R;
import com.polimi.datacar.controller.ClientController;
import com.polimi.datacar.model.Cliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddClienteActivity extends AppCompatActivity  {

    EditText nome,cognome,citta,cap,cod_fiscale,telefono,indirizzo;
    Spinner sesso;
    ClientController clientController;
    Button addCliente;
    int token;
    String cliente_sesso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);
        initializeViews();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());;
        token = sharedPref.getInt(getString(R.string.token), 0);
        Intent goToAutoCliente = new Intent(getBaseContext(), AddAutoClienteActivity.class);
        clientController = new ClientController();

    }




    private void initializeViews(){
        nome = findViewById(R.id.nome_cliente);
        cognome = findViewById(R.id.cognome_cliente);
        citta = findViewById(R.id.citta_cliente);
        cap = findViewById(R.id.cap_cliente);
        cod_fiscale = findViewById(R.id.cod_fiscale_cliente);
        sesso = findViewById(R.id.spinner);
        telefono = findViewById(R.id.telefono_cliente);
        indirizzo = findViewById(R.id.indirizzo_cliente);
        addCliente = findViewById(R.id.add_cliente);
    }


    public void addCliente(View view) {
        if(checkClienteCreation()){
            Cliente cliente = new Cliente();
            cliente.setNome(nome.getText().toString());
            cliente.setCognome(cognome.getText().toString());
            cliente.setCitta(citta.getText().toString());
            cliente.setCap(cap.getText().toString());
            cliente.setCod_fiscale(cod_fiscale.getText().toString());
            cliente.setSesso(sesso.getSelectedItem().toString());
            cliente.setTelefono(Long.parseLong(telefono.getText().toString()));
            cliente.setIndirizzo(indirizzo.getText().toString());

            clientController.addClient(token,cliente).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()) {
                        startActivity(new Intent(getBaseContext(), AddAutoClienteActivity.class)
                                .putExtra("cod_fiscale_cliente", cod_fiscale.getText().toString()));
                        finish();
                    }
                    else
                        Toast.makeText(getBaseContext(),R.string.error_generic,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getBaseContext(),R.string.error_generic,Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
            Toast.makeText(this,R.string.error_field,Toast.LENGTH_SHORT).show();
    }


    public boolean checkNome(){
        return !TextUtils.isEmpty(nome.getText());
    }

    public boolean checkCognome(){
        return !TextUtils.isEmpty(cognome.getText());
    }

    public boolean checkCitta(){
        return !TextUtils.isEmpty(citta.getText());
    }

    public boolean checkCodiceFiscale(){
        return !TextUtils.isEmpty(cod_fiscale.getText());
    }

    public boolean checkCap(){
        return !TextUtils.isEmpty(String.valueOf(cap.getText()));
    }

    public boolean checkTelefono(){
        return !TextUtils.isEmpty(String.valueOf(telefono.getText()));
    }

    public boolean checkIndirizzo(){
        return !TextUtils.isEmpty(indirizzo.getText());
    }

    public boolean checkClienteCreation(){
        return checkNome() && checkCognome() && checkCap() && checkCap() && checkIndirizzo() && checkCodiceFiscale() && checkTelefono() && checkCitta();
    }
}
