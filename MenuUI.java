import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MenuUI {
  private static final int TOP_HEIGHT = 160;

  public MenuUI(BorderPane root) {
    VBox center = new VBox(10);
    center.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #fffd77, #fa8334)");
    center.setPrefSize(200, 20);

    Pane top = new Pane();
    top.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #271033, #388697)");
    top.setPrefSize(1040, TOP_HEIGHT);
    //top.setPadding(new Insets(20));
    constructUI(center, top);
    root.setTop(top);
    root.setMargin(top, new Insets(2));
    root.setCenter(center);
    root.setMargin(center, new Insets(2));


  }

  public void constructUI(VBox center, Pane top) {
    constructInputFields(top);
  }

  public void constructInputFields(Pane top) {
    TextField boxA = new TextField();
    boxA.setPrefSize(80, 50);
    boxA.relocate(200, (TOP_HEIGHT-70) /2);

    TextField boxB = new TextField();
    boxB.setPrefSize(80, 50);
    boxB.relocate(480, (TOP_HEIGHT-70) /2);

    TextField boxC = new TextField();
    boxC.setPrefSize(80, 50);
    boxC.relocate(760, (TOP_HEIGHT-70) /2);

    top.getChildren().addAll(boxA, boxB, boxC);

  }
}
