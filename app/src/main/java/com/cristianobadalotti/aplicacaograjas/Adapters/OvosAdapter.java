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
import com.cristianobadalotti.aplicacaograjas.ViewHolders.ViewHolderOvos;

import java.util.ArrayList;

public class OvosAdapter extends RecyclerView.Adapter<ViewHolderOvos> {

    private ArrayList<Ovos> dados;

    public OvosAdapter(ArrayList<Ovos> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public ViewHolderOvos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_ovos, viewGroup, false);

        ViewHolderOvos holderOvos = new ViewHolderOvos(view, viewGroup.getContext());
        holderOvos.setDados(dados);

        return holderOvos;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderOvos viewHolder, int i) {

        if ( (dados != null) && (dados.size() > 0)) {
            Ovos ovos = dados.get(i);
            viewHolder.textCodigo.setText("Código: " + ovos.getCodigo());
            viewHolder.textData.setText("Data: " + ovos.getData());
            viewHolder.textOPostura.setText("Código Postura: " + ovos.getPostura());
            viewHolder.textQualidade.setText("Qualidade: " + ovos.getQualidade());
            viewHolder.textQuantidade.setText("Quantidade de aves: " + ovos.getQuantidade());
            viewHolder.textTipoAve.setText("Tipo de ave: " + ovos.getTipoAve());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}

