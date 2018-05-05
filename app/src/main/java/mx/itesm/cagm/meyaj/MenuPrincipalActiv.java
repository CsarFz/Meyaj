package mx.itesm.cagm.meyaj;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MenuPrincipalActiv extends AppCompatActivity {

    private BottomNavigationView mainNav;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navBuscar:
                    fragment = new BuscarFrag();
                    break;

                case R.id.navAgenda:
                    fragment = new AgendaFrag();
                    break;

                case R.id.navPerfil:

                    fragment = new PerfilFrag();
                    break;
            }
            return loadFragment(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mainNav = findViewById(R.id.navigation);

        loadFragment(new BuscarFrag());

        Log.i("CicloVida", "OnCreate");
    }


    private boolean loadFragment(Fragment fragment) {
        if(fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frContenedor, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
