package kp;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.shape.DrawMode;
import java.util.Random;
import java.util.ArrayList;

public class CreateParcel {
private static Group parcelGroup = new Group();
private static Parcel parcel;
private static Vector3D size;
private static Vector3D pos;
private static Box box0;
private static Box box;
private static boolean _cleared = false;
private static int width = 660;
private static int height = 100;
private static int depth = 160;
private static boolean axisDisplay = true;

public CreateParcel() {
        Box xAxis = new Box(500, 3, 3);
        xAxis.setMaterial(getColor(Color.PINK));

        Box yAxis = new Box(3, 500, 3);
        yAxis.setMaterial(getColor(Color.BLACK));

        Box zAxis = new Box(3, 3, 500);
        zAxis.setMaterial(getColor(Color.AQUA));

        Text x = new Text (200, 20, "X axis ->");
        Text y = new Text (-15, 230, "Y axis ->");
        y.setRotate(90);


        Box boxOutline = new Box(width, 3, 3);
        boxOutline.setMaterial(getColor(Color.BLACK));
        boxOutline.setTranslateX(0.5*width);

        Box boxOutline1 = new Box(width, 3, 3);
        boxOutline1.setMaterial(getColor(Color.BLACK));
        boxOutline1.setTranslateX(0.5*width);
        boxOutline1.setTranslateY(height);

        Box boxOutline2 = new Box(width, 3, 3);
        boxOutline2.setMaterial(getColor(Color.BLACK));
        boxOutline2.setTranslateX(0.5*width);
        boxOutline2.setTranslateY(height);
        boxOutline2.setTranslateZ(depth);


        Box boxOutline3 = new Box(width, 3, 3);
        boxOutline3.setMaterial(getColor(Color.BLACK));
        boxOutline3.setTranslateX(0.5*width);
        boxOutline3.setTranslateZ(depth);

        Box boxOutline4 = new Box(3, height, 3);
        boxOutline4.setTranslateY(0.5*height);
        boxOutline4.setMaterial(getColor(Color.BLACK));

        Box boxOutline5 = new Box(3, height, 3);
        boxOutline5.setMaterial(getColor(Color.BLACK));
        boxOutline5.setTranslateY(0.5*height);
        boxOutline5.setTranslateZ(depth);

        Box boxOutline7 = new Box(3, height, 3);
        boxOutline7.setMaterial(getColor(Color.BLACK));
        boxOutline7.setTranslateY(0.5*height);
        boxOutline7.setTranslateZ(depth);
        boxOutline7.setTranslateX(width);

        Box boxOutline8 = new Box(3, height, 3);
        boxOutline8.setMaterial(getColor(Color.BLACK));
        boxOutline8.setTranslateY(0.5*height);
        boxOutline8.setTranslateX(width);

        Box boxOutline6 = new Box(3, 3, depth);
        boxOutline6.setMaterial(getColor(Color.BLACK));
        boxOutline6.setTranslateZ(0.5*depth);

        Box boxOutline9 = new Box(3, 3, depth);
        boxOutline9.setMaterial(getColor(Color.BLACK));
        boxOutline9.setTranslateZ(0.5*depth);
        boxOutline9.setTranslateY(height);

        Box boxOutline10 = new Box(3, 3, depth);
        boxOutline10.setMaterial(getColor(Color.BLACK));
        boxOutline10.setTranslateZ(0.5*depth);
        boxOutline10.setTranslateX(width);

        Box boxOutline11 = new Box(3, 3, depth);
        boxOutline11.setMaterial(getColor(Color.BLACK));
        boxOutline11.setTranslateZ(0.5*depth);
        boxOutline11.setTranslateY(height);
        boxOutline11.setTranslateX(width);

        if(axisDisplay)
                parcelGroup.getChildren().addAll(xAxis, yAxis, zAxis, x, y);
        parcelGroup.getChildren().addAll(
                boxOutline, boxOutline1, boxOutline2,
                boxOutline3, boxOutline4, boxOutline5, boxOutline6, boxOutline7,
                boxOutline8, boxOutline9, boxOutline10, boxOutline11);

}

public static PhongMaterial getColor(Color color) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        return material;
}

public static void createParcel(Parcel p){
        Random rand = new Random();
        int scaleConstant = 40;
        size = p.getSize();
        pos = p.getPosition();
        box0 = new Box(size.x * scaleConstant, size.y * scaleConstant, size.z * scaleConstant);
        box = new Box(size.x * scaleConstant, size.y * scaleConstant, size.z * scaleConstant);
        box0.setDrawMode(DrawMode.LINE);
        box0.setMaterial(getColor(Color.BLACK));
        if(p instanceof ParcelA) {
                box.setMaterial(getColor(Color.RED));
        } else if(p instanceof ParcelB) {
                box.setMaterial(getColor(Color.BLUE));
        } else if(p instanceof ParcelC) {
                box.setMaterial(getColor(Color.GREEN));
        } else {
                box.setMaterial(getColor(Color.PINK));
        }
        //box.setMaterial(getColor(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), .99)));
        box.setTranslateX((pos.x + size.x/2)* scaleConstant);
        box.setTranslateY((pos.y + size.y/2)* scaleConstant);
        box.setTranslateZ((pos.z + size.z/2)* scaleConstant);
        parcelGroup.getChildren().addAll(box);
        box0.setTranslateX((pos.x + size.x/2)* scaleConstant);
        box0.setTranslateY((pos.y + size.y/2)* scaleConstant);
        box0.setTranslateZ((pos.z + size.z/2)* scaleConstant);
        parcelGroup.getChildren().addAll(box0);
        ArrayList<Node> boxList = new ArrayList<Node>();
        boxList.add(box0);
}

public static void createSphere(int x, int y, int z) {
        Sphere sphere = new Sphere(5);
        sphere.setMaterial(getColor(Color.BLACK));
        sphere.setTranslateZ(z/2);
        sphere.setTranslateX(x/2);
        sphere.setTranslateY(y/2);

        parcelGroup.getChildren().addAll(sphere);

        //extreme points coordinates need to be added but can only be done
}
public static void createSphere(Vector3D v) {
        double x = v.x;
        double y = v.y;
        double z = v.z;
        Sphere sphere = new Sphere(5);
        int scaleConstant = 40;
        //System.out.println("New sphere created at coord: " + v);
        sphere.setMaterial(getColor(Color.BLACK));
        sphere.setTranslateZ((z)*scaleConstant);
        sphere.setTranslateX((x)*scaleConstant);
        sphere.setTranslateY((y)*scaleConstant);

        parcelGroup.getChildren().addAll(sphere);

        //extreme points coordinates need to be added but can only be done
}
public static boolean getCleared()
{
        boolean result = _cleared;         //buffer the result
        _cleared = false;         //auto reset so we can just check for cleared once
        return result;
}
public static Group getParcels() {
        return parcelGroup;
}

public static void clearAllParcels() {
        parcelGroup.getChildren().clear();
        Box xAxis = new Box(500, 3, 3);
        xAxis.setMaterial(getColor(Color.PINK));

        Box yAxis = new Box(3, 500, 3);
        yAxis.setMaterial(getColor(Color.BLACK));

        Box zAxis = new Box(3, 3, 500);
        zAxis.setMaterial(getColor(Color.AQUA));

        Text x = new Text (200, 20, "X axis ->");
        Text y = new Text (-15, 230, "Y axis ->");
        y.setRotate(90);


        Box boxOutline = new Box(width, 3, 3);
        boxOutline.setMaterial(getColor(Color.BLACK));
        boxOutline.setTranslateX(0.5*width);

        Box boxOutline1 = new Box(width, 3, 3);
        boxOutline1.setMaterial(getColor(Color.BLACK));
        boxOutline1.setTranslateX(0.5*width);
        boxOutline1.setTranslateY(height);

        Box boxOutline2 = new Box(width, 3, 3);
        boxOutline2.setMaterial(getColor(Color.BLACK));
        boxOutline2.setTranslateX(0.5*width);
        boxOutline2.setTranslateY(height);
        boxOutline2.setTranslateZ(depth);


        Box boxOutline3 = new Box(width, 3, 3);
        boxOutline3.setMaterial(getColor(Color.BLACK));
        boxOutline3.setTranslateX(0.5*width);
        boxOutline3.setTranslateZ(depth);

        Box boxOutline4 = new Box(3, height, 3);
        boxOutline4.setTranslateY(0.5*height);
        boxOutline4.setMaterial(getColor(Color.BLACK));

        Box boxOutline5 = new Box(3, height, 3);
        boxOutline5.setMaterial(getColor(Color.BLACK));
        boxOutline5.setTranslateY(0.5*height);
        boxOutline5.setTranslateZ(depth);

        Box boxOutline7 = new Box(3, height, 3);
        boxOutline7.setMaterial(getColor(Color.BLACK));
        boxOutline7.setTranslateY(0.5*height);
        boxOutline7.setTranslateZ(depth);
        boxOutline7.setTranslateX(width);

        Box boxOutline8 = new Box(3, height, 3);
        boxOutline8.setMaterial(getColor(Color.BLACK));
        boxOutline8.setTranslateY(0.5*height);
        boxOutline8.setTranslateX(width);

        Box boxOutline6 = new Box(3, 3, depth);
        boxOutline6.setMaterial(getColor(Color.BLACK));
        boxOutline6.setTranslateZ(0.5*depth);

        Box boxOutline9 = new Box(3, 3, depth);
        boxOutline9.setMaterial(getColor(Color.BLACK));
        boxOutline9.setTranslateZ(0.5*depth);
        boxOutline9.setTranslateY(height);

        Box boxOutline10 = new Box(3, 3, depth);
        boxOutline10.setMaterial(getColor(Color.BLACK));
        boxOutline10.setTranslateZ(0.5*depth);
        boxOutline10.setTranslateX(width);

        Box boxOutline11 = new Box(3, 3, depth);
        boxOutline11.setMaterial(getColor(Color.BLACK));
        boxOutline11.setTranslateZ(0.5*depth);
        boxOutline11.setTranslateY(height);
        boxOutline11.setTranslateX(width);

        if(axisDisplay)
                parcelGroup.getChildren().addAll(xAxis, yAxis, zAxis, x, y);
        parcelGroup.getChildren().addAll(boxOutline, boxOutline1
                                         , boxOutline2, boxOutline3, boxOutline4, boxOutline5, boxOutline6, boxOutline7
                                         , boxOutline8, boxOutline9, boxOutline10, boxOutline11);
        _cleared = true;
}

public static int getContainerHeight () {
        return height;
}

public static int getContainerWidth () {
        return width;
}

public static int getContainerDepth () {
        return depth;
}

public static void removeParcel(Parcel p) {
        //parcelGroup.getChildren().removeIf()
        pos = p.getPosition();

        for(Node box : parcelGroup.getChildren()) {
                if(box.getTranslateX() == pos.x * 2 && box.getTranslateY() == pos.y * 2 && box.getTranslateZ() == pos.z * 2) {
                        parcelGroup.getChildren().remove(box);
                }
        }
}
}
