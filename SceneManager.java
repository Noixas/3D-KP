import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.Group;
import javafx.scene.Scene;

public class SceneManager {
  private static Stage stage;
  private static Scene menuScene;
  private static Scene worldScene;

  public SceneManager(Stage mainStage){
    BorderPane root = new BorderPane();
    Group worldGroup = new Group();
    menuScene = new Scene(root, 1040, 750);
    worldScene = new Scene(worldGroup, 1040, 750);
    stage = mainStage;
    mainStage.setScene(menuScene);
    MenuUI menu = new MenuUI(root);
    WorldUI world = new WorldUI(worldGroup);
  }

  public static void changeScene(int i) {

    if (i == 1) {
      stage.setScene(menuScene);
    }
    else if (i == 2) {
      stage.setScene(worldScene);
    }
  }
}
