package mx.itesm.cagm.meyaj;

public class UserInformation {

    public String nombre;
    public String apellido;
    public String numeroTelefono;
    //public String direccion;

    public UserInformation(){

    }

    public UserInformation(String nombre, String apellido, String numeroTelefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        //this.direccion = direccion;
    }
}
