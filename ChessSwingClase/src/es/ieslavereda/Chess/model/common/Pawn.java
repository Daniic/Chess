package es.ieslavereda.Chess.model.common;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Pieza {

	public Pawn(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		
		if(color==Color.WHITE)
			tipo = Tipo.WHITE_PAWN;
		else
			tipo = Tipo.BLACK_PAWN;
		
		colocate(posicion);
	}

	@Override
	public Set<Coordenada> getNextMovements() {
		Set<Coordenada> lista = new HashSet();
		if (getColor() == Color.WHITE) {
			if(!tablero.getCeldaAt(posicion.up()).contienePieza()) {
				lista.add(posicion.up());
				if (posicion.getRow() == 2 && !tablero.getCeldaAt(posicion.up().up()).contienePieza()) {
					lista.add(posicion.up().up());
				}
			}
			if (comprobarDiagonal(posicion.diagonalUpLeft())) {
				lista.add(posicion.diagonalUpLeft());
			}

			if (comprobarDiagonal(posicion.diagonalUpRight())) {
				lista.add(posicion.diagonalUpRight());
			}
		} else {
			if(!tablero.getCeldaAt(posicion.down()).contienePieza()) {
			
				lista.add(posicion.down());
				if (posicion.getRow() == 7 && !tablero.getCeldaAt(posicion.down().down()).contienePieza()) {
					lista.add(posicion.down().down());
			}
			}
			if (comprobarDiagonal(posicion.diagonalDownLeft())) {
				lista.add(posicion.diagonalDownLeft());
			}

			if (comprobarDiagonal(posicion.diagonalDownRight())) {
				lista.add(posicion.diagonalDownRight());
			}
		}

		return lista;
	}

	/**
	 * comprueba si hay una pieza de distinto color en una coordenada
	 * @param c coordenada a comprobar
	 * @return true si hay una pieza de distinto color
	 */
	private boolean comprobarDiagonal(Coordenada c) {
		if (tablero.contiene(c) && tablero.getCeldaAt(c).contienePieza()
				&& tablero.getPiezaAt(c).getColor() != this.getColor()) {
			return true;
		} else
			return false;
	}
	
	public void moveTo(Coordenada c) {
		super.moveTo(c);
		
		if(getColor()==Color.WHITE && posicion.getRow()==8) {
			tablero.eliminarPieza(this);
			tablero.getBlancas().add(new Queen(Color.WHITE,c,tablero));
		} else if (getColor()==Color.BLACK && posicion.getRow()==1){
			tablero.eliminarPieza(this);
			tablero.getNegras().add(new Queen(Color.BLACK,c,tablero));
		}
		
	}

}
