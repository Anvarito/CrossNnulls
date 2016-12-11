import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 9999 on 26.11.2015.
 */
public class tankgame {
    //public static ArrayList<Rectangle> bonuses = new ArrayList<>();



    static boolean isRunning = false;

    int bots_count = 500;  //Kolichestvo botov
    int BossSmall_count = 0; //Kolichestvo bossovwwwww

    Player_Tank player_tank;


    /**
     * arena for game
     */
    ArenaFighting_01 arena;

    ExecutorService exec = Executors.newCachedThreadPool();

    static ArrayList<Obj> objects = new ArrayList<Obj>();
    static ArrayList<Shell> shells = new ArrayList<Shell>();
    static ArrayList<Player_Tank> players = new ArrayList<Player_Tank>();
    static ArrayList<Tank> bots = new ArrayList<Tank>();
    //static ArrayList<BossSmall>bossSmalls = new ArrayList<BossSmall>();

    //BossSmall boss = new BossSmall(300, 300);

    public tankgame() {
        isRunning = true;
        Main.root.setPrefSize(Main.width, Main.height);
        //Main.root.setLayoutX(300);
        player_tank = new Player_Tank(2500, 2500);
        // Map map = new Map();

        player_tank.Draw();
        players.add(player_tank);
        // if(objects.get(0).getClass().getSuperclass().getName().equals("Tank"))
        //  System.out.println("TANK!");
        // System.out.println(objects.get(0).getClass().getSuperclass().getName());

        Main.root.getChildren().addAll(player_tank); /**добавить игрока**/

        for (int i = 0; i < BossSmall_count; i++) {
            bots.add(new BossSmall((int) Math.floor(Math.random() * (Main.width - 195)), (int) Math.floor(Math.random() * (Main.height - 195))));
            //objects.add();
            //bots.get(i).Draw();
            //Main.root.getChildren().addAll(bots.get(i));
        }

        for (int i = 0; i < bots_count; i++) {                       //количество ботов                                                                         //???????? ?????
            bots.add(new Bot_fag((int) Math.floor(Math.random() * (Main.width - 36)), (int) Math.floor(Math.random() * (Main.height - 36))));
        }


        for (int i = 0; i < bots.size(); i++) {
            bots.get(i).Draw();
            Main.root.getChildren().addAll(bots.get(i));
        }

        //arena = new ArenaFighting_01();

        ThreadRender threadRender = new ThreadRender();
        Thread thread = new Thread(threadRender);
        thread.start();
        ThreadCalculate threadUpdateGame = new ThreadCalculate();
        Thread thread1 = new Thread(threadUpdateGame);
        thread1.start();

    }

    // game cycle
    public void render() {

        for (int i = 0; i<objects.size();i++){
            objects.get(i).setTranslateX(objects.get(i).getX());
            objects.get(i).setTranslateY(objects.get(i).getY());
        }

        for (int i = 0; i<bots.size();i++) {
            bots.get(i).moveRandom();
        }

        System.out.println("Objects: " + objects.size() + " Players: " + players.size() + " Bots: " + bots.size() + " Shells: " + shells.size() + " " + Thread.currentThread().getName());




    }


    /**
     * calculate - тут происходят все расчёты
     */

    public void calculate() {

        player_tank.updatePlayer();


        /*if(isPressed(KeyCode.P)){                                                                                       //????????? ????? ?? ??????? ??????? P
            bots.add(new Bot_fag((int) Math.floor(Math.random() * (width - 40)), (int) Math.floor(Math.random() * (height - 40))));
            root.getChildren().addAll(bots.get(bots.size() - 1));
        }*/


        for (int i = 0; i < bots.size(); i++)

        {
                                                                                            //???????? ?????
 //           String[] shot_orientation = {"UP", "DOWN", "LEFT", "RIGHT"};
//
  //          bots.get(i).shotrandom(shot_orientation[(int) Math.floor(Math.random() * 4)]); // index from 0 to 3
        }
        /**
         * фон постоянно отправляется "назад" что бы не закрывать собою проиходяще на экране
         */
        //arena.ImageView_ground.toBack();
        //  ArenaFighting_01.fon.toBack();
        //ArenaFighting_01.fon2.toBack();

        /**move shells**/
        for (int j = 0; j < shells.size(); j++) {
  //          shells.get(j).Move();
        }


    }

}