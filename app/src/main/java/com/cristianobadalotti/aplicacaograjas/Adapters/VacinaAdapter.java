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

import com.cristianobadalotti.aplicacaograjas.Entidades.Vacina;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarVacinasActivity;
import com.cristianobadalotti.aplicacaograjas.R;

import java.util.ArrayList;

public class VacinaAdapter extends RecyclerView.Adapter<VacinaAdapter.ViewHolderVacina> {
    private ArrayList<Vacina> dados;

    public VacinaAdapter(ArrayList<Vacina> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public VacinaAdapter.ViewHolderVacina onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_vacina, viewGroup, false);

        VacinaAdapter.ViewHolderVacina holderVacina = new VacinaAdapter.ViewHolderVacina(view, viewGroup.getContext());

        return holderVacina;
    }

    @Override
    public void onBindViewHolder(@NonNull VacinaAdapter.ViewHolderVacina viewHolder, int i) {

        if ( (dados != null) && (dados.size() > 0)) {
            Vacina vacina = dados.get(i);
            viewHolder.textTipo.setText("Tipo de tratamento: " + vacina.getTipoTratamento());
            viewHolder.textData.setText("Data: " + vacina.getDataTratamento());
            viewHolder.textDetalhe.setText("Observação: " + vacina.getDetalhe());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderVacina extends RecyclerView.ViewHolder {

        public TextView textTipo;
        public TextView textData;
        public TextView textDetalhe;

        public ViewHolderVacina(@NonNull View itemView, final Context context) {
            super(itemView);

            textTipo = itemView.findViewById(R.id.textViewTipoVacina);
            textData = itemView.findViewById(R.id.textViewDataVacina);
            textDetalhe = itemView.findViewById(R.id.textViewDetalheVacina);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (dados.size() > 0) {

                        Vacina vacina = dados.get(getLayoutPosition());

                        Intent intent = new Intent(context, EditarVacinasActivity.class);

                        intent.putExtra("VACINA", vacina);

                        ((AppCompatActivity) context).startActivityForResult(intent, 0);
                    }
                }
            });
        }
    }
}
