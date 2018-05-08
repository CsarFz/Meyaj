package mx.itesm.cagm.meyaj;

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
    ArrayList<Integer> totalus= new ArrayList<>();
    int total = 0;

    public AdaptadorServicio(ArrayList<Servicio> servicios) {
        this.servicios = servicios;

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
        holder.duracion.setText(servicios.get(position).getDuracion()+" min");
        holder.descripcion.setText(servicios.get(position).getDescripcion());
        holder.precio.setText("$ "+servicios.get(position).getPrecio());
        holder.setServiceClickListener(new ServiceClickListener() {
            @Override
            public void onServiceClick(View v, int pos) {
                CheckBox cb = (CheckBox) v;
                if(cb.isChecked()){
                    checkedServices.add(servicios.get(pos));
                    totalus.add(Integer.valueOf(servicios.get(pos).getPrecio()));
                }else if(!cb.isChecked()){
                    checkedServices.remove(servicios.get(pos));
                    totalus.remove(Integer.valueOf(servicios.get(pos).getPrecio()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicios.size();
    }


    public int getTotal(){ return total; }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public ArrayList<Servicio> getCheckedServices() {
        return checkedServices;
    }

    public ArrayList<Integer> getTotalus() {
        return totalus;
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
