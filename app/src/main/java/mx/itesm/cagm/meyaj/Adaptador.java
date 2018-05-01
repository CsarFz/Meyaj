package mx.itesm.cagm.meyaj;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.Rating;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.PViewHOlder>{

    List<String[]> datos;
    int[] datosImg;

    public Adaptador(List<String[]> profesionistas, int[] imagenes) {
        this.datos = profesionistas;
        this.datosImg = imagenes;
    }


    @Override
    public PViewHOlder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_resultados,parent,false);
        PViewHOlder holder = new PViewHOlder(v);
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

    /*
        private static LayoutInflater inflater = null;

        Context contexto;
        List<String[]> datos;
        int[] datosImg;

        public Adaptador(Context conexto, List<String[]> datos, int[] datosImg) {
            this.contexto = conexto;
            this.datos = datos;
            this.datosImg = datosImg;
            inflater = (LayoutInflater)contexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
        }



        @Override
        public int getCount() {
            return datosImg.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            final View vista = inflater.inflate(R.layout.elemento_lista_resultados,null);

            TextView nombre = vista.findViewById(R.id.tvNombre);
            TextView profesion = vista.findViewById(R.id.tvProfesion);
            TextView distancia = vista.findViewById(R.id.tvDistancia);
            TextView votos = vista.findViewById(R.id.tvVotos);
            ImageView imagen = vista.findViewById(R.id.ivPerfil);
            RatingBar calificacion = vista.findViewById(R.id.ratingBar);
            nombre.setText(datos.get(i)[0]);
            profesion.setText(datos.get(i)[1]);
            distancia.setText(datos.get(i)[2]+" KM");
            imagen.setImageResource(datosImg[i]);
            calificacion.setProgress(Integer.valueOf(datos.get(i)[3]));
            votos.setText("("+datos.get(i)[4]+")");
            imagen.setTag(i);


            imagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                   Intent visorImagen = new Intent(contexto, VisorImagen.class);
                    visorImagen.putExtra("IMG",datosImg[(Integer)v.getTag()]);
                    contexto.startActivity(visorImagen);
                }
            });


            return vista;
        }
        */
    public static class PViewHOlder extends RecyclerView.ViewHolder{
        TextView nombre,profesion,distancia,votos;
        ImageView imagen;
        RatingBar calificacion;

        public PViewHOlder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombre);
            profesion = itemView.findViewById(R.id.tvProfesion);
            distancia = itemView.findViewById(R.id.tvDistancia);
            votos = itemView.findViewById(R.id.tvVotos);
            imagen = itemView.findViewById(R.id.ivPerfil);
            calificacion = itemView.findViewById(R.id.ratingBar);

        }
    }

}
