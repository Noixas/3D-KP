import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
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
    Pane center = new Pane();
    center.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #fffd77, #fa8334)");
    center.setPrefSize(200, 20);

    Pane top = new Pane();
    top.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #271033, #388697)");
    top.setPrefSize(1040, TOP_HEIGHT);
    constructUI(center, top);
    root.setTop(top);
    root.setMargin(top, new Insets(2));
    root.setCenter(center);
    root.setMargin(center, new Insets(2));


  }

  public void constructUI(Pane center, Pane top) {
    constructInputFields(top);
    constructLabels(top);
    constructButtons(center);
  }

  public void constructInputFields(Pane top) {
    TextField boxA = new TextField();
    boxA.setPrefSize(80, 50);
    boxA.relocate(200, (TOP_HEIGHT) /2);

    TextField boxB = new TextField();
    boxB.setPrefSize(80, 50);
    boxB.relocate(480, (TOP_HEIGHT) /2);

    TextField boxC = new TextField();
    boxC.setPrefSize(80, 50);
    boxC.relocate(760, (TOP_HEIGHT) /2);

    top.getChildren().addAll(boxA, boxB, boxC);

  }

  public void constructLabels(Pane top) {
    Label labelBoxA = new Label();
    labelBoxA.setText("Amount of box A: ");
    labelBoxA.setStyle( "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    labelBoxA.relocate(205, 15);
    labelBoxA.setPrefWidth(82);
    labelBoxA.setWrapText(true);

    Label labelBoxB = new Label();
    labelBoxB.setText("Amount of box B: ");
    labelBoxB.setStyle( "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    labelBoxB.relocate(485, 15);
    labelBoxB.setPrefWidth(80);
    labelBoxB.setWrapText(true);

    Label labelBoxC = new Label();
    labelBoxC.setText("Amount of box C: ");
    labelBoxC.setStyle( "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    labelBoxC.relocate(765, 15);
    labelBoxC.setPrefWidth(80);
    labelBoxC.setWrapText(true);

    top.getChildren().addAll(labelBoxA, labelBoxB, labelBoxC);
  }

  public void constructButtons(Pane center) {
    Button calcButton = new Button();
    calcButton.setText("Calculate...");
    calcButton.setPrefSize(500, 500);

    Button viewCargo = new Button();
    viewCargo.setText("Show 3D-model");


    center.getChildren().addAll(calcButton, viewCargo);
  }
}
