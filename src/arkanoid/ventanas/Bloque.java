package arkanoid.ventanas;

import java.awt.Color;

import lombok.AllArgsConstructor;
import lombok.Data;



@AllArgsConstructor
@Data
public class Bloque {

	private int ancho;
	private int alto;
	private int posicionX;
	private int posicionY;
	private int golpes;
	protected Color color;
	
}
