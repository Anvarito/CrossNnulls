import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by ������������������ on 19.07.2016.
 */
public class Game extends Pane {
    int countX;
    int count0;
    int flag;
    CrossOnZero fields[][];
    Pane rootWinOrDraw = new Pane();

    public Game(int sosise){

        //gridForGame();
        grid(sosise,50);

    }

    //    public void gridForGame(){
//        Rectangle rect = new Rectangle(0,0,50,50);
//        rect.setFill(Color.WHITE);
//        rect.setStroke(Color.BLACK);
//        rect.setOnMouseClicked(event -> {
//            if(flag == 0) {
//                Line line = new Line(0,0,47,47);
//                line.setStroke(Color.BLUE);
//                line.setStrokeWidth(5);
//                Line line2 = new Line(47,0,0,47);
//                line2.setStroke(Color.BLUE);
//                line2.setStrokeWidth(5);
//                if(fields[0][0] != "0") {
//                    flag =1;
//                    Main.root.getChildren().addAll(line, line2);
//                    fields[0][0] = "X";
//                    countX++;
//                }
//                checkToWinner();
//            }else if (flag == 1) {
//                Circle circle = new Circle(25,25,20);
//                circle.setFill(Color.WHITE);
//                circle.setStroke(Color.RED);
//                circle.setStrokeWidth(5);
//                if(fields[0][0] != "X") {
//                    flag =0;
//                    Main.root.getChildren().addAll(circle);
//                    fields[0][0] = "0";
//                    count0++;
//                }
//                checkToWinner();
//            }
//        });

    public void grid(int sosise, int rectSize){
        fields = new CrossOnZero[sosise][sosise];
        for(int i =0;i<sosise;i++){
            for(int j=0;j<sosise; j++){
                CrossOnZero rect = new CrossOnZero(i*rectSize,j*rectSize,rectSize);
                fields[i][j]=rect;
            }
        }
    }





    public void ifWinX(){
        Label labelX = new Label("X fields!!!");
        Rectangle forWinerX = new Rectangle(0,0,150,150);
        forWinerX.setFill(Color.LIGHTCORAL);
        forWinerX.setOnMouseClicked(event -> {
            System.exit(0);
        });
        labelX.setLayoutY(50);
        labelX.setLayoutX(40);
        labelX.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:#000066;-fxbackground-color:lightgrey;");

        rootWinOrDraw.getChildren().addAll(forWinerX,labelX);

    }

    public void ifWin0(){
        Label label0 = new Label("0 fields!!!");
        Rectangle forWiner0 = new Rectangle(0,0,150,150);
        forWiner0.setFill(Color.LIGHTCORAL);
        forWiner0.setOnMouseClicked(event -> {System.exit(0);
        });
        label0.setLayoutY(50);
        label0.setLayoutX(40);
        label0.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:#000066;-fxbackground-color:lightgrey;");
        rootWinOrDraw.getChildren().addAll(forWiner0,label0);

    }
    public void drawGame(){
        Label label0 = new Label("Draw tankgame");
        Rectangle DrawGame = new Rectangle(0,0,150,150);
        DrawGame.setFill(Color.LIGHTCORAL);
        DrawGame.setOnMouseClicked(event -> {System.exit(0);});
        label0.setLayoutY(50);
        label0.setLayoutX(10);
        label0.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:#000066;-fxbackground-color:lightgrey;");
        rootWinOrDraw.getChildren().addAll(DrawGame,label0);
    }


    public void checkToWinner() {
//        if
//             ((fields[0][0] == "X" && fields[0][1] == "X" && fields[0][2] == "X") || /**�������������� ������**/
//                    (fields[1][0] == "X" && fields[1][1] == "X" && fields[1][2] == "X") ||
//                    (fields[2][0] == "X" && fields[2][1] == "X" && fields[2][2] == "X") ||
//
//                    (fields[0][0] == "X" && fields[1][0] == "X" && fields[2][0] == "X") ||/**������������ ������**/
//                    (fields[0][1] == "X" && fields[1][1] == "X" && fields[2][1] == "X") ||
//                    (fields[0][2] == "X" && fields[1][2] == "X" && fields[2][2] == "X") ||
//
//                    (fields[0][0] == "X" && fields[1][1] == "X" && fields[2][2] == "X") || /**������������ ������**/
//                    (fields[2][0] == "X" && fields[1][1] == "X" && fields[0][2] == "X")
//
//            ) {
//            System.out.println("X wins!!!!");
//            Main.scene.setRoot(rootWinOrDraw);
//            ifWinX();
//        }
//        else if (((fields[0][0]== "0" && fields[0][1]=="0" && fields[0][2]=="0") || /**�������������� ������**/
//                (fields[1][0]=="0" && fields[1][1]=="0" && fields[1][2]=="0") ||
//                (fields[2][0]=="0" && fields[2][1]=="0" && fields[2][2]=="0")||
//
//                (fields[0][0]=="0" && fields[1][0]=="0" && fields[2][0]=="0") ||/**������������ ������**/
//                (fields[0][1]=="0" && fields[1][1]=="0" && fields[2][1]=="0") ||
//                (fields[0][2]=="0" && fields[1][2]=="0" && fields[2][2]=="0") ||
//
//                (fields[0][0]=="0" && fields[1][1]=="0" && fields[2][2]=="0") ||/**������������ ������**/
//                (fields[2][0]=="0" && fields[1][1]=="0" && fields[0][2]=="0")

//        )) {
//            System.out.println("0 wins!!!!");
//            Main.scene.setRoot(rootWinOrDraw);
//            ifWin0();
//
//        }else if(countX == 5 || count0 == 5) {
//            System.out.println("draw game");
//            Main.scene.setRoot(rootWinOrDraw);
//            drawGame();

    }



}
