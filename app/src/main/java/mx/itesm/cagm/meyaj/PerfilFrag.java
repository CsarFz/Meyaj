package mx.itesm.cagm.meyaj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFrag extends Fragment
{
    //Button btnRegistro;
    Button btnConfigurar;
    TextView tvRegistro;

    public PerfilFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_perfil, container, false);

        tvRegistro = myView.findViewById(R.id.btnRegistrar);
        tvRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intRegistro = new Intent(getActivity(), RegistroActiv.class);
                startActivity(intRegistro);
            }
        });

        // LISTENER DE BOTON REGISTRARSE
        //btnRegistro = myView.findViewById(R.id.btnRegistrar);
        /*btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intRegistro = new Intent(getActivity(), RegistroActiv.class);
                startActivity(intRegistro);
            }
        });/*gis

        // LISTENER DE BOTON CONFIGURAR
        btnConfigurar = myView.findViewById(R.id.btnConfig);
        btnConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intConfigurar = new Intent(getActivity(), ConfigurarActiv.class);
                startActivity(intConfigurar);
            }
        });*/

        // Inflate the layout for this fragment
        return myView;
    }
}