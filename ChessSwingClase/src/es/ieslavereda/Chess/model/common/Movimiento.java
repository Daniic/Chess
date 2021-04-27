package es.ieslavereda.Chess.model.common;


import java.io.Serializable;

public class Movimiento implements Serializable {

	private static int numero = 1;

	public static final int KILL = 0;
	public static final int RISE = 1;
	public static final int RISE_KILLING = 2;
	public static final int NOT_KILL = 3;

	private Coordenada origen;
	private Coordenada destino;
	private int numeroMovimiento;
	private int tipoAccion;
	private Pieza ficha;
	private Pieza fichaGenerada;
	private Pieza fichaPeon;

	public Movimiento(Coordenada origen, Coordenada destino, int tipoAccion, Pieza ficha, Pieza fichaGenerada, Pieza fichaPeon) {

		this.origen = origen;
		this.destino = destino;
		this.tipoAccion = tipoAccion;
		this.ficha = ficha;
		this.fichaGenerada = fichaGenerada;
		this.fichaPeon = fichaPeon;
		numeroMovimiento = numero++;
		System.out.println(" Mov " + numeroMovimiento + " -> " + tipoAccion);
	}

	// getters

	public static void restartNumberOfMovements() {
		numero = 1;
	}

	public static void decreaseNumberOfMovements() {
		numero--;
	}

	public static void increaseNumberOfMovements() {
		numero++;
	}
	

	public Pieza getFicha() {
		return ficha;
	}

	public void setFicha(Pieza ficha) {
		this.ficha = ficha;
	}

	public Pieza getFichaGenerada() {
		return fichaGenerada;
	}

	public void setFichaGenerada(Pieza fichaGenerada) {
		this.fichaGenerada = fichaGenerada;
	}

	public Pieza getFichaPeon() {
		return fichaPeon;
	}

	public void setFichaPeon(Pieza fichaPeon) {
		this.fichaPeon = fichaPeon;
	}

	public static int getNumero() {
		return numero;
	}

	public static void setNumero(int numero) {
		Movimiento.numero = numero;
	}

	public Coordenada getOrigen() {
		return origen;
	}

	public void setOrigen(Coordenada origen) {
		this.origen = origen;
	}

	public Coordenada getDestino() {
		return destino;
	}

	public void setDestino(Coordenada destino) {
		this.destino = destino;
	}

	public int getNumeroMovimiento() {
		return numeroMovimiento;
	}

	public void setNumeroMovimiento(int numeroMovimiento) {
		this.numeroMovimiento = numeroMovimiento;
	}

	public int getTipoAccion() {
		return tipoAccion;
	}

	public void setTipoAccion(int tipoAccion) {
		this.tipoAccion = tipoAccion;
	}


	@Override
	public String toString() {
		return numeroMovimiento + " - [start=" + origen + " ,end=" + destino + "]";
	}
}
