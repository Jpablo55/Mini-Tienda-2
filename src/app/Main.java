package app;

import app.model.Producto;
import app.service.ProductoService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main{

    public static void main(String[] args){
        ProductoService service = new ProductoService();
        boolean exit = false;
        while (!exit) {
            String opcMenu = JOptionPane.showInputDialog(null,
                    """
                            1. Agregar producto.
                            2. Listar inventario.
                            3. Comprar producto.
                            4. Mostrar estadísticas.
                            5. Buscar producto por nombre.
                            6. Salir con ticket final.
                            """,
                    "Mini tienda",
                    JOptionPane.PLAIN_MESSAGE
            );
            if (opcMenu == null) {
                exit = true;
                continue;
            }
            switch (opcMenu) {
                case "1":
                    service.agregarProducto();
                    break;
                case "2":
                    service.listarInventario();
                    break;
                case "3":
                    service.comprarProducto();
                    break;
                case "4":
                    service.mostrarEstadisticas();
                    break;
                case "5":
                    service.buscarProducto();
                    break;
                case "6":
                    service.ticketFinal();
                    exit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida", "Error", JOptionPane.WARNING_MESSAGE);
                    break;
            }
        }
    }
}