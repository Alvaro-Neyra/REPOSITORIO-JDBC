package org.example.ACTIVIDAD_INTEGRADORA.servicios;

import org.example.ACTIVIDAD_INTEGRADORA.entidades.Comentario;
import org.example.ACTIVIDAD_INTEGRADORA.persistencia.ComentarioDAO;
import org.example.PATRON_DISENO_DAO.entidades.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ComentarioService {
    private ComentarioDAO comentarioDAO;
    public ComentarioService() {
        comentarioDAO = new ComentarioDAO();
    }

    public Comentario guardarComentario(Comentario comentario) throws Exception{
        verificarComentario(comentario);
        if (verificarComentarioDuplicado(comentario.getIdComentario())) {
            System.out.println("Comentario ya existente!");
            return buscarComentario(comentario.getIdComentario()).getFirst();
        }
        comentarioDAO.guardarComentario(comentario);
        return comentario;
    }

    public List<Comentario> buscarComentario(int idComentario) throws Exception {
        verificarCodigo(idComentario);
        List<Comentario> comentariosBuscados = new ArrayList<>();
        try {
            comentariosBuscados = comentarioDAO.buscarComentario(idComentario);
            if (comentariosBuscados.isEmpty()) {
                System.out.println("Comentario(s) no encontrado(s)");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return comentariosBuscados;
    }

    public List<Comentario> listarComentarios() throws Exception {
        List<Comentario> comentarios = new ArrayList<>();
        try {
            comentarios = comentarioDAO.listarComentarios();
            if (comentarios.isEmpty()) {
                System.out.println("No hay comentarios existentes");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return comentarios;
    }

    private boolean verificarComentarioDuplicado(int idComentario) throws Exception{
        List<Comentario> comentariosEncontrados = buscarComentario(idComentario);
        return !comentariosEncontrados.isEmpty();
    }

    private void verificarCodigo(int codigo) throws Exception {
        if (codigo < 0) {
            throw new Exception("El id del comentario no puede ser negativo");
        }
    }

    private void verificarComentario(Comentario comentario) throws Exception{
        if (comentario.getIdComentario() < 0) {
            throw new Exception("El id del comentario no puede ser negativo");
        }

        if (comentario.getIdCasa() < 0) {
            throw new Exception("El id de la casa no puede ser negativo");
        }

        if (comentario.getComentario().isEmpty() || comentario.getComentario() == null) {
            throw new Exception("El comentario no puede ser vacio");
        }
    }
}
