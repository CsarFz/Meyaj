package mx.itesm.cagm.meyaj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.Result;

public class BuscarServicioActiv extends AppCompatActivity {

    ListView searchService;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_servicio);

        searchService = findViewById(R.id.search_service);

        ArrayList<String> arrayService = new ArrayList<>();
        arrayService.addAll(Arrays.asList(getResources().getStringArray(R.array.my_services)));

        adapter= new ArrayAdapter<String>(
                BuscarServicioActiv.this,
                android.R.layout.simple_list_item_1,
                arrayService
        );

        //Seleccion de opcion para pasar a la otra actividad
        searchService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BuscarServicioActiv.this, ResultadosActiv.class);
                intent.putExtra("ServiceType", searchService.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });


        searchService.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.search_service);

        SearchView searchView = (SearchView)item.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
