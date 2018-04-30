package mx.itesm.cagm.meyaj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ResultadosActiv extends AppCompatActivity {

    ListView lista;

    String[][] datosProfesionistas = {
            {"Nombre","Profesion", "11.6", "8", "45","Direccion"},
            {"Nombre","Profesion", "12.5", "8", "35"},
            {"Nombre","Profesion", "20.9", "6", "19"},
            {"Nombre","Profesion", "30.8", "9", "101"},
            {"Nombre","Profesion", "8.9", "2", "50"},
    };

    int[] imgProfesionistas = {R.drawable.carpintero,R.drawable.electricista,R.drawable.plomero,R.drawable.mecanico,R.drawable.taxi};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(FBReferences.PROEFSIONISTAS_REF);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    System.out.println(snapshot.getValue());
                    System.out.println("QUE PASA");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("No es posible");
            }
        });



        lista = (ListView) findViewById(R.id.lvProfesionistas);
        lista.setAdapter(new Adaptador(this,datosProfesionistas,imgProfesionistas));

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

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
