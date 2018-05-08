package mx.itesm.cagm.meyaj;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoPerfilFrag extends Fragment
{
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    private TextView tvNombre;
    private TextView tvTelefono;
    private ImageView ivFoto;

    private Button btnConfigurar;

    private String apellido;
    private Uri imageUri;


    public InfoPerfilFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_info_perfil, container, false);

        tvNombre = myView.findViewById(R.id.tvNombre);
        tvTelefono = myView.findViewById(R.id.tvTelefono);
        ivFoto = myView.findViewById(R.id.ivFoto);


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        databaseReference = FirebaseDatabase.getInstance().getReference();

        DatabaseReference infoUsuario = databaseReference.child(user.getUid());
        infoUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nombre = dataSnapshot.child("nombre").getValue(String.class);
                String apellido = dataSnapshot.child("apellido").getValue(String.class);
                String tel = dataSnapshot.child("numeroTelefono").getValue(String.class);

                tvNombre.setText(nombre + " " + apellido);
                tvTelefono.setText(tel);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(user.getUid()).child("profilepic.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(isAdded()) {
                    Glide.with(getContext())
                            .asBitmap()
                            .load(uri)
                            .into(ivFoto);
                }
            }
        });


        btnConfigurar = myView.findViewById(R.id.btnConfig);
        btnConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intConfig = new Intent(getActivity(), ConfigurarActiv.class);
                startActivity(intConfig);
            }
        });

        // Inflate the layout for this fragment
        return myView;
    }
}
