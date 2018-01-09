import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class MenuUI {
  public MenuUI(BorderPane root) {
    VBox center = new VBox(10);
    center.setStyle("-fx-background-color: #357dff");
    center.setPrefSize(200, 20);
    center.setPadding
    HBox top = new HBox(10);
    top.setStyle("-fx-background-color: #666666");
    top.setPrefSize(1030, 175);

    root.setTop(top);
    root.setCenter(center);
  }

  public void constructUI(VBox center, HBox top) {

  }
}
