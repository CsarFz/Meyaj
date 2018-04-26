package mx.itesm.cagm.meyaj;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistroActiv extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etNombre;
    private EditText etApellido;
    private EditText etEdad;
    private EditText etCorreo;
    private EditText etOcupacion;
    private EditText etPassword;
    private Button btnEnviar;
    private Button btnCancelar;




    private ProgressDialog progressDialog;


    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        firebaseAuth = FirebaseAuth.getInstance();


        progressDialog = new ProgressDialog(this);




        setContentView(R.layout.activity_registro);
        etUsuario = findViewById(R.id.etUsuario);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        etCorreo = findViewById(R.id.etCorreo);
        etOcupacion = findViewById(R.id.etOcupacion);
        etPassword = findViewById(R.id.etPassword);

        btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
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

    private void registrarUsuario(){
        String usuario = etUsuario.getText().toString().trim();
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String edad = etEdad.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String ocupacion = etOcupacion.getText().toString().trim();
        String password = etPassword.getText().toString().trim();


        //Comprobar que los campos esten llenos
        if(TextUtils.isEmpty(usuario)){
            Toast.makeText(getApplicationContext(), "Este campo debe estar lleno", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(nombre)){
            Toast.makeText(getApplicationContext(), "Este campo debe estar lleno", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(apellido)){
            Toast.makeText(getApplicationContext(), "Este campo debe estar lleno", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(edad)){
            Toast.makeText(getApplicationContext(), "Este campo debe estar lleno", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(correo)){
            Toast.makeText(getApplicationContext(), "Este campo debe estar lleno", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(ocupacion)){
            Toast.makeText(getApplicationContext(), "Este campo debe estar lleno", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Este campo debe estar lleno", Toast.LENGTH_SHORT).show();
            return;
        }



        progressDialog.setMessage("Registrando usuario....");
        progressDialog.show();



        firebaseAuth.createUserWithEmailAndPassword(correo,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //El usuario esta registrado exitosamente, vamos a abrir la actividad para llena rel perfil
                            Toast.makeText(RegistroActiv.this,"Usuario registrado", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegistroActiv.this,"No se pudo registrar, intenta de nuevo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
