package mx.itesm.cagm.meyaj;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    // Arrays
    public  int[] slide_images = {
        R.drawable.carpintero,
        R.drawable.electricista,
        R.drawable.plomero
    };

    public  String[] slide_headings = {
        "BUSCA UN SERVICIO",
        "PIDE UN SERVICIO",
        "O REGISTRATE COMO PROFESIONISTA"
    };

    public String[] slide_desc = {
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse rhoncus hendrerit auctor. Nullam quis pharetra arcu.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse rhoncus hendrerit auctor. Nullam quis pharetra arcu.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse rhoncus hendrerit auctor. Nullam quis pharetra arcu."
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = v.findViewById(R.id.slide_image);
        TextView slideHeading = v.findViewById(R.id.slide_heading);
        TextView slideDescription = v.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_desc[position]);

        container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
