package org.example.PATRON_DISENO_DAO.entidades;

public class Cliente {
    private int idCliente;
    private int codigoCliente;
    private String nombreCliente;
    private String nombreContacto;
    private String apellidoContacto;
    private String telefono;
    private String fax;
    private String ciudad;
    private String region;
    private String pais;
    private String codigoPostal;
    private int idEmpleado;
    private double limiteCredito;
    public Cliente(int idCliente, int codigoCliente, String nombreCliente, String nombreContacto,
                        String apellidoContacto, String telefono, String fax, String ciudad, String region, String pais,
                        String codigoPostal, int idEmpleado, double limiteCredito) {
        this.idCliente = idCliente;
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.nombreContacto = nombreContacto;
        this.apellidoContacto = apellidoContacto;
        this.telefono = telefono;
        this.fax = fax;
        this.ciudad = ciudad;
        this.region = region;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
        this.idEmpleado = idEmpleado;
        this.limiteCredito = limiteCredito;
    }
    public Cliente(int codigoCliente, String nombreCliente, String nombreContacto,
                        String apellidoContacto, String telefono, String fax, String ciudad, String region, String pais,
                        String codigoPostal, int idEmpleado, double limiteCredito) {
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.nombreContacto = nombreContacto;
        this.apellidoContacto = apellidoContacto;
        this.telefono = telefono;
        this.fax = fax;
        this.ciudad = ciudad;
        this.region = region;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
        this.idEmpleado = idEmpleado;
        this.limiteCredito = limiteCredito;
    }
    public Cliente() {
    }
    public int getIdCliente() {
            return idCliente;
    }
    public void setIdCliente(int idCliente) {
            this.idCliente = idCliente;
    }
    public int getCodigoCliente() {
            return codigoCliente;
    }
    public void setCodigoCliente(int codigoCliente) {
            this.codigoCliente = codigoCliente;
    }
    public String getNombreCliente() {
            return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
            this.nombreCliente = nombreCliente;
    }
    public String getNombreContacto() {
            return nombreContacto;
    }
    public void setNombreContacto(String nombreContacto) {
            this.nombreContacto = nombreContacto;
    }
    public String getApellidoContacto() {
            return apellidoContacto;
    }
    public void setApellidoContacto(String apellidoContacto) {
            this.apellidoContacto = apellidoContacto;
    }
    public String getTelefono() {
            return telefono;
    }
    public void setTelefono(String telefono) {
            this.telefono = telefono;
    }
    public String getFax() {
            return fax;
    }
    public void setFax(String fax) {
            this.fax = fax;
    }
    public String getCiudad() {
            return ciudad;
    }
    public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
    }
    public String getRegion() {
            return region;
    }
    public void setRegion(String region) {
            this.region = region;
    }
    public String getPais() {
            return pais;
    }
    public void setPais(String pais) {
            this.pais = pais;
    }
    public String getCodigoPostal() {
            return codigoPostal;
    }
    public void setCodigoPostal(String codigoPostal) {
            this.codigoPostal = codigoPostal;
    }
    public int getIdEmpleado() {
            return idEmpleado;
    }
    public void setIdEmpleado(int idEmpleado) {
            this.idEmpleado = idEmpleado;
    }
    public double getLimiteCredito() {
            return limiteCredito;
    }
    public void setLimiteCredito(double limiteCredito) {
            this.limiteCredito = limiteCredito;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", codigoCliente=" + codigoCliente +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", nombreContacto='" + nombreContacto + '\'' +
                ", apellidoContacto='" + apellidoContacto + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fax='" + fax + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", region='" + region + '\'' +
                ", pais='" + pais + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", idEmpleado=" + idEmpleado +
                ", limiteCredito=" + limiteCredito +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (idCliente != cliente.idCliente) return false;
        if (codigoCliente != cliente.codigoCliente) return false;
        if (idEmpleado != cliente.idEmpleado) return false;
        if (Double.compare(cliente.limiteCredito, limiteCredito) != 0) return false;
        if (!nombreCliente.equals(cliente.nombreCliente)) return false;
        if (!nombreContacto.equals(cliente.nombreContacto)) return false;
        if (!apellidoContacto.equals(cliente.apellidoContacto)) return false;
        if (!telefono.equals(cliente.telefono)) return false;
        if (!fax.equals(cliente.fax)) return false;
        if (!ciudad.equals(cliente.ciudad)) return false;
        if (!region.equals(cliente.region)) return false;
        if (!pais.equals(cliente.pais)) return false;
        return codigoPostal.equals(cliente.codigoPostal);
    }

    @Override
    public int hashCode() {
        int result = idCliente;
        result = 31 * result + codigoCliente;
        result = 31 * result + (nombreCliente != null ? nombreCliente.hashCode() : 0);
        result = 31 * result + (nombreContacto != null ? nombreContacto.hashCode() : 0);
        result = 31 * result + (apellidoContacto != null ? apellidoContacto.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (pais != null ? pais.hashCode() : 0);
        result = 31 * result + (codigoPostal != null ? codigoPostal.hashCode() : 0);
        result = 31 * result + idEmpleado;
        long temp = Double.doubleToLongBits(limiteCredito);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

}
