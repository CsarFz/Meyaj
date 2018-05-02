package mx.itesm.cagm.meyaj;

public class Profesionista
{
    public String nombre;
    public String apellido;
    public String numeroTelefono;
    public String direccion;
    public String profesion;

    public Profesionista(){

    }

    public Profesionista(String nombre, String apellido, String numeroTelefono, String direccion, String profesion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.direccion = direccion;
        this.profesion = profesion;
    }
}
