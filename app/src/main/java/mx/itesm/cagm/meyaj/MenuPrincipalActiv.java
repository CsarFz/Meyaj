package mx.itesm.cagm.meyaj;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MenuPrincipalActiv extends AppCompatActivity {

    private boolean estaEnBuscar; // Para saber que fragmento mostrar
    private boolean estaEnAgenda;
    private boolean estaEnPerfil;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navBuscar:
                    if (!estaEnBuscar) {   // Si no está mostrando el fragmento, lo muestra
                        BuscarFrag fragCaptura = new BuscarFrag();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frContenedor, fragCaptura);
                        ft.commit();
                        estaEnBuscar = true;
                    }
                    return true;
                case R.id.navAgenda:
                    if (estaEnBuscar) {   // Si no está mostrando el fragmento, lo muestra
                        AgendaFrag fragCaptura = new AgendaFrag();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frContenedor, fragCaptura);
                        ft.commit();
                        estaEnBuscar = false;
                    }
                    return true;
                case R.id.navPerfil:
                    if (estaEnBuscar) {   // Si no está mostrando el fragmento, lo muestra
                        PerfilFrag fragCaptura = new PerfilFrag();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frContenedor, fragCaptura);
                        ft.commit();
                        estaEnBuscar = false;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Fragmento inicial (Buscar servicios)
        BuscarFrag fragBuscar = new BuscarFrag();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frContenedor, fragBuscar);
        ft.commit();
        estaEnBuscar = true;
    }
}
