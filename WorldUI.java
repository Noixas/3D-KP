import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class WorldUI {

  final XformWorld world = new XformWorld();
  final PerspectiveCamera camera = new PerspectiveCamera(true);
  static final XformCamera cameraXform = new XformCamera();
  final CreateParcel parcels = new CreateParcel();
  private static final double CAMERA_INITIAL_DISTANCE = -2000;
  private static final double CAMERA_NEAR_CLIP = 0.1;
  private static final double CAMERA_FAR_CLIP = 10000.0;
  double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY;
  double mouseFactorX, mouseFactorY;
  private static double xCoord;
  private static double yCoord;
  private static double zCoord;
  private static double height;
  private static double width;
  private static double length;
  private static Label coords;
  private static Label dimensions;
  private static Label yLabel;
  private static Label zLabel;
  private static Label resultPrint;
  private static MenuUI menuRef = new MenuUI();
  private static String resultString;
  private static Box temp = new Box();
  private static Button resetView;
  private static Button nextStep;
  private static Button computeAll;
  private static double deltaRX = 0;
  private static double deltaRY = 0;


  public WorldUI(Group worldGroup) {
    worldGroup.getChildren().add(world);
    worldGroup.setDepthTest(DepthTest.ENABLE);
    buildCamera(worldGroup);
    buildBodySystem();
    handleMouse(SceneManager.getScene());
    SceneManager.getScene().setCamera(camera);
    mouseFactorX = 180.0 / SceneManager.getSceneWidth();
    mouseFactorY = 180.0 / SceneManager.getSceneHeight();
  }

  public WorldUI(GridPane root) {
    root.setPadding(new Insets(5));

    Label coordLabel = new Label();
    coordLabel.setText("Coordinates: ");
    coordLabel.setStyle(
      "-fx-font-size: 14px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #000000;" +
      "-fx-font-style: italic;");

    coords = new Label();

    Label dimensionLabel = new Label();
    dimensionLabel.setText("Dimensions: ");
    dimensionLabel.setStyle(
      "-fx-font-size: 14px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #000000;" +
      "-fx-font-style: italic;");
    dimensionLabel.setTranslateY(20);

    dimensions = new Label();
    dimensions.setTranslateY(20);

    Label resultsLabel = new Label();
    resultsLabel.setText("Results: ");
    resultsLabel.setStyle(
      "-fx-font-size: 14px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #000000;" +
      "-fx-font-style: italic;");
    resultsLabel.setTranslateY(40);

    resultPrint = new Label();
    resultPrint.setText("okay");
    resultPrint.setTranslateY(40);

    nextStep = new Button();
    nextStep.setText("Next Step");
    nextStep.setPrefSize(140, 20);
    nextStep.setTranslateY(60);
    nextStep.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent step) {
        Algorithm a = menuRef.getAlgorithm();
        if(a instanceof AlgorithmZ)
        {
          AlgorithmZ z = (AlgorithmZ) a;
          z.nextStep();
        }
        if(a instanceof AlgorithmPentomino)
        {
          AlgorithmPentomino z = (AlgorithmPentomino) a;
          z.nextStep();
        }
      }
    });

    computeAll = new Button();
    computeAll = new Button();
    computeAll.setText("Calculate");
    computeAll.setPrefSize(140, 20);
    computeAll.setTranslateY(80);
    computeAll.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent step) {

      }
    });

    resetView = new Button();
    resetView.setText("Reset View");
    resetView.setPrefSize(140, 20);
    resetView.setTranslateY(100);
    resetView.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent step) {
        cameraXform.ry(-deltaRY);
        cameraXform.rx(-deltaRX);
        deltaRY = 0;
        deltaRX = 0;
      }
    });

    root.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #b2ceff, #ffffff)");
    root.add(coordLabel, 0, 0);
    root.add(coords, 0, 1);
    root.add(dimensionLabel, 0, 2);
    root.add(dimensions, 0, 3);
    root.add(resultsLabel, 0, 4);
    root.add(resultPrint, 0, 5);
    root.add(nextStep, 0, 6);
    root.add(computeAll, 0, 7);
    root.add(resetView, 0, 8);

  }
  public static void printInfo() {
    coords.setText(
      "X: " + xCoord + "\n" +
      "Y: " + yCoord + "\n" +
      "Z: " + zCoord);

    dimensions.setText(
      "Length: " + length + "\n" +
      "Width: " + width + "\n" +
      "Height: " + height);

    resultPrint.setText(menuRef.getResultText());
  }

  public static void printResults() {
    try {
      //resultString = (String) menuRef.getResultText();
      resultPrint.setText("The algorithm that was used is: " + "chosenAlgorithm" + "\n" +
      "Total amount of boxes used: " + "46" + "\n" +
      "Total value of the used boxes: " + "635" + "\n" +
      "Total amount of second the algorithm took: " + "twer" + "\n" +
      "Total amount of different possibilities: " + "4365");
    }
    catch(NullPointerException e) {
      resultPrint.setText("error");
    }
  }

  public void updateCoords(MouseEvent event) {
    try {
      Box temp1 = temp;
      temp1.setMaterial(CreateParcel.getColor(Color.BLACK));
      PickResult result = event.getPickResult();
      Node testNode = result.getIntersectedNode();
      temp = (Box) testNode;
      temp.setMaterial(CreateParcel.getColor(Color.YELLOW));

      length = temp.getWidth() / 40;
      width = temp.getHeight() / 40;
      height = temp.getDepth() / 40;

      xCoord = testNode.getTranslateX() / 40 - length / 2;
      yCoord = testNode.getTranslateY() / 40 - width / 2;
      zCoord = testNode.getTranslateZ() / 40 - height / 2;
    }
    catch(NullPointerException e) {
      temp = new Box();
    }
  }

  private void buildCamera(Group worldGroup) {
    worldGroup.getChildren().add(cameraXform);
    cameraXform.getChildren().add(camera);
    camera.setNearClip(CAMERA_NEAR_CLIP);
    camera.setFarClip(CAMERA_FAR_CLIP);
    camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
  }

  private void buildBodySystem() {
    Group parcelGroup = parcels.getParcels();

    parcelGroup.setTranslateX(-1*CreateParcel.getContainerWidth()/2);
    parcelGroup.setTranslateY(-1*CreateParcel.getContainerHeight()/2);
    parcelGroup.setTranslateZ(-1*CreateParcel.getContainerDepth()/2);
    world.getChildren().addAll(parcelGroup);
  }

  private void handleMouse(Scene scene) {
    scene.setOnMousePressed((MouseEvent me) -> {
      mousePosX = me.getSceneX();
      mousePosY = me.getSceneY();
      mouseOldX = me.getSceneX();
      mouseOldY = me.getSceneY();
      updateCoords(me);
    });

    scene.setOnMouseDragged((MouseEvent me) -> {
      mouseOldX = mousePosX;
      mouseOldY = mousePosY;
      mousePosX = me.getSceneX();
      mousePosY = me.getSceneY();
      mouseDeltaX = (mousePosX - mouseOldX);
      mouseDeltaY = (mousePosY - mouseOldY);
      if (me.isPrimaryButtonDown()) {
        cameraXform.ry(mouseDeltaX * 180.0 / scene.getWidth());
        cameraXform.rx(-mouseDeltaY * 180.0 / scene.getHeight());

        deltaRY = deltaRY + mouseDeltaX * 180.0 / scene.getWidth();
        deltaRX = deltaRX + -mouseDeltaY * 180.0 / scene.getHeight();
      //  System.out.println(deltaRX + "test");
      }
      else if (me.isSecondaryButtonDown()) {
        camera.setTranslateZ(camera.getTranslateZ() + mouseDeltaY);
      }
    });
  }
}

class XformWorld extends Group {
final Translate t = new Translate(0.0, 0.0, 0.0);
final Rotate rx = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
final Rotate ry = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
final Rotate rz = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);

public XformWorld() {
        super();
        this.getTransforms().addAll(t, rx, ry, rz);
}
}

class XformCamera extends Group {
Point3D px = new Point3D(1.0, 0.0, 0.0);
Point3D py = new Point3D(0.0, 1.0, 0.0);
Rotate r;
Transform t = new Rotate();

public XformCamera() {
        super();
}

public void rx(double angle) {
        r = new Rotate(angle, px);
        this.t = t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().addAll(t);
}

public void ry(double angle) {
        r = new Rotate(angle, py);
        this.t = t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().addAll(t);
}
}
