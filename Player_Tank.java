import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;

import java.util.HashMap;

/**
 * Created by 9999 on 16.11.2015.
 */
public class Player_Tank extends Tank {
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    int up;
    int down;
    int right;
    int left;



    public Player_Tank(int X, int Y) {
        super(X, Y);

        count = 3;
        columns = 3;

        height_carcass = 300;
        width_carcass = 300;
        height_tower = 300;
        width_tower = 300;

        // vertikalnoe polojenie spraita
        LEFT_EDGE_VERT = 120;
        RIGHT_EDGE_VERT = 180;
        TOP_EDGE_VERT = 125;
        BOT_EDGE_VERT = 210;

        // gorizontalnoe polojenie spraita
        LEFT_EDGE_HOR = 90;
        RIGHT_EDGE_HOR = 175;
        TOP_EDGE_HOR = 120;
        BOT_EDGE_HOR = 180;

        BiasQ = 148;
        BiasW = -68;
        BiasE = 15;
        BiasR = 230;

        Speed_shells = 25;
        shoot_rate = 500;

        String_carcass = "pictures/Carcass_Avel.png";
        String_tower = "pictures/Tower_Avel.png";

        speed_tank = 7;
        maxHP = 30;

        weight = 2500;

        positionHP_X = 80; //polojenie poloski HP otnositelno tanka
        positionHP_Y = -60;

        up = 0;
        down = 0;
        right = 0;
        left = 0;
    }

    public void death(){
        Main.root.getChildren().remove(this.health);
        Main.root.getChildren().remove(this);
        Game.objects.remove(this);
        Game.players.remove(this);

        System.out.println("я игрок и умер");

        ExplosionTankAnimation explsn = new ExplosionTankAnimation(80,80,this.getTranslateX(),this.getTranslateY(),0,0);
        delete();
    }

    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);                                                                           //?????? ?? ???????
    }

    public void updatePlayer() {
        getScene().setOnKeyPressed(event -> keys.put(event.getCode(), true));
        getScene().setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
        });

        if (isPressed(KeyCode.ESCAPE)) {
            Game.isRunning = false;
        }

        if (isPressed(KeyCode.W)) {                                                                          //???????? ??????
            if (up == 0)
                up = 1;
            else
                up = 2;
        } else
            up = 0;
        if (isPressed(KeyCode.S)) {
            if (down == 0)
                down = 1;
            else
                down = 2;
        } else
            down = 0;
        if (isPressed(KeyCode.D)) {
            if (right == 0)
                right = 1;
            else
                right = 2;
        } else
            right = 0;
        if (isPressed(KeyCode.A)) {
            if (left == 0)
                left = 1;
            else
                left = 2;
        } else
            left = 0;


        if (up != 1 && down != 1 && right != 1 && left != 1) {
            if (up == 2 && !this.isBarrierUp()) {                                                                          //???????? ??????
                this.updateUp();
            } else if (down == 2 && !this.isBarrierDown()) {
                this.updateDown();
            } else if (right == 2 && !this.isBarrierRight()) {
                this.updateRight();
            } else if (left == 2 && !this.isBarrierLeft()) {
                this.updateLeft();
            } else {
                this.animation.stop();
            }
        } else {
            if (up == 1 && !this.isBarrierUp()) {                                                                          //???????? ??????
                this.updateUp();
            } else if (down == 1 && !this.isBarrierDown()) {
                this.updateDown();
            } else if (right == 1 && !this.isBarrierRight()) {
                this.updateRight();
            } else if (left == 1 && !this.isBarrierLeft()) {
                this.updateLeft();
            } else {
                this.animation.stop();
            }
        }

        if (isPressed(KeyCode.UP)) {                                                                            //??????? ????? ? ????????
            this.ImageView_tower.setViewport(new Rectangle2D(0, 0, 300, 300));
            this.shot("UP");

        } else if (isPressed(KeyCode.DOWN)) {
            this.ImageView_tower.setViewport(new Rectangle2D(300, 0, 300, 300));
            this.shot("DOWN");
        } else if (isPressed(KeyCode.RIGHT)) {
            this.ImageView_tower.setViewport(new Rectangle2D(300, 300, 300, 300));
            this.shot("RIGHT");
        } else if (isPressed(KeyCode.LEFT)) {
            this.ImageView_tower.setViewport(new Rectangle2D(0, 300, 300, 300));
            this.shot("LEFT");
        }
        //TODO catch the key for tranforming using transorm methodsof Tank class


        if (isPressed(KeyCode.E)) {
            boolean transform = true;

            getChildren().removeAll(ImageView_tower, ImageView_carcass);

            Player_Robot robot = new Player_Robot(this.X,this.Y);

        }

        /*
        naezd na dom s ischeznaveniem poslednego
         */
        for (int i = 0; i < Game.objects.size(); i++) {
            if (Game.objects.get(i).equals(this))
                continue;
            if (Game.objects.get(i).getClass().getName().equals("Buildings")) {
                if (this.getTranslateX() + this.left_edge < Game.objects.get(i).getTranslateX() + Game.objects.get(i).getRight_edge() &&
                        this.getTranslateX() + this.right_edge > Game.objects.get(i).getTranslateX() &&
                        this.getTranslateY() + this.top_edge < Game.objects.get(i).getTranslateY() + Game.objects.get(i).getTop_edge() &&
                        this.getTranslateY() + this.bot_edge > Game.objects.get(i).getTranslateY()) {
                    Game.objects.get(i).transparent("invisible");
                } else {
                    Game.objects.get(i).transparent("visible");
                }
            }
        }
        setCamera();


    }

    // движение "камеры"
    //по горизонтали
    public void setCamera(){
        double delta = Game.players.get(0).getTranslateX() + (Game.players.get(0).left_edge + Game.players.get(0).right_edge)/2 + Main.root.getLayoutX() - (double)Main.windowWidth * 0.75;
        if(delta > 0) {
            Main.root.setLayoutX(Main.root.getLayoutX() - delta/8);
        }

        delta = Game.players.get(0).getTranslateX() + (Game.players.get(0).left_edge + Game.players.get(0).right_edge)/2 + Main.root.getLayoutX() - (double)Main.windowWidth * 0.25;
        if(delta < 0) {
            Main.root.setLayoutX(Main.root.getLayoutX() - delta/8);
        }
            //по вертикали
        delta = Game.players.get(0).getTranslateY() + (Game.players.get(0).bot_edge + Game.players.get(0).top_edge)/2 + Main.root.getLayoutY() - (double)Main.windowHeight * 0.55;
        if(delta > 0) {
            Main.root.setLayoutY(Main.root.getLayoutY() - delta/8);
        }

        delta = Game.players.get(0).getTranslateY() + (Game.players.get(0).bot_edge + Game.players.get(0).top_edge)/2 + Main.root.getLayoutY() - (double)Main.windowHeight * 0.45;
        if(delta < 0) {
            Main.root.setLayoutY(Main.root.getLayoutY() - delta/8);
        }



    }

}
