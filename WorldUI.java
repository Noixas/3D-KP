import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.control.Label;
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
  final XformCamera cameraXform = new XformCamera();
  final CreateParcel parcels = new CreateParcel();
  private static final double CAMERA_INITIAL_DISTANCE = -2000;
  private static final double CAMERA_NEAR_CLIP = 0.1;
  private static final double CAMERA_FAR_CLIP = 10000.0;
  double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY;
  double mouseFactorX, mouseFactorY;
  private static double xCoord;
  private static double yCoord;
  private static double zCoord;

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
      "-fx-font-size: 18px;" +
      "-fx-font-weight: bold;" +
      "-fx-text-fill: #000000;" +
      "-fx-font-style: italic;");

    root.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #b2ceff, #ffffff)");
    root.add(coordLabel, 0, 0);

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
      try {
        PickResult result = me.getPickResult();
        Node testNode = result.getIntersectedNode();
        xCoord = testNode.getTranslateX();
        yCoord = testNode.getTranslateZ();
        zCoord = testNode.getTranslateZ();
      }
      catch(NullPointerException e) {}
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
