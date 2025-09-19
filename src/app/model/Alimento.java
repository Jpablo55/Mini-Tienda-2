package app.model;

public class Alimento extends Producto{

    public Alimento(double precio, String nombre) {
        super(precio, nombre);
    }

    @Override
    public String getDescription() {
        return "Alimento: " + getNombre() + "\n" +
                "Precio: $" + getPrecio() + "\n";
    }
}
