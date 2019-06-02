package com.cristianobadalotti.aplicacaograjas.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristianobadalotti.aplicacaograjas.Entidades.Incubatorio;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.ViewHolders.ViewHolderIncubatorio;

import java.util.ArrayList;

public class IncubatorioAdapter extends RecyclerView.Adapter<ViewHolderIncubatorio>{
    private ArrayList<Incubatorio> dados;

    public IncubatorioAdapter(ArrayList<Incubatorio> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public ViewHolderIncubatorio onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_incubatorio, viewGroup, false);

        ViewHolderIncubatorio holderIncubatorio = new ViewHolderIncubatorio(view, viewGroup.getContext());

        return holderIncubatorio;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderIncubatorio viewHolder, int i) {

        if ( (dados != null) && (dados.size() > 0)) {
            Incubatorio incubatorio = dados.get(i);
            viewHolder.textTipoAve.setText("Tipo de ave: " + incubatorio.getTipoAve());
            viewHolder.textMortalidade.setText("Mortalidade: " + incubatorio.getMortalidade());
            viewHolder.textDataInicio.setText("Data: " + incubatorio.getDataInicio());
            viewHolder.textTempoChocar.setText("Tempo chocagem: " + incubatorio.getTempoChocar());
            viewHolder.textUmidade.setText("Umidade: " + incubatorio.getUmidade());
            viewHolder.textTemperatura.setText("Temperatura: " + incubatorio.getTemperatura());
            viewHolder.textCodigoOvos.setText("Lode de ovos: " + incubatorio.getCodigoOvos());
            viewHolder.textCodigo.setText("Código: " + incubatorio.getCodigoIncubatorio());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}
