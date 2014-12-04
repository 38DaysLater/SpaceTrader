package spacetrader;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import javafx.animation.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.input.KeyCode;
import javafx.scene.Node;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.controlsfx.dialog.Dialogs;

public class MiniGame extends Application {
    private final GameScreen game = new GameScreen();
   
    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setScene(new Scene(root));
        root.getChildren().add(game);
    }

    @Override public void stop() {
        game.stop();
    }

    public void play() {
        game.start();
    }
   
    public static class GameScreen extends Pane {
        private final Singleton single = new Singleton();
        private final AnimationTimer timer;
        private final Canvas canvas;
       // private final ImageView background;
        private final List<Bullet> bullets = new ArrayList<Bullet>();
        private int playX, playY, speed, oppX, oppY, oppSpeed, oppHealth;
        private final Paint[] colors;
        private boolean hasWon = false;
        private boolean hasLost = false;
        private boolean dialogBoxShown = false;
        private boolean keyCurrentlyPressed = false;
       
        public GameScreen() {
            playX = 25;
            playY = 300;
            oppX = 450;
            oppY = 100;
            oppSpeed = 50 ; /* / Singleton.getCharacter.getFight();*/
            speed = 10 ;/** Singleton.getCharacter().getPilot();*/
            // create a color palette of 180 colors
            colors = new Paint[181];
            colors[0] = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.WHITE),
                        new Stop(0.2, Color.hsb(59, 0.38, 1)),
                        new Stop(0.6, Color.hsb(59, 0.38, 1,0.1)),
                        new Stop(1, Color.hsb(59, 0.38, 1,0))
                        );
            for (int h=0;h<360;h+=2) {
                colors[1+(h/2)] = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.WHITE),
                        new Stop(0.2, Color.hsb(h, 1, 1)),
                        new Stop(0.6, Color.hsb(h, 1, 1,0.1)),
                        new Stop(1, Color.hsb(h, 1, 1,0))
                        );
            }
            // create canvas
            canvas = new Canvas(500, 500);
            //sets keyListener
            canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
                public void handle(KeyEvent ke) {
                    //System.out.println(ke.getCode());
                    KeyCode key = ke.getCode();
                        if (key.equals(KeyCode.UP)) {
                            playY -= speed;
                        }
                        
                        if (key.equals(KeyCode.DOWN)) {
                             playY += speed;
                        }
                        
                        if (key.equals(KeyCode.SPACE)) {
                               fireGun();
                        }
                    }
                });
            
            //background = new ImageView(getClass().getResource("/images/sf.jpg").toExternalForm());
            getChildren().addAll(/*background,*/canvas);
            canvas.requestFocus();
            // create animation timer that will be called every frame
            // final AnimationTimer timer = new AnimationTimer() {
            timer = new AnimationTimer() {
                    //on animation
                @Override public void handle(long now) {
                    handleGameOver();
                    if (dialogBoxShown) {
                        canvas.getScene().getWindow().hide();
                        return;
                    }
                    canvas.requestFocus();
                    GraphicsContext gc = canvas.getGraphicsContext2D();
                    // clear area with transparent black
                    gc.setFill(Color.rgb(0, 0, 0, 0.2));
                    gc.fillRect(0, 0, 500, 500);
                    drawGame(gc);
                }
            };
        }
       
        public void start() { timer.start(); }
        public void stop() { timer.stop(); }

        
   
        private void drawGame(GraphicsContext gc) {
            //draw Text
            gc.setFill(Color.WHITESMOKE);
            gc.fillText("QUICK DRAW", 200, 30);
            gc.fillText("Space to shoot, arrow keys to move up and down", 100, 50);
            gc.fillText("Hit your opponent before they hit you!", 100, 65);
            //draw player
            gc.setFill(Color.BLUEVIOLET);
            gc.fillRect(playX, playY, 25, 16);
            
            //draw opponent
            moveEnemy();
            gc.setFill(Color.RED);
            gc.fillRect(oppX, oppY, 25, 16);
            Iterator<Bullet> iter = bullets.iterator();
            //handles Bullets
            while(iter.hasNext()) {
                Bullet o = iter.next();
                // if hit
                if (o.posX < oppX + 25 &&
                o.posX + 5 > oppX &&
                o.posY < oppY + 16  &&
                5 + o.posY > oppY) {
                     // enemy hit, end game
                    hasWon = true;
                }
                
                if (o.posX < playX + 25 &&
                o.posX + 5 > playX &&
                o.posY < playY + 16  &&
                5 + o.posY > playY) {
                     // you're hit, end game
                    hasLost = true;
                }
                o.update();
                o.draw(gc);
            }
        }
        
        private void handleGameOver() {
            String resultDialog;
            if (!hasWon || !hasLost || dialogBoxShown) {
                return;
            } else if (hasWon) {
                resultDialog = "You Win! Your fight skill has increased!";
                int fight = Singleton.getCharacter().getFight();
                Singleton.getCharacter().setFight(fight + 1);
            } else {
                resultDialog = "You lose. Your ship has taken 10 damage.";
                Singleton.getCharacter().getShip().subtractDamage(10);
            }
            dialogBoxShown = true;
             //start dialog box
                Dialogs.create()
                .title("Something's Happened!")
                .masthead("Something has happened!")
                .message(resultDialog)
                .showWarning();
                //end dialog box
        }
        
        private void moveEnemy() {
            float shootChance = (float)0.005;
            if ((playY - 10) > oppY) {
                oppSpeed = 1;
            } else if ((playY +10) < oppY) {
                oppSpeed = -1;
            } else {
                oppSpeed = 0;
                shootChance = (float)0.05;
            }
            
            oppY += oppSpeed;
            if (Math.random() < shootChance) {
                enemyGun();
            }
        }
       
        private void fireGun() {
            bullets.add(new Bullet((double)playX + 25, (double)playY + 8, 10, 0, colors[2], 5));
        }
        
        private void enemyGun() {
            bullets.add(new Bullet((double)oppX - 25, (double)oppY + 8, -10, 0, colors[1], 5));
        }
    }


    /**
     * A Bullet representation
     */
    public static class Bullet {
        // properties for animation
        // and colouring
        double posX;
        double posY;
        double velX;
        double velY;
        final Paint color;
        final int size;
        double lastPosX;
        double lastPosY;
       
        public Bullet (double posX, double posY, double velX, double velY, Paint color,int size ) {
            this.posX = posX;
            this.posY = posY;
            this.velX = velX;
            this.velY = velY;
            this.color = color;
            this.size = size;
        }

        public void update() {
            lastPosX = posX;
            lastPosY = posY;
            posX += velX;
            posY += velY;
        }

        public void draw(GraphicsContext context) {
                // draw Bullet
                context.setFill(color);
                context.fillRect(posX, posY, size, size);
        }
    }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        play();
    }
    public static void main(String[] args) { launch(args); }
}

