package ec.edu.ups.prueba.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Auto {

	@Id
	private int codigo;
	private String marca;
	private String placa;
	private Double precio;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Auto [codigo=" + codigo + ", marca=" + marca + ", placa=" + placa + ", precio=" + precio + "]";
	}	
}
