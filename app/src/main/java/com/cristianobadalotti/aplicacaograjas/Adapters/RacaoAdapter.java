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

import com.cristianobadalotti.aplicacaograjas.Entidades.Racao;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarRacoesActivity;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.ViewHolders.ViewHolderRacao;

import java.util.ArrayList;

public class RacaoAdapter extends RecyclerView.Adapter<ViewHolderRacao> {
    private ArrayList<Racao> dados;

    public RacaoAdapter(ArrayList<Racao> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public ViewHolderRacao onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_racao, viewGroup, false);

        ViewHolderRacao holderRacao = new ViewHolderRacao(view, viewGroup.getContext());
        holderRacao.setDados(dados);

        return holderRacao;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRacao viewHolder, int i) {

        if ( (dados != null) && (dados.size() > 0)) {
            Racao racao = dados.get(i);
            viewHolder.textDataEntrada.setText("Data de Entrada: " + racao.getDataEntrada());
            viewHolder.textQuantidade.setText("Quantidade: " + racao.getQuantidade() + " Kg");
            viewHolder.textTipoRacao.setText("Tipo de ração: " + racao.getTipoRacao());
            viewHolder.textCodigo.setText("Código: " + racao.getCodigoRacao());
            viewHolder.textCodigoPostura.setText("Código postura: " + racao.getCodigoPostura());
            viewHolder.textCodigoCorte.setText("Código corte: " + racao.getCodigoCorte());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}
