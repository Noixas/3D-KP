import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.Group;

public class CreateBox {
	private static PhongMaterial purpleMaterial = new PhongMaterial();
	private static PhongMaterial grey;
	private static PhongMaterial blue;
	private static PhongMaterial purple;
	private static PhongMaterial green;
	private static PhongMaterial yellow;
	private static PhongMaterial black;
	private static PhongMaterial red;



	private static Group boxC;

	public CreateBox() {}

	public static void constructColors() {
		grey = new PhongMaterial();
		grey.setDiffuseColor(Color.GREY);

		blue = new PhongMaterial();
		blue.setDiffuseColor(Color.BLUE);

		purple = new PhongMaterial();
		purple.setDiffuseColor(Color.PURPLE);

		green = new PhongMaterial();
		green.setDiffuseColor(Color.GREEN);

		yellow = new PhongMaterial();
		yellow.setDiffuseColor(Color.YELLOW);

		black = new PhongMaterial();
		black.setDiffuseColor(Color.BLACK);

		red = new PhongMaterial();
		red.setDiffuseColor(Color.RED);
	}

	public static Group constructBoxA (int width, int height, int dept){
		constructColors();
		Box box = new Box(width, height, dept);
		box.setMaterial(blue);
		Group boxA = new Group();
	  boxA.getChildren().addAll(box);
		return boxA;
	}

	public static Group constructBoxB (int width, int height, int dept){
		constructColors();
		Box box = new Box(width, height, dept);
		box.setMaterial(yellow);
		Group boxB = new Group();
	  boxB.getChildren().addAll(box);
		return boxB;
	}

	public static Group constructBoxC (int width, int height, int depth){
		constructColors();
		Box box = new Box(width, height, depth);
		box.setMaterial(red);
		boxC = new Group();
		createSphere(width, height, depth, grey);
	  boxC.getChildren().addAll(box);
		return boxC;
	}

	public static void createSphere(int x, int y, int z, PhongMaterial color) {
		constructColors();
		Sphere sphere = new Sphere(5);
		sphere.setMaterial(color);
		sphere.setTranslateZ(z/2);
	  sphere.setTranslateX(x/2);
	  sphere.setTranslateY(y/2);

		boxC.getChildren().addAll(sphere);
	}
}
