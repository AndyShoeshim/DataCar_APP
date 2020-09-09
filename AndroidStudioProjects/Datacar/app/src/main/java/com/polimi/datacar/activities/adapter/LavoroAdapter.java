package com.polimi.datacar.activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polimi.datacar.R;
import com.polimi.datacar.controller.LavoroController;
import com.polimi.datacar.model.Lavoro;
import com.polimi.datacar.network.UpdateLavoroStatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LavoroAdapter extends RecyclerView.Adapter<LavoroAdapter.LavoroAdapterViewHolder> {


    List<Lavoro> lavoroList;
    LayoutInflater layoutInflater;
    Context context;
    UpdateLavoroStatus updateLavoroCallback;


    public LavoroAdapter(List<Lavoro> lavoroList, Context context, UpdateLavoroStatus updateLavoroCallback) {
        this.lavoroList = lavoroList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.updateLavoroCallback = updateLavoroCallback;
    }

    @NonNull
    @Override
    public LavoroAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = layoutInflater.inflate(R.layout.lavoro_item_row, parent, false);
        return new LavoroAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LavoroAdapterViewHolder holder, final int position) {
        LavoroAdapterViewHolder lavh = (LavoroAdapterViewHolder) holder;
        lavh.targa.setText(lavoroList.get(position).getTarga());
        lavh.tipo_lavoro.setText(lavoroList.get(position).getTipoLavoro());
        lavh.desc_lavoro.setText(lavoroList.get(position).getDescLavoro());

        if(lavoroList.get(position).getDataScandenza()!=null)
            lavh.data_scadenza.setText(lavoroList.get(position).getDataScandenza().toString());
        else
            lavh.data_scadenza.setText("N/D");

        if(lavoroList.get(position).isEffettuato())
            lavh.button_concludi_lavoro.setVisibility(View.INVISIBLE);
        else
            lavh.button_concludi_lavoro.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return lavoroList.size();
    }


    public void updateListInAdapter(List<Lavoro> listLavoroForTarga){
        lavoroList = listLavoroForTarga;
        notifyDataSetChanged();
    }


    public class LavoroAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView targa, tipo_lavoro, desc_lavoro, data_scadenza;
        Button button_concludi_lavoro;

        public LavoroAdapterViewHolder(@NonNull final View itemView) {
            super(itemView);
            targa = itemView.findViewById(R.id.rv_item_lavoro_targa);
            tipo_lavoro = itemView.findViewById(R.id.rv_item_lavoro_tipo_lavoro);
            desc_lavoro = itemView.findViewById(R.id.rv_item_lavoro_descrizione_lavoro);
            data_scadenza = itemView.findViewById(R.id.rv_item_lavoro_data_scadenza);
            button_concludi_lavoro = itemView.findViewById(R.id.rv_item_button_lavoro);

            button_concludi_lavoro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LavoroController lavoroController = new LavoroController();
                    Lavoro lavoroInsideView = lavoroList.get(getAdapterPosition());
                    lavoroController.updateLavoroStatus(lavoroInsideView.getId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            updateLavoroCallback.updateLavoroStatus(response.isSuccessful());
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            updateLavoroCallback.updateLavoroStatus(false);
                        }
                    });
                }
            });
        }

    }

}
