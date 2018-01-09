import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage mainStage) {
    mainStage.setTitle("Phase 3");
    BorderPane root = new BorderPane();
    Scene scene = new Scene(root, 1040, 750);
    mainStage.setScene(scene);
    MenuUI menu = new MenuUI(root);
    AlgorithmZ z = new AlgorithmZ();
    mainStage.show();
  }
}
