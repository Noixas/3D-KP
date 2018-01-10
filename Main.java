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

  public static void main(String[] args) {

    launch(args);
  }

  public void start(Stage mainStage) {
    mainStage.setTitle("Phase 3");
    BorderPane root = new BorderPane();
    Group worldGroup = new Group();
    Scene menuScene = new Scene(root, 1040, 750);
    Scene worldScene = new Scene(worldGroup, 1040, 750);
    mainStage.setScene(menuScene);
    MenuUI menu = new MenuUI(root);
    WorldUI world = new WorldUI(worldGroup);
    AlgorithmZ z = new AlgorithmZ();



    mainStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
      public void handle(KeyEvent esc) {
        if(esc.getCode() == KeyCode.ESCAPE) {
          System.exit(0);
        }
      }
    });



    Button testButton = MenuUI.getButton();
    testButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        System.out.println("test");
        //mainStage.setScene(worldScene);
      }
    });




  mainStage.show();
  }
}
