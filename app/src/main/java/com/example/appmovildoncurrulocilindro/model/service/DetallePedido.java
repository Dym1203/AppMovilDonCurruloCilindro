package com.example.appmovildoncurrulocilindro.model.service;

public class DetallePedido
{
	private int id_detalle;
	private int cantidad;
	private Double precio;
	private Plato plato;
	private Pedido pedido;


	public int getId_detalle() {
		return id_detalle;
	}

	public void setId_detalle(int id_detalle) {
		this.id_detalle = id_detalle;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public String getNombre() {
		return this.plato != null ? this.plato.getNombre() : "----";
	}

	public void addOne() {
		this.cantidad++;
	}

	public void removeOne() {
		this.cantidad--;
	}
	
	public Double getTotal() {
		return this.cantidad * this.precio;
	}

}