package com.polimi.datacar.activities.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polimi.datacar.R;
import com.polimi.datacar.activities.ModifyClienteActivity;
import com.polimi.datacar.controller.ClientController;
import com.polimi.datacar.model.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    List<Cliente> listOfCliente;
    LayoutInflater layoutInflater;
    Context context;
    ClientController clientController;
    int token;

    public ClienteAdapter(List<Cliente> listOfCliente, Context context, ClientController clientController, int token) {
        this.listOfCliente = listOfCliente;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.clientController = clientController;
        this.token = token;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cliente_item_row,parent,false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, final int position) {
        ClienteViewHolder cvh = (ClienteViewHolder) holder;
        cvh.nome_cliente.setText(listOfCliente.get(position).getNome());
        cvh.cognome_cliente.setText(listOfCliente.get(position).getCognome());
        cvh.num_targhe.setText(String.valueOf(listOfCliente.get(position).getTarghe_associate()));

        cvh.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(ClienteAdapter.class.getSimpleName(),"" + token);
                String cod_fiscale = listOfCliente.get(position).getCod_fiscale();
                clientController.deleteClient(token,cod_fiscale).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            listOfCliente.remove(position);
                            notifyItemRemoved(position);
                        } else {
                            Log.e(ClienteAdapter.class.getSimpleName(), "error in deleting");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
            }
        });

        cvh.modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cliente cliente = listOfCliente.get(position);
                Intent goToModifyActivity = new Intent(context, ModifyClienteActivity.class);
                goToModifyActivity.putExtra("id_cliente",cliente.getId());
                goToModifyActivity.putExtra("nome", cliente.getNome());
                goToModifyActivity.putExtra("cognome", cliente.getCognome());
                goToModifyActivity.putExtra("cap", cliente.getCap());
                goToModifyActivity.putExtra("cod_fiscale", cliente.getCod_fiscale());
                goToModifyActivity.putExtra("citta", cliente.getCitta());
                goToModifyActivity.putExtra("telefono", cliente.getTelefono());
                goToModifyActivity.putExtra("indirizzo",cliente.getIndirizzo());
                goToModifyActivity.putExtra("sesso",cliente.getSesso());
                context.startActivity(goToModifyActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfCliente.size();
    }


    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        TextView nome_cliente, cognome_cliente, num_targhe;
        ImageButton modifyButton, deleteButton;

        public ClienteViewHolder(@NonNull final View itemView) {
            super(itemView);

            nome_cliente = itemView.findViewById(R.id.rv_item_cliente_nome);
            cognome_cliente = itemView.findViewById(R.id.rv_item_cliente_cognome);
            num_targhe = itemView.findViewById(R.id.rv_item_cliente_num_targhe);
            modifyButton = itemView.findViewById(R.id.rv_item_cliente_modifica);
            deleteButton = itemView.findViewById(R.id.rv_item_cliente_elimina);


        }


    }
}
