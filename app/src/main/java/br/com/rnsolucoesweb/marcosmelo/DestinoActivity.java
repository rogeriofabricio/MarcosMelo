package br.com.rnsolucoesweb.marcosmelo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class DestinoActivity extends AppCompatActivity {

    public String messageOrigemR;
    public String messageDestino;
    ListView listViewDestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destino);

        // Get the Intent that started this activity and extract the string
        Bundle origem = getIntent().getExtras();
        messageOrigemR = origem.getString("origem");

        // Array of strings...
        final String[] destinoArray = {"Olinda", "Recife", "Gravatá", "Bezerros", "Caruaru", "Belo Jardim", "Pesqueira", "Arcoverde", "Algodões", "Custódia", "Sítio dos Nunes", "Serra Talhada"};

        ArrayAdapter adapterDestino = new ArrayAdapter<String>(this,
                R.layout.activity_list_view_destino, destinoArray);

        listViewDestino = (ListView) findViewById(R.id.listaCidadeDestino);
        listViewDestino.setAdapter(adapterDestino);

        //Configuração Cabeçalho da listview
        TextView textViewDestino = new TextView(this);
        textViewDestino.setTextSize(20);
        textViewDestino.setTypeface(null, Typeface.BOLD);
        textViewDestino.setTextColor(Color.parseColor("#FFFFFF"));
        textViewDestino.setBackgroundColor(Color.parseColor("#FFA3A7A7"));
        textViewDestino.setPadding(25, 25, 15, 25);
        textViewDestino.setText("CIDADE DESTINO:");
        listViewDestino.addHeaderView(textViewDestino);

        listViewDestino.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                messageDestino = destinoArray[position - 1].toString();
                //Toast.makeText(getApplicationContext(), "Clicou no item " + messageDestino, Toast.LENGTH_LONG).show();

                //Próxima tela
                TelaViagem();
            }
        });
    }

    public void TelaViagem() {
        Intent ii = new Intent(this,ViagemActivity.class);
        Bundle destino = new Bundle();
        destino.putString("origem", messageOrigemR);
        destino.putString("destino", messageDestino);
        ii.putExtras(destino);
        startActivity(ii);
    }
}
