package mx.itesm.cagm.meyaj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetalleProfesionistaActiv extends AppCompatActivity {

    TextView tvN, tvP, tvD;
    String nombre,profesion,direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_profesionista);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nombre = (String) bundle.get("NAME");
        profesion = (String) bundle.get("PROFESSION");
        direccion = (String) bundle.get("ADDRESS");

        tvN = findViewById(R.id.tvNombre);
        tvD = findViewById(R.id.tvDireccion);
        tvP = findViewById(R.id.tvProfesion);

        tvN.setText(nombre);
        tvP.setText(profesion);
        tvD.setText(direccion);


    }
}
