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
  private static TextField boxACntr;
  private static TextField boxBCntr;
  private static TextField boxCCntr;
  private static TextField boxAWeight;
  private static TextField boxBWeight;
  private static TextField boxCWeight;
  private static double amountBoxA;
  private static double amountBoxB;
  private static double amountBoxC;
  private static double weightBoxA;
  private static double weightBoxB;
  private static double weightBoxC;
  private static String chosenAlgorithm;
  private static int errorCheck;
  private static GreedyAlgorithm greedy = new GreedyAlgorithm();

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
  }

  public void constructInputFields(Pane top) {
    boxACntr = new TextField();
    boxACntr.setText("5");
    boxACntr.setPrefSize(80, 20);
    boxACntr.relocate(100, (TOP_HEIGHT-50) /2);

    boxBCntr = new TextField();
    boxBCntr.setText("5");
    boxBCntr.setPrefSize(80, 20);
    boxBCntr.relocate(280, (TOP_HEIGHT-50) /2);

    boxCCntr = new TextField();
    boxCCntr.setText("5");
    boxCCntr.setPrefSize(80, 20);
    boxCCntr.relocate(460, (TOP_HEIGHT-50) /2);

    boxAWeight = new TextField();
    boxAWeight.setText("1");
    boxAWeight.setPrefSize(80, 20);
    boxAWeight.relocate(100, (TOP_HEIGHT+20) /2);

    boxBWeight = new TextField();
    boxBWeight.setText("2");
    boxBWeight.setPrefSize(80, 20);
    boxBWeight.relocate(280, (TOP_HEIGHT+20) /2);

    boxCWeight = new TextField();
    boxCWeight.setText("5");
    boxCWeight.setPrefSize(80, 20);
    boxCWeight.relocate(460, (TOP_HEIGHT+20) /2);

    top.getChildren().addAll(boxACntr, boxBCntr, boxCCntr, boxAWeight, boxBWeight, boxCWeight);

  }

  public void constructLabels(Pane top) {
    Label labelBoxA = new Label();
    labelBoxA.setText("Box A");
    labelBoxA.setStyle(
      "-fx-font-size: 18px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    labelBoxA.relocate(105, 20);
    labelBoxA.setPrefWidth(82);
    labelBoxA.setWrapText(true);

    Label labelBoxB = new Label();
    labelBoxB.setText("Box B");
    labelBoxB.setStyle(
      "-fx-font-size: 18px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    labelBoxB.relocate(285, 20);
    labelBoxB.setPrefWidth(80);
    labelBoxB.setWrapText(true);

    Label labelBoxC = new Label();
    labelBoxC.setText("Box C");
    labelBoxC.setStyle(
      "-fx-font-size: 18px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    labelBoxC.relocate(465, 20);
    labelBoxC.setPrefWidth(80);
    labelBoxC.setWrapText(true);

    Label amountA = new Label();
    amountA.setText("Amount");
    amountA.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    amountA.relocate(35, (TOP_HEIGHT-50) / 2);

    Label amountB = new Label();
    amountB.setText("Amount");
    amountB.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    amountB.relocate(215, (TOP_HEIGHT-50) / 2);

    Label amountC = new Label();
    amountC.setText("Amount");
    amountC.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    amountC.relocate(395, (TOP_HEIGHT-50) / 2);

    Label weightA = new Label();
    weightA.setText("Weight");
    weightA.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    weightA.relocate(40, (TOP_HEIGHT+20) / 2);

    Label weightB = new Label();
    weightB.setText("Weight");
    weightB.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    weightB.relocate(215, (TOP_HEIGHT+20) / 2);

    Label weightC = new Label();
    weightC.setText("Weight");
    weightC.setStyle(
      "-fx-font-size: 15px;" +
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
      "Greedy Volume",
      "Greedy Density",
      "Greedy Value",
      "Extreme Points",
      "Algorithm C");
    algorithms.setPrefSize(140, 20);
    algorithms.relocate(50, 50);
    algorithms.setValue("Greedy Volume");
    center.getChildren().addAll(algorithms);
  }

  public void constructButtons(Pane center) {
    Button calcButton = new Button();
    calcButton.setText("Calculate");
    calcButton.setPrefSize(140, 20);
    calcButton.relocate(50, 90);
    calcButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent calc) {
        errorCheck = 0;
        updateInput();
        if(errorCheck == 0) {
          if(algorithms.getValue() == null) {
            results.setText("No algorithm was selected.");
          }
          else if(algorithms.getValue() == "Greedy Volume") {
            chosenAlgorithm = algorithms.getValue().toString();
            greedy.setID(1);
            greedy.Start();
          }
          else if(algorithms.getValue() == "Greedy Density") {
            chosenAlgorithm = algorithms.getValue().toString();
            greedy.setID(3);
            greedy.Start();
          }
          else if(algorithms.getValue() == "Greedy Value") {
            chosenAlgorithm = algorithms.getValue().toString();
            greedy.setID(2);
            greedy.Start();
          }
          else if(algorithms.getValue() == "Algorithm B") {
            chosenAlgorithm = algorithms.getValue().toString();
          }
          else if(algorithms.getValue() == "Algorithm C") {
            chosenAlgorithm = algorithms.getValue().toString();
          }
        }
        else {
          results.setText("There was an input-error detected.");
        }
        //start the algorithm
      }
    });

    Button display = new Button();
    display.setText("Display solution");
    display.setPrefSize(140, 20);
    display.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent display) {

      }
    });

    Button viewCargo = new Button();
    viewCargo.setText("Show 3D-model");
    viewCargo.setPrefSize(140, 20);
    viewCargo.relocate(50, 130);
    viewCargo.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent swtch) {
        int i = 2;
        SceneManager.changeScene(i);

      }
    });

    Button printResult = new Button();
    printResult.setText("Print results");
    printResult.setPrefSize(140, 20);
    printResult.relocate(50, 170);
    printResult.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent print) {
        results.setText(
          "The algorithm that was used is: " + chosenAlgorithm + "\n" +
          "Total amount of boxes used: " + "46" + "\n" +
          "Total value of the used boxes: " + "635" + "\n" +
          "Total amount of second the algorithm took: " + "1231" + "\n" +
          "Total amount of different possibilities: " + "873" + "\n" + amountBoxA );
      }
    });

    Button resetWorld = new Button();
    resetWorld.setText("Clear all parcels");
    resetWorld.setPrefSize(140, 20);
    resetWorld.relocate(50, 210);
    resetWorld.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent clear) {
        CreateParcel.clearAllParcels();
      }
    });

    center.getChildren().addAll(calcButton, viewCargo, printResult, resetWorld, display);
  }

  public void constructResultField(Pane center) {
    results = new TextArea();
    results.setPrefSize(320, 190);
    results.relocate(230, 50);

    Label resultLabel = new Label();
    resultLabel.setText("Results");
    resultLabel.setStyle(
      "-fx-font-size: 18px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #271033;" +
      "-fx-font-style: italic;");
    resultLabel.relocate(230, 20);
    center.getChildren().addAll(results, resultLabel);
  }

  public void updateInput() {
    try {
      amountBoxA = Double.parseDouble(boxACntr.getText());
    }
    catch(NumberFormatException e) {
      boxACntr.setText("Error");
      errorCheck = 1;
    }

    try {
      amountBoxB = Double.parseDouble(boxBCntr.getText());
    }
    catch(NumberFormatException e) {
      boxBCntr.setText("Error");
      errorCheck = 1;
    }

    try {
      amountBoxC = Double.parseDouble(boxCCntr.getText());
    }
    catch(NumberFormatException e) {
      boxCCntr.setText("Error");
      errorCheck = 1;
    }

    try {
      weightBoxA = Double.parseDouble(boxAWeight.getText());
    }
    catch(NumberFormatException e) {
      boxAWeight.setText("Error");
      errorCheck = 1;
    }

    try {
      weightBoxB = Double.parseDouble(boxBWeight.getText());
    }
    catch(NumberFormatException e) {
      boxBWeight.setText("Error");
      errorCheck = 1;
    }

    try {
      weightBoxC = Double.parseDouble(boxCWeight.getText());
    }
    catch(NumberFormatException e) {
      boxCWeight.setText("Error");
      errorCheck = 1;
    }
  }
}
