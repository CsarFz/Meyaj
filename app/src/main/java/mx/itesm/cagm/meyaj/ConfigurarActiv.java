package mx.itesm.cagm.meyaj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ConfigurarActiv extends AppCompatActivity {


    private Button btnCerrarSesion;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);




        btnCerrarSesion = findViewById(R.id.btnCerrarSesionP);

        firebaseAuth = FirebaseAuth.getInstance();


        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), MenuPrincipalActiv.class));
            }
        });

    }
}
