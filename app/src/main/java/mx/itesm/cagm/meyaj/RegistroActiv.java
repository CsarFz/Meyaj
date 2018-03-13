package mx.itesm.cagm.meyaj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistroActiv extends AppCompatActivity {

    Button btnCancelar;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void cancelar(View v){
        super.onBackPressed();
    }

    public void mostrarPerfil(View v){
        super.onBackPressed();
    }
}
