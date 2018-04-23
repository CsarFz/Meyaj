package mx.itesm.cagm.meyaj;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class SplashActiv extends AppCompatActivity
{
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        iv = findViewById(R.id.ivLogo);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.transition);

        iv.startAnimation(animation);
        final Intent intMenuPrincipal = new Intent(this, MenuPrincipalActiv.class);
        Thread timer = new Thread(){
            public void run() {
                try {
                    sleep(2000);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intMenuPrincipal);
                    finish();
                }
            }
        };
        timer.start();
    }
}
