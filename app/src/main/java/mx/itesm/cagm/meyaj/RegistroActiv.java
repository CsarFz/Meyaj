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
        String url =  "https://docs.google.com/forms/d/e/1FAIpQLSfyI1Z3_eGvFmEC4tqOQFfhXy2h5E8p7zzNYASSrEgzzw85EA/formResponse";
        String paramUsuario = "entry.283016652";
        String paramNombre = "entry.1840762877";
        String paramEdad = "entry.712716663";
        String paramCorreo = "entry.1203683998";
        String paramOcupacion = "entry.1426666584";
        String paramPassword = "entry.952065839";

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

//  https://docs.google.com/forms/d/e/1FAIpQLSfyI1Z3_eGvFmEC4tqOQFfhXy2h5E8p7zzNYASSrEgzzw85EA/formResponse
//  usuario: entry.283016652
//  nombre: entry.1840762877
//  edad: entry.712716663
//  correo: entry.1203683998
//  ocupacion: entry.1426666584
//  password: entry.952065839
