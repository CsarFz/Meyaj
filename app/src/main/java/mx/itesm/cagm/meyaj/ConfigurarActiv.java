package mx.itesm.cagm.meyaj;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ConfigurarActiv extends AppCompatActivity
{

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button btnCerrarSesion;
    private Button btnGuardarPerfil;
    private Button btnCancelar;

    private EditText etNombre;
    private EditText etApellidos;
    private EditText etNumeroTel;
    private EditText etDireccion;

    private ImageButton btnImagen;
    private Uri imageUri;
    private StorageReference mStorageRef;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);

        //Log.i("CAMBIO", "Enttro a configurar.");

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

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


        btnImagen = findViewById(R.id.btnImagen);
        btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void guardarInformacion(){
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellidos.getText().toString().trim();
        String numTel = etNumeroTel.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();

        FirebaseUser user = firebaseAuth.getCurrentUser();


        if(nombre == "" || apellido == "" || numTel == "" || direccion == ""){
            Toast.makeText(this, "Llena todos los campos.", Toast.LENGTH_SHORT).show();
        } else if(imageUri != null){
            StorageReference fileReference = mStorageRef.child(user.getUid() + "/" + "profilepic.jpg");
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //Toast.makeText(DatosRegistroActiv.this, "Se subió la imagen", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ConfigurarActiv.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            UserInformation userInformation = new UserInformation(nombre, apellido, numTel, direccion);
            databaseReference.child(user.getUid()).setValue(userInformation);
            Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ConfigurarActiv.this, MenuPrincipalActiv.class);
            startActivity(intent);
        } else {
            UserInformation userInformation = new UserInformation(nombre, apellido, numTel, direccion);
            databaseReference.child(user.getUid()).setValue(userInformation);
            Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ConfigurarActiv.this, MenuPrincipalActiv.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            btnImagen.setImageURI(imageUri);
        }
    }
}
