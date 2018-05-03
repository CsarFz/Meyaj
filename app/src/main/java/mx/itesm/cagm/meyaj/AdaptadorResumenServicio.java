package mx.itesm.cagm.meyaj;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorResumenServicio extends RecyclerView.Adapter<AdaptadorResumenServicio.RSViewHOlder>{

    ArrayList<Servicio> servicios;
    ArrayList<Servicio> checkedServices= new ArrayList<>();

    public AdaptadorResumenServicio(ArrayList<Servicio> servicios) {
        this.servicios = servicios;

    }

    @Override
    public AdaptadorResumenServicio.RSViewHOlder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_resumen_servicio,parent,false);
        AdaptadorResumenServicio.RSViewHOlder holder = new AdaptadorResumenServicio.RSViewHOlder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(AdaptadorResumenServicio.RSViewHOlder holder, int position) {
        holder.servicio.setText(servicios.get(position).getServicio());
        holder.duracion.setText(servicios.get(position).getDuracion());
        holder.precio.setText(servicios.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return servicios.size();
    }


    public static class RSViewHOlder extends RecyclerView.ViewHolder{
        TextView servicio, duracion, precio;

        public RSViewHOlder(View itemView) {
            super(itemView);
            servicio = itemView.findViewById(R.id.tvRServicio);
            precio = itemView.findViewById(R.id.tvRPrecio);
            duracion = itemView.findViewById(R.id.tvRServicio);

        }

    }
}
