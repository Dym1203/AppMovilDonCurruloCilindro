package com.example.appmovildoncurrulocilindro.model.service;

import java.sql.Date;

public class Pedido
{
	private int id_pedido;
	private Date fecha;
	private Cliente cliente;
	private Double total;
	private boolean anularPedido;


	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public boolean isAnularPedido() {
		return anularPedido;
	}

	public void setAnularPedido(boolean anularPedido) {
		this.anularPedido = anularPedido;
	}
}