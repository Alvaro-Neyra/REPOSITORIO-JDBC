package org.example.ACTIVIDAD_INTEGRADORA.servicios;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import org.example.ACTIVIDAD_INTEGRADORA.persistencia.CasaDAO;
import org.example.ACTIVIDAD_INTEGRADORA.entidades.Casa;

public class CasaService {
    private CasaDAO casaDAO;

    public CasaService() {
        casaDAO = new CasaDAO();
    }

    public Casa crearNuevaCasa(Casa casa) throws Exception {
        verificarCasa(casa);
        if (casaRepetida(casa.getIdCasa())) {
            System.out.println("Casa ya existe");
            return buscarCasa(casa.getIdCasa()).getFirst();
        }
        casaDAO.guardarCasa(casa);
        return casa;
    }

    public List<Casa> buscarCasa(int idCasa) throws Exception {
        verificarCodigo(idCasa);
        List<Casa> casas = new ArrayList<>();
        try {
            casas = casaDAO.buscarCasa(idCasa);
            if (casas.isEmpty()) {
                System.out.println("Casa no encontrada");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return casas;
    }

    public List<Casa> listarCasas() throws Exception {
        List<Casa> casas = new ArrayList<>();
        try {
            casas = casaDAO.listarCasas();
            if (casas.isEmpty()) {
                System.out.println("No hay casas existentes");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return casas;
    }

    public List<Casa> listarCasasPorAgosto() throws Exception {
        List<Casa> casas = new ArrayList<>();
        try {
            casas = casaDAO.listarCasasPorAgostoEnReinoUnido();
            if (casas.isEmpty()) {
                System.out.println("No hay casas existentes");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return casas;
    }

    public List<Casa> listarCasaPorPeriodo(String fecha_desde, int tiempo_minimo) throws Exception {
        List<Casa> casas = new ArrayList<>();
        try {
            casas = casaDAO.listarCasasPorPeriodo(fecha_desde, tiempo_minimo);
            if (casas.isEmpty()) {
                System.out.println("No hay casas existentes");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return casas;
    }

    public List<Casa> incrementarPrecioPorDia(double porcentaje) throws Exception {
        List<Casa> casasActualizadas = new ArrayList<>();
        try {
            casaDAO.incrementarPrecioPorDia(porcentaje);
            casasActualizadas = casaDAO.listarCasas();
        } catch (Exception e) {
            throw new Exception("Error al incrementar el precio por dia: " + e.getMessage());
        }
        return casasActualizadas;
    }

    private boolean casaRepetida(int idCasa) throws Exception {
        List<Casa> casasEncontradas = buscarCasa(idCasa);
        return !casasEncontradas.isEmpty();
    }
    private void verificarCodigo(int codigo) throws Exception {
        if (codigo < 0) {
            throw new Exception("Codigo invalido");
        }
    }

    private void verificarCasa(Casa casa) throws Exception {
        if (casa.getIdCasa() < 0) {
            throw new Exception("Id de la casa no es válido.");
        }

        if (casa.getCalle() == null || casa.getCalle().isEmpty()) {
            throw new Exception("Calle no es válida.");
        }

        if (casa.getNumero() < 0) {
            throw new Exception("Número de la casa no es válido.");
        }

        if (casa.getCodigoPostal() == null || casa.getCodigoPostal().isEmpty()) {
            throw new Exception("Código Postal no es válido.");
        }

        if (casa.getCiudad() == null || casa.getCiudad().isEmpty()) {
            throw new Exception("Ciudad no es válida.");
        }

        if (casa.getPais() == null || casa.getPais().isEmpty()) {
            throw new Exception("País no es válido.");
        }

        Date fechaDesde = casa.getFechaDesde();
        Date fechaHasta = casa.getFechaHasta();

        if (fechaDesde == null) {
            throw new Exception("Fecha Desde no puede ser nula.");
        }

        if (fechaHasta == null) {
            throw new Exception("Fecha Hasta no puede ser nula.");
        }

        if (fechaHasta.before(fechaDesde)) {
            throw new Exception("Fecha Hasta debe ser posterior a Fecha Desde.");
        }

        if (casa.getTiempoMinimo() < 0) {
            throw new Exception("El tiempo mínimo no puede ser negativo.");
        }

        if (casa.getTiempoMaximo() <= 0 || casa.getTiempoMaximo() < casa.getTiempoMinimo()) {
            throw new Exception("El tiempo máximo no es válido.");
        }

        if (casa.getPrecioHabitacion() <= 0) {
            throw new Exception("El precio de la habitación debe ser mayor que 0.");
        }

        if (casa.getTipoVivienda() == null || casa.getTipoVivienda().isEmpty()) {
            throw new Exception("El tipo de vivienda no es válido.");
        }
    }
}
