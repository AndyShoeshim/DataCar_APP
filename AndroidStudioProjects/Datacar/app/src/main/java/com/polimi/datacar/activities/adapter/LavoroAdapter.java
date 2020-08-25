package com.polimi.datacar.activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polimi.datacar.R;
import com.polimi.datacar.model.Lavoro;

import java.util.List;

public class LavoroAdapter extends RecyclerView.Adapter<LavoroAdapter.LavoroAdapterViewHolder> {


    List<Lavoro> lavoroList;
    LayoutInflater layoutInflater;
    Context context;

    public LavoroAdapter(List<Lavoro> lavoroList, Context context) {
        this.lavoroList = lavoroList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LavoroAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = layoutInflater.inflate(R.layout.lavoro_item_row, parent, false);
        return new LavoroAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LavoroAdapterViewHolder holder, int position) {
        LavoroAdapterViewHolder lavh = (LavoroAdapterViewHolder) holder;
        lavh.targa.setText(lavoroList.get(position).getTarga());
        lavh.tipo_lavoro.setText(lavoroList.get(position).getTipoLavoro());
        lavh.desc_lavoro.setText(lavoroList.get(position).getDescLavoro());
        if(lavoroList.get(position).getDataScandenza()!=null)
            lavh.data_scadenza.setText(lavoroList.get(position).getDataScandenza().toString());
        else
            lavh.data_scadenza.setText("N/D");
    }

    @Override
    public int getItemCount() {
        return lavoroList.size();
    }

    public class LavoroAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView targa, tipo_lavoro, desc_lavoro, data_scadenza;

        public LavoroAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            targa = itemView.findViewById(R.id.rv_item_lavoro_targa);
            tipo_lavoro = itemView.findViewById(R.id.rv_item_lavoro_tipo_lavoro);
            desc_lavoro = itemView.findViewById(R.id.rv_item_lavoro_descrizione_lavoro);
            data_scadenza = itemView.findViewById(R.id.rv_item_lavoro_data_scadenza);
        }
    }

}
