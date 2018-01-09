import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;


public class MenuUI {
  public MenuUI(BorderPane root) {
    VBox center = new VBox(10);
    center.setStyle("-fx-background-color: #357dff");
    center.setPrefSize(200, 20);

    HBox top = new HBox(10);
    top.setStyle("-fx-background-color: #666666");
    top.setPrefSize(1030, 175);

    root.setTop(top);
    root.setMargin(top, new Insets(2));
    root.setCenter(center);
    root.setMargin(center, new Insets(2));

    constructUI(center, top);
  }

  public void constructUI(VBox center, HBox top) {
    constructInputFields(top);
  }

  public void constructInputFields(HBox top) {
    TextField boxA = new TextField();
    boxA.setPrefSize(200, 150);

    top.getChildren().addAll(boxA);
  }
}
