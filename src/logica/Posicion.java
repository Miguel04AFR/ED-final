package logica;

public class Posicion {
	private boolean robot;
	private boolean meta;
	
	
	public Posicion(boolean robot, boolean meta) {
		super();
		this.robot = robot;
		this.meta = meta;
	}
	public boolean getRobot() {
		return robot;
	}
	public void setRobot(boolean robot) {
		this.robot = robot;
	}
	public boolean getMeta() {
		return meta;
	}
	public void setMeta(boolean meta) {
		this.meta = meta;
	}
	
	
	

}
