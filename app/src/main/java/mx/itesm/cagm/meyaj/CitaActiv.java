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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class CitaActiv extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    ArrayList<Servicio> servicios;
    int total, horaICita, minICita;
    String llaveP, fechaCita;
    AdaptadorResumenServicio adaptadorServicio;
    TextView horaFinal;
    DatabaseReference ref,refUsuario;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        servicios = (ArrayList<Servicio>) bundle.get("SelectedServices");

        total = (int) bundle.get("Total");
        llaveP = (String) bundle.get("LlaveProfesionista");

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

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference(FBReferences.SERVICIOS_ACTIVOS_REF);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();


        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CitaActiv.this, MenuPrincipalActiv.class);
                //Llave del servicio y datos
                String id = ref.push().getKey();
                ref.child(id).child("Hora").setValue(horaICita);
                ref.child(id).child("Minutos").setValue(minICita);
                ref.child(id).child("Fecha").setValue(fechaCita);
                ref.child(id).child("Profesionista").setValue(llaveP);
                ref.child(id).child("Cliente").setValue(user.getUid());

                //Guarda llave del servicio dentro de usuario
                refUsuario = database.getReference(user.getUid());
                String idServicio = refUsuario.push().getKey();
                refUsuario.child("Servicios").child(idServicio).setValue(id);

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
        fechaCita = DateFormat.getDateInstance().format(c.getTime());
        TextView textView = (TextView) findViewById(R.id.tvFecha);
        textView.setText(currentDS);
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int h, int m) {
        TextView hInit = findViewById(R.id.tvHoraInicio);
        minICita = m;
        horaICita = h;
        //Pendiente finalizacion de cita
        TextView hFinit = findViewById(R.id.tvHoraFin);
        if(m<10){
            if(h>12){
                hInit.setText(h+" : 0"+ m+" PM");
            }else{
                hInit.setText(h+" : 0"+ m+" AM");
            }
        }else{
            if(h>12){
                hInit.setText(h+" : "+ m+" PM");
            }else{
                hInit.setText(h+" : "+ m+" AM");
            }

        }

    }
}