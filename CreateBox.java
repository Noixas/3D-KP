import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.Group;

public class CreateBox {
	private static Group boxC;

	public CreateBox() {}

	public static PhongMaterial getColor(Color color) {
		PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(color);
		return material;
	}

	public static Group constructBoxA (int width, int height, int dept){
		Box box = new Box(width, height, dept);
		box.setMaterial(getColor(Color.BLUE));
		Group boxA = new Group();
	  boxA.getChildren().addAll(box);
		return boxA;
	}

	public static Group constructBoxB (int width, int height, int dept){
		Box box = new Box(width, height, dept);
		box.setMaterial(getColor(Color.YELLOW));
		Group boxB = new Group();
	  boxB.getChildren().addAll(box);
		return boxB;
	}

	public static Group constructBoxC (int width, int height, int depth){
		Box box = new Box(width, height, depth);
		box.setMaterial(getColor(Color.RED));
		boxC = new Group();
		createSphere(width, height, depth, getColor(Color.BLACK));
	  boxC.getChildren().addAll(box);
		return boxC;
	}

	public static void createSphere(int x, int y, int z, PhongMaterial color) {
		Sphere sphere = new Sphere(5);
		sphere.setMaterial(color);
		sphere.setTranslateZ(z/2);
	  sphere.setTranslateX(x/2);
	  sphere.setTranslateY(y/2);

		boxC.getChildren().addAll(sphere);
	}

	public static Group updateGroup() {
		Group boxGroup = new Group();
		constructBoxA(50,50,50);
		boxGroup.getChildren().addAll();
		return boxGroup;
	}
}
