package org.example.PATRON_DISENO_DAO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);

    public void iniciar() {
        int opcion = -1;
        do {
            menuGeneral();
            opcion = pedirOpcion(1, 5, "Ingresa una opcion: ");
            elegirServicio(opcion);
        } while (opcion != 5);
    }

    private void menuGeneral() {
        System.out.println("\nMENU: Que deseas gestionar?");
        System.out.println("1. Clientes");
        System.out.println("2. Productos");
        System.out.println("3. Pedidos");
        System.out.println("4. Oficinas");
        System.out.println("5. Salir");
    }
    private void menuClientes() {
        System.out.println("\nMENU DE CLIENTES:");
        System.out.println("1. Crear nuevo cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Listar Clientes por codigo");
        System.out.println("4. Eliminar Cliente");
        System.out.println("5. Listar Clientes por ID empleado");
        System.out.println("6. Incrementar por un 15% el limite de credito");
        System.out.println("7. Salir");
    }
    private void menuProductos() {
        System.out.println("\nMENU DE PRODUCTOS:");
        System.out.println("1. Crear nuevo producto");
        System.out.println("2. Listar Productos");
        System.out.println("3. Listar Productos por codigo");
        System.out.println("4. Eliminar Producto");
        System.out.println("5. Listar Productos por proveedor");
        System.out.println("6. Modificar Producto");
        System.out.println("7. Listar productos con el menor stock");
        System.out.println("8. Listar los productos con menor precio");
        System.out.println("9. Salir");
    }

    private void menuPedidos() {
        System.out.println("\nMENU DE PEDIDOS:");
        System.out.println("1. Crear nuevo pedido");
        System.out.println("2. Listar Pedidos por codigo");
        System.out.println("3. Listar Pedidos por cliente ID");
        System.out.println("4. Listar Pedidos por estado");
        System.out.println("5. Listar Pedidos por producto ID");
        System.out.println("6. Salir");
    }

    private void menuOficinas() {
        System.out.println("\nMENU DE OFICINAS:");
        System.out.println("1. Crear nuevo oficina");
        System.out.println("2. Listar Oficinas por su codigo");
        System.out.println("3. Modificar una oficina");
        System.out.println("4. Salir");
    }

    private int obtenerNumero(String mensaje) {
        int numero = -1;
        boolean error;
        do {
            try {
                error = false;
                System.out.print(mensaje);
                numero = sc.nextInt();
                if (numero < 0) {
                    System.out.println("No se aceptan números negativos");
                    error = true;
                }
            } catch (InputMismatchException e) {
                error = true;
                System.err.println("Valor inválido, solo se aceptan números enteros.");
                sc.nextLine();
            } catch (Exception e) {
                error = true;
                System.err.println("Error al ingresar el dato, intente de nuevo: " + e.getMessage());
                sc.nextLine();
            }
        } while (error);
        return numero;
    }

    private int pedirOpcion(int valorMinimo, int valorMaximo, String mensaje) {
        int opcion = -1;
        try {
            do {
                opcion = obtenerNumero(mensaje);
                if (opcion < valorMinimo || opcion > valorMaximo) {
                    System.out.println("Ingrese un numero valido!");
                }
            } while (opcion < valorMinimo || opcion > valorMaximo);
        } catch (Exception e) {
            System.err.println("Error al ingresar el dato, intente de nuevo: " + e.getMessage());
        }
        return opcion;
    }
    private void elegirServicio(int opcion) {
        int opcionServicio = -1;
        switch (opcion) {
            case 1:
                menuClientes();
                opcionServicio = pedirOpcion(1, 5, "Que deseas gestionar en clientes?: ");
                break;
            case 2:
                menuProductos();
                opcionServicio = pedirOpcion(1, 8, "Que deseas gestionar en productos?: ");
                break;
            case 3:
                menuPedidos();
                opcionServicio = pedirOpcion(1, 6, "Que deseas gestionar en pedidos?: ");
                break;
            case 4:
                menuOficinas();
                opcionServicio = pedirOpcion(1, 4, "Que deseas gestionar en oficinas?: ");
                break;
            case 5:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
}
