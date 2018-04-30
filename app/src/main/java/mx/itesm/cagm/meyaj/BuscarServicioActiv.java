package mx.itesm.cagm.meyaj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.Result;

public class BuscarServicioActiv extends AppCompatActivity {

    ListView searchService;
    ArrayAdapter<String> adapter;

    EditText searchField;
    RecyclerView resultList;
    ImageButton btnSearch;
    DatabaseReference dbReference;
    ArrayList<String> nameService;
    ArrayList<String> imgService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_servicio);

        searchService = findViewById(R.id.search_service);

        // <----- César
        searchField = findViewById(R.id.etSearch);
        resultList = findViewById(R.id.rvListServices);
        btnSearch = findViewById(R.id.btnSearch);
        dbReference = FirebaseDatabase.getInstance().getReference();

        resultList.setHasFixedSize(true);
        resultList.setLayoutManager(new LinearLayoutManager(this));
        resultList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        nameService = new ArrayList<>();
        imgService = new ArrayList<>();

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                }
            }
        });
        // -----> César

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

    private void setAdapter(final String searchedString) {
        dbReference.child("services").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = 0;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String sid = snapshot.getKey();
                    String name_service = snapshot.child("name").getValue(String.class);
                    String img_service = snapshot.child("image").getValue(String.class);

                    if (name_service.contains(searchedString)) {
                        nameService.add(name_service);
                        imgService.add(img_service);
                        count++;
                    }

                    if(count == 15) {
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
