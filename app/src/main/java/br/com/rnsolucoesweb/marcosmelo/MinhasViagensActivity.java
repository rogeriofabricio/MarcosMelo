package br.com.rnsolucoesweb.marcosmelo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MinhasViagensActivity extends AppCompatActivity {

    DatabaseReference myRef;
    ListView lista;
    ArrayList<Viagem> viagens = new ArrayList<Viagem>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_viagens);

        lista = (ListView) findViewById(R.id.lvViagens);
        adapter = new ViagemAdapter(this, viagens);
        lista.setAdapter(adapter);
        myRef = FirebaseDatabase.getInstance().getReference();
        //myRef.child("viagens2").child("EFHCFf8OboaG2rYFtTPtblmWeGU2").child("20180701");
        myRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                viagens.clear();
//                Viagem value = dataSnapshot.getValue(Viagem.class);
//                viagens.add(value);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}