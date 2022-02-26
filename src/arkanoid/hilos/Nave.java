package arkanoid.hilos;

import java.awt.Image;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class Nave{

	private int ancho;
	private int alto;
	private int posicionX, posicionY, incrementoX;
	private Image image;
	
	public Nave(int ancho, int alto,int posicionX, int posicionY, int incrementoX, Image image) {
		super();
		this.ancho = ancho;
		this.alto = alto;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.incrementoX = incrementoX;
		this.image = image;
	}

/*
	public int getDimension() {
		// TODO Auto-generated method stub
		return 0;
	}

*/		
	
}