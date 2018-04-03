package mx.itesm.cagm.meyaj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class ResultadosActiv extends AppCompatActivity {

    ListView lista;

    String[][] datosProfesionista = {
            {"Nombre","Profesion", "11.6", "8", "45"},
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

        lista = (ListView) findViewById(R.id.lvProfesionistas);

        lista.setAdapter(new Adaptador(this,datosProfesionista,imgProfesionistas));
    }
}
