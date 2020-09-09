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
import com.polimi.datacar.callbacks.UpdateOnResult;
import com.polimi.datacar.controller.ClientController;
import com.polimi.datacar.model.Cliente;
import com.polimi.datacar.utilities.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyClienteActivity extends AppCompatActivity implements UpdateOnResult {


    EditText nome,cognome,citta,cap,cod_fiscale,telefono,indirizzo;
    Button button;
    ClientController clientController;
    AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_cliente);
        final Intent callingIntent = getIntent();
        final int id_cliente = callingIntent.getIntExtra("id_cliente",0);
        initializeViews(callingIntent);

        clientController = new ClientController();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                final Cliente clientUpdated = buildClientInfo(callingIntent);

                clientController.updateClientInfo(id_cliente,clientUpdated).enqueue(new Callback<Cliente>() {
                    @Override
                    public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                        updateClientResult(response.isSuccessful());
                    }

                    @Override
                    public void onFailure(Call<Cliente> call, Throwable t) {
                        Log.e(ModifyClienteActivity.class.getSimpleName(), "error");
                    }
                });
                dialog.cancel();
                finish();
            }
        });
    }

    private void initializeViews(Intent callingIntent){
        nome = findViewById(R.id.nome_cliente);
        cognome = findViewById(R.id.cognome_cliente);
        citta = findViewById(R.id.citta_cliente);
        cap = findViewById(R.id.cap_cliente);
        cod_fiscale = findViewById(R.id.cod_fiscale_cliente);
        telefono = findViewById(R.id.telefono_cliente);
        indirizzo = findViewById(R.id.indirizzo_cliente);
        button = findViewById(R.id.button_modify_cliente);
        dialog = Utility.createWaitingAlertDialog(this,R.layout.layout_loading_items);

        nome.setText(String.valueOf(callingIntent.getStringExtra("nome")));
        cognome.setText(String.valueOf(callingIntent.getStringExtra("cognome")));
        citta.setText(String.valueOf(callingIntent.getStringExtra("citta")));
        cap.setText(String.valueOf(callingIntent.getStringExtra("cap")));
        cod_fiscale.setText(String.valueOf(callingIntent.getStringExtra("cod_fiscale")));
        telefono.setText(String.valueOf(callingIntent.getLongExtra("telefono",0)));
        indirizzo.setText(String.valueOf(callingIntent.getStringExtra("indirizzo")));
    }


    private Cliente buildClientInfo(Intent callingIntent){

        Cliente cliente = new Cliente();
        cliente.setNome(nome.getText().toString());
        cliente.setCognome(cognome.getText().toString());
        cliente.setCitta(citta.getText().toString());
        cliente.setCap(cap.getText().toString());
        cliente.setCod_fiscale(cod_fiscale.getText().toString());
        cliente.setSesso(callingIntent.getStringExtra("sesso"));
        cliente.setTelefono(Long.parseLong(telefono.getText().toString()));
        cliente.setIndirizzo(indirizzo.getText().toString());

        return cliente;
    }


    @Override
    public void updateClientResult(boolean result) {
        if(result){
            Toast.makeText(this,R.string.modifica_con_successo,Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,R.string.error_generic,Toast.LENGTH_SHORT).show();
        }
    }
}
