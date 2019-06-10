package com.cristianobadalotti.aplicacaograjas.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristianobadalotti.aplicacaograjas.Entidades.Corte;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.ViewHolders.ViewHolderCorte;

import java.util.ArrayList;

public class CorteAdapter extends RecyclerView.Adapter<ViewHolderCorte> {
    private ArrayList<Corte> dados;

    public CorteAdapter(ArrayList<Corte> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public ViewHolderCorte onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_corte, viewGroup, false);

        ViewHolderCorte holderCorte = new ViewHolderCorte(view, viewGroup.getContext());
        holderCorte.setDados(dados);

        return holderCorte;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCorte viewHolder, int i) {

        if ( (dados != null) && (dados.size() > 0)) {
            Corte corte = dados.get(i);
            viewHolder.textComentario.setText("Observção: " + corte.getComentario());
            viewHolder.textDataEntrada.setText("Data de Entrada: " + corte.getDataEntrada());
            viewHolder.textDataSaida.setText("Data de Saida: " + corte.getDataSaida());
            viewHolder.textMaxAves.setText("Maximo de aves: " + corte.getMaximo());
            viewHolder.textQuantidade.setText("Quantidade de aves: " + corte.getQuantidadeAves());
            viewHolder.textTipoAve.setText("Tipo de ave: " + corte.getTipoAve());
            viewHolder.textMortalidade.setText("Mortalidade: " + corte.getMortalidade());
            viewHolder.textCodigo.setText("Código: " + corte.getCodigoCorte());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}
