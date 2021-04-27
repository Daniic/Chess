package es.ieslavereda.Chess.model.common;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Pieza {

	public Knight(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);

		if (color == Color.WHITE)
			tipo = Tipo.WHITE_KNIGHT;
		else
			tipo = Tipo.BLACK_KNIGHT;

		colocate(posicion);

	}

	@Override
	public Set<Coordenada> getNextMovements() {
		Set<Coordenada> lista = new HashSet<Coordenada>();
		
		addCoordenada(posicion.up().diagonalUpRight(), lista);
		addCoordenada(posicion.diagonalUpLeft().up() ,lista);
		addCoordenada(posicion.right().diagonalUpRight(), lista);
		addCoordenada(posicion.left().diagonalUpLeft(), lista);
		addCoordenada(posicion.down().diagonalDownRight(), lista);
		addCoordenada(posicion.diagonalDownLeft().down() ,lista);
		addCoordenada(posicion.right().diagonalDownRight(), lista);
		addCoordenada(posicion.left().diagonalDownLeft(), lista);

		return lista;
	}

	/**
	 * añade una coordenada a la lista si la pieza se puede mover a ella
	 * @param c coordenada a añadir
	 * @param lista lista en la que se añadira la coordenada
	 */
	
	private void addCoordenada(Coordenada c, Set<Coordenada> lista) {
		if (tablero.contiene(c)) {
			if (tablero.getCeldaAt(c).contienePieza()) {
				if (tablero.getCeldaAt(c).getPieza().getColor() != getColor())
					lista.add(c);
			} else {
				lista.add(c);
			}
		}
	}

}
