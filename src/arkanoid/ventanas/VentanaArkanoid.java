package arkanoid.ventanas;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Set;

import arkanoid.eventos.EventosMio;
import arkanoid.hilos.Bola;
import arkanoid.hilos.Nave;
import arkanoid.hilos.Pintor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
@Data
public class VentanaArkanoid extends Frame {

	private Set<Bloque> cuadrados;
	private int ancho;
	private int alto;
	private int golpes;
	private int numeroBloques;
	private Graphics externo;
	private Image imagen;
	private boolean primeraVez = true;
	private Bola bola;
	private int velocidad;
	private int dimensionBola;
	private Image imagenBola;
	private Image imagenFondo;
	private Image imagenNave;
	private Nave nave;

	VentanaArkanoid() {
		Properties properties = new Properties();
		try {
			properties.load(new InputStreamReader(new FileInputStream("arkanoid.properties")));
			setAlto(Integer.parseInt(properties.getProperty("alto")));
			setAncho(Integer.parseInt(properties.getProperty("ancho")));
			setNumeroBloques(Integer.parseInt(properties.getProperty("bloques")));
			setGolpes(Integer.parseInt(properties.getProperty("golpes")));
			setVelocidad(Integer.parseInt(properties.getProperty("velocidad")));
			setDimensionBola(Integer.parseInt(properties.getProperty("dimensionBola")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		EventosMio eventosMio = new EventosMio(this);
		this.addWindowListener(eventosMio);
		this.addMouseListener(eventosMio);
	}

	@Override
	public void paint(Graphics g) {
		if (isPrimeraVez()) {
			setImagen(createImage(2550, 1200));
			setExterno(getImagen().getGraphics());

			CaracteristicasDeBloques tonterias = new CaracteristicasDeBloques(this);
			tonterias.cargaBloques();
			Pintor pintor = new Pintor(this);
			pintor.start();
			setPrimeraVez(false);
			setImagenBola(Toolkit.getDefaultToolkit().getImage("bolaRoja.png"));
			setImagenNave(Toolkit.getDefaultToolkit().getImage("nave2.0.png"));
			setImagenFondo(Toolkit.getDefaultToolkit().getImage("fondoArkanoid3.0.jpg"));
			nave = new Nave(getAncho()*2,getAlto(),100,1000,10,getImagenNave());
		}

		getExterno().clearRect(0, 0, 2550, 1200);
		getExterno().drawImage(getImagenFondo(), 0, 30, this.getWidth(), this.getHeight(), this);
 
		for (Bloque bloque : cuadrados) {
			getExterno().setColor(bloque.getColor());
			getExterno().fillRect(bloque.getPosicionX(), bloque.getPosicionY(), bloque.getAncho(), bloque.getAlto());
			getExterno().setColor(Color.BLUE);
			getExterno().drawRect(bloque.getPosicionX(), bloque.getPosicionY(), bloque.getAncho(), bloque.getAlto());

			
		}
		if (getBola() != null)
			getExterno().drawImage(getImagenBola(), getBola().getPosicionX(), getBola().getPosicionY(),
					getBola().getDimension(), getBola().getDimension(), this);
		
		
		if (getNave() != null)
			getExterno().drawImage(nave.getImage(), getNave().getPosicionX(), getNave().getPosicionY(),
					getNave().getAncho(), getNave().getAlto(), this);

		g.drawImage(getImagen(), 0, 0, this);

	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

}
