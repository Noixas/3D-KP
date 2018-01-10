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
  private static Button testButton;

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
    //dropdown menu for algorithm
    //button for printing results
  }

  public void constructInputFields(Pane top) {
    TextField boxACntr = new TextField();
    boxACntr.setPrefSize(80, 20);
    boxACntr.relocate(200, (TOP_HEIGHT-50) /2);

    TextField boxBCntr = new TextField();
    boxBCntr.setPrefSize(80, 20);
    boxBCntr.relocate(480, (TOP_HEIGHT-50) /2);

    TextField boxCCntr = new TextField();
    boxCCntr.setPrefSize(80, 20);
    boxCCntr.relocate(760, (TOP_HEIGHT-50) /2);

    TextField boxAWeight = new TextField();
    boxAWeight.setPrefSize(80, 20);
    boxAWeight.relocate(200, (TOP_HEIGHT+20) /2);

    TextField boxBWeight = new TextField();
    boxBWeight.setPrefSize(80, 20);
    boxBWeight.relocate(480, (TOP_HEIGHT+20) /2);

    TextField boxCWeight = new TextField();
    boxCWeight.setPrefSize(80, 20);
    boxCWeight.relocate(760, (TOP_HEIGHT+20) /2);

    top.getChildren().addAll(boxACntr, boxBCntr, boxCCntr, boxAWeight, boxBWeight, boxCWeight);

  }

  public void constructLabels(Pane top) {
    Label labelBoxA = new Label();
    labelBoxA.setText("Box A");
    labelBoxA.setStyle( "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    labelBoxA.relocate(205, 20);
    labelBoxA.setPrefWidth(82);
    labelBoxA.setWrapText(true);

    Label labelBoxB = new Label();
    labelBoxB.setText("Box B");
    labelBoxB.setStyle( "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    labelBoxB.relocate(485, 20);
    labelBoxB.setPrefWidth(80);
    labelBoxB.setWrapText(true);

    Label labelBoxC = new Label();
    labelBoxC.setText("Box C");
    labelBoxC.setStyle( "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    labelBoxC.relocate(765, 20);
    labelBoxC.setPrefWidth(80);
    labelBoxC.setWrapText(true);

    Label amountA = new Label();
    amountA.setText("Amount");
    amountA.setStyle( "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    amountA.relocate(135, (TOP_HEIGHT-50) / 2);

    Label amountB = new Label();
    amountB.setText("Amount");
    amountB.setStyle( "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    amountB.relocate(405, (TOP_HEIGHT-50) / 2);

    Label amountC = new Label();
    amountC.setText("Amount");
    amountC.setStyle( "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    amountC.relocate(695, (TOP_HEIGHT-50) / 2);

    Label weightA = new Label();
    weightA.setText("Weight");
    weightA.setStyle( "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    weightA.relocate(135, (TOP_HEIGHT+20) / 2);

    Label weightB = new Label();
    weightB.setText("Weight");
    weightB.setStyle( "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    weightB.relocate(405, (TOP_HEIGHT+20) / 2);

    Label weightC = new Label();
    weightC.setText("Weight");
    weightC.setStyle( "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    weightC.relocate(695, (TOP_HEIGHT+20) / 2);

    top.getChildren().addAll(labelBoxA, labelBoxB, labelBoxC, amountA, amountB, amountC, weightA, weightB, weightC);
  }

  public void constructButtons(Pane center) {
    Button calcButton = new Button();
    calcButton.setText("Calculate...");
    calcButton.setPrefSize(50, 50);
    calcButton.relocate(200, 200);

    Button viewCargo = new Button();
    viewCargo.setText("Show 3D-model");
    viewCargo.relocate(100, 100);

    Button newButton = new Button();
    newButton.setText("New BUtton");
    newButton.relocate(300, 300);

    //testButton = new Button("test");

    center.getChildren().addAll(calcButton, viewCargo, newButton);
  }

  public void handle() {

  }

  public static Button getButton() {
    testButton = new Button();
    return testButton;
  }
}
