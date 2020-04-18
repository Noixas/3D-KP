package kp;
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

/**
 * Class that creates and manages which scenes are shown.
 */
public class SceneManager {
  private static Stage stage;
  private static Stage secondStage;
  private static Scene menuScene;
  private static Scene worldScene;
  private static Scene infoScene;
  private static double screenBoundWidth;
  private static double screenBoundHeight;

  /**
   * Creates the scenes and adds them to the stage.
   * @param mainStage     The stage to which the scenes are added to.
   */
  public SceneManager(Stage mainStage){
    BorderPane root = new BorderPane();
    Group worldGroup = new Group();
    menuScene = new Scene(root, 640, 670);
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

  /**
   * Used to choose which scene is shown at any moment.
   * @param i     The integer on which is based which scenes are shown.
   */
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

  /**
   * Fetches the screen width and height for the relocation of the windows.
   */
  public static void getScreenBounds() {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    screenBoundWidth = screenBounds.getWidth();
    screenBoundHeight = screenBounds.getHeight();
  }

  /**
   * Fetches the width of the scene.
   * @return the width of the scene.
   */
  public static double getSceneWidth() {
    return worldScene.getWidth();
  }

  /**
   * Fetches the height of the scene.
   * @return the height of the scene.
   */
  public static double getSceneHeight() {
    return worldScene.getHeight();
  }

  /**
   * Fetches the scene that is being used at that moment.
   * @return the used scene.
   */
  public static Scene getScene() {
    return worldScene;
  }
}
