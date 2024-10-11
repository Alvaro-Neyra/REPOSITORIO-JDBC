package org.example.ACTIVIDAD_INTEGRADORA.servicios;

import org.example.ACTIVIDAD_INTEGRADORA.persistencia.FamiliaDAO;
import org.example.ACTIVIDAD_INTEGRADORA.entidades.Familia;

import java.util.ArrayList;
import java.util.List;

public class FamiliaService {
    private FamiliaDAO familiaDAO;
    public FamiliaService() {
        this.familiaDAO = new FamiliaDAO();
    }

    public Familia agregarFamilia(Familia familia) throws Exception {
        verificarFamilia(familia);
        if (verificarFamiliaDuplicado(familia.getIdFamilia())) {
            System.out.println("La familia ya existe");
            return buscarFamilia(familia.getIdFamilia()).getFirst();
        }
        familiaDAO.guardarFamilia(familia);
        return familia;
    }

    public List<Familia> buscarFamilia(int idFamilia) throws Exception{
        verificarCodigo(idFamilia);
        List<Familia> familiasBuscadas = new ArrayList<>();
        try {
            familiasBuscadas = familiaDAO.buscarFamilia(idFamilia);
            if (familiasBuscadas.isEmpty()) {
                System.out.println("Familia(s) no encontrada(s)");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return familiasBuscadas;
    }

    public List<Familia> listarFamilias() throws Exception {
        List<Familia> familias = new ArrayList<>();
        try {
            familias = familiaDAO.listarFamilias();
            if (familias.isEmpty()) {
                System.out.println("No hay familias existentes");
            }
        } catch (Exception e) {
            throw new Exception("Error al listar familias: " + e.getMessage());
        }
        return familias;
    }

    private boolean verificarFamiliaDuplicado(int idFamilia) throws Exception{
        List<Familia> familiasEncontradas = buscarFamilia(idFamilia);
        return !familiasEncontradas.isEmpty();
    }

    private void verificarCodigo(int codigo) throws Exception {
        if (codigo < 0) {
            throw new Exception("El id del comentario no puede ser negativo");
        }
    }

    public void verificarFamilia(Familia familia) throws Exception {
        if (familia == null) {
            throw new Exception("La familia no puede ser nula.");
        }
        if (familia.getNombre() == null || familia.getNombre().isEmpty()) {
            throw new Exception("El nombre de la familia no puede ser nulo o vacío.");
        }
        if (familia.getIdFamilia() < 0) {
            throw new Exception("El id de familia no peude ser negativo");
        }
        if (familia.getEdadMinima() < 0) {
            throw new Exception("Edad minima invalida");
        }
        if (familia.getEdadMaxima() < 0) {
            throw new Exception("Edad maxima invalida");
        }
        if (familia.getNumHijos() < 0) {
            throw new Exception("Numero de hijos invalida");
        }
        if (familia.getEmail().isEmpty() || familia.getEmail().equals("")) {
            throw new Exception("Email invalido!");
        }
    }
}
