package app.service;

import app.interfaces.IProducto;
import app.model.Alimento;
import app.model.Electrodomestico;
import app.model.Producto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ProductoService  implements IProducto {

    static ArrayList<Producto> productos = new ArrayList<>();
    static double[] precios = new double[0];
    static HashMap<String, Integer> stocks = new HashMap<>();
    static double precioTotal = 0;

    public  void agregarProducto() {
        int longitudPrecios = precios.length;
        String[] opc = {"Alimento", "Electrodoméstico"};
        int tipoProducto = JOptionPane.showOptionDialog(null,"Seleccione el tipo de producto que deseas agregar","Selección tipo",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, opc, opc[0]);
        if (tipoProducto == -1) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
        }
        try {
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto", "Nombre", JOptionPane.PLAIN_MESSAGE);
            if (nombre == null || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "'Nombre' no puede estar vacío", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            nombre = nombre.toLowerCase();
            for (Producto producto : productos){
                if (producto.getNombre().contains(nombre)) {
                    JOptionPane.showMessageDialog(null, "Este producto ya existe", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }


            double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el precio del producto", "Precio", JOptionPane.PLAIN_MESSAGE));

            int stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el stock del producto", "Stock", JOptionPane.PLAIN_MESSAGE));

            double[] nuevosPrecios = new double[longitudPrecios + 1];
            for (int i = 0; i < longitudPrecios; i++) {
                nuevosPrecios[i] = precios[i];
            }
            precios = nuevosPrecios;
            precios[longitudPrecios] = precio;
            Producto nuevoProducto = (tipoProducto == 0) ? new Alimento(precio, nombre ) : new Electrodomestico(precio, nombre);
            productos.add(nuevoProducto);
            stocks.put(nombre, stock);

            JOptionPane.showMessageDialog(null, "Producto agregado con éxito");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Precio o stock inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public  void listarInventario() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos en el inventario.");
            return;
        }

        StringBuilder sb = new StringBuilder("Inventario:\n");
        for (int i = 0; i < productos.size(); i++) {
            String nombre = productos.get(i).getNombre();
            double precio = precios[i];
            int stock = stocks.get(nombre);
            sb.append(String.format("%d. %s - $%.2f - Stock: %d\n", i + 1, nombre, precio, stock));
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public  void comprarProducto() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos disponibles para comprar.");
            return;
        }

        String producto = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto a comprar:");
        if (producto == null || producto.isEmpty()) return;

        producto = producto.toLowerCase();

        int index = -1;
        for(Producto producto1 : productos){
            if (Objects.equals(producto1.getNombre(), producto)){
                index = producto1.getNombre().indexOf(producto);
            }
        }
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            return;
        }

        int stockActual = stocks.get(producto);
        String cantidadStr = JOptionPane.showInputDialog(null, "Ingrese la cantidad a comprar:");
        try {
            int cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0.");
                return;
            }
            if (cantidad > stockActual) {
                JOptionPane.showMessageDialog(null, "Stock insuficiente. Disponible: " + stockActual);
                return;
            }

            double subtotal = precios[index] * cantidad;
            precioTotal += subtotal;
            stocks.put(producto, stockActual - cantidad);

            JOptionPane.showMessageDialog(null, "Compra realizada.\nProducto: " + producto +
                    "\nCantidad: " + cantidad +
                    "\nSubtotal: $" + String.format("%.2f", subtotal));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public  void mostrarEstadisticas() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos para estadísticas.");
            return;
        }

        double min = precios[0];
        double max = precios[0];
        int minIndex = 0;
        int maxIndex = 0;

        for (int i = 1; i < precios.length; i++) {
            if (precios[i] < min) {
                min = precios[i];
                minIndex = i;
            }
            if (precios[i] > max) {
                max = precios[i];
                maxIndex = i;
            }
        }

        JOptionPane.showMessageDialog(null, String.format(
                "Producto más barato: %s ($%.2f)\nProducto más caro: %s ($%.2f)",
                productos.get(minIndex).getNombre(), min, productos.get(maxIndex).getNombre(), max
        ));
    }

    public void buscarProducto() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos para buscar.");
            return;
        }

        String query = JOptionPane.showInputDialog(null, "Ingrese el nombre o parte del nombre a buscar:");
        if (query == null || query.isEmpty()) return;

        query = query.toLowerCase();
        StringBuilder sb = new StringBuilder("Resultados de búsqueda:\n");
        boolean found = false;

        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getNombre().contains(query)) {
                found = true;
                sb.append(String.format("%s - $%.2f - Stock: %d\n",
                        productos.get(i).getNombre(), precios[i], stocks.get(productos.get(i).getNombre())));
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
        } else {
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    public void ticketFinal() {
        JOptionPane.showMessageDialog(null, "Gracias por su compra.\nTotal acumulado: $" +
                String.format("%.2f", precioTotal));
    }
}

