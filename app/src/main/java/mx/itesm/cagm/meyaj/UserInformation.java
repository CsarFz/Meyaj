package mx.itesm.cagm.meyaj;

public class UserInformation {

    public String nombre;
    public String apellido;
    public String numeroTelefono;

    public UserInformation(){

    }

    public UserInformation(String nombre, String apellido, String numeroTelefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
    }
}
