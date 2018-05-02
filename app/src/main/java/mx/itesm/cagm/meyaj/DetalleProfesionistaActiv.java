package mx.itesm.cagm.meyaj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetalleProfesionistaActiv extends AppCompatActivity {

    TextView tvN, tvP, tvD;
    String nombre,profesion,direccion,llave;
    StringBuffer sb=null;
    AdaptadorServicio adaptadorServicio;

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

        tvN = findViewById(R.id.tvNombre);
        tvD = findViewById(R.id.tvDireccion);
        tvP = findViewById(R.id.tvProfesion);
        Button solicitar = findViewById(R.id.btnSolicitar);

        adaptadorServicio = new AdaptadorServicio(this,getServicios());
        tvN.setText(nombre);
        tvP.setText(profesion);
        tvD.setText(direccion);

        System.out.println(llave);

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
                    Toast.makeText(DetalleProfesionistaActiv.this,sb.toString(),Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(DetalleProfesionistaActiv.this,"Nada en lista",Toast.LENGTH_SHORT).show();

                }

            }
        });

        RecyclerView rv = findViewById(R.id.rvServicios);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adaptadorServicio);



    }

    private ArrayList<Servicio> getServicios() {
        ArrayList<Servicio> servicios = new ArrayList<>();
        Servicio s = new Servicio("Limpieza" ,"25","Jamon","2");
        servicios.add(s);
        s = new Servicio("Limpieza" ,"25","Jamon","2");
        servicios.add(s);
        s = new Servicio("Limpieza" ,"25","Jamon","2");
        servicios.add(s);
        s = new Servicio("Limpieza" ,"25","Jamon","2");
        servicios.add(s);
        s = new Servicio("Limpieza" ,"25","Jamon","2");
        servicios.add(s);
        return servicios;

    }
}
