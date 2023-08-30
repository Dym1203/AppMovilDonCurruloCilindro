package com.example.appmovildoncurrulocilindro.model.service;

public class Plato
{
	private int id_plato;
	private String nombre;
	private DocumentoAlmacenado foto;
	private Double precio;
	private int stock;
	private String descripcion;
	private Categoria categoria;
	private boolean vigencia;
	private boolean recomendado;

	public int getId_plato() {
		return id_plato;
	}
	public void setId_plato(int id_plato) {
		this.id_plato = id_plato;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DocumentoAlmacenado getFoto() {
		return foto;
	}

	public void setFoto(DocumentoAlmacenado foto) {
		this.foto = foto;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public boolean isVigencia() {
		return vigencia;
	}

	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}

	public boolean isRecomendado() {
		return recomendado;
	}

	public void setRecomendado(boolean recomendado) {
		this.recomendado = recomendado;
	}
}