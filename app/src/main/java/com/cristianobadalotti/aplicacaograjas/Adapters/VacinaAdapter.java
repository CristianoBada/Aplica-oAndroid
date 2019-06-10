package com.cristianobadalotti.aplicacaograjas.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristianobadalotti.aplicacaograjas.Entidades.Vacina;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.ViewHolders.ViewHolderVacina;

import java.util.ArrayList;

public class VacinaAdapter extends RecyclerView.Adapter<ViewHolderVacina> {
    private ArrayList<Vacina> dados;

    public VacinaAdapter(ArrayList<Vacina> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public ViewHolderVacina onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_vacina, viewGroup, false);

        ViewHolderVacina holderVacina = new ViewHolderVacina(view, viewGroup.getContext());
        holderVacina.setDados(dados);

        return holderVacina;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVacina viewHolder, int i) {

        if ( (dados != null) && (dados.size() > 0)) {
            Vacina vacina = dados.get(i);
            viewHolder.textTipo.setText("Tipo de tratamento: " + vacina.getTipoTratamento());
            viewHolder.textData.setText("Data: " + vacina.getDataTratamento());
            viewHolder.textDetalhe.setText("Observação: " + vacina.getDetalhe());
            viewHolder.textCodigo.setText("Código: " + vacina.getCodigoVacina());
            viewHolder.textCodigoPostura.setText("Código postura: " + vacina.getCodigoPostura());
            viewHolder.textCodigoCorte.setText("Código corte: " + vacina.getCodigoCorte());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}
