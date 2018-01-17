import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class Main extends Application {
  private static Stage stage;
  private static Stage secondStage;
  public static void main(String[] args) {

    launch(args);
  }

  public void start(Stage mainStage) {
    stage = mainStage;
    Stage infoStage = new Stage();
    secondStage = infoStage;
    infoStage.setTitle("Info");
    mainStage.centerOnScreen();
    mainStage.setTitle("Phase 3");
    SceneManager scenemanager = new SceneManager(mainStage);
    //AlgorithmZ z = new AlgorithmZ();
    mainStage.show();
  }
}
