package com.polimi.datacar.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polimi.datacar.R;
import com.polimi.datacar.model.Cliente;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    List<Cliente> listOfCliente;
    LayoutInflater layoutInflater;
    Context context;

    public ClienteAdapter(List<Cliente> listOfCliente, Context context){
        this.listOfCliente = listOfCliente;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cliente_item_row,parent,false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        ClienteViewHolder cvh = (ClienteViewHolder) holder;
        cvh.nome_cliente.setText(listOfCliente.get(position).getNome());
        cvh.cognome_cliente.setText(listOfCliente.get(position).getCognome());
        cvh.num_targhe.setText(String.valueOf(listOfCliente.get(position).getTarghe_associate()));
    }

    @Override
    public int getItemCount() {
        return listOfCliente.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        TextView nome_cliente, cognome_cliente, num_targhe;
        ImageButton modifyButton, deleteButton;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);

            nome_cliente = itemView.findViewById(R.id.rv_item_cliente_nome);
            cognome_cliente = itemView.findViewById(R.id.rv_item_cliente_cognome);
            num_targhe = itemView.findViewById(R.id.rv_item_cliente_num_targhe);
            modifyButton = itemView.findViewById(R.id.rv_item_cliente_modifica);
            deleteButton = itemView.findViewById(R.id.rv_item_cliente_elimina);
        }


    }
}
