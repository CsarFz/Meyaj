package mx.itesm.cagm.meyaj;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgendaFrag extends Fragment {

    ArrayList<String[]> datosAgenda;
    ArrayList<List> serviciosAgenda;
    ArrayList<String> sks;
    String[] lista;
    RecyclerView rv;
    AdaptadorElementoAgenda adaptador;

    String serviceKey;

    public AgendaFrag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_agenda,container,false);


        rv = vista.findViewById(R.id.rvServiciosAgenda);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        datosAgenda = new ArrayList<String[]>();
        serviciosAgenda = new ArrayList<>();
        sks = new ArrayList<>();
        lista = new String[7];

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        DatabaseReference ref = database.getReference(FBReferences.SERVICIOS_ACTIVOS_REF);
        if(user==null){
            System.out.println("Diversion");
        }else{
            adaptador = new AdaptadorElementoAgenda(datosAgenda);
            rv.setAdapter(adaptador);

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    datosAgenda.clear();
                    serviciosAgenda.clear();

                    for (DataSnapshot snapshot :
                            dataSnapshot.getChildren()) {
                        if(user.getUid()==null){

                        }else{
                            if(snapshot.child("Cliente").getValue().equals(user.getUid())){
                                lista[0]= (String) snapshot.child("Fecha").getValue();
                                lista[1]= String.valueOf(snapshot.child("Hora").getValue(Integer.class));
                                lista[2]= String.valueOf(snapshot.child("Minutos").getValue(Integer.class));
                                lista[3]= (String) snapshot.child("Profesionista").getValue();
                                serviciosAgenda.add((List) snapshot.child("Servicios").getValue());
                                datosAgenda.add(lista);
                                lista = new String[4];
                            }


                        }
                    }
                    adaptador.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("No es posible");
                }


            });
        }



        // Inflate the layout for this fragment
        return vista;
    }

}
