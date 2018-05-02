package mx.itesm.cagm.meyaj;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorServicio extends RecyclerView.Adapter<AdaptadorServicio.DSViewHOlder>{

    ArrayList<Servicio> servicios;
    ArrayList<Servicio> checkedServices= new ArrayList<>();
    Context c;
    private View.OnClickListener listener;

    public AdaptadorServicio(Context c, ArrayList<Servicio> servicios) {
        this.servicios = servicios;
        this.c=c;
    }

    @Override
    public DSViewHOlder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_detalle_servicio,parent,false);
        AdaptadorServicio.DSViewHOlder holder = new AdaptadorServicio.DSViewHOlder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(DSViewHOlder holder, int position) {
        holder.servicio.setText(servicios.get(position).getServicio());
        holder.duracion.setText(servicios.get(position).getDuracion());
        holder.descripcion.setText(servicios.get(position).getDescripcion());
        holder.precio.setText(servicios.get(position).getPrecio());
        holder.setServiceClickListener(new ServiceClickListener() {
            @Override
            public void onServiceClick(View v, int pos) {
                CheckBox cb = (CheckBox) v;
                if(cb.isChecked()){
                    System.out.println("AÑADI ALGO");
                    checkedServices.add(servicios.get(pos));
                }else if(!cb.isChecked()){
                    checkedServices.remove(servicios.get(pos));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicios.size();
    }


    public static class DSViewHOlder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView servicio,precio,descripcion,duracion;
        CheckBox cb;

        ServiceClickListener serviceClickListener;

        public DSViewHOlder(View itemView) {
            super(itemView);
            servicio = itemView.findViewById(R.id.tvServicio);
            precio = itemView.findViewById(R.id.tvPrecio);
            descripcion = itemView.findViewById(R.id.tvDescripcion);
            duracion = itemView.findViewById(R.id.tvDuracion);
            cb = itemView.findViewById(R.id.checkBoxServicio);

            cb.setOnClickListener(this);

        }

        public void setServiceClickListener(ServiceClickListener sc){
            this.serviceClickListener=sc;
        }

        @Override
        public void onClick(View view) {
            this.serviceClickListener.onServiceClick(view,getLayoutPosition());
        }
    }
}
