package es.ieslavereda.Chess.model.common;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Pieza {

	public Bishop(Color color,Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		
		if(color==Color.WHITE)
			tipo = Tipo.WHITE_BISHOP;
		else
			tipo = Tipo.BLACK_BISHOP;
		
		colocate(posicion);
	}

	@Override
	public Set<Coordenada> getNextMovements() {
		// TODO Auto-generated method stub
		return getNextMovements(this);
	}
	
	public static Set<Coordenada> getNextMovements(Pieza p){

		Tablero t = p.tablero;
		Set<Coordenada> lista = new HashSet<>();
		Coordenada c;
		
		// UpRight 
		c= p.posicion.diagonalUpRight();
		while(t.contiene(c) && t.getPiezaAt(c)==null) {
			lista.add(c);
			c=c.diagonalUpRight();
		}
		if(t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor()) 
			lista.add(c);
		
		// UpLeft
		c= p.posicion.diagonalUpLeft();
		while(t.contiene(c) && t.getPiezaAt(c)==null) {
			lista.add(c);
			c=c.diagonalUpLeft();
		}
		if(t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor()) 
			lista.add(c);
		// DownRight
		c= p.posicion.diagonalDownRight();
		while(t.contiene(c) && t.getPiezaAt(c)==null) {
			lista.add(c);
			c=c.diagonalDownRight();
		}
		if(t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor()) 
			lista.add(c);
		// DownLeft
		c= p.posicion.diagonalDownLeft();
		while(t.contiene(c) && t.getPiezaAt(c)==null) {
			lista.add(c);
			c=c.diagonalDownLeft();
		}
		if(t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor()) 
			lista.add(c);
		
		return lista;
	}
}
