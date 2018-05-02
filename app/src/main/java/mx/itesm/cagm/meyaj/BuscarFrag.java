package mx.itesm.cagm.meyaj;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;



/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarFrag extends Fragment
{
    GridLayout mainGrid;

    // CARDVIEWS (BOTONES)
    CardView cvCarpintero;
    CardView cvElectricista;
    CardView cvPlomero;
    CardView cvAlbanil;
    CardView cvBuscar;
    CardView cvTecnico;
    CardView cvTaxi;
    CardView cvCerrajero;
    CardView cvMecanico;


    public BuscarFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_buscar, container, false);

        mainGrid = getActivity().findViewById(R.id.mainGrid);

        // Listener de Card View Buscar
        cvCarpintero = myView.findViewById(R.id.cvCarpintero);
        cvElectricista = myView.findViewById(R.id.cvElectricista);
        cvPlomero = myView.findViewById(R.id.cvPlomero);
        cvAlbanil = myView.findViewById(R.id.cvAlbanil);
        cvBuscar = myView.findViewById(R.id.cvBuscar);
        cvTecnico = myView.findViewById(R.id.cvTecnico);
        cvTaxi = myView.findViewById(R.id.cvTaxi);
        cvCerrajero = myView.findViewById(R.id.cvCerrajero);
        cvMecanico = myView.findViewById(R.id.cvMecanico);

        cvCarpintero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResultadosActiv.class);
                intent.putExtra("ServiceType", "Carpintero");
                startActivity(intent);
            }
        });
        cvElectricista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResultadosActiv.class);
                intent.putExtra("ServiceType", "Electricista");
                startActivity(intent);
            }
        });
        cvPlomero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResultadosActiv.class);
                intent.putExtra("ServiceType", "Plomero");
                startActivity(intent);
            }
        });
        cvAlbanil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResultadosActiv.class);
                intent.putExtra("ServiceType", "Albanil");
                startActivity(intent);
            }
        });
        cvBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intBuscador = new Intent(getActivity(), BuscarServicioActiv.class);
                startActivity(intBuscador);
            }
        });
        cvTecnico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResultadosActiv.class);
                intent.putExtra("ServiceType", "Tecnico");
                startActivity(intent);
            }
        });
        cvTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResultadosActiv.class);
                intent.putExtra("ServiceType", "Taxista");
                startActivity(intent);
            }
        });
        cvCerrajero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResultadosActiv.class);
                intent.putExtra("ServiceType", "Cerrajero");
                startActivity(intent);
            }
        });
        cvMecanico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResultadosActiv.class);
                intent.putExtra("ServiceType", "Mecanico");
                startActivity(intent);
            }
        });

        //setToggle(mainGrid);

        return myView;

    }

   /* private void setToggle(GridLayout mainGrid) {
        for(int i = 0; i < mainGrid.getChildCount(); i++){
            final CardView cardView = (CardView)mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1){
                        cardView.setCardBackgroundColor(Color.parseColor("#6EAFC2"));
                    } else {
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                }
            });
        }
    }*/

}
