package com.polimi.datacar.utilities;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.polimi.datacar.R;


public class UtilityUI {

    public static AlertDialog createWaitingAlertDialog(Context context, int viewId){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setView(viewId);
        return builder.create();
    }

    public static void createActionOnItemDialog(Context context, int id){

    }

    public static void retrofitOnFailure(Context context){
        Toast.makeText(context, R.string.server_error,Toast.LENGTH_SHORT).show();
    }
}
