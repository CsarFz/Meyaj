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


    private EditText etCorreo;

    private EditText etPassword;
    private Button btnEnviar;
    private Button btnCancelar;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        firebaseAuth = FirebaseAuth.getInstance();


        progressDialog = new ProgressDialog(this, R.style.MyAlertDialogStyle);




        setContentView(R.layout.activity_registro);
        etCorreo = findViewById(R.id.etCorreo);
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

        String correo = etCorreo.getText().toString().trim();
        String password = etPassword.getText().toString().trim();


        //Comprobar que los campos esten llenos

        if(TextUtils.isEmpty(correo)){
            Toast.makeText(getApplicationContext(), "El campo Correo debe estar lleno", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "El campo Password debe estar lleno", Toast.LENGTH_SHORT).show();
            return;
        }



        progressDialog.setMessage("Registrando usuario...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();



        firebaseAuth.createUserWithEmailAndPassword(correo,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //El usuario esta registrado exitosamente, vamos a abrir la actividad para llena rel perfil
                            Toast.makeText(RegistroActiv.this,"Usuario registrado.", Toast.LENGTH_SHORT).show();
                            try {
                                Toast.makeText(getApplicationContext(),"Usuario Registrado.",Toast.LENGTH_SHORT).show();
                                Thread.sleep(3000);
                                RegistroActiv.super.onBackPressed();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }else{
                            progressDialog.cancel();
                            Toast.makeText(RegistroActiv.this,"No se pudo registrar, intenta de nuevo.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}

//  https://docs.google.com/forms/d/e/1FAIpQLSfyI1Z3_eGvFmEC4tqOQFfhXy2h5E8p7zzNYASSrEgzzw85EA/formResponse
//  usuario: entry.283016652
//  nombre: entry.1840762877
//  edad: entry.712716663
//  correo: entry.1203683998
//  ocupacion: entry.1426666584
//  password: entry.952065839
