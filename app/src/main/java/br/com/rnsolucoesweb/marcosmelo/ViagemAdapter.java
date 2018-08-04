package br.com.rnsolucoesweb.marcosmelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViagemAdapter extends ArrayAdapter<Viagem> {

    private final  Context context;
    private final  ArrayList<Viagem>  elementos;
    public ViagemAdapter(Context context, ArrayList<Viagem> elementos)  {
        super(context,  R.layout.linha,  elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)  {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha,  parent,  false);

        TextView data = (TextView) rowView.findViewById(R.id.data);
        TextView origemSaida = (TextView) rowView.findViewById(R.id.origemSaida);
        TextView destinoChegada = (TextView) rowView.findViewById(R.id.destinoChegada);
        data.setText(elementos.get(position).getData());
        origemSaida.setText(elementos.get(position).getOrigem());
        destinoChegada.setText(elementos.get(position).getDestino());
        return rowView;
    }
}