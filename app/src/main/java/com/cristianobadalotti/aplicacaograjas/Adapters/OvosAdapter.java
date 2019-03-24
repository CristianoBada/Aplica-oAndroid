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

import com.cristianobadalotti.aplicacaograjas.Entidades.Ovos;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarOvosActivity;
import com.cristianobadalotti.aplicacaograjas.R;

import java.util.ArrayList;

public class OvosAdapter extends RecyclerView.Adapter<OvosAdapter.ViewHolderOvos> {

    private ArrayList<Ovos> dados;

    public OvosAdapter(ArrayList<Ovos> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public OvosAdapter.ViewHolderOvos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_ovos, viewGroup, false);

        OvosAdapter.ViewHolderOvos holderOvos = new OvosAdapter.ViewHolderOvos(view, viewGroup.getContext());

        return holderOvos;
    }

    @Override
    public void onBindViewHolder(@NonNull OvosAdapter.ViewHolderOvos viewHolder, int i) {

        if ( (dados != null) && (dados.size() > 0)) {
            Ovos ovos = dados.get(i);
            viewHolder.textData.setText("Data: " + ovos.getData());
            viewHolder.textLote.setText("Lote: " + ovos.getLote());
            viewHolder.textQualidade.setText("Qualidade: " + ovos.getQualidade());
            viewHolder.textQuantidade.setText("Quantidade de aves: " + ovos.getQuantidade());
            viewHolder.textTipoAve.setText("Tipo de ave: " + ovos.getTipoAve());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderOvos extends RecyclerView.ViewHolder {

        public TextView textQuantidade;
        public TextView textQualidade;
        public TextView textData;
        public TextView textLote;
        public TextView textTipoAve;

        public ViewHolderOvos(@NonNull View itemView, final Context context) {
            super(itemView);

            textQuantidade = itemView.findViewById(R.id.textViewQuantidadeOvos);
            textQualidade = itemView.findViewById(R.id.textViewQualidadeOvos);
            textData = itemView.findViewById(R.id.textViewDataOvos);
            textLote = itemView.findViewById(R.id.textViewLoteOvos);
            textTipoAve = itemView.findViewById(R.id.textViewTipoAveOvos);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (dados.size() > 0) {

                        Ovos ovos = dados.get(getLayoutPosition());

                        Intent intent = new Intent(context, EditarOvosActivity.class);

                        intent.putExtra("OVOS", ovos);

                        ((AppCompatActivity) context).startActivityForResult(intent, 0);
                    }
                }
            });
        }
    }
}

