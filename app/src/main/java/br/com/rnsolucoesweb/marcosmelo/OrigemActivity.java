package br.com.rnsolucoesweb.marcosmelo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OrigemActivity extends AppCompatActivity {

    public String messageOrigem;
    public ListView listViewOrigem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origem);

        // Array of strings...
        final String[] origemArray = {"Recife", "Gravatá", "Bezerros", "Caruaru", "Belo Jardim", "Pesqueira", "Arcoverde", "Algodões", "Custódia", "Sítio dos Nunes", "Serra Talhada"};

        ArrayAdapter adapterOrigem = new ArrayAdapter<String>(this,
                R.layout.activity_list_view_origem, origemArray);

        listViewOrigem = (ListView) findViewById(R.id.listaCidadeOrigem);
        listViewOrigem.setAdapter(adapterOrigem);

        //Configuração Cabeçalho da listview
        TextView textViewOrigem = new TextView(this);
        textViewOrigem.setTextSize(20);
        textViewOrigem.setTypeface(null, Typeface.BOLD);
        textViewOrigem.setTextColor(Color.parseColor("#FFFFFF"));
        textViewOrigem.setBackgroundColor(Color.parseColor("#FFA3A7A7"));
        textViewOrigem.setPadding(25, 25, 15, 25);
        textViewOrigem.setText("CIDADE PARTIDA:");
        listViewOrigem.addHeaderView(textViewOrigem);

        listViewOrigem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                messageOrigem = origemArray[position - 1].toString();
                //Toast.makeText(getApplicationContext(), "Clicou no item " + messageOrigem, Toast.LENGTH_LONG).show();

                //Próxima tela
                TelaDestino();
            }
        });
    }

    public void TelaDestino() {

        Intent i = new Intent(this,DestinoActivity.class);
        Bundle origem = new Bundle();
        origem.putString("origem", messageOrigem);
        i.putExtras(origem);
        startActivity(i);
    }
}
