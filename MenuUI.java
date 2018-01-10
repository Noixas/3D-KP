import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MenuUI {
  private static final int TOP_HEIGHT = 160;
  private static TextArea results;
  private static ComboBox algorithms;

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
    constructChoices(center);
    constructResultField(center);
    //textfield for results
  }

  public void constructInputFields(Pane top) {
    TextField boxACntr = new TextField();
    boxACntr.setPrefSize(80, 20);
    boxACntr.relocate(100, (TOP_HEIGHT-50) /2);

    TextField boxBCntr = new TextField();
    boxBCntr.setPrefSize(80, 20);
    boxBCntr.relocate(280, (TOP_HEIGHT-50) /2);

    TextField boxCCntr = new TextField();
    boxCCntr.setPrefSize(80, 20);
    boxCCntr.relocate(460, (TOP_HEIGHT-50) /2);

    TextField boxAWeight = new TextField();
    boxAWeight.setPrefSize(80, 20);
    boxAWeight.relocate(100, (TOP_HEIGHT+20) /2);

    TextField boxBWeight = new TextField();
    boxBWeight.setPrefSize(80, 20);
    boxBWeight.relocate(280, (TOP_HEIGHT+20) /2);

    TextField boxCWeight = new TextField();
    boxCWeight.setPrefSize(80, 20);
    boxCWeight.relocate(460, (TOP_HEIGHT+20) /2);

    top.getChildren().addAll(boxACntr, boxBCntr, boxCCntr, boxAWeight, boxBWeight, boxCWeight);

  }

  public void constructLabels(Pane top) {
    Label labelBoxA = new Label();
    labelBoxA.setText("Box A");
    labelBoxA.setStyle( "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    labelBoxA.relocate(105, 20);
    labelBoxA.setPrefWidth(82);
    labelBoxA.setWrapText(true);

    Label labelBoxB = new Label();
    labelBoxB.setText("Box B");
    labelBoxB.setStyle( "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    labelBoxB.relocate(285, 20);
    labelBoxB.setPrefWidth(80);
    labelBoxB.setWrapText(true);

    Label labelBoxC = new Label();
    labelBoxC.setText("Box C");
    labelBoxC.setStyle( "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    labelBoxC.relocate(465, 20);
    labelBoxC.setPrefWidth(80);
    labelBoxC.setWrapText(true);

    Label amountA = new Label();
    amountA.setText("Amount");
    amountA.setStyle( "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    amountA.relocate(35, (TOP_HEIGHT-50) / 2);

    Label amountB = new Label();
    amountB.setText("Amount");
    amountB.setStyle( "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    amountB.relocate(215, (TOP_HEIGHT-50) / 2);

    Label amountC = new Label();
    amountC.setText("Amount");
    amountC.setStyle( "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    amountC.relocate(395, (TOP_HEIGHT-50) / 2);

    Label weightA = new Label();
    weightA.setText("Weight");
    weightA.setStyle( "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    weightA.relocate(40, (TOP_HEIGHT+20) / 2);

    Label weightB = new Label();
    weightB.setText("Weight");
    weightB.setStyle( "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    weightB.relocate(215, (TOP_HEIGHT+20) / 2);

    Label weightC = new Label();
    weightC.setText("Weight");
    weightC.setStyle( "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffe882;" +
                        "-fx-font-style: italic;");
    weightC.relocate(395, (TOP_HEIGHT+20) / 2);

    top.getChildren().addAll(
      labelBoxA, labelBoxB, labelBoxC,
      amountA, amountB, amountC,
      weightA, weightB, weightC);
  }

  public void constructChoices(Pane center) {
    algorithms = new ComboBox();
    algorithms.getItems().addAll(
      "Algorithm A",
      "Algorithm B",
      "Algorithm C");
    algorithms.setPrefSize(120, 20);
    algorithms.relocate(100, 50);
    center.getChildren().addAll(algorithms);
  }

  public void constructButtons(Pane center) {
    Button calcButton = new Button();
    calcButton.setText("Calculate");
    calcButton.setPrefSize(120, 20);
    calcButton.relocate(100, 100);
    calcButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent calc) {
          System.out.println("Calculate");
      }
    });



    Button viewCargo = new Button();
    viewCargo.setText("Show 3D-model");
    viewCargo.setPrefSize(120, 20);
    viewCargo.relocate(100, 150);
    viewCargo.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent swtch) {
        int i = 2;
        SceneManager.changeScene(i);

      }
    });

    Button printResult = new Button();
    printResult.setText("Print results");
    printResult.setPrefSize(120, 20);
    printResult.relocate(100, 200);
    printResult.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent print) {
        results.setText(
        "The algorithm that was used is: " + algorithms.getValue() + "\n" +
        "Total amount of boxes used: " + "46" + "\n" +
        "Total value of the used boxes: " + "635" + "\n" +
        "Total amount of second the algorithm took: " + "1231" + "\n" +
        "Total amount of different possibilities: " + "873" );
      }
    });

    center.getChildren().addAll(calcButton, viewCargo, printResult);
  }

  public void constructResultField(Pane center) {
    results = new TextArea();
    results.setPrefSize(280, 180);
    results.relocate(280, 50);

    Label resultLabel = new Label();
    resultLabel.setText("Results");
    resultLabel.setStyle(
      "-fx-font-size: 18px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #271033;" +
      "-fx-font-style: italic;");
    resultLabel.relocate(280, 20);
    //results.setEditable(false);
    center.getChildren().addAll(results, resultLabel);
  }
}
