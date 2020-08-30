package com.polimi.datacar.activities;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.polimi.datacar.R;
import com.polimi.datacar.callbacks.LavoroCallback;
import com.polimi.datacar.controller.LavoroController;
import com.polimi.datacar.model.Lavoro;
import com.polimi.datacar.utilities.UtilityUI;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddLavoroActivity extends AppCompatActivity implements LavoroCallback {


    EditText targa_lavoro, date_scad_lavoro;
    Spinner tipo_lavoro_spinner, desc_lavoro_spinner;
    Button create_lavoro_button;
    LavoroController lavoroController;
    int token;
    AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lavoro);
        lavoroController = new LavoroController();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());;
        token = sharedPref.getInt(getString(R.string.token), 0);

        initializeViews();
    }


    private void initializeViews(){
        targa_lavoro = findViewById(R.id.lavoro_targa);
        date_scad_lavoro = findViewById(R.id.lavoro_scadenza);
        tipo_lavoro_spinner = findViewById(R.id.spinner_tipo_lavoro);
        desc_lavoro_spinner = findViewById(R.id.spinner_lavoro_descrizione);
        create_lavoro_button = findViewById(R.id.button_create_lavoro);
        dialog = UtilityUI.createWaitingAlertDialog(this,R.layout.layout_loading_items);
        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(calendar);
            }

        };

        date_scad_lavoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddLavoroActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    private void updateLabel(Calendar calendar) {
        String dateFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat dateFormatted = new SimpleDateFormat(dateFormat, Locale.US);
        date_scad_lavoro.setText(dateFormatted.format(calendar.getTime()));
    }


    public Lavoro createLavoroFromField(){
        String targa = targa_lavoro.getText().toString();
        Date data_scadenza = Date.valueOf(date_scad_lavoro.getText().toString());
        String tipo_lavoro = tipo_lavoro_spinner.getSelectedItem().toString();
        String desc_lavoro = desc_lavoro_spinner.getSelectedItem().toString();

        Lavoro lavoro = new Lavoro();
        lavoro.setTarga(targa);
        lavoro.setDataScandenza(data_scadenza);
        lavoro.setTipoLavoro(tipo_lavoro);
        lavoro.setDescLavoro(desc_lavoro);
        lavoro.setEffettuato(false);

        return lavoro;
    }

    public void addLavoro(View view) {
        dialog.show();
        lavoroController.addLavoro(token, createLavoroFromField()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                    addLavoroCallback(true);
                else
                    addLavoroCallback(false);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                addLavoroCallback(false);
            }
        });
    }

    @Override
    public void addLavoroCallback(boolean result) {
        if(result){
            dialog.cancel();
            Toast.makeText(this,R.string.lavoro_success,Toast.LENGTH_SHORT).show();
            finish();
        } else {
            dialog.cancel();
            Toast.makeText(this,R.string.lavoro_failure,Toast.LENGTH_SHORT).show();
        }
    }
}
