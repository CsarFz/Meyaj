package mx.itesm.cagm.meyaj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class ResultadosActiv extends AppCompatActivity {

    ListView lista;

    String[][] datosProfesionista = {
            {"Nombre","Profesion", "Distancia", "10", "Votos"},
            {"Nombre","Profesion", "Distancia", "10", "Votos"},
            {"Nombre","Profesion", "Distancia", "10", "Votos"},
            {"Nombre","Profesion", "Distancia", "10", "Votos"},
            {"Nombre","Profesion", "Distancia", "10", "Votos"},

    };

    int[] imgProfesionistas = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        lista = (ListView) findViewById(R.id.lvProfesionistas);
    }
}
