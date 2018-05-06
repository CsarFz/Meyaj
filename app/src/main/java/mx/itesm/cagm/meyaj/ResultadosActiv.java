package mx.itesm.cagm.meyaj;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ResultadosActiv extends AppCompatActivity {

    RecyclerView rv;

    List<String[]> profesionistas;
    int[] imgProfesionistas;
    String serviceType;

    String[] datosProf;

    Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        rv = (RecyclerView) findViewById(R.id.rvProfesionistas);
        rv.setLayoutManager(new LinearLayoutManager(this));
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        serviceType = (String) bundle.get("ServiceType");
        profesionistas = new ArrayList<>();
        datosProf = new String[7];
        imgProfesionistas = new int[]{R.drawable.carpintero, R.drawable.electricista, R.drawable.plomero, R.drawable.mecanico, R.drawable.taxi};


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(FBReferences.PROFESIONISTAS_REF);

        adaptador = new Adaptador(profesionistas,imgProfesionistas);
        rv.setAdapter(adaptador);



        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultadosActiv.this, DetalleProfesionistaActiv.class);
                intent.putExtra("NAME", profesionistas.get(rv.getChildAdapterPosition(view))[0]);
                intent.putExtra("ADDRESS", profesionistas.get(rv.getChildAdapterPosition(view))[5]);
                intent.putExtra("PROFESSION", profesionistas.get(rv.getChildAdapterPosition(view))[1]);
                intent.putExtra("KEY",profesionistas.get(rv.getChildAdapterPosition(view))[6]);
                startActivity(intent);
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                profesionistas.clear();
                int contador = 0;
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String prof = snapshot.child(FBReferences.PROFESION_REF).getValue(String.class);
                    if(!serviceType.equals("")){
                        System.out.println("Service Type = "+serviceType);
                        if(prof.equals(serviceType)) {

                            datosProf[0] = snapshot.child(FBReferences.NOMBRE_REF).getValue(String.class) + " " + snapshot.child(FBReferences.APELLIDO_REF).getValue(String.class);
                            //Captura de Profesion
                            datosProf[1] = snapshot.child(FBReferences.PROFESION_REF).getValue(String.class);
                            contador++;
                            //Captura distancia PENDIENTE
                            datosProf[2] = "1.2";
                            //Captura calificacion
                            datosProf[3] = String.valueOf(snapshot.child(FBReferences.CALIFICACION_REF).getValue(Integer.class));
                            //Captura calificaciones
                            datosProf[4] = String.valueOf(snapshot.child(FBReferences.CALIFICACIONES_REF).getValue(Integer.class));
                            //Captura Direccion
                            datosProf[5] = (String) snapshot.child(FBReferences.DIRECCION_REF).child("Colonia").getValue();
                            datosProf[6] = String.valueOf(snapshot.getKey());
                            profesionistas.add(datosProf);
                            System.out.println(profesionistas.toString());
                            datosProf = new String[7];
                        }
                    }
                }
                System.out.println("CONTADOR: "+contador);
                if(contador==0){
                    //Toast.makeText(getApplicationContext(),,Toast.LENGTH_SHORT).show();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if (!isFinishing()){
                                new AlertDialog.Builder(ResultadosActiv.this)
                                        .setTitle("BD Null")
                                        .setMessage("Error en la busqueda, intenta con otro atributo")
                                        .setCancelable(false)
                                        .setPositiveButton("Ir", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Whatever...
                                                Intent intentBusqueda = new Intent(ResultadosActiv.this, BuscarServicioActiv.class);
                                                startActivity(intentBusqueda);
                                                finish();
                                            }
                                        }).show();
                            }
                        }
                    });
                }
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("No es posible");
                System.out.println("--------------------------------------------");
            }


        });

    }




}
