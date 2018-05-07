package mx.itesm.cagm.meyaj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigurarActiv extends AppCompatActivity {


    private Button btnCerrarSesion;
    private Button btnGuardarPerfil;
    private Button btnCancelar;

    private EditText etNombre;
    private EditText etApellidos;
    private EditText etNumeroTel;
    private EditText etDireccion;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);

        //Log.i("CAMBIO", "Enttro a configurar.");

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        etNombre = findViewById(R.id.etNombreCP);
        etApellidos = findViewById(R.id.etApellidosCP);
        etNumeroTel = findViewById(R.id.etNumeroTelCP);
        etDireccion = findViewById(R.id.etProfesion);


        btnGuardarPerfil = findViewById(R.id.btnGuardarPerfil);
        btnGuardarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarInformacion();

            }
        });


        btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigurarActiv.super.onBackPressed();
            }
        });


        btnCerrarSesion = findViewById(R.id.btnCerrarSesionP);
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), MenuPrincipalActiv.class));
            }
        });

    }

    private void guardarInformacion(){
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellidos.getText().toString().trim();
        String numTel = etNumeroTel.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();

        UserInformation userInformation = new UserInformation(nombre,apellido,numTel,direccion);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this,"Guardando informacion....", Toast.LENGTH_LONG).show();

    }
}
