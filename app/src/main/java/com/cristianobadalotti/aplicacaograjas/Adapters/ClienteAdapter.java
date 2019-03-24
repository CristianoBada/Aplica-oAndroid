package com.cristianobadalotti.aplicacaograjas.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cristianobadalotti.aplicacaograjas.Forms.ActCadCliente;
import com.cristianobadalotti.aplicacaograjas.Entidades.Cliente;
import com.cristianobadalotti.aplicacaograjas.R;

import java.util.ArrayList;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolderCliente> {

    private ArrayList<Cliente> dados;

    public ClienteAdapter(ArrayList<Cliente> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public ClienteAdapter.ViewHolderCliente onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_list_cliente, viewGroup, false);

        ClienteAdapter.ViewHolderCliente holderCliente = new ViewHolderCliente(view, viewGroup.getContext());

        return holderCliente;
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteAdapter.ViewHolderCliente viewHolder, int i) {

        if ( (dados != null) && (dados.size() > 0)) {
            Cliente cliente = dados.get(i);
            viewHolder.textNome.setText(cliente.getNome());
            viewHolder.textTelefone.setText(cliente.getTelefone());
            viewHolder.textEmail.setText(cliente.getEmail());
            viewHolder.textEndereco.setText(cliente.getEndereco());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderCliente extends RecyclerView.ViewHolder {

        public TextView textNome;
        public TextView textEndereco;
        public TextView textEmail;
        public TextView textTelefone;

        public ViewHolderCliente(@NonNull View itemView, final Context context) {
            super(itemView);

            textNome = itemView.findViewById(R.id.textViewNome);
            textEndereco = itemView.findViewById(R.id.textViewEndereco);
            textEmail = itemView.findViewById(R.id.textViewEmail);
            textTelefone = itemView.findViewById(R.id.textViewTelefone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (dados.size() > 0) {

                        Cliente cliente = dados.get(getLayoutPosition());

                        Intent intent = new Intent(context, ActCadCliente.class);

                        intent.putExtra("CLIENTE", cliente);

                        ((AppCompatActivity) context).startActivityForResult(intent, 0);
                    }
                }
            });
        }
    }
}
