package mx.itesm.cagm.meyaj;

import java.io.Serializable;

public class Servicio implements Serializable {
    String servicio,precio,descripcion,duracion;

    public Servicio(String servicio, String precio, String descripcion, String duracion) {
        this.servicio = servicio;
        this.precio = precio;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

}
