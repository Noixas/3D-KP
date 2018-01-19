import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class SceneManager {
  private static Stage stage;
  private static Stage secondStage;
  private static Scene menuScene;
  private static Scene worldScene;
  private static Scene infoScene;
  private static double screenBoundWidth;
  private static double screenBoundHeight;

  public SceneManager(Stage mainStage){
    BorderPane root = new BorderPane();
    Group worldGroup = new Group();
    menuScene = new Scene(root, 640, 700);
    worldScene = new Scene(worldGroup, 1220, 1000, true);
    worldScene.setFill(Color.WHITE);
    stage = mainStage;
    mainStage.setScene(menuScene);
    MenuUI menu = new MenuUI(root);
    WorldUI worldUI = new WorldUI(worldGroup);


    Stage infoStage = new Stage();
    infoStage.setTitle("Info");
    GridPane root2 = new GridPane();
    infoScene = new Scene(root2, 300, 1000);
    secondStage = infoStage;
    //secondStage.setScene(infoScene);
    WorldUI infoUI = new WorldUI(root2);




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
          secondStage.hide();
          mainStage.setScene(menuScene);
          mainStage.centerOnScreen();
        }
      }
    });

    infoScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      public void handle(KeyEvent esc) {
        if(esc.getCode() == KeyCode.ESCAPE) {
          secondStage.hide();
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
      secondStage.hide();
    }
    else if (i == 2) {
      stage.setScene(worldScene);
      stage.centerOnScreen();

      getScreenBounds();
      secondStage.setX((screenBoundWidth / 2) + (stage.getWidth() / 2));
      secondStage.setY(0);
      secondStage.setScene(infoScene);
      secondStage.show();
    }
  }

  public static void getScreenBounds() {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    screenBoundWidth = screenBounds.getWidth();
    screenBoundHeight = screenBounds.getHeight();
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
