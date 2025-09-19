package app;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main{
    static ArrayList<Producto> productos = new ArrayList<>();
    static HashMap<String, Integer> stocks = new HashMap<>();
    static double precioTotal = 0;
    public static void main(String[] args){
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
//                    agregarProducto();
                    break;
                case "2":
//                    listarInventario();
                    break;
                case "3":
//                    comprarProducto();
                    break;
                case "4":
//                    mostrarEstadisticas();
                    break;
                case "5":
//                    buscarProducto();
                    break;
                case "6":
//                    ticketFinal();
                    exit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida", "Error", JOptionPane.WARNING_MESSAGE);
                    break;
            }
        }
    }
}