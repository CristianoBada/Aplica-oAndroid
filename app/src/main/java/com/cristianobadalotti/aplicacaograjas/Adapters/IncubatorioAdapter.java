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

import com.cristianobadalotti.aplicacaograjas.Entidades.Incubatorio;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarIncubatorioActivity;
import com.cristianobadalotti.aplicacaograjas.R;

import java.util.ArrayList;

public class IncubatorioAdapter extends RecyclerView.Adapter<IncubatorioAdapter.ViewHolderIncubatorio>{
    private ArrayList<Incubatorio> dados;

    public IncubatorioAdapter(ArrayList<Incubatorio> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public IncubatorioAdapter.ViewHolderIncubatorio onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_incubatorio, viewGroup, false);

        IncubatorioAdapter.ViewHolderIncubatorio holderIncubatorio = new IncubatorioAdapter.ViewHolderIncubatorio(view, viewGroup.getContext());

        return holderIncubatorio;
    }

    @Override
    public void onBindViewHolder(@NonNull IncubatorioAdapter.ViewHolderIncubatorio viewHolder, int i) {

        if ( (dados != null) && (dados.size() > 0)) {
            Incubatorio incubatorio = dados.get(i);
            viewHolder.textTipoAve.setText("Tipo de ave: " + incubatorio.getTipoAve());
            viewHolder.textMortalidade.setText("Mortalidade: " + incubatorio.getMortalidade());
            viewHolder.textDataInicio.setText("Data: " + incubatorio.getDataInicio());
            viewHolder.textTempoChocar.setText("Tempo chocagem: " + incubatorio.getTempoChocar());
            viewHolder.textUmidade.setText("Umidade: " + incubatorio.getUmidade());
            viewHolder.textTemperatura.setText("Temperatura: " + incubatorio.getTemperatura());
            viewHolder.textLoteOvos.setText("Lode de ovos: " + incubatorio.getLoteOvos());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderIncubatorio extends RecyclerView.ViewHolder {

        public TextView textLoteOvos;
        public TextView textTemperatura;
        public TextView textUmidade;
        public TextView textTempoChocar;
        public TextView textDataInicio;
        public TextView textMortalidade;
        public TextView textTipoAve;

        public ViewHolderIncubatorio(@NonNull View itemView, final Context context) {
            super(itemView);

            textLoteOvos = itemView.findViewById(R.id.textViewLoteOvos);
            textTemperatura = itemView.findViewById(R.id.textViewTemperaturaIncubatorio);
            textUmidade = itemView.findViewById(R.id.textViewUmidadeIncubatorio);
            textTempoChocar = itemView.findViewById(R.id.textViewTempChocaIncubatorio);
            textDataInicio = itemView.findViewById(R.id.textViewDataIncubatorio);
            textMortalidade = itemView.findViewById(R.id.textViewMortalidadeIncubatorio);
            textTipoAve = itemView.findViewById(R.id.textViewTipoAveIncubatorio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (dados.size() > 0) {

                        Incubatorio incubatorio = dados.get(getLayoutPosition());

                        Intent intent = new Intent(context, EditarIncubatorioActivity.class);

                        intent.putExtra("INCUBATORIO", incubatorio);

                        ((AppCompatActivity) context).startActivityForResult(intent, 0);
                    }
                }
            });
        }
    }
}
