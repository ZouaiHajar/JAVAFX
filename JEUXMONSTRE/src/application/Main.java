package application;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
	private double widthWindow = 800;
	private double heightWindow = 600;
	private Pane container = new Pane();
	Line line = new Line(0,200,widthWindow,200);
	Zone zone1=new Zone(0,0,line.getEndX()-50,line.getEndY()-50);
	Zone zone2=new Zone(line.getStartX(),line.getStartY(),line.getEndX()-50,heightWindow-50);
	String music1="photosJeu/tle9.mp3";
	String music2="photosJeu/start.mp3";
	String music3="photosJeu/dead.mp3";
	Media mApplause3 = new Media( new File(music1).toURI().toString());
	MediaPlayer kill= new MediaPlayer(mApplause3);
	private Hero hero = new Hero(zone2);
	private List<Monstre> monstres = new ArrayList<>();
	private List<Balle> balles = new ArrayList<>();
	Arme arme = new Arme(hero);
	EventHandler<KeyEvent> event = new EventHandler<KeyEvent>(){
		@Override
		public void handle(KeyEvent event) {
			if(event.getCode()== KeyCode.X) {
				arme.rotateleft();
			}
            if(event.getCode()== KeyCode.W) {
				arme.rotateRight();
			}
            if(event.getCode()== KeyCode.SPACE) {
            	MediaPlayer kill= new MediaPlayer(mApplause3);
    				kill.play();
				Balle balle = new Balle(arme);
				container.getChildren().add(balle.getCorps());
				balles.add(balle);
			}
             if(event.getCode()== KeyCode.LEFT) {
            	 if(hero.getCorps().getTranslateX() > 0) {
            		 hero.getCorps().setTranslateX(hero.getCorps().getTranslateX()-5);
     				arme.attachtohero(hero);
               	} 	
			}
             if(event.getCode()== KeyCode.RIGHT) {
            	 if(hero.getCorps().getTranslateX() < 700) {
            		 hero.getCorps().setTranslateX(hero.getCorps().getTranslateX()+5);
                	 arme.attachtohero(hero);
               	} 	 
 			}
             if(event.getCode()== KeyCode.UP) {
            	 if(hero.getCorps().getTranslateY() > 200) {
            		hero.getCorps().setTranslateY(hero.getCorps().getTranslateY()-5);
      				arme.attachtohero(hero);
             	} 
 			}
              if(event.getCode()== KeyCode.DOWN) {
            	  if(hero.getCorps().getTranslateY() < 450) {
            		  hero.getCorps().setTranslateY(hero.getCorps().getTranslateY()+5);
                  	 arme.attachtohero(hero);
               	}  	 
  			}
		}
	};
	AnimationTimer animation = new AnimationTimer() {
    double count = 0;
		@Override
		public void handle(long arg0) {
			if(Math.random()<0.01){
				if(count <= 9) {
					Monstre a = new Monstre(zone1);
					container.getChildren().add(a.getCorps());
					monstres.add(a);
					count++;
				}	
			}
			refreshContent();	
		}	
	};
	private void refreshContent() {
		double I =0;
		for(Balle balle:balles) {
			for(Monstre monster:monstres) {
				if(balle.touch(monster)) {
					container.getChildren().removeAll(monster.getCorps(),balle.getCorps());
					balle.setAlive(false);
					monster.setAlive(false);
					I=I+1;
				}
			}
		}
		monstres.removeIf(Entite::isDead);
		balles.removeIf(Entite::isDead);
		for(Balle balle:balles){
			balle.update();
		}	
	}
	
	@Override
	public void start(Stage window) {
		window.setWidth(widthWindow);
		window.setHeight(heightWindow);
		window.setTitle("jeu guerre !");
		createContent();
		container.setId("pane");
		Scene scene = new Scene(container);
		scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
		Media mApplause3 = new Media( new File(music2).toURI().toString());
		MediaPlayer start= new MediaPlayer(mApplause3);
		start.play();
		window.setScene(scene);
		animation.start();
		scene.setOnKeyPressed(event);
		window.show();	
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	private void createContent() {
		container.getChildren().add(line);
		container.getChildren().add(hero.getCorps());
		container.getChildren().add(arme.getCorps());
	}
	
}
