package br.com.rnsolucoesweb.marcosmelo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import br.com.rnsolucoesweb.marcosmelo.model.Viagens;


public class ViagemActivity extends AppCompatActivity implements View.OnClickListener {

    public String messageOrigemRR;
    public String messageDestinoR;
    public Button bt_confirmar;
    public Button bt_cancelar;

    // Firebase Auth Object.
    public FirebaseAuth firebaseAuth;
    // Firebase Database Object.
    private DatabaseReference mDatabase;
    public DatabaseReference myRef;

    public String nomeUsuario;
    public String uID;
    public String data;
    public String dataViagem;
    public String dataViagem2;

    public TextView dataEscolhida;
    public Button bt_date;
    static final int DATE_DIALOG_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagem);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Get the Intent that started this activity and extract the string
        Bundle destino = getIntent().getExtras();
        messageOrigemRR = destino.getString("origem");
        messageDestinoR = destino.getString("destino");

        //Get Username
        // Getting Firebase Auth Instance into firebaseAuth object.
        firebaseAuth = FirebaseAuth.getInstance();

        // Getting Current Login user details.
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        uID = firebaseUser.getUid();
        nomeUsuario = firebaseUser.getDisplayName();

        //Data da viagem
        bt_date = (Button) findViewById(R.id.bt_date);
        bt_date.setOnClickListener(this);

        // Capture the layout's TextView and set the string as its text
        TextView textView1 = findViewById(R.id.textView1);
        textView1.setText(messageOrigemRR);

        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(messageDestinoR);

        TextView textView3 = findViewById(R.id.textView4);
        textView3.setText(nomeUsuario);

        dataEscolhida = (TextView)findViewById(R.id.textView5);

        bt_confirmar = (Button)findViewById(R.id.bt_confirmar);
        bt_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                SalvarViagem(uID, data, nomeUsuario, messageOrigemRR, messageDestinoR);
            }
        });

        bt_cancelar = (Button)findViewById(R.id.bt_cancelar);
        bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                CancelarViagem();
            }
        });
    }



    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar calendario = Calendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, ano, mes,
                        dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {

                    if (monthOfYear < 10 && dayOfMonth < 10) {
                        dataViagem = String.valueOf(year) + "0" + String.valueOf(monthOfYear+1) + "0" + String.valueOf(dayOfMonth);
                    } else if (monthOfYear < 10 && dayOfMonth > 9) {
                        dataViagem = String.valueOf(year) + "0" + String.valueOf(monthOfYear+1) + String.valueOf(dayOfMonth);
                    } else if (monthOfYear > 9 && dayOfMonth < 10){
                        dataViagem = String.valueOf(year) + String.valueOf(monthOfYear+1) + "0" + String.valueOf(dayOfMonth);
                    } else {
                        dataViagem = String.valueOf(year) + String.valueOf(monthOfYear+1) + String.valueOf(dayOfMonth);
                    }

                    dataViagem2 = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1) + "/" + String.valueOf(year);
                    dataEscolhida.setText(dataViagem2);
                    //Toast.makeText(ViagemActivity.this, "DATA = " + dataViagem, Toast.LENGTH_SHORT).show();
                }
            };

    @Override
    public void onClick(View v) {
        if (v == bt_date)
            showDialog(DATE_DIALOG_ID);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void SalvarViagem(String uID, String data, String nomeUsuario, String origem, String destino) {

        myRef = mDatabase.child("viagens").child(uID).child(nomeUsuario).child(dataViagem);
        myRef.child("id").setValue(dataViagem);
        myRef.child("data").setValue(dataViagem2);
        myRef.child("origem").setValue(origem);
        myRef.child("destino").setValue(destino);

        Toast.makeText(ViagemActivity.this, "Viagem Salva!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, GoogleSignInActivity.class);
        startActivity(intent);

    }

    public void CancelarViagem() {
        Intent intentCancelar = new Intent(this, GoogleSignInActivity.class);
        startActivity(intentCancelar);
    }
}
