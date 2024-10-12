package org.example.PATRON_DISENO_DAO.entidades;

import java.sql.Date;

public class Pedido {
    private int idPedido;
    private int codigoPedido;
    private Date fechaPedido;
    private Date fechaEsperada;
    private Date fechaEntrega;
    private String estado;
    private String comentarios;
    private int idCliente;

    public Pedido(int idPedido, int codigoPedido, Date fechaPedido, Date fechaEsperada, Date fechaEntrega, String estado, String comentarios, int idCliente) {
        this.idPedido = idPedido;
        this.codigoPedido = codigoPedido;
        this.fechaPedido = fechaPedido;
        this.fechaEsperada = fechaEsperada;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.comentarios = comentarios;
        this.idCliente = idCliente;
    }

    public Pedido(int codigoPedido, Date fechaPedido, Date fechaEsperada, Date fechaEntrega, String estado, String comentarios, int idCliente) {
        this.codigoPedido = codigoPedido;
        this.fechaPedido = fechaPedido;
        this.fechaEsperada = fechaEsperada;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.comentarios = comentarios;
        this.idCliente = idCliente;
    }

    public Pedido() {}

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaEsperada() {
        return fechaEsperada;
    }

    public void setFechaEsperada(Date fechaEsperada) {
        this.fechaEsperada = fechaEsperada;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", codigoPedido=" + codigoPedido +
                ", fechaPedido=" + fechaPedido +
                ", fechaEsperada=" + fechaEsperada +
                ", fechaEntrega=" + fechaEntrega +
                ", estado='" + estado + '\'' +
                ", comentarios='" + comentarios + '\'' +
                ", idCliente=" + idCliente +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        if (idPedido != pedido.idPedido) return false;
        if (codigoPedido != pedido.codigoPedido) return false;
        if (idCliente != pedido.idCliente) return false;
        if (!fechaPedido.equals(pedido.fechaPedido)) return false;
        if (!fechaEsperada.equals(pedido.fechaEsperada)) return false;
        if (!fechaEntrega.equals(pedido.fechaEntrega)) return false;
        if (!estado.equals(pedido.estado)) return false;
        return comentarios.equals(pedido.comentarios);
    }

    @Override
    public int hashCode() {
        int result = idPedido;
        result = 31 * result + codigoPedido;
        result = 31 * result + idCliente;
        result = 31 * result + (fechaPedido != null ? fechaPedido.hashCode() : 0);
        result = 31 * result + (fechaEsperada != null ? fechaEsperada.hashCode() : 0);
        result = 31 * result + (fechaEntrega != null ? fechaEntrega.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (comentarios != null ? comentarios.hashCode() : 0);
        return result;
    }

}