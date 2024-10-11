package org.example.ACTIVIDAD_INTEGRADORA;

import org.example.ACTIVIDAD_INTEGRADORA.entidades.Casa;
import org.example.ACTIVIDAD_INTEGRADORA.entidades.Cliente;
import org.example.ACTIVIDAD_INTEGRADORA.entidades.Comentario;
import org.example.ACTIVIDAD_INTEGRADORA.entidades.Familia;
import org.example.ACTIVIDAD_INTEGRADORA.servicios.*;

import java.sql.Date;
import java.util.List;

public class MainProbarServicios {
    public static void main(String[] args) {
        try {
            // Servicios
            CasaService casaService = new CasaService();
            ClienteService clienteService = new ClienteService();
            ComentarioService comentarioService = new ComentarioService();
            FamiliaService familiaService = new FamiliaService();

            // CASAS
            System.out.println("=== CASAS ===");
            Casa casa1 = new Casa();
            casa1.setCalle("Avenida Flores");
            casa1.setNumero(101);
            casa1.setCodigoPostal("56789");
            casa1.setCiudad("Monterrey");
            casa1.setPais("México");
            casa1.setFechaDesde(Date.valueOf("2024-01-01"));
            casa1.setFechaHasta(Date.valueOf("2024-12-31"));
            casa1.setTiempoMinimo(3);
            casa1.setTiempoMaximo(30);
            casa1.setPrecioHabitacion(1500.00);
            casa1.setTipoVivienda("Casa de campo");
            casaService.crearNuevaCasa(casa1);

            List<Casa> casasListadas = casaService.listarCasas();
            System.out.println("Casas listadas: " + casasListadas.size());

            Casa casaBuscada = casaService.buscarCasa(1).get(0);
            System.out.println("Casa encontrada: " + casaBuscada.getCalle());

            // CLIENTES
            System.out.println("\n=== CLIENTES ===");
            Cliente cliente1 = new Cliente(1, "Carlos Pérez", "Avenida Siempreviva", 742, "12345", "Springfield", "EE.UU.", "carlos@example.com");
            clienteService.agregarCliente(cliente1);

            List<Cliente> clientesListados = clienteService.listarClientes();
            System.out.println("Clientes listados: " + clientesListados.size());

            Cliente clienteBuscado = clienteService.buscarCliente(1).get(0);
            System.out.println("Cliente encontrado: " + clienteBuscado.getNombre());

            // COMENTARIOS
            System.out.println("\n=== COMENTARIOS ===");
            Comentario nuevoComentario = new Comentario(1, 1, "Buena casa, excelente ubicación.");
            comentarioService.guardarComentario(nuevoComentario);

            List<Comentario> comentariosListados = comentarioService.listarComentarios();
            System.out.println("Comentarios listados: " + comentariosListados.size());

            Comentario comentarioBuscado = comentarioService.buscarComentario(1).get(0); // Buscar comentario por ID
            System.out.println("Comentario encontrado: " + comentarioBuscado.getComentario());

            // FAMILIAS
            System.out.println("\n=== FAMILIAS ===");
            Familia familia1 = new Familia(1, "Familia García", 30, 40, 2, "garcia@gmail.com", 101);
            familiaService.agregarFamilia(familia1);

            List<Familia> familiasListadas = familiaService.listarFamilias();
            System.out.println("Familias listadas: " + familiasListadas.size());

            Familia familiaBuscada = familiaService.buscarFamilia(1).get(0);
            System.out.println("Familia encontrada: " + familiaBuscada.getNombre());

        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
