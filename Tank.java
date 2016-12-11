import javafx.geometry.Rectangle2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public abstract class Tank extends Obj {
    protected Image Image_carcass;
    protected Image Image_tower;

    ImageView ImageView_carcass;
    ImageView ImageView_tower;

    int speed_tank;
    int shoot_rate; //skorostrel'nost'

    int count;
    int columns;
    int offsetX;
    int offsetY;

    int width_carcass;
    int height_carcass;

    int width_tower;
    int height_tower;

    int BiasQ;  // Spisok znachenii dl'a smesheniya koordinat vistrela otnositelno dula
    int BiasW;  //oni vse peredauts'a v konstriktor shella
    int BiasE;
    int BiasR;

    int Speed_shells; // teper scorost' snar'ada reguliruets'a v kajdom tipe tankov

    SpriteAnimation animation;

    // massa
    int weight;

    Rectangle health;
    double heightOfHealth;
    double widthOfHealth;

    //tank orientation
    String[] orient = {"vert", "horiz"};

    long timer;
    int o;
    String O;
    //tank move orientation
    String[] orientation = {"UP", "DOWN", "LEFT", "RIGHT"};

    // vertikalnoe polojenie spraita
    int LEFT_EDGE_VERT;
    int RIGHT_EDGE_VERT;
    int TOP_EDGE_VERT;
    int BOT_EDGE_VERT;

    // gorizontalnoe polojenie spraita
    int LEFT_EDGE_HOR;
    int RIGHT_EDGE_HOR;
    int TOP_EDGE_HOR;
    int BOT_EDGE_HOR;

    // polojenie poloski HP otnositelno tanka
    int positionHP_X;
    int positionHP_Y;

    String String_carcass;
    String String_tower;


    public Tank(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public void Draw() {
        Image_carcass = new Image(getClass().getResourceAsStream(String_carcass));
        Image_tower = new Image(getClass().getResourceAsStream(String_tower));

        timer = -1000000000;

        setTranslateX(X);
        setTranslateY(Y);
        // izobrajenie basi tanka
        ImageView ImageView_carcass = new ImageView(Image_carcass);
        // ten'
        ImageView_carcass.setEffect(new DropShadow());

        ImageView ImageView_tower = new ImageView(Image_tower);
        // ImageView_tower.setEffect(new DropShadow());
        this.ImageView_carcass = ImageView_carcass;

        String orient_1 = orient[(int) (Math.random() * 2)];
        int offsetY1 = 0;
        if (orient_1.equals("vert")) {
            left_edge = LEFT_EDGE_VERT;
            right_edge = RIGHT_EDGE_VERT;
            top_edge = TOP_EDGE_VERT;
            bot_edge = BOT_EDGE_VERT;
            offsetY1 = 0;
        }
        if (orient_1.equals("horiz")) {
            left_edge = LEFT_EDGE_HOR;
            right_edge = RIGHT_EDGE_HOR;
            top_edge = TOP_EDGE_HOR;
            bot_edge = BOT_EDGE_HOR;
            offsetY1 = height_carcass;
        }

        this.ImageView_tower = ImageView_tower;
        ImageView_tower.setViewport(new Rectangle2D(offsetX, offsetY, width_tower, height_tower));
        animation = new SpriteAnimation(ImageView_carcass, Duration.millis(200), count, columns,
                offsetX, offsetY1, width_carcass, height_carcass);

        heightOfHealth = 4;
        widthOfHealth = RIGHT_EDGE_HOR - LEFT_EDGE_HOR;

        health = new Rectangle(widthOfHealth, heightOfHealth, Color.DARKGREEN);
        health.setX(X + positionHP_X);
        health.setY(Y - positionHP_Y);


        Main.root.getChildren().addAll(health);
        getChildren().addAll(ImageView_carcass, ImageView_tower);

        this.o = -1;
        String O = this.O;

        currentHP = maxHP;
    }


    public void moveX(int speed_tank_X) {
        boolean right = speed_tank_X > 0 ? true : false;
        for (int i = 0; i < Math.abs(speed_tank_X); i++) {
            if (right) {
                if (!this.isBarrierRight()) {
                    left_edge = TOP_EDGE_VERT;
                    right_edge = BOT_EDGE_VERT;
                    top_edge = TOP_EDGE_HOR;
                    bot_edge = BOT_EDGE_HOR;
                    this.setX(this.getX() + 1);
                  //  health.setX(health.getX() + 1);
                }
            } else {
                if (!this.isBarrierLeft()) {
                    left_edge = LEFT_EDGE_HOR;
                    right_edge = RIGHT_EDGE_VERT;
                    top_edge = TOP_EDGE_HOR;
                    bot_edge = BOT_EDGE_HOR;
                    this.setX(this.getX() - 1);
                   // health.setX(health.getX() - 1);
                }
            }
        }
    }

    public void moveY(int speed_tank_Y) {
        boolean down = speed_tank_Y > 0 ? true : false;
        for (int i = 0; i < Math.abs(speed_tank_Y); i++) {
            if (down) {
                if (!this.isBarrierDown()) {
                    left_edge = LEFT_EDGE_VERT;
                    right_edge = RIGHT_EDGE_VERT;
                    top_edge = TOP_EDGE_VERT;
                    bot_edge = BOT_EDGE_VERT;
                    this.setY(this.getY() + 1);
                   // health.setY(health.getY() + 1);
                }
            } else {
                if (!this.isBarrierUp()) {
                    left_edge = LEFT_EDGE_VERT;
                    right_edge = RIGHT_EDGE_VERT;
                    top_edge = LEFT_EDGE_HOR;
                    bot_edge = RIGHT_EDGE_HOR;
                    this.setY(this.getY() - 1);
                   // health.setY(health.getY() - 1);
                }
            }
        }
    }

    public void moveRandom() {
        if (o == -1 || ((O.equals("UP")) && isBarrierUp()) || ((O.equals("DOWN")) && isBarrierDown())
                || ((O.equals("LEFT")) && isBarrierLeft()) || ((O.equals("RIGHT")) && isBarrierRight())) {

            Boolean[] fl = {isBarrierUp(), isBarrierDown(), isBarrierLeft(), isBarrierRight()};
            int n = 0;

            for (int i = 0; i < 4; i++) {
                if (!fl[i])
                    n++;
            }
            if (n == 0) {
                o = -1;
                return;
            }
            int j = 0;
            String[] actual_orientation = new String[n];
            for (int i = 0; i < 4; i++) {
                if (!fl[i]) {
                    actual_orientation[j] = orientation[i];
                    j++;
                }
            }
            o = (int) Math.floor(Math.random() * n);
            if (actual_orientation[o].equals("UP")) {
                updateUp();
                O = "UP";
            }
            if (actual_orientation[o].equals("DOWN")) {
                updateDown();
                O = "DOWN";
            }
            if (actual_orientation[o].equals("LEFT")) {
                updateLeft();
                O = "LEFT";
            }
            if (actual_orientation[o].equals("RIGHT")) {
                updateRight();
                O = "RIGHT";
            }
        }

        if (O.equals("UP")) {
            updateUp();
            O = "UP";
        }
        if (O.equals("DOWN")) {
            updateDown();
            O = "DOWN";
        }
        if (O.equals("LEFT")) {
            updateLeft();
            O = "LEFT";
        }
        if (O.equals("RIGHT")) {
            updateRight();
            O = "RIGHT";
        }
    }

    public void updateUp() {
        animation.play();
        animation.setOffsetY(3 * this.height_carcass);
        moveY(-speed_tank);
    }

    public void updateDown() {
        animation.play();
        animation.setOffsetY(0 * this.height_carcass);
        moveY(speed_tank);
    }

    public void updateRight() {
        animation.play();
        animation.setOffsetY(2 * this.height_carcass);
        moveX(speed_tank);
    }

    public void updateLeft() {
        animation.play();
        animation.setOffsetY(1 * this.height_carcass);
        moveX(-speed_tank);
    }

    public void shot(String ShellOrient) {
        long elapsed = (System.nanoTime() - this.timer) / 1000000;
        if (elapsed >= shoot_rate) {
            Game.shells.add(new Shell((int) this.getTranslateX(), (int) this.getTranslateY(), ShellOrient, this, Speed_shells, BiasQ, BiasW, BiasE, BiasR));
            timer = System.nanoTime();
        }
    }

    public void shotrandom(String ShellOrient) {
        long elapsed = (System.nanoTime() - this.timer) / 1000000;
        if (elapsed >= 1000) {
            if (ShellOrient.equals("UP"))
                this.ImageView_tower.setViewport(new Rectangle2D(0, 0, this.getWidth_tower(), this.getHeight_tower()));
            if (ShellOrient.equals("DOWN"))
                this.ImageView_tower.setViewport(new Rectangle2D(this.getWidth_tower(), 0, this.getWidth_tower(), this.getHeight_tower()));
            if (ShellOrient.equals("LEFT"))
                this.ImageView_tower.setViewport(new Rectangle2D(0, this.getWidth_tower(), this.getWidth_tower(), this.getHeight_tower()));
            if (ShellOrient.equals("RIGHT"))
                this.ImageView_tower.setViewport(new Rectangle2D(this.getWidth_tower(), this.getWidth_tower(), this.getWidth_tower(), this.getHeight_tower()));
            Game.shells.add(new Shell((int) this.getTranslateX(), (int) this.getTranslateY(), ShellOrient, this, Speed_shells, BiasQ, BiasW, BiasE, BiasR));
            timer = System.nanoTime();
        }
    }

    //todo превращение в робота и обратно
//    public void transformToRobot(String tors, String foot){
//        Image Image_tors = new Image(getClass().getResourceAsStream(tors));
//        ImageView ImageView_tors = new ImageView(Image_tors);
//
//        Image Image_foot = new Image(getClass().getResourceAsStream(foot));
//        ImageView ImageView_foot = new ImageView(Image_foot);
//        getChildren().addAll(ImageView_foot,ImageView_tors);
//    }
    public void transformToTank() {

    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getWidth_tower() {
        return this.width_tower;
    }

    public int getHeight_tower() {
        return this.height_tower;
    }

    public void death() {
        Main.root.getChildren().remove(this.health);
        Main.root.getChildren().remove(this);
        Game.objects.remove(this);

        System.out.println("я танк и умер");
        //  Game.dom.transparent("visible");

        ExplosionTankAnimation explsn = new ExplosionTankAnimation(80, 80, this.getTranslateX(), this.getTranslateY(), -10, -10);
        delete();
    }
}
