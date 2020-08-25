package com.polimi.datacar.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.polimi.datacar.R;
import com.polimi.datacar.controller.OfficinaController;
import com.polimi.datacar.model.Officina;
import com.polimi.datacar.utilities.EditTextCheck;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText rag_sociale;
    EditText p_iva;
    EditText email;
    EditText password;
    EditText num_telefono;
    EditText indirizzo;
    Button button;


    OfficinaController officinaController;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        officinaController = new OfficinaController();
        rag_sociale = findViewById(R.id.register_activity_rag_sociale);
        p_iva = findViewById(R.id.register_activity__p_iva);
        email = findViewById(R.id.register_activity__email);
        password = findViewById(R.id.register_activity__password);
        num_telefono = findViewById(R.id.register_activity__num_telefono);
        indirizzo = findViewById(R.id.register_activity__indirizzo);
        button = findViewById(R.id.register_activity_register);

    }

    public void registerOfficina(View view) {
        if(checkValues(buildEditTextList())){
            Officina officinaBuilt = buildOfficina(new Officina());
            officinaController.registerOfficina(officinaBuilt).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    int token = response.body().intValue();
                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(getString(R.string.token), token);
                    editor.apply();
                    Intent changeActivity = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(changeActivity);
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    t.printStackTrace();
                    Log.e("token", t.getMessage());
                }
            });
        }
    }

    public boolean checkValues(List<EditText> editTexts){
        for(EditText et : editTexts){
            if(!EditTextCheck.checkEditTextView(et))
                return false;
        }

        return true;
    }

    public List<EditText> buildEditTextList(){
        List<EditText> editTextList = new ArrayList<>();
        editTextList.add(rag_sociale);
        editTextList.add(p_iva);
        editTextList.add(email);
        editTextList.add(password);
        editTextList.add(num_telefono);
        editTextList.add(indirizzo);

        return editTextList;
    }

    public Officina buildOfficina(Officina officina){
        String officina_rag_sociale = rag_sociale.getText().toString();
        String officina_p_iva = p_iva.getText().toString();
        String officina_email = email.getText().toString();
        String officina_password = password.getText().toString();
        int officina_num_telefono= Integer.parseInt(num_telefono.getText().toString());
        String officina_indirizzo = indirizzo.getText().toString();

        officina.setRag_sociale(officina_rag_sociale);
        officina.setP_iva(officina_p_iva);
        officina.setEmail(officina_email);
        officina.setPassword(officina_password);
        officina.setNum_telefono(officina_num_telefono);
        officina.setIndirizzo(officina_indirizzo);

        return officina;
    }
}
