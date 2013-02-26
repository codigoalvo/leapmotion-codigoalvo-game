package codigoalvo.leap.alvogame;

import java.awt.Color;


public enum TipoObjeto {
	AZUL,
	VERDE,
	AMARELO,
	PINK,
	VERMELHO,
	CYAN;
	
	public static TipoObjeto objetoByNum(int num) {
		switch (num) {
			case 1: return AZUL;
			case 2: return VERDE;
			case 3: return AMARELO;
			case 4: return PINK;
			case 5: return VERMELHO;
			case 6: return CYAN;
		}
		return VERMELHO;
	}

	public static Color colorByTipo(TipoObjeto tipo) {
		switch (tipo) {
			case AZUL: return Color.BLUE;
			case VERDE: return Color.GREEN;
			case AMARELO: return Color.YELLOW;
			case PINK: return Color.MAGENTA;
			case VERMELHO: return Color.RED;
			case CYAN: return Color.CYAN;
		}
		return Color.BLUE;
	}

}
