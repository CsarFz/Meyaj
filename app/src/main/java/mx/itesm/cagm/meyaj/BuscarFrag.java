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
    CardView cdBuscar;

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
        cdBuscar = myView.findViewById(R.id.cdBuscar);
        cdBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intBuscador = new Intent(getActivity(), BuscarServicioActiv.class);
                startActivity(intBuscador);
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
