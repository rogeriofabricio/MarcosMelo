package br.com.rnsolucoesweb.marcosmelo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import br.com.rnsolucoesweb.marcosmelo.config.ConfiguracaoFirebase;

import java.util.ArrayList;


public class MinhasViagensActivity extends AppCompatActivity {

    private ValueEventListener valueEventListenerViagem;
    private DatabaseReference firebase;
    private ArrayList<String> viagens;
    private ArrayAdapter adapter;
    private String uID;
    private String nomeUsuario;
    private FirebaseAuth mAuth;
    public ListView lista;
    private Viagem viagemR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_viagens);

        //ListView
        lista = (ListView) findViewById(R.id.lvViagens);

        //Recuperar o ID e nome do usuário
        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser();
        uID = mAuth.getUid();
        nomeUsuario = mAuth.getCurrentUser().getDisplayName();

        //Montar o listview e o adapter
        viagens = new ArrayList<String>();
        adapter = new ArrayAdapter(
                MinhasViagensActivity.this,
                android.R.layout.simple_list_item_1,
                viagens
        );
        lista.setAdapter(adapter);

        //Recuperar viagens do Firebase
        firebase = ConfiguracaoFirebase.getFirebase()
                .child("viagens")
                .child(uID)
                .child(nomeUsuario);

        // Cria listener para mensagens
        valueEventListenerViagem = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Limpar mensagens
                viagens.clear();

                // Recupera mensagens
                for ( DataSnapshot dados: dataSnapshot.getChildren() ){
                    viagemR = dados.getValue( Viagem.class );
                    String idR = viagemR.getId();
                    String dataR = viagemR.getData();
                    String origemR = viagemR.getOrigem();
                    String destinoR = viagemR.getDestino();
                    viagens.add(idR);
                    viagens.add(dataR + " - " + origemR + " x " + destinoR);


                    lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {

                            String dataRef = lista.getItemAtPosition(position).toString();

                            //firebase.child(dataRef).setValue(null);
                            //TODO REMOVE NODE

                            Toast.makeText(getApplicationContext(), "Clicou no item " + dataRef, Toast.LENGTH_LONG).show();

                        }
                    });

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        firebase.addValueEventListener( valueEventListenerViagem );

    }

    @Override
    protected void onStop() {
        super.onStop();

        firebase.removeEventListener(valueEventListenerViagem);
    }
}