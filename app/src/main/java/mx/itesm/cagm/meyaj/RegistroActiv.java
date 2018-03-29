package mx.itesm.cagm.meyaj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

public class RegistroActiv extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etNombre;
    private EditText etEdad;
    private EditText etCorreo;
    private EditText etOcupacion;
    private EditText etPassword;
    private Button btnEnviar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etUsuario = findViewById(R.id.etUsuario);
        etNombre = findViewById(R.id.etNombre);
        etEdad = findViewById(R.id.etEdad);
        etCorreo = findViewById(R.id.etCorreo);
        etOcupacion = findViewById(R.id.etOcupacion);
        etPassword = findViewById(R.id.etPassword);

        btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarADrive();
            }
        });

        btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               RegistroActiv.super.onBackPressed();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void enviarADrive() {
        // SUBIR datos a DRIVE
        String url =  "https://docs.google.com/forms/d/e/1FAIpQLScqxXsrrKDmzDiBlZ-cT9hlj8ou11gwIRFhUeVtCKWIFag_dA/formResponse";
        String paramUsuario = "entry.570506592";
        String paramNombre = "entry.55769158";
        String paramEdad = "entry.1731655249";
        String paramCorreo = "entry.1334015331";
        String paramOcupacion = "entry.1712960939";
        String paramPassword = "entry.923629146";

        AndroidNetworking.post(url)
                .addBodyParameter(paramUsuario,etUsuario.getText().toString())
                .addBodyParameter(paramNombre,etNombre.getText().toString())
                .addBodyParameter(paramEdad,etEdad.getText().toString())
                .addBodyParameter(paramCorreo,etCorreo.getText().toString())
                .addBodyParameter(paramOcupacion,etOcupacion.getText().toString())
                .addBodyParameter(paramPassword,etPassword.getText().toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

        etUsuario.setText("");
        etNombre.setText("");
        etEdad.setText("");
        etCorreo.setText("");
        etOcupacion.setText("");
        etPassword.setText("");

        RegistroActiv.super.onBackPressed();
    }
}

//  https://docs.google.com/forms/d/e/1FAIpQLScqxXsrrKDmzDiBlZ-cT9hlj8ou11gwIRFhUeVtCKWIFag_dA/formResponse
//  usuario: entry.570506592
//  nombre: entry.55769158
//  edad: entry.1731655249
//  correo: entry.1334015331
//  ocupacion: entry.1712960939
//  password:  entry.923629146
