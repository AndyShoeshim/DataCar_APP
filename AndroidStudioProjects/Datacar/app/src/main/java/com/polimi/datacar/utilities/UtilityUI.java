package com.polimi.datacar.utilities;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;


public class UtilityUI {

    public static AlertDialog createWaitingAlertDialog(Context context, int viewId){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setView(viewId);
        return builder.create();
    }

    public static void createActionOnItemDialog(Context context, int id){

    }
}
