import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.Group;
import javafx.scene.text.Text;
import java.util.Random;

public class CreateParcel {
	private static Group parcelGroup = new Group();
	private static Parcel parcel;
	private static Vector3D size;
	private static Vector3D pos;

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

		parcelGroup.getChildren().addAll(xAxis, yAxis, zAxis, x, y);
	}

	public static PhongMaterial getColor(Color color) {
		PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(color);
		return material;
	}

	public static void createParcel(Parcel p){
		//System.out.println(p.getSize());
		Random rand = new Random();
		p.setPosition(new Vector3D(100, 50, 30));
		size = p.getSize();
		pos = p.getPosition();
		Box box = new Box(size.x, size.y, size.z);
		box.setMaterial(getColor(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), .99)));
		box.setTranslateX(pos.x);
		box.setTranslateY(pos.y);
		box.setTranslateZ(pos.z);
		parcelGroup.getChildren().addAll(box);
	}

	/*
	public static void createParcelB(Parcel p){
		p.setPosition(new Vector3D(60, 20, 100));
		size = p.getSize();
		pos = p.getPosition();
		Box box = new Box(size.x, size.y, size.z);
		box.setMaterial(getColor(Color.YELLOW));
		box.setTranslateX(pos.x);
		box.setTranslateY(pos.y);
		box.setTranslateZ(pos.z);
		parcelGroup.getChildren().addAll(box);
	}

	public static void createParcelC(Parcel p){
		p.setPosition(new Vector3D(40, 100, 70));
		size = p.getSize();
		pos = p.getPosition();
		Box box = new Box(size.x, size.y, size.z);
		box.setMaterial(getColor(Color.RED));
		box.setTranslateX(pos.x);
		box.setTranslateY(pos.y);
		box.setTranslateZ(pos.z);
		parcelGroup.getChildren().addAll(box);
	}
	*/

	public static void createSphere(int x, int y, int z) {
		Sphere sphere = new Sphere(5);
		sphere.setMaterial(getColor(Color.BLACK));
		sphere.setTranslateZ(z/2);
	  sphere.setTranslateX(x/2);
	  sphere.setTranslateY(y/2);

		parcelGroup.getChildren().addAll(sphere);

		//extreme points coordinates need to be added but can only be done
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

		parcelGroup.getChildren().addAll(xAxis, yAxis, zAxis, x, y);

	}

	public static void removeParcel() {
		
	}
}
