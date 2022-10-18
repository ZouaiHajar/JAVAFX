package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Arme extends Entite{
	public Arme(Hero hero) {
		Image image=null;
		try {
			image = new Image(new FileInputStream("photosJeu/Arme.png"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		corps = new ImageView(image);
	    ((ImageView)corps).setX(0);
	    ((ImageView)corps).setY(0);
	    corps.setTranslateX(hero.getCorps().getTranslateX());
	    corps.setTranslateY(hero.getCorps().getTranslateY());
	    
	}
	
	
	public void rotateRight() {
		corps.setRotate(corps.getRotate()-5);
	}
	
	public void rotateleft() {
		corps.setRotate(corps.getRotate()+5);
	}
	
	public double getRotate() {
		return corps.getRotate()-90;
	}
	
	public void attachtohero(Hero hero) {
		corps.setTranslateX(hero.getCorps().getTranslateX());
		corps.setTranslateY(hero.getCorps().getTranslateY());
	}
}
