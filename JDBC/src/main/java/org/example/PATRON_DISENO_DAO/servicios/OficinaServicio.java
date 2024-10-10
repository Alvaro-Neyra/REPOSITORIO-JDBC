package org.example.PATRON_DISENO_DAO.servicios;

import org.example.PATRON_DISENO_DAO.entidades.Oficina;
import org.example.PATRON_DISENO_DAO.persistencia.OficinaDAO;
import java.util.List;


public class OficinaServicio {
    private OficinaDAO oficinaDAO;
    public OficinaServicio() {
        oficinaDAO = new OficinaDAO();
    }
    public Oficina agregarOficina(Oficina oficina) throws Exception {
        verificarOficina(oficina);
        if (verificarOficinaRepetida(oficina.getCodigoOficina())) {
            System.out.println("El oficina ya existe!");
            return buscarOficinaPorCodigo(oficina.getCodigoOficina()).getFirst();
        }
        oficinaDAO.agregarOficina(oficina);
        return oficina;
    }

    public List<Oficina> buscarOficinaPorCodigo(String codigoOficina) throws Exception{
        verificarCodigoOficina(codigoOficina);
        List<Oficina> oficinasPorCodigo = null;
        try {
            oficinasPorCodigo = oficinaDAO.buscarOficinaPorCodigo(codigoOficina);
            if (oficinasPorCodigo.isEmpty()) {
                System.out.println("No oficinas encontradas");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oficinasPorCodigo;
    }

    public void modificarOficina(Oficina oficina) throws Exception {
        verificarOficina(oficina);
        if (!verificarOficinaRepetida(oficina.getCodigoOficina())) {
            throw new Exception("La oficina no existe no se puede modificar");
        }
        oficinaDAO.modificarOficina(oficina);
    }

    private boolean verificarOficinaRepetida(String codigoOficina) throws Exception {
        List<Oficina> oficinas = buscarOficinaPorCodigo(codigoOficina);
        return !oficinas.isEmpty(); // Si es true existe oficinas con ese codigo especificado
    }

    private void verificarCodigoOficina(String codigoOficina) throws Exception {
        if (codigoOficina == null || codigoOficina.trim().equals("")) {
            throw new Exception("El codigo de la oficina no puede ser nulo");
        }
    }

    private void verificarOficina(Oficina oficina) throws Exception {
        if (oficina == null) {
            throw new Exception("Oficina no puede ser nula");
        }

        if (oficina.getCodigoOficina() == null || oficina.getCodigoOficina().trim().isEmpty()) {
            throw new Exception("El código de la oficina no puede estar vacío");
        }
        if (oficina.getCiudad() == null || oficina.getCiudad().trim().isEmpty()) {
            throw new Exception("La ciudad no puede estar vacía");
        }
        if (oficina.getPais() == null || oficina.getPais().trim().isEmpty()) {
            throw new Exception("El país no puede estar vacío");
        }
        if (oficina.getRegion() == null || oficina.getRegion().trim().isEmpty()) {
            throw new Exception("La región no puede estar vacía");
        }
        if (oficina.getCodigoPostal() == null || oficina.getCodigoPostal().trim().isEmpty()) {
            throw new Exception("El código postal no puede estar vacío");
        }
        if (oficina.getTelefono() == null || oficina.getTelefono().trim().isEmpty()) {
            throw new Exception("El teléfono no puede estar vacío");
        }

        if (oficina.getIdOficina() < 0) {
            throw new Exception("El ID de la oficina no puede ser negativo");
        }
    }

}
