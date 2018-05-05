package mx.itesm.cagm.meyaj;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.PViewHOlder> implements View.OnClickListener{

    List<String[]> datos;
    int[] datosImg;
    private View.OnClickListener listener;

    public Adaptador(List<String[]> profesionistas, int[] imagenes) {
        this.datos = profesionistas;
        this.datosImg = imagenes;
    }

    @Override
    public PViewHOlder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_resultados,parent,false);
        PViewHOlder holder = new PViewHOlder(v);

        v.setOnClickListener(this);

        return holder;

    }

    @Override
    public void onBindViewHolder(PViewHOlder holder, int position) {
        holder.nombre.setText(datos.get(position)[0]);
        holder.profesion.setText(datos.get(position)[1]);
        holder.distancia.setText(datos.get(position)[2]+" KM");
        holder.imagen.setImageResource(datosImg[position]);
        holder.calificacion.setProgress(Integer.valueOf(datos.get(position)[3]));
        holder.votos.setText("("+datos.get(position)[4]+")");
        holder.imagen.setTag(position);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }



    public static class PViewHOlder extends RecyclerView.ViewHolder{
        TextView nombre,profesion,distancia,votos;
        ImageView imagen;
        RatingBar calificacion;

        public PViewHOlder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvTelefono);
            profesion = itemView.findViewById(R.id.tvProfesion);
            distancia = itemView.findViewById(R.id.tvDistancia);
            votos = itemView.findViewById(R.id.tvVotos);
            imagen = itemView.findViewById(R.id.ivPerfil);
            calificacion = itemView.findViewById(R.id.ratingBar);

        }
    }

}
