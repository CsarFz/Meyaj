package mx.itesm.cagm.meyaj;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetalleProfesionistaActiv extends AppCompatActivity {

    TextView tvN, tvP, tvD;
    String nombre,profesion,direccion,llave;
    StringBuffer sb=null;
    AdaptadorServicio adaptadorServicio;

    ArrayList<Servicio> servicios;
    Servicio s;


    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_profesionista);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nombre = (String) bundle.get("NAME");
        profesion = (String) bundle.get("PROFESSION");
        direccion = (String) bundle.get("ADDRESS");
        llave = (String) bundle.get("KEY");
        servicios = new ArrayList<>();

        //Generar la notification
        notification = new NotificationCompat.Builder(getApplicationContext());
        notification.setAutoCancel(false);

        tvN = findViewById(R.id.tvTelefono);
        tvD = findViewById(R.id.tvDireccion);
        tvP = findViewById(R.id.tvProfesion);
        Button solicitar = findViewById(R.id.btnSolicitar);

        tvN.setText(nombre);
        tvP.setText(profesion);
        tvD.setText(direccion);

        solicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb = new StringBuffer();
                for(Servicio s: adaptadorServicio.checkedServices){
                    sb.append(s.getServicio());
                    sb.append("\n");
                }
                System.out.println("TAMAÑO: "+adaptadorServicio.checkedServices.size());
                if (adaptadorServicio.checkedServices.size()>0){
                    Intent intent = new Intent(DetalleProfesionistaActiv.this, CitaActiv.class);
                    //Pasa servicios seleccionados
                    intent.putExtra("SelectedServices",  adaptadorServicio.getCheckedServices());
                    //Pasa total tarifa seleccionados
                    intent.putExtra("Total",  getSum(adaptadorServicio.totalus));
                    //Llave profesionista
                    intent.putExtra("LlaveProfesionista",llave);

                    startActivity(intent);

                }else{
                    Toast.makeText(DetalleProfesionistaActiv.this,"Seleccione al menos 1 servicio",Toast.LENGTH_LONG).show();
                }

            }
        });

        RecyclerView rv = findViewById(R.id.rvServicios);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(FBReferences.PROFESIONISTAS_REF);
        servicios = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                servicios.clear();
                for (DataSnapshot snapshot: dataSnapshot.child(llave).child(FBReferences.SERVICIOS_REF).getChildren()) {
                    String titulo = snapshot.child("Titulo").getValue(String.class);
                    String descripcion = snapshot.child("Descripcion").getValue(String.class);
                    String precio = snapshot.child("Precio").getValue(String.class);
                    String duracion = snapshot.child("Duracion").getValue(String.class);
                    s = new Servicio(titulo,precio,descripcion,duracion);
                    servicios.add(s);
                }
                adaptadorServicio.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Caduco");
            }
        });

        adaptadorServicio = new AdaptadorServicio(servicios);

        rv.setAdapter(adaptadorServicio);
    }

    private int getSum(ArrayList<Integer> totalus) {
        int total=0;
        for(int i=0; i<totalus.size();i++){
            total+=totalus.get(i);
        }
        return total;
    }
}
