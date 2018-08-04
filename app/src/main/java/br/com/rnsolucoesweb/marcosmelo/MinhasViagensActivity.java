package br.com.rnsolucoesweb.marcosmelo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MinhasViagensActivity extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference viagens2Reference = databaseReference.child("viagens2");

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
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

        // Ler o banco de dados
        viagens2Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("FIREBASE", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
}