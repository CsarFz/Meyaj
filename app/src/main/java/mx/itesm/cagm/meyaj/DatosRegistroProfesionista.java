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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DatosRegistroProfesionista extends AppCompatActivity
{
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton btnSubirImagen;
    private Uri imageUri;
    private StorageReference mStorageRef;

    private Button btnGuardarPerfil;
    private EditText etNombre;
    private EditText etApellidos;
    private EditText etNumeroTel;
    private EditText etDireccion;
    private EditText etProfesion;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_registro_profesionista);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();


        etNombre = findViewById(R.id.etNombreCP);
        etApellidos = findViewById(R.id.etApellidosCP);
        etNumeroTel = findViewById(R.id.etNumeroTelCP);
        etDireccion = findViewById(R.id.etDireccion);
        etProfesion = findViewById(R.id.etProfesion);


        btnGuardarPerfil = findViewById(R.id.btnGuardarPerfil);
        btnGuardarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarInformacion();

            }
        });

        btnSubirImagen = findViewById(R.id.btnImagen);
        btnSubirImagen.setOnClickListener(new View.OnClickListener() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            btnSubirImagen.setImageURI(imageUri);
        }
    }

    private void guardarInformacion(){
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellidos.getText().toString().trim();
        String numTel = etNumeroTel.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();
        String profesion = etProfesion.getText().toString().trim();


        if(nombre == "" || apellido == "" || numTel == "" || direccion == "" || profesion == ""){
            Toast.makeText(this, "Llena todos los campos.", Toast.LENGTH_SHORT).show();
        } else if(imageUri == null){
            Toast.makeText(this, "Sube una foto.", Toast.LENGTH_SHORT).show();
        } else {

            Profesionista profesionista = new Profesionista(nombre,apellido,numTel,direccion,profesion);
            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child(user.getUid()).setValue(profesionista);

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
                            Toast.makeText(DatosRegistroProfesionista.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            Toast.makeText(this,"Usuario registrado", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(DatosRegistroProfesionista.this, MenuPrincipalActiv.class);
            startActivity(intent);
        }
    }
}
