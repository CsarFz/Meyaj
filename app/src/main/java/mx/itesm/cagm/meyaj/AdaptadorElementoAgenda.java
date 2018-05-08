package mx.itesm.cagm.meyaj;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorElementoAgenda extends RecyclerView.Adapter<AdaptadorElementoAgenda.EAViewHOlder> {

    ArrayList<String[]> datosAgenda;

    public AdaptadorElementoAgenda(ArrayList<String[]> datosAgenda) {
        this.datosAgenda = datosAgenda;

    }

    @Override
    public AdaptadorElementoAgenda.EAViewHOlder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_servicioactivo_agenda,parent,false);
        AdaptadorElementoAgenda.EAViewHOlder holder = new AdaptadorElementoAgenda.EAViewHOlder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(AdaptadorElementoAgenda.EAViewHOlder holder, int position) {
        holder.servicio.setText(datosAgenda.get(position)[0]);
        holder.profesionista.setText(datosAgenda.get(position)[3]);
        holder.fecha.setText(datosAgenda.get(position)[0]);
        holder.hora.setText(datosAgenda.get(position)[1]+datosAgenda.get(position)[2]);

    }

    @Override
    public int getItemCount() {
        return datosAgenda.size();
    }

    public static class EAViewHOlder extends RecyclerView.ViewHolder{
        TextView servicio,profesionista,fecha,hora,total;
        ServiceClickListener serviceClickListener;

        public EAViewHOlder(View itemView) {
            super(itemView);
            servicio = itemView.findViewById(R.id.tvServicioA);
            fecha = itemView.findViewById(R.id.tvFechaA);
            profesionista = itemView.findViewById(R.id.tvProfesionistaA);
            hora = itemView.findViewById(R.id.tvHoraA);
            total = itemView.findViewById(R.id.tvTotalA);
        }

        public void setServiceClickListener(ServiceClickListener sc){
            this.serviceClickListener=sc;
        }

    }
}
