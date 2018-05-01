package mx.itesm.cagm.meyaj;

import android.content.Intent;
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

    String[] datosProf;

    Adaptador adaptador;
/*
    String[][] datosProfesionistas = {
            {"Nombre","Profesion", "11.6", "8", "45","Direccion"},
            {"Nombre","Profesion", "12.5", "8", "35"},
            {"Nombre","Profesion", "20.9", "6", "19"},
            {"Nombre","Profesion", "30.8", "9", "101"},
            {"Nombre","Profesion", "8.9", "2", "50"},
    };
*/
    String[] d1 = {"Nombre","Profesion", "11.6", "8", "45","Direccion"};
    String[] d2 = {"Ms","Profesion", "11.6", "8", "45","Direccion"};
    String[] d3 = {"Mr","Profesion", "11.6", "8", "45","Direccion"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);


        rv = (RecyclerView) findViewById(R.id.rvProfesionistas);
        rv.setLayoutManager(new LinearLayoutManager(this));

        profesionistas = new ArrayList<>();
        datosProf = new String[6];
        imgProfesionistas = new int[]{R.drawable.carpintero, R.drawable.electricista, R.drawable.plomero, R.drawable.mecanico, R.drawable.taxi};

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(FBReferences.PROFESIONISTAS_REF);

        adaptador = new Adaptador(profesionistas,imgProfesionistas);
        rv.setAdapter(adaptador);

        final String pSolicitada = "Electricista";
        System.out.println("HICE ANTES ESTO");

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                profesionistas.clear();
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()) {
                    /*System.out.println("Todos los datos");
                    System.out.println(snapshot.getValue());
                    System.out.println("Profesion");
                    String prof = snapshot.child(FBReferences.PROFESION_REF).getValue(String.class);/*
                    if(prof.equals(pSolicitada)){
                        System.out.println("ENTONCRE UNA");
                        //Captura de nombre
                        datosProf[0]= snapshot.child(FBReferences.NOMBRE_REF).getValue(String.class) + " "+snapshot.child(FBReferences.APELLIDO_REF).getValue(String.class) ;
                        //Captura de Profesion
                        datosProf[1]= snapshot.child(FBReferences.PROFESION_REF).getValue(String.class);
                        //Captura distancia PENDIENTE
                        datosProf[2] = "1.2";
                        //Captura calificacion
                        datosProf[3] = snapshot.child(FBReferences.CALIFICACION_REF).getValue(String.class);
                        //Captura calificaciones
                        datosProf[4] = snapshot.child(FBReferences.CALIFICACIONES_REF).getValue(String.class);
                        //Captura Direccion
                        datosProf[5] = "Monte alegría";
                        profesionistas.add(datosProf);
                    }




                    System.out.println(prof);
                    System.out.println("Hijo Servicios");
                    System.out.println(snapshot.child(FBReferences.SERVICIOS_REF).getValue());
                */

                    datosProf[0]= snapshot.child(FBReferences.NOMBRE_REF).getValue(String.class) + " "+snapshot.child(FBReferences.APELLIDO_REF).getValue(String.class) ;
                    System.out.println("CAPTURE A: "+datosProf[0]);
                    //Captura de Profesion
                    datosProf[1]= snapshot.child(FBReferences.PROFESION_REF).getValue(String.class);
                    //Captura distancia PENDIENTE
                    datosProf[2] = "1.2";
                    //Captura calificacion
                    datosProf[3] = String.valueOf(snapshot.child(FBReferences.CALIFICACION_REF).getValue(Integer.class));
                    //Captura calificaciones
                    datosProf[4] = String.valueOf(snapshot.child(FBReferences.CALIFICACIONES_REF).getValue(Integer.class));
                    //Captura Direccion
                    datosProf[5] = "Monte alegría";
                    profesionistas.add(datosProf);
                    System.out.println(profesionistas.toString());
                    datosProf = new String[6];
                }

                adaptador.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("No es posible");
            }


        });
/*
        profesionistas2.add(d1);
        profesionistas2.add(d2);
        profesionistas2.add(d3);
        profesionistas2.add(d4);
        profesionistas2.add(d5);
        profesionistas2.add(d6);
        profesionistas2.add(d4);
        profesionistas2.add(d5);
        profesionistas2.add(d6);

        System.out.println("COMO LLEGUE HASTA ACA?"+ profesionistas2.size());
        //Ingresa datos extraídos de la BD
        lista = findViewById(R.id.lvProfesionistas);
        lista.setAdapter(new Adaptador(this,profesionistas2,imgProfesionistas));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent visorDetalles = new Intent(view.getContext(), DetalleProfesionistaActiv.class);
                visorDetalles.putExtra("NOM", datosProfesionistas[i][0]);
                visorDetalles.putExtra("TIT", datosProfesionistas[i][0]);
                visorDetalles.putExtra("TIT", datosProfesionistas[i][0]);
                visorDetalles.putExtra("TIT", datosProfesionistas[i][0]);
                startActivity(visorDetalles);
            }
        });
*/
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


}
