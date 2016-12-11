import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by ������������������ on 19.07.2016.
 */
public class Main extends Application {

    static Pane root = new Pane();
    public static Game game;
    static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(root, 140,140);
        primaryStage.setTitle("CrossZero");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        game = new Game(3);

    }

}
