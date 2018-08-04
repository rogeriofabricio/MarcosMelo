package br.com.rnsolucoesweb.marcosmelo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.rnsolucoesweb.marcosmelo.config.ConfiguracaoFirebase;

import java.util.ArrayList;


public class MinhasViagensActivity extends AppCompatActivity {

    private ValueEventListener valueEventListenerViagem;
    private DatabaseReference firebase;
    private ArrayList<Viagem> viagens;
    private ArrayAdapter adapter;
    private String uID;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    ListView lista;
    //ArrayList<Viagem> viagens = new ArrayList<Viagem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_viagens);

        lista = (ListView) findViewById(R.id.lvViagens);

        //Recuperar o ID do usuário
        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser();
        uID = mAuth.getUid();

        //Montar o listview e o adapter
        viagens = new ArrayList<>();
        adapter = new ArrayAdapter(
                MinhasViagensActivity.this,
                android.R.layout.simple_list_item_1,
                viagens
        );
        lista.setAdapter(adapter);

        //Recuperar viagens do Firebase
        firebase = ConfiguracaoFirebase.getFirebase()
                .child("viagens2")
                .child(uID);

        // Cria listener para mensagens
        valueEventListenerViagem = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Limpar mensagens
                viagens.clear();

                // Recupera mensagens
                for ( DataSnapshot dados: dataSnapshot.getChildren() ){
                    Viagem viagem = dados.getValue( Viagem.class );
                    //Log.i("FIREBASE", dataSnapshot.getValue().toString());
                    //viagens.add(viagem.getData());
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        firebase.addValueEventListener( valueEventListenerViagem );

        // Ler o banco de dados
//        viagens2Reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.i("FIREBASE", dataSnapshot.getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//
//            }
//        });

    }

    @Override
    protected void onStop() {
        super.onStop();

        firebase.removeEventListener(valueEventListenerViagem);
    }
}