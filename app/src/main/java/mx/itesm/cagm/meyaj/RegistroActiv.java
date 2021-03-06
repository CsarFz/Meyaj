package mx.itesm.cagm.meyaj;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActiv extends AppCompatActivity {


    private EditText etCorreo;

    private EditText etPassword;
    private Button btnEnviar;
    private Button btnCancelar;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    private DatabaseReference db;
    //private UserInformation usuario;

    private CheckBox cbProfesionista;
    private boolean esProfesionista = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        firebaseAuth = FirebaseAuth.getInstance();

        db = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(this, R.style.MyAlertDialogStyle);

        //cbProfesionista = findViewById(R.id.cbProfesionista);

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
            Toast.makeText(getApplicationContext(), "El campo  de correo debe de estar lleno.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "El campo de contraseña debe de estar lleno.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registrando usuario, por favor espere...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(correo,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            try {
                                //Toast.makeText(getApplicationContext(),"Usuario Registrado.",Toast.LENGTH_SHORT).show();
                                Thread.sleep(3000);

                                if(esProfesionista){
                                    Intent intentD = new Intent(RegistroActiv.this, DatosRegistroProfesionista.class);
                                    progressDialog.cancel();
                                    startActivity(intentD);
                                } else {
                                    Intent intentD = new Intent(RegistroActiv.this, DatosRegistroActiv.class);
                                    progressDialog.cancel();
                                    startActivity(intentD);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }else{
                            progressDialog.cancel();

                            try {
                                throw task.getException();
                            } catch (Exception e) {

                                if(e.toString().contains("The email address is already in use by another")){
                                    Toast.makeText(RegistroActiv.this,"Ya existe una cuenta asociada con esta dirección de correo", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(RegistroActiv.this,"No se pudo registrar, intente de nuevo.", Toast.LENGTH_SHORT).show();
                                }

                            }

                        }
                    }
                });
    }

    /*
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.cbProfesionista:
                if (checked) {
                    esProfesionista = true;
                } else{
                    esProfesionista = false;
                }
                break;
        }
    }
    */

}

//  https://docs.google.com/forms/d/e/1FAIpQLSfyI1Z3_eGvFmEC4tqOQFfhXy2h5E8p7zzNYASSrEgzzw85EA/formResponse
//  usuario: entry.283016652
//  nombre: entry.1840762877
//  edad: entry.712716663
//  correo: entry.1203683998
//  ocupacion: entry.1426666584
//  password: entry.952065839
