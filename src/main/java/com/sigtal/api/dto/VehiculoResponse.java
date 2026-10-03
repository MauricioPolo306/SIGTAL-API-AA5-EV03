package com.sigtal.api.dto;

import com.sigtal.api.model.Vehiculo;

public class VehiculoResponse {

    private Integer idVehiculo;
    private String placa;
    private String marca;
    private String modelo;
    private Integer anio;
    private String color;
    private String tipoVehiculo;

    public VehiculoResponse() {
    }

    public VehiculoResponse(Vehiculo vehiculo) {

        this.idVehiculo = vehiculo.getIdVehiculo();
        this.placa = vehiculo.getPlaca();
        this.marca = vehiculo.getMarca();
        this.modelo = vehiculo.getModelo();
        this.anio = vehiculo.getAnio();
        this.color = vehiculo.getColor();
        this.tipoVehiculo = vehiculo.getTipoVehiculo();
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
}