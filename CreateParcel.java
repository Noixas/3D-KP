import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Text;
import java.util.Random;

public class CreateParcel {
	private static Group parcelGroup = new Group();
	private static Parcel parcel;
	private static Vector3D size;
	private static Vector3D pos;
	private static Box box;

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

		Box boxOutline = new Box(650, 3, 3);
		boxOutline.setMaterial(getColor(Color.BLACK));
		boxOutline.setTranslateX(325);

		Box boxOutline1 = new Box(650, 3, 3);
		boxOutline1.setMaterial(getColor(Color.BLACK));
		boxOutline1.setTranslateX(325);
		boxOutline1.setTranslateY(100);

		Box boxOutline2 = new Box(650, 3, 3);
		boxOutline2.setMaterial(getColor(Color.BLACK));
		boxOutline2.setTranslateX(325);
		boxOutline2.setTranslateY(100);
		boxOutline2.setTranslateZ(160);


		Box boxOutline3 = new Box(650, 3, 3);
		boxOutline3.setMaterial(getColor(Color.BLACK));
		boxOutline3.setTranslateX(325);
		boxOutline3.setTranslateZ(160);

		Box boxOutline4 = new Box(3, 100, 3);
		boxOutline4.setTranslateY(50);
		boxOutline4.setMaterial(getColor(Color.BLACK));

		Box boxOutline5 = new Box(3, 100, 3);
		boxOutline5.setMaterial(getColor(Color.BLACK));
		boxOutline5.setTranslateY(50);
		boxOutline5.setTranslateZ(160);

		Box boxOutline7 = new Box(3, 100, 3);
		boxOutline7.setMaterial(getColor(Color.BLACK));
		boxOutline7.setTranslateY(50);
		boxOutline7.setTranslateZ(160);
		boxOutline7.setTranslateX(650);

		Box boxOutline8 = new Box(3, 100, 3);
		boxOutline8.setMaterial(getColor(Color.BLACK));
		boxOutline8.setTranslateY(50);
		boxOutline8.setTranslateX(650);

		Box boxOutline6 = new Box(3, 3, 160);
		boxOutline6.setMaterial(getColor(Color.BLACK));
		boxOutline6.setTranslateZ(80);

		Box boxOutline9 = new Box(3, 3, 160);
		boxOutline9.setMaterial(getColor(Color.BLACK));
		boxOutline9.setTranslateZ(80);
		boxOutline9.setTranslateY(100);

		Box boxOutline10 = new Box(3, 3, 160);
		boxOutline10.setMaterial(getColor(Color.BLACK));
		boxOutline10.setTranslateZ(80);
		boxOutline10.setTranslateX(650);

		Box boxOutline11 = new Box(3, 3, 160);
		boxOutline11.setMaterial(getColor(Color.BLACK));
		boxOutline11.setTranslateZ(80);
		boxOutline11.setTranslateY(100);
		boxOutline11.setTranslateX(650);



		parcelGroup.getChildren().addAll(xAxis, yAxis, zAxis, x, y, boxOutline, boxOutline1
			, boxOutline2, boxOutline3, boxOutline4, boxOutline5, boxOutline6, boxOutline7
			, boxOutline8, boxOutline9, boxOutline10, boxOutline11);
	}

	public static PhongMaterial getColor(Color color) {
		PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(color);
		return material;
	}

	public static void createParcel(Parcel p){
		//System.out.println(p.getSize());
		Random rand = new Random();
		//p.setPosition(new Vector3D(100, 50, 30));
		size = p.getSize();
		pos = p.getPosition();
		box = new Box(size.x * 40, size.y * 40, size.z * 40);
		/*if(p instanceof ParcelA) {
			box.setMaterial(getColor(Color.RED));
		} else if(p instanceof ParcelA) {
			box.setMaterial(getColor(Color.BLUE));
		} else if(p instanceof ParcelA) {
			box.setMaterial(getColor(Color.GREEN));
		} else {
			box.setMaterial(getColor(Color.PINK));
		}*/
		box.setMaterial(getColor(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), .99)));
		box.setTranslateX((pos.x + size.x/2)* 40);
		box.setTranslateY((pos.y + size.y/2)* 40);
		box.setTranslateZ((pos.z + size.z/2)* 40);
		parcelGroup.getChildren().addAll(box);
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
