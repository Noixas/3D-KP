import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class SceneManager {
  private static Stage stage;
  private static Scene menuScene;
  private static Scene worldScene;

  public SceneManager(Stage mainStage){
    BorderPane root = new BorderPane();
    Group worldGroup = new Group();
    BorderPane worldPane = new BorderPane();
    menuScene = new Scene(root, 640, 460);
    worldScene = new Scene(worldGroup, 1220, 980, true);
    worldScene.setFill(Color.WHITE);
    stage = mainStage;
    mainStage.setScene(menuScene);
    MenuUI menu = new MenuUI(root);
    WorldUI world = new WorldUI(worldGroup);


    menuScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      public void handle(KeyEvent esc) {
        if(esc.getCode() == KeyCode.ESCAPE) {
          System.exit(0);
        }
      }
    });

    worldScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      public void handle(KeyEvent esc) {
        if(esc.getCode() == KeyCode.ESCAPE) {
          //System.exit(0);
          mainStage.setScene(menuScene);
          mainStage.centerOnScreen();
        }
      }
    });
  }

  public static void changeScene(int i) {

    if (i == 1) {
      stage.setScene(menuScene);
      stage.centerOnScreen();
    }
    else if (i == 2) {
      stage.setScene(worldScene);
      stage.centerOnScreen();
    }
  }

  public static double getSceneWidth() {
    return worldScene.getWidth();
  }

  public static double getSceneHeight() {
    return worldScene.getHeight();
  }

  public static Scene getScene() {
    return worldScene;
  }
}
