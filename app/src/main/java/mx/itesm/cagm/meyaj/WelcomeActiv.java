package mx.itesm.cagm.meyaj;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActiv extends AppCompatActivity {

    private ViewPager slideViewPager;
    private LinearLayout dotLayout;

    private TextView[] dots;

    private SliderAdapter sliderAdapter;

    private Button nextBtn;
    private Button backBtn;
    private Button skipBtn;
    private int currentPage;


    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        slideViewPager = findViewById(R.id.slideViewPager);
        dotLayout = findViewById(R.id.dotsLayout);
        nextBtn = findViewById(R.id.nextBtn);
        backBtn = findViewById(R.id.prevBtn);
        skipBtn = findViewById(R.id.skipBtn);

        sliderAdapter = new SliderAdapter(this);

        slideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        slideViewPager.addOnPageChangeListener(viewListener);


        //Checar que no exista sesion iniciada, si si entonces se salta esta pantalla
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        if(user!=null){
            Intent intMenuPrincipal = new Intent(WelcomeActiv.this, MenuPrincipalActiv.class);
            startActivity(intMenuPrincipal);
        }




        // OnClickListener
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(currentPage + 1);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(currentPage - 1);
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intMenuPrincipal = new Intent(WelcomeActiv.this, MenuPrincipalActiv.class);
                startActivity(intMenuPrincipal);
            }
        });
    }

    public void addDotsIndicator(int position) {
        dots = new TextView[2];
        dotLayout.removeAllViews();

        for(int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorWhiteTransparent));

            dotLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorIcons));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currentPage = i;
            if(i == 0) {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(false);
                backBtn.setVisibility(View.INVISIBLE);

                nextBtn.setText("SIGUIENTE");
                backBtn.setText("");

            } else if(i == dots.length - 1) {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);

                nextBtn.setText("");
                backBtn.setText("REGRESAR");

            } else {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);

                nextBtn.setText("SIGUIENTE");
                backBtn.setText("REGRESAR");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
