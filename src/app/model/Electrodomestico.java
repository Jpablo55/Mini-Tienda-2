package app.model;

public class Electrodomestico extends Producto{
    public Electrodomestico(double precio, String nombre) {
        super(precio, nombre);
    }

    @Override
    public String getDescription() {
        return "Electrodomestico: " + getNombre() + "\n" +
                "Precio: $" + getPrecio() + "\n";
    }
}
