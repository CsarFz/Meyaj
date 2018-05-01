package mx.itesm.cagm.meyaj;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.model.Progress;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFrag extends Fragment {


    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    //Button btnRegistro;
    private Button btnIniciarSesion;
    private EditText etCorreo;
    private EditText etPassword;


    private TextView tvRegistro;

    public PerfilFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_perfil, container, false);



        progressDialog = new ProgressDialog(getContext(), R.style.MyAlerDialogStyle);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            // AQUI DEBE IR LA LIGA PARA LA ACTIVIDAD DEL PERFIL
            // Iniciar la actividad del perfil
            //startActivity( new Intent(getContext(), MenuPrincipalActiv.class));
        }


        tvRegistro = myView.findViewById(R.id.btnRegistrar);
        tvRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intRegistro = new Intent(getActivity(), RegistroActiv.class);
                startActivity(intRegistro);
            }
        });

        etCorreo = myView.findViewById(R.id.etCorreoI);
        etPassword = myView.findViewById(R.id.etPasswordI);



        btnIniciarSesion = myView.findViewById(R.id.btnIngresar);
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }

            private void userLogin() {
                String email = etCorreo.getText().toString().trim();
                String password = etPassword.getText().toString().trim();



                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getContext(), "El campo  de correo debe estar lleno.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getContext(), "El campo de contraseña debe estar lleno", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("Ingresando...");
                progressDialog.setIndeterminate(true);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();


                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();

                                if(task.isSuccessful()){
                                    // Iniciar la actividad del perfil
                                    startActivity( new Intent(getContext(), ConfigurarActiv.class));
                                }else{
                                    Toast.makeText(getContext(),"Usuario o contraseña inválidos.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        // Inflate the layout for this fragment
        return myView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}