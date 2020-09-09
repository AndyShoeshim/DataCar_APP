package com.polimi.datacar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.polimi.datacar.R;

public class ClientDetailActivity extends AppCompatActivity {

    TextView titleActivity;
    EditText nome,cognome,citta,cap,cod_fiscale,telefono,indirizzo;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_cliente);
        initializeViews();
        populateViews(getIntent());
    }

    private void initializeViews(){
        nome = findViewById(R.id.nome_cliente);
        cognome = findViewById(R.id.cognome_cliente);
        citta = findViewById(R.id.citta_cliente);
        cap = findViewById(R.id.cap_cliente);
        cod_fiscale = findViewById(R.id.cod_fiscale_cliente);
        telefono = findViewById(R.id.telefono_cliente);
        indirizzo = findViewById(R.id.indirizzo_cliente);
        button = findViewById(R.id.button_modify_cliente);
        titleActivity = findViewById(R.id.form_modifica_cliente);

        nome.setEnabled(false);
        cognome.setEnabled(false);
        citta.setEnabled(false);
        cap.setEnabled(false);
        cod_fiscale.setEnabled(false);
        telefono.setEnabled(false);
        indirizzo.setEnabled(false);
        titleActivity.setText(R.string.detail_client);

        button.setVisibility(View.INVISIBLE);
    }


    private void populateViews(Intent callingIntent){
        nome.setText(String.valueOf(callingIntent.getStringExtra("nome")));
        cognome.setText(String.valueOf(callingIntent.getStringExtra("cognome")));
        citta.setText(String.valueOf(callingIntent.getStringExtra("citta")));
        cap.setText(String.valueOf(callingIntent.getStringExtra("cap")));
        cod_fiscale.setText(String.valueOf(callingIntent.getStringExtra("cod_fiscale")));
        telefono.setText(String.valueOf(callingIntent.getLongExtra("telefono",0)));
        indirizzo.setText(String.valueOf(callingIntent.getStringExtra("indirizzo")));
    }
}
