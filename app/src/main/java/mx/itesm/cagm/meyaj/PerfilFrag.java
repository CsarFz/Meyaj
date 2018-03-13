package mx.itesm.cagm.meyaj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFrag extends Fragment
{
    Button btnRegistro;

    public PerfilFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_perfil, container, false);

        // LISTENER DE BOTON REGISTRARSE
        btnRegistro = myView.findViewById(R.id.btnRegistrar);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intRegistro = new Intent(getActivity(), RegistroActiv.class);
                startActivity(intRegistro);
            }
        });

        // Inflate the layout for this fragment
        return myView;
    }
}