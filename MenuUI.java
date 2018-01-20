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
import java.util.ArrayList;

public class MenuUI {
  private static TextArea results;
  private static ComboBox<String> algorithms;

  private static TextField parcelACntr;
  private static TextField parcelBCntr;
  private static TextField parcelCCntr;

  private static TextField parcelAValue;
  private static TextField parcelBValue;
  private static TextField parcelCValue;

  private static double amountParcelA;
  private static double amountParcelB;
  private static double amountParcelC;

  private static double valueParcelA;
  private static double valueParcelB;
  private static double valueParcelC;

  private static TextField widthA;
  private static TextField heightA;
  private static TextField lengthA;

  private static TextField widthB;
  private static TextField heightB;
  private static TextField lengthB;

  private static TextField widthC;
  private static TextField heightC;
  private static TextField lengthC;

  private static String chosenAlgorithm;
  private static int errorCheck;
  private static Algorithm _activeAlgorithm;
  private static GreedyAlgorithm greedy = new GreedyAlgorithm();
  private static AlgorithmZ extremePoints = new AlgorithmZ();
  private static Vector3D vectors;
  private static ArrayList<Parcel> listOfParcels;
  private static SolutionSet solutions;


  public MenuUI() {}

  public MenuUI(BorderPane root) {
    Pane center = new Pane();
    center.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #fffd77, #fa8334)");
    center.setPrefSize(200, 20);
    Pane top = new Pane();
    top.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #271033, #388697)");
    top.setPrefSize(1040, 300);
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
    parcelACntr = new TextField();
    parcelACntr.setText("5");
    parcelACntr.setPrefSize(80, 20);
    parcelACntr.relocate(100, 55);

    parcelBCntr = new TextField();
    parcelBCntr.setText("5");
    parcelBCntr.setPrefSize(80, 20);
    parcelBCntr.relocate(280, 55);

    parcelCCntr = new TextField();
    parcelCCntr.setText("5");
    parcelCCntr.setPrefSize(80, 20);
    parcelCCntr.relocate(460, 55);

    parcelAValue = new TextField();
    parcelAValue.setText("1");
    parcelAValue.setPrefSize(80, 20);
    parcelAValue.relocate(100, 90);

    parcelBValue = new TextField();
    parcelBValue.setText("2");
    parcelBValue.setPrefSize(80, 20);
    parcelBValue.relocate(280, 90);

    parcelCValue = new TextField();
    parcelCValue.setText("5");
    parcelCValue.setPrefSize(80, 20);
    parcelCValue.relocate(460, 90);

    widthA = new TextField();
    widthA.setText("5");
    widthA.setPrefSize(80, 20);
    widthA.relocate(100, 125);

    widthB = new TextField();
    widthB.setText("5");
    widthB.setPrefSize(80, 20);
    widthB.relocate(280, 125);

    widthC = new TextField();
    widthC.setText("5");
    widthC.setPrefSize(80, 20);
    widthC.relocate(460, 125);

    heightA = new TextField();
    heightA.setText("5");
    heightA.setPrefSize(80, 20);
    heightA.relocate(100, 160);

    heightB = new TextField();
    heightB.setText("5");
    heightB.setPrefSize(80, 20);
    heightB.relocate(280, 160);

    heightC = new TextField();
    heightC.setText("5");
    heightC.setPrefSize(80, 20);
    heightC.relocate(460, 160);

    lengthA = new TextField();
    lengthA.setText("5");
    lengthA.setPrefSize(80, 20);
    lengthA.relocate(100, 195);

    lengthB = new TextField();
    lengthB.setText("5");
    lengthB.setPrefSize(80, 20);
    lengthB.relocate(280, 195);

    lengthC = new TextField();
    lengthC.setText("5");
    lengthC.setPrefSize(80, 20);
    lengthC.relocate(460, 195);

    top.getChildren().addAll(
      parcelACntr, parcelBCntr, parcelCCntr,
      parcelAValue, parcelBValue, parcelCValue,
      widthA, widthB, widthC,
      heightA, heightB, heightC,
      lengthA, lengthB, lengthC);
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
    amountA.relocate(35, 55);

    Label amountB = new Label();
    amountB.setText("Amount");
    amountB.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    amountB.relocate(215, 55);

    Label amountC = new Label();
    amountC.setText("Amount");
    amountC.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    amountC.relocate(395, 55);

    Label weightA = new Label();
    weightA.setText("Weight");
    weightA.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    weightA.relocate(40, 90);

    Label weightB = new Label();
    weightB.setText("Weight");
    weightB.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    weightB.relocate(215, 90);

    Label weightC = new Label();
    weightC.setText("Weight");
    weightC.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    weightC.relocate(395, 90);

    Label widthA = new Label();
    widthA.setText("Width");
    widthA.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    widthA.relocate(40, 125);

    Label widthB = new Label();
    widthB.setText("Width");
    widthB.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    widthB.relocate(215, 125);

    Label widthC = new Label();
    widthC.setText("Width");
    widthC.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    widthC.relocate(395, 125);

    Label lengthA = new Label();
    lengthA.setText("Length");
    lengthA.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    lengthA.relocate(215, 160);

    Label lengthB = new Label();
    lengthB.setText("Length");
    lengthB.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    lengthB.relocate(40, 160);

    Label lengthC = new Label();
    lengthC.setText("Length");
    lengthC.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    lengthC.relocate(395, 160);



    Label heightA = new Label();
    heightA.setText("Height");
    heightA.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    heightA.relocate(215, 195);

    Label heightB = new Label();
    heightB.setText("Height");
    heightB.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    heightB.relocate(40, 195);

    Label heightC = new Label();
    heightC.setText("Height");
    heightC.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    heightC.relocate(395, 195);


    top.getChildren().addAll(
      labelBoxA, labelBoxB, labelBoxC,
      amountA, amountB, amountC,
      weightA, weightB, weightC,
      widthA, widthB, widthC,
      lengthA, lengthB, lengthC,
      heightA, heightB, heightC);
  }

  public void constructChoices(Pane center) {
    algorithms = new ComboBox<String>();
    algorithms.getItems().addAll(
      "Greedy Volume",
      "Greedy Density",
      "Greedy Value",
      "Extreme Points",
      "Algorithm C");
    algorithms.setPrefSize(140, 20);
    algorithms.relocate(50, 50);
    algorithms.setValue("Greedy Volume");
    _activeAlgorithm = greedy;
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
        makeParcelList();
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
          else if(algorithms.getValue() == "Extreme Points") {
            results.setText("Algorithm B has started calculating the possibilities.");
            chosenAlgorithm = algorithms.getValue().toString();
            extremePoints.Start();
          }
          else if(algorithms.getValue() == "Algorithm C") {
            chosenAlgorithm = algorithms.getValue().toString();
          }
        }
        else {
          results.setText("There was an input-error detected.");
        }
      }
    });

    Button display = new Button();
    display.setText("Display solution");
    display.setPrefSize(140, 20);
    display.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent display) {
        errorCheck = 0;
        updateInput();
        if(errorCheck == 0) {
          if(algorithms.getValue() == null) {
            results.setText("No algorithm was selected.");
          }
          else if(algorithms.getValue() == "Greedy Volume") {
            chosenAlgorithm = algorithms.getValue().toString();
            greedy.setID(1);
            greedy.display();
          }
          else if(algorithms.getValue() == "Greedy Density") {
            chosenAlgorithm = algorithms.getValue().toString();
            greedy.setID(3);
            greedy.display();
          }
          else if(algorithms.getValue() == "Greedy Value") {
            chosenAlgorithm = algorithms.getValue().toString();
            greedy.setID(2);
            greedy.display();
          }
          else if(algorithms.getValue() == "Extreme Points") {
            results.setText("Algorithm B has started calculating the possibilities.");
            chosenAlgorithm = algorithms.getValue().toString();
            extremePoints.display();
          }
          else if(algorithms.getValue() == "Algorithm C") {
            chosenAlgorithm = algorithms.getValue().toString();
          }
        }
        else {
          results.setText("There was an input-error detected.");
        }
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
                            solutions = new SolutionSet();
                            results.setText(
                                    "The algorithm that was used is: " + chosenAlgorithm + "\n" +
                                    "Total amount of boxes used: " + "46" + "\n" +
                                    "Total value of the used boxes: " + "635" + "\n" +
                                    "Total amount of second the algorithm took: " + solutions.getTotalTime() + "\n" +
                                    "Total amount of different possibilities: " + "4365" + "\n" );
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
      amountParcelA = Double.parseDouble(parcelACntr.getText());
    }
    catch(NumberFormatException e) {
      parcelACntr.setText("Error");
      errorCheck = 1;
    }

    try {
      amountParcelB = Double.parseDouble(parcelBCntr.getText());
    }
    catch(NumberFormatException e) {
      parcelBCntr.setText("Error");
      errorCheck = 1;
    }

    try {
      amountParcelC = Double.parseDouble(parcelCCntr.getText());
    }
    catch(NumberFormatException e) {
      parcelCCntr.setText("Error");
      errorCheck = 1;
    }

    try {
      valueParcelA = Double.parseDouble(parcelAValue.getText());
    }
    catch(NumberFormatException e) {
      parcelAValue.setText("Error");
      errorCheck = 1;
    }

    try {
      valueParcelB = Double.parseDouble(parcelBValue.getText());
    }
    catch(NumberFormatException e) {
      parcelBValue.setText("Error");
      errorCheck = 1;
    }

    try {
      valueParcelC = Double.parseDouble(parcelCValue.getText());
    }
    catch(NumberFormatException e) {
      parcelCValue.setText("Error");
      errorCheck = 1;
    }
  }

  public void makeParcelList() {
    listOfParcels = new ArrayList<Parcel>();

    Parcel parcelA = new ParcelA();
    Parcel parcelB = new ParcelB();
    Parcel parcelC = new ParcelC();

    double xA = Double.parseDouble(lengthA.getText());
    double yA = Double.parseDouble(widthA.getText());;
    double zA = Double.parseDouble(heightA.getText());;

    double xB = Double.parseDouble(lengthB.getText());;
    double yB = Double.parseDouble(widthB.getText());;
    double zB = Double.parseDouble(heightB.getText());;

    double xC = Double.parseDouble(lengthC.getText());;
    double yC = Double.parseDouble(widthC.getText());;
    double zC = Double.parseDouble(heightC.getText());;

    for(int i = 0; i < amountParcelA; i++) {
      parcelA.setSize(new Vector3D(xA, yA, zA));
      parcelA.setValue(valueParcelA);
      listOfParcels.add(parcelA);
    }

    for(int j = 0; j < amountParcelB; j++) {
      parcelB.setSize(new Vector3D(xB, yB, zB));
      parcelB.setValue(valueParcelB);
      listOfParcels.add(parcelB);
    }

    for(int k = 0; k < amountParcelC; k++) {
      parcelC.setSize(new Vector3D(xC, yC, zC));
      parcelC.setValue(valueParcelC);
      listOfParcels.add(parcelC);
    }
  }

  public ArrayList<Parcel> getParcelList() {
    return listOfParcels;
  }
}
