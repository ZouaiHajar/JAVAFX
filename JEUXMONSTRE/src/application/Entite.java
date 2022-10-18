package application;

import javafx.scene.Node;

public class Entite {
	protected Node corps;
    private boolean alive=true; 
    
    public boolean isDead(){
    	return !alive;
    }
    
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Node getCorps() {
		return corps;
	}

	public void setCorps(Node corps) {
		this.corps = corps;
	}
	
	public boolean touch(Entite ebj) {
		return corps.getBoundsInParent().intersects(ebj.getCorps().getBoundsInParent());
	}

}
