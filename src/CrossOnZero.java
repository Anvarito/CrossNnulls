import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Created by Анварито on 09.12.2016.
 */
public class CrossOnZero extends Pane {

    public CrossOnZero(int rectX, int rectY, int rectSize){

        Rectangle rect = new Rectangle(rectX,rectY,rectSize,rectSize);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        Main.root.getChildren().addAll(rect);
        rect.setOnMouseClicked(event -> {
            makeCross(rectX,rectY,rectSize);
            makeZero(rectX,rectY,rectSize);
        });

    }

    public void makeCross(int strtX, int strtY,int rectSize){
        Line line1 = new Line(strtX,strtY,strtX+rectSize,strtY+rectSize);
        Line line2 = new Line(strtX+rectSize,strtY,strtX,strtY+rectSize);
        line1.setStroke(Color.GREEN);
        line1.setStrokeWidth(3);
        line2.setStroke(Color.GREEN);
        line2.setStrokeWidth(3);
        Main.root.getChildren().addAll(line1,line2);
    }

    public void makeZero(int rectX, int rectY, int rectSize){
        Circle circle = new Circle(rectX+(rectSize/2),rectY+(rectSize/2),rectSize/3);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.RED);
        circle.setStrokeWidth(3);
        Main.root.getChildren().addAll(circle);
    }
}
