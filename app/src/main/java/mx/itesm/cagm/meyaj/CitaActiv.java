package mx.itesm.cagm.meyaj;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class CitaActiv extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    ArrayList<Servicio> servicios;
    int total;
    AdaptadorResumenServicio adaptadorServicio;
    TextView horaFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        servicios = (ArrayList<Servicio>) bundle.get("SelectedServices");

        total = (int) bundle.get("Total");

        Button btnDate = (Button) findViewById(R.id.btnFecha);
        Button btnHour = (Button) findViewById(R.id.btnHora);
        Button btnSolicitar = (Button) findViewById(R.id.btnAgendar);

        adaptadorServicio = new AdaptadorResumenServicio(servicios);
        //Actualizacion datos pantalla
        TextView totalCuenta = findViewById(R.id.tvTotal);
        totalCuenta.setText("Total: $ "+String.valueOf(total)+".00 (IVA incluido)");

        RecyclerView rv = findViewById(R.id.rvRServicios);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adaptadorServicio);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FechaFragment datePicker = new FechaFragment();

                datePicker.show(getSupportFragmentManager(),"Date picker");
            }
        });

        btnHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment hourPicker = new HoraFragment();
                android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                hourPicker.show(manager,"hola");


            }
        });

        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CitaActiv.this, MenuPrincipalActiv.class);
                startActivity(intent);
                finishAffinity();
                Toast.makeText(getApplicationContext(),"Cita realizada con éxito, puede revisarla en su agenda para consultar detalles",Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);
        String currentDS = DateFormat.getDateInstance().format(c.getTime());
        TextView textView = (TextView) findViewById(R.id.tvFecha);
        textView.setText(currentDS);
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        TextView hInit = findViewById(R.id.tvHoraInicio);
        TextView hFinit = findViewById(R.id.tvHoraFin);
        if(i1<10){
            if(i>12){
                hInit.setText(i+" : 0"+ i1+" PM");
            }else{
                hInit.setText(i+" : 0"+ i1+" AM");
            }
        }else{
            if(i>12){
                hInit.setText(i+" : "+ i1+" PM");
            }else{
                hInit.setText(i+" : "+ i1+" AM");
            }

        }

    }
}
