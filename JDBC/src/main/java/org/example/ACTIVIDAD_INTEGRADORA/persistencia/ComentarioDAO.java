package org.example.ACTIVIDAD_INTEGRADORA.persistencia;

import org.example.ACTIVIDAD_INTEGRADORA.entidades.Comentario;

import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO extends DAO{
    public void guardarComentario(Comentario comentario) throws Exception{
        try {
            String sql = "INSERT INTO comentarios (id_casa, comentario) VALUES("+comentario.getIdCasa()+ ", '"+comentario.getComentario()+"')";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw new Exception("Comentario no puede ser nulo");
        }
    }

    public List<Comentario> buscarComentario(int idComentario) throws Exception {
        if (idComentario < 0) {
            throw new Exception("El id del comentario no puede ser nulo ni negativo");
        }

        List<Comentario> comentarios = new ArrayList<>();

        try {
            String sql = "SELECT * FROM comentarios WHERE id_comentario = " + idComentario;
            consultarBase(sql);
            while(resultSet.next()) {
                Comentario nuevoComentario = new Comentario();
                nuevoComentario.setIdComentario(resultSet.getInt("id_comentario"));
                nuevoComentario.setIdCasa(resultSet.getInt("id_casa"));
                nuevoComentario.setComentario(resultSet.getString("comentario"));
                comentarios.add(nuevoComentario);
            }
            desconectarBaseDeDatos();
        } catch (Exception e) {
            desconectarBaseDeDatos();
            throw new Exception("Error al encontrar comentario: " + e.getMessage());
        }
        return comentarios;
    }

    public List<Comentario> listarComentarios() throws Exception {
        List<Comentario> comentarios = new ArrayList<Comentario>();
        try {
            String sql = "SELECT * FROM comentarios";
            consultarBase(sql);
            while(resultSet.next()) {
                Comentario comentario = new Comentario();
                comentario.setIdComentario(resultSet.getInt("id_comentario"));
                comentario.setIdCasa(resultSet.getInt("id_casa"));
                comentario.setComentario(resultSet.getString("comentario"));
                comentarios.add(comentario);
            }
        } catch (Exception e) {
            throw new Exception("Error al listar comentarios: " + e.getMessage());
        } finally {
            desconectarBaseDeDatos();
        }
        return comentarios;
    }

    // public buscar Defender
    public void listarCasasReinoUnidoEstanLimpias(int idComentario) throws Exception {
        try {
            String sql = "SELECT ca.*" + "FROM casas ca" + "JOIN comentarios co ON ca.id_casa = co.id_casa" + "WHERE ca.pais = 'Reino Unido' AND co.comentario LIKE '%limpia%'";
            consultarBase(sql);
        } catch (Exception e) {
            throw new Exception("Comentario no puede ser nulo ni negativo");
        } finally {
            desconectarBaseDeDatos();
        }
    }
}
