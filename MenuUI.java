import javafx.animation.AnimationTimer;
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
import java.util.LinkedList;

/**
 * This class creates all of the attributes of which the userinterface consists.
 */

public class MenuUI {
  private static TextArea results;
  private static ComboBox<String> algorithms;
  private static ComboBox<String> presets;

  private static TextField parcelACntr;
  private static TextField parcelBCntr;
  private static TextField parcelCCntr;

  private static TextField parcelAValue;
  private static TextField parcelBValue;
  private static TextField parcelCValue;

  private static TextField widthA;
  private static TextField heightA;
  private static TextField lengthA;

  private static TextField widthB;
  private static TextField heightB;
  private static TextField lengthB;

  private static TextField widthC;
  private static TextField heightC;
  private static TextField lengthC;

  private static TextField containerX;
  private static TextField containerY;
  private static TextField containerZ;

  private static double amountParcelA;
  private static double amountParcelB;
  private static double amountParcelC;

  private static double valueParcelA;
  private static double valueParcelB;
  private static double valueParcelC;

  private static double xA;
  private static double yA;
  private static double zA;

  private static double xB;
  private static double yB;
  private static double zB;

  private static double xC;
  private static double yC;
  private static double zC;

  private static double contX = 16.5;
  private static double contY = 2.5;
  private static double contZ = 4;

  private static String chosenAlgorithm = "Empty";
  private static int errorCheck;
  private static Algorithm _activeAlgorithm;
  private static GreedyAlgorithm greedy = new GreedyAlgorithm();
  private static AlgorithmZ extremePoints = new AlgorithmZ();
  private static AlgorithmPentomino extremePentominos = new AlgorithmPentomino();
  private static Vector3D vectors;
  private static ArrayList<Parcel> listOfParcels;
  private static SolutionSet solutions;
  private static AnimationTimer infoTimer;
  private static AnimationTimer inputTimer;

 /**
  * Default constructor
  */
  public MenuUI() {}

 /**
  * Constructs the borderpane that gets added to the menuScene in sceneManager.
  * @param root borderpane that is added to the menuScene.
  */
  public MenuUI(BorderPane root) {
    Pane bottom = new Pane();
    bottom.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #fffd77, #fa8334)");
    bottom.setPrefSize(640, 300);
    Pane top = new Pane();
    top.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #271033, #388697)");
    top.setPrefSize(640, 250);
    Pane center = new Pane();
    center.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #388697, #271033)");
    center.setPrefSize(640, 110);

    constructUI(center, top, bottom);
    root.setTop(top);
    root.setMargin(top, new Insets(2));
    root.setCenter(center);
    root.setMargin(center, new Insets(2));
    root.setBottom(bottom);
    root.setMargin(bottom, new Insets(2));

    infoTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                    WorldUI.printInfo();
                    //WorldUI.printResults();
            }
    };

    inputTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
              updateInputFields();
            }
    };
    inputTimer.start();
  }

  /**
   * Is used in the sceneManager class to construct the menu.
   * @param center    The pane that gets added to the center of the borderpane.
   * @param top       The pane that gets added to the top of the borderpane.
   * @param bottom    The pane that gets added to the bottom of the borderpane.
   */
  public void constructUI(Pane center, Pane top, Pane bottom) {
    constructInputFields(top, center);
    constructLabels(top, center);
    constructButtons(bottom);
    constructChoices(bottom);
    constructResultField(bottom);
  }

  /**
   * Constructs the TextFields and adds them to the top and center panes.
   * @param top       The pane to which TextFields for the input of the parcels are added to.
   * @param center    The pane to which TextFields for the input of the container size are added to.
   */
  public void constructInputFields(Pane top, Pane center) {
    parcelACntr = new TextField();
    parcelACntr.setText("0");
    parcelACntr.setPrefSize(80, 20);
    parcelACntr.relocate(100, 55);

    parcelBCntr = new TextField();
    parcelBCntr.setText("0");
    parcelBCntr.setPrefSize(80, 20);
    parcelBCntr.relocate(280, 55);

    parcelCCntr = new TextField();
    parcelCCntr.setText("0");
    parcelCCntr.setPrefSize(80, 20);
    parcelCCntr.relocate(460, 55);

    parcelAValue = new TextField();
    parcelAValue.setText("3");
    parcelAValue.setPrefSize(80, 20);
    parcelAValue.relocate(100, 90);

    parcelBValue = new TextField();
    parcelBValue.setText("4");
    parcelBValue.setPrefSize(80, 20);
    parcelBValue.relocate(280, 90);

    parcelCValue = new TextField();
    parcelCValue.setText("5");
    parcelCValue.setPrefSize(80, 20);
    parcelCValue.relocate(460, 90);

    //x
    lengthA = new TextField();
    lengthA.setText("1");
    lengthA.setPrefSize(80, 20);
    lengthA.relocate(100, 125);

    lengthB = new TextField();
    lengthB.setText("1");
    lengthB.setPrefSize(80, 20);
    lengthB.relocate(280, 125);

    lengthC = new TextField();
    lengthC.setText("1.5");
    lengthC.setPrefSize(80, 20);
    lengthC.relocate(460, 125);

    //y
    widthA = new TextField();
    widthA.setText("1");
    widthA.setPrefSize(80, 20);
    widthA.relocate(100, 160);

    widthB = new TextField();
    widthB.setText("1.5");
    widthB.setPrefSize(80, 20);
    widthB.relocate(280, 160);

    widthC = new TextField();
    widthC.setText("1.5");
    widthC.setPrefSize(80, 20);
    widthC.relocate(460, 160);

    //z
    heightA = new TextField();
    heightA.setText("2");
    heightA.setPrefSize(80, 20);
    heightA.relocate(100, 195);

    heightB = new TextField();
    heightB.setText("2");
    heightB.setPrefSize(80, 20);
    heightB.relocate(280, 195);

    heightC = new TextField();
    heightC.setText("1.5");
    heightC.setPrefSize(80, 20);
    heightC.relocate(460, 195);

    containerX = new TextField();
    containerX.setText("16.5");
    containerX.setPrefSize(80, 20);
    containerX.relocate(100, 55);

    containerY = new TextField();
    containerY.setText("2.5");
    containerY.setPrefSize(80, 20);
    containerY.relocate(280, 55);

    containerZ = new TextField();
    containerZ.setText("4");
    containerZ.setPrefSize(80, 20);
    containerZ.relocate(460, 55);

    top.getChildren().addAll(
      parcelACntr, parcelBCntr, parcelCCntr,
      parcelAValue, parcelBValue, parcelCValue,
      widthA, widthB, widthC,
      heightA, heightB, heightC,
      lengthA, lengthB, lengthC);

    center.getChildren().addAll(containerX, containerY, containerZ);
  }

  /**
   * Constructs the labels that gives information about what each inputfield is linked to.
   * @param top       The pane to which the labels are added that set the information of the parcels.
   * @param center    The pane to which the labels are added that set the information of the containers.
   */
  public void constructLabels(Pane top, Pane center) {
    Label labelBoxA = new Label();
    labelBoxA.setText("Parcel A");
    labelBoxA.setStyle(
      "-fx-font-size: 18px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    labelBoxA.relocate(105, 20);
    labelBoxA.setPrefWidth(82);
    labelBoxA.setWrapText(true);

    Label labelBoxB = new Label();
    labelBoxB.setText("Parcel B");
    labelBoxB.setStyle(
      "-fx-font-size: 18px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    labelBoxB.relocate(285, 20);
    labelBoxB.setPrefWidth(80);
    labelBoxB.setWrapText(true);

    Label labelBoxC = new Label();
    labelBoxC.setText("Parcel C");
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
    weightA.setText("Value");
    weightA.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    weightA.relocate(40, 90);

    Label weightB = new Label();
    weightB.setText("Value");
    weightB.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    weightB.relocate(215, 90);

    Label weightC = new Label();
    weightC.setText("Value");
    weightC.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    weightC.relocate(395, 90);

    Label lengthA = new Label();
    lengthA.setText("Length");
    lengthA.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    lengthA.relocate(40, 125);

    Label lengthB = new Label();
    lengthB.setText("Length");
    lengthB.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    lengthB.relocate(215, 125);

    Label lengthC = new Label();
    lengthC.setText("Length");
    lengthC.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    lengthC.relocate(395, 125);

    Label widthA = new Label();
    widthA.setText("Width");
    widthA.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    widthA.relocate(40, 160);

    Label widthB = new Label();
    widthB.setText("Width");
    widthB.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    widthB.relocate(215, 160);

    Label widthC = new Label();
    widthC.setText("Width");
    widthC.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    widthC.relocate(395, 160);

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

    Label containerLabel = new Label();
    containerLabel.setText("Container Size");
    containerLabel.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    containerLabel.relocate(105, 20);


    Label containerLength = new Label();
    containerLength.setText("Length");
    containerLength.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    containerLength.relocate(35, 55);

    Label containerWidth = new Label();
    containerWidth.setText("Width");
    containerWidth.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    containerWidth.relocate(215, 55);

    Label containerHeight = new Label();
    containerHeight.setText("Height");
    containerHeight.setStyle(
      "-fx-font-size: 15px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #ffe882;" +
      "-fx-font-style: italic;");
    containerHeight.relocate(395, 55);


    top.getChildren().addAll(
      labelBoxA, labelBoxB, labelBoxC,
      amountA, amountB, amountC,
      weightA, weightB, weightC,
      widthA, widthB, widthC,
      lengthA, lengthB, lengthC,
      heightA, heightB, heightC);

    center.getChildren().addAll(
      containerLabel, containerWidth,
      containerHeight, containerLength);
  }

  /**
   * Creates dropdown menu's for chosing the algorithms and presets.
   * @param bottom    The pane to which the ComboBoxes are added to.
   */
  public void constructChoices(Pane bottom) {
    algorithms = new ComboBox<String>();
    algorithms.getItems().addAll(
      "Greedy Volume",
      "Greedy Density",
      "Greedy Value",
      "Extreme Points",
      "Extreme Pentominos");
    algorithms.setPrefSize(140, 20);
    algorithms.relocate(50, 20);
    algorithms.setValue("Greedy Volume");

    presets = new ComboBox<String>();
    presets.getItems().addAll(
      "Manual",
      "All A",
      "All B",
      "All C",
      "A and B",
      "A and C",
      "B and C",
      "Max Greedy Volume/Value",
      "Max Greedy Density",
      "Max Extreme Points");
    presets.setPrefSize(140, 20);
    presets.relocate(50, 60);
    presets.setValue("Manual");
    bottom.getChildren().addAll(presets, algorithms);
  }

  /**
   * Creates the buttons and their actions.
   * @param Bottom    The pane to which all of the buttons are added.
   */
  public void constructButtons(Pane bottom) {
    Button calcButton = new Button();
    calcButton.setText("Calculate");
    calcButton.setPrefSize(140, 20);
    calcButton.relocate(50, 100);
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
            _activeAlgorithm = greedy;
            greedy.setID(1);
            greedy.Start(listOfParcels);
          }
          else if(algorithms.getValue() == "Greedy Density") {
            chosenAlgorithm = algorithms.getValue().toString();
            _activeAlgorithm = greedy;
            greedy.setID(3);
            greedy.Start(listOfParcels);
          }
          else if(algorithms.getValue() == "Greedy Value") {
            chosenAlgorithm = algorithms.getValue().toString();
            _activeAlgorithm = greedy;
            greedy.setID(2);
            greedy.Start(listOfParcels);
          }
          else if(algorithms.getValue() == "Extreme Points") {
            chosenAlgorithm = algorithms.getValue().toString();
            System.out.println("Wait, computing best possible way, this could take some seconds");
            if(presets.getValue() ==  "Max Extreme Points"){
            extremePoints.findBest();
            results.setText("Wait, computing best possible way, this could take a few seconds");
            }
            _activeAlgorithm = extremePoints;
            extremePoints.Start(listOfParcels);
          }
          else if(algorithms.getValue() == "Extreme Pentominos") {
            _activeAlgorithm = extremePentominos;
            extremePentominos.Start(listOfParcels);
            chosenAlgorithm = algorithms.getValue().toString();
          }
        }
        else {
          results.setText("There was an input-error detected.");
        }

        solutions = _activeAlgorithm.getSolutions().get(0);
        infoTimer.start();
        results.setText(getResultText());
      }
    });

    Button reset = new Button();
    reset.setText("Reset");
    reset.setPrefSize(140, 20);
    reset.relocate(50, 180);
    reset.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent display) {
        CreateParcel.clearAllParcels();
        if(_activeAlgorithm == greedy) {
          greedy = new GreedyAlgorithm();
          _activeAlgorithm = greedy;
        }
        else if(_activeAlgorithm == extremePoints){
          extremePoints = new AlgorithmZ();
          _activeAlgorithm = extremePoints;
        }
        else{
          extremePentominos = new AlgorithmPentomino();
          _activeAlgorithm = extremePentominos;
        }
        results.setText("");
      }
    });

    Button viewCargo = new Button();
    viewCargo.setText("Show 3D-model");
    viewCargo.setPrefSize(140, 20);
    viewCargo.relocate(50, 140);
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

                    }
            });
    Button resetWorld = new Button();
    resetWorld.setText("Reset");
    resetWorld.setText("Clear all parcels");
    resetWorld.setPrefSize(140, 20);
    resetWorld.relocate(50, 210);
    resetWorld.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent clear) {
                            CreateParcel.clearAllParcels();
                            if(_activeAlgorithm == greedy) {
                              greedy = new GreedyAlgorithm();
                              _activeAlgorithm = greedy;
                            }
                            else if(_activeAlgorithm == extremePoints){
                              extremePoints = new AlgorithmZ();
                              _activeAlgorithm = extremePoints;
                            }
                            else{
                              extremePentominos = new AlgorithmPentomino();
                              _activeAlgorithm = extremePentominos;
                            }
                    }
            });

    Button display = new Button();
    display.setText("Display solution");
    display.setPrefSize(140, 20);
    display.relocate(50, 220);
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
            _activeAlgorithm = extremePoints;
            chosenAlgorithm = algorithms.getValue().toString();
            extremePoints.display();
          }
          else if(algorithms.getValue() == "Extreme Pentominos") {
            chosenAlgorithm = algorithms.getValue().toString();
            _activeAlgorithm = extremePentominos;
            extremePentominos.display();
          }
        }
        else {
          results.setText("There was an input-error detected.");
        }
      }
    });

    Button clearParcels = new Button();
    clearParcels.setText("Clear All Parcels");
    clearParcels.setPrefSize(140, 20);
    clearParcels.relocate(50, 260);
    clearParcels.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent display) {
        CreateParcel.clearAllParcels();
      }
    });

    bottom.getChildren().addAll(calcButton, viewCargo, reset, display, clearParcels);
  }

  /**
   * Creates the TextField where the results are displayed.
   * @param bottom    The pane to which the TextField is added.
   */
  public void constructResultField(Pane bottom) {
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
    bottom.getChildren().addAll(results, resultLabel);
  }

  /**
   * Checks the 'amount' inputFields for non-numerical inputs, if the amountfields are empty it will be seen as a value of 0.
   * Checks the rest of the inputFields for non-numerical inputs, if these are empty there will be an error displayed in the resultsField.
   */
  public void updateInput() {
    try {
      if(parcelACntr.getText().isEmpty() || parcelBCntr.getText().isEmpty() || parcelCCntr.getText().isEmpty()) {
        if(parcelACntr.getText().isEmpty() && parcelBCntr.getText().isEmpty() && parcelCCntr.getText().isEmpty()) {
          amountParcelA = 0;
          amountParcelB = 0;
          amountParcelC = 0;
        }
        else if(parcelACntr.getText().isEmpty() && parcelBCntr.getText().isEmpty()) {
          amountParcelA = 0;
          amountParcelB = 0;
          amountParcelC = Double.parseDouble(parcelCCntr.getText());
        }
        else if(parcelBCntr.getText().isEmpty() && parcelCCntr.getText().isEmpty()) {
          amountParcelB = 0;
          amountParcelC = 0;
          amountParcelA = Double.parseDouble(parcelACntr.getText());
        }
        else if(parcelACntr.getText().isEmpty() && parcelCCntr.getText().isEmpty()) {
          amountParcelA = 0;
          amountParcelC = 0;
          amountParcelB = Double.parseDouble(parcelBCntr.getText());
        }
        else if(parcelACntr.getText().isEmpty()) {
          amountParcelA = 0;
          amountParcelB = Double.parseDouble(parcelBCntr.getText());
          amountParcelC = Double.parseDouble(parcelCCntr.getText());
        }
        else if(parcelBCntr.getText().isEmpty()) {
          amountParcelB = 0;
          amountParcelA = Double.parseDouble(parcelACntr.getText());
          amountParcelC = Double.parseDouble(parcelCCntr.getText());
        }
        else if(parcelCCntr.getText().isEmpty()) {
          amountParcelC = 0;
          amountParcelA = Double.parseDouble(parcelACntr.getText());
          amountParcelB = Double.parseDouble(parcelBCntr.getText());
        }
      }
      else {
        amountParcelA = Double.parseDouble(parcelACntr.getText());
        amountParcelB = Double.parseDouble(parcelBCntr.getText());
        amountParcelC = Double.parseDouble(parcelCCntr.getText());
      }

      valueParcelA = Double.parseDouble(parcelAValue.getText());
      valueParcelB = Double.parseDouble(parcelBValue.getText());
      valueParcelC = Double.parseDouble(parcelCValue.getText());

      xA = Double.parseDouble(lengthA.getText());
      yA = Double.parseDouble(widthA.getText());;
      zA = Double.parseDouble(heightA.getText());;

      xB = Double.parseDouble(lengthB.getText());;
      yB = Double.parseDouble(widthB.getText());;
      zB = Double.parseDouble(heightB.getText());;

      xC = Double.parseDouble(lengthC.getText());;
      yC = Double.parseDouble(widthC.getText());;
      zC = Double.parseDouble(heightC.getText());;

      contX = Double.parseDouble(containerX.getText());
      contY = Double.parseDouble(containerY.getText());
      contZ = Double.parseDouble(containerZ.getText());
    }
    catch(NumberFormatException e) {
      errorCheck = 1;
    }
    catch(IndexOutOfBoundsException e) {
      System.out.println("test");
    }
  }

  /**
   * Creates a list of parcels that are used in the algorithms.
   */
  public void makeParcelList() {
    listOfParcels = new ArrayList<Parcel>();

    Parcel parcelA = new ParcelA();
    Parcel parcelB = new ParcelB();
    Parcel parcelC = new ParcelC();

    parcelA.setSize(new Vector3D(xA, yA, zA));
    parcelA.setValue(valueParcelA);
    parcelB.setSize(new Vector3D(xB, yB, zB));
    parcelB.setValue(valueParcelB);
    parcelC.setSize(new Vector3D(xC, yC, zC));
    parcelC.setValue(valueParcelC);

    for(int i = 0; i < amountParcelA; i++) {
      listOfParcels.add(parcelA.clone());
    }

    for(int j = 0; j < amountParcelB; j++) {
      listOfParcels.add(parcelB.clone());
    }

    for(int k = 0; k < amountParcelC; k++) {
      listOfParcels.add(parcelC.clone());
    }
  }

  /**
   * Fetches the parcellist that was made in the 'makeParcelList()' method.
   * @return the ArrayList of Parcels.
   */
  public ArrayList<Parcel> getParcelList() {
    return listOfParcels;
  }

  /**
   * Fetches the containersize from the inputFields.
   * @return the size of the container.
   */
  public Vector3D getContainerSize() {
    Vector3D tempVector = new Vector3D(contX, contY, contZ);
    return tempVector;
  }

  /**
   * Fetches the text in which the results are incorporated.
   * @return the text with the results in it.
   */
  public String getResultText() {
    String resultString =
      "The algorithm that was used is: " + chosenAlgorithm + "\n" +
      "Total amount of boxes used: " + solutions.getLength() + "\n" +
      "Total value of the used boxes: " + solutions.getValue() + "\n" +
      "Total amount of milliseconds the algorithm took: " + solutions.getTotalTime() + "\n" +
      "Total amount of different possibilities: " + _activeAlgorithm.getSolutions().size();
    return resultString;

  }

  /**
   * Sets the values of the inputFields depending on which preset was chosen.
   */
  public void updateInputFields() {
    if(presets.getValue() == "All A") {
      parcelACntr.setText("200");
      parcelBCntr.setText("0");
      parcelCCntr.setText("0");

      parcelAValue.setText("3");
      parcelBValue.setText("4");
      parcelCValue.setText("5");

      widthA.setText("1");
      heightA.setText("2");
      lengthA.setText("1");

      widthB.setText("1.5");
      heightB.setText("2");
      lengthB.setText("1");

      widthC.setText("1.5");
      heightC.setText("1.5");
      lengthC.setText("1.5");

      containerX.setText("16.5");
      containerY.setText("2.5");
      containerZ.setText("4");
    }
    else if(presets.getValue() == "All B") {
      parcelACntr.setText("0");
      parcelBCntr.setText("200");
      parcelCCntr.setText("0");

      parcelAValue.setText("3");
      parcelBValue.setText("4");
      parcelCValue.setText("5");

      widthA.setText("1");
      heightA.setText("2");
      lengthA.setText("1");

      widthB.setText("1.5");
      heightB.setText("2");
      lengthB.setText("1");

      widthC.setText("1.5");
      heightC.setText("1.5");
      lengthC.setText("1.5");

      containerX.setText("16.5");
      containerY.setText("2.5");
      containerZ.setText("4");
    }
    else if(presets.getValue() == "All C") {
      parcelACntr.setText("0");
      parcelBCntr.setText("0");
      parcelCCntr.setText("200");

      parcelAValue.setText("3");
      parcelBValue.setText("4");
      parcelCValue.setText("5");

      widthA.setText("1");
      heightA.setText("2");
      lengthA.setText("1");

      widthB.setText("1.5");
      heightB.setText("2");
      lengthB.setText("1");

      widthC.setText("1.5");
      heightC.setText("1.5");
      lengthC.setText("1.5");

      containerX.setText("16.5");
      containerY.setText("2.5");
      containerZ.setText("4");
    }
    else if(presets.getValue() == "A and B") {
      parcelACntr.setText("200");
      parcelBCntr.setText("200");
      parcelCCntr.setText("0");

      parcelAValue.setText("3");
      parcelBValue.setText("4");
      parcelCValue.setText("5");

      widthA.setText("1");
      heightA.setText("2");
      lengthA.setText("1");

      widthB.setText("1.5");
      heightB.setText("2");
      lengthB.setText("1");

      widthC.setText("1.5");
      heightC.setText("1.5");
      lengthC.setText("1.5");

      containerX.setText("16.5");
      containerY.setText("2.5");
      containerZ.setText("4");
    }
    else if(presets.getValue() == "A and C") {
      parcelACntr.setText("200");
      parcelBCntr.setText("0");
      parcelCCntr.setText("200");

      parcelAValue.setText("3");
      parcelBValue.setText("4");
      parcelCValue.setText("5");

      widthA.setText("1");
      heightA.setText("2");
      lengthA.setText("1");

      widthB.setText("1.5");
      heightB.setText("2");
      lengthB.setText("1");

      widthC.setText("1.5");
      heightC.setText("1.5");
      lengthC.setText("1.5");

      containerX.setText("16.5");
      containerY.setText("2.5");
      containerZ.setText("4");
    }
    else if(presets.getValue() == "B and C") {
      parcelACntr.setText("0");
      parcelBCntr.setText("200");
      parcelCCntr.setText("200");

      parcelAValue.setText("3");
      parcelBValue.setText("4");
      parcelCValue.setText("5");

      widthA.setText("1");
      heightA.setText("2");
      lengthA.setText("1");

      widthB.setText("1.5");
      heightB.setText("2");
      lengthB.setText("1");

      widthC.setText("1.5");
      heightC.setText("1.5");
      lengthC.setText("1.5");

      containerX.setText("16.5");
      containerY.setText("2.5");
      containerZ.setText("4");
    }
    else if(presets.getValue() == "Max Greedy Volume/Value") {
      parcelACntr.setText("11");
      parcelBCntr.setText("22");
      parcelCCntr.setText("22");

      parcelAValue.setText("3");
      parcelBValue.setText("4");
      parcelCValue.setText("5");

      widthA.setText("1");
      heightA.setText("2");
      lengthA.setText("1");

      widthB.setText("1.5");
      heightB.setText("2");
      lengthB.setText("1");

      widthC.setText("1.5");
      heightC.setText("1.5");
      lengthC.setText("1.5");

      containerX.setText("16.5");
      containerY.setText("2.5");
      containerZ.setText("4");
    }
    else if(presets.getValue() == "Max Greedy Density") {}
      else if(presets.getValue() ==  "Max Extreme Points")
      {
        parcelACntr.setText("1");
        parcelBCntr.setText("1");
        parcelCCntr.setText("1");

        parcelAValue.setText("3");
        parcelBValue.setText("4");
        parcelCValue.setText("5");

        widthA.setText("1");
        heightA.setText("2");
        lengthA.setText("1");

        widthB.setText("1.5");
        heightB.setText("2");
        lengthB.setText("1");

        widthC.setText("1.5");
        heightC.setText("1.5");
        lengthC.setText("1.5");

        containerX.setText("16.5");
        containerY.setText("2.5");
        containerZ.setText("4");
      }
  }

  /**
   * Fetches the algorithm that was used to calculate.
   * @return the used algorithm.
   */
  public Algorithm getAlgorithm() {
    return _activeAlgorithm;
  }
}
