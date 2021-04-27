package es.ieslavereda.Chess.model.common;

import java.io.Serializable;
import java.util.Set;

public abstract class Pieza implements Serializable{

	protected Tipo tipo;
	protected Tablero tablero;
	protected Coordenada posicion;
	
	public Pieza(Coordenada posicion, Tablero tablero) {
		super();
		this.posicion = posicion;
		this.tablero = tablero;
		
//		colocate(posicion);
	}
	
	public void colocate(Coordenada c) {
		
		tablero.getCeldaAt(posicion).setPieza(null);
		posicion = c;
		tablero.getCeldaAt(posicion).setPieza(this);
		
	}
	
	public void moveTo(Coordenada c) {
		
		if(tablero.getPiezaAt(c)==null) {
			colocate(c);
		} else {
			tablero.eliminarPieza(tablero.getPiezaAt(c));
			colocate(c);
		}
	}
	public boolean canMoveTo(Coordenada c) {
		return this.getNextMovements().contains(c);
	}

	public Color getColor() {
		return tipo.getColor();
	}
	
	public String getFileName() {
		return tipo.getFileName();
	}
	
	@Override
	public String toString() {
		return tipo.getFileName();
	}
	public Tablero getTablero() {
		return tablero;
	}
	
	public abstract Set<Coordenada> getNextMovements();

	public Coordenada getPosicion() {
		return posicion;
	}
	
}











