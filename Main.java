import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

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


    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      public void handle(KeyEvent esc) {
        if(esc.getCode() == KeyCode.ESCAPE) {
          System.exit(0);
        }
      }
    });



  }
}
