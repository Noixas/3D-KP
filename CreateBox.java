import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.Group;

public class CreateBox {
	private static PhongMaterial purpleMaterial = new PhongMaterial();

	public CreateBox (){
		
	}

public static Group constructBoxA (int width, int height, int dept){
	PhongMaterial blueMaterial = new PhongMaterial();
    blueMaterial.setDiffuseColor(Color.BLUE);
	Box box = new Box(width, height, dept);
	box.setMaterial(blueMaterial);
	purpleMaterial.setDiffuseColor(Color.PURPLE);
	Group boxA = new Group();
    
    
    Sphere sphere = new Sphere (10);
    sphere.setMaterial(purpleMaterial);
    sphere.setTranslateZ(-dept/2);
    sphere.setTranslateX(-width/2);
    sphere.setTranslateY(-height/2);

    Sphere sphere1 = new Sphere (10);
    sphere1.setMaterial(purpleMaterial);
    sphere1.setTranslateZ(-dept/2);
    sphere1.setTranslateX(width/2);
    sphere1.setTranslateY(height/2);

    Sphere sphere2 = new Sphere (10);
    sphere2.setMaterial(purpleMaterial);
    sphere2.setTranslateZ(dept/2);
    sphere2.setTranslateX(-width/2);
    sphere2.setTranslateY(height/2);

    Sphere sphere3 = new Sphere (10);
    sphere3.setMaterial(purpleMaterial);
    sphere3.setTranslateZ(dept/2);
    sphere3.setTranslateX(width/2);
    sphere3.setTranslateY(-height/2);

    Sphere sphere4 = new Sphere (10);
    sphere4.setMaterial(purpleMaterial);
    sphere4.setTranslateZ(-dept/2);
    sphere4.setTranslateX(-width/2);
    sphere4.setTranslateY(height/2);

    Sphere sphere5 = new Sphere (10);
    sphere5.setMaterial(purpleMaterial);
    sphere5.setTranslateZ(-dept/2);
    sphere5.setTranslateX(width/2);
    sphere5.setTranslateY(-height/2);

    Sphere sphere6 = new Sphere (10);
    sphere6.setMaterial(purpleMaterial);
    sphere6.setTranslateZ(dept/2);
    sphere6.setTranslateX(-width/2);
    sphere6.setTranslateY(-height/2);

    Sphere sphere7 = new Sphere (10);
    sphere7.setMaterial(purpleMaterial);
    sphere7.setTranslateZ(dept/2);
    sphere7.setTranslateX(width/2);
    sphere7.setTranslateY(height/2);


    boxA.getChildren().addAll(box, sphere, sphere1, sphere2, sphere3, 
    						sphere4, sphere5, sphere6, sphere7);
	return boxA;
}

public Group constructBoxB (int width, int height, int dept){
	PhongMaterial yellowMaterial = new PhongMaterial();
    yellowMaterial.setDiffuseColor(Color.YELLOW);
	Box box = new Box(width, height, dept);
	box.setMaterial(yellowMaterial);
		purpleMaterial.setDiffuseColor(Color.PURPLE);
	Group boxB = new Group();
    
    
    Sphere sphere = new Sphere (10);
    sphere.setMaterial(purpleMaterial);
    sphere.setTranslateZ(-dept/2);
    sphere.setTranslateX(-width/2);
    sphere.setTranslateY(-height/2);

    Sphere sphere1 = new Sphere (10);
    sphere1.setMaterial(purpleMaterial);
    sphere1.setTranslateZ(-dept/2);
    sphere1.setTranslateX(width/2);
    sphere1.setTranslateY(height/2);

    Sphere sphere2 = new Sphere (10);
    sphere2.setMaterial(purpleMaterial);
    sphere2.setTranslateZ(dept/2);
    sphere2.setTranslateX(-width/2);
    sphere2.setTranslateY(height/2);

    Sphere sphere3 = new Sphere (10);
    sphere3.setMaterial(purpleMaterial);
    sphere3.setTranslateZ(dept/2);
    sphere3.setTranslateX(width/2);
    sphere3.setTranslateY(-height/2);

    Sphere sphere4 = new Sphere (10);
    sphere4.setMaterial(purpleMaterial);
    sphere4.setTranslateZ(-dept/2);
    sphere4.setTranslateX(-width/2);
    sphere4.setTranslateY(height/2);

    Sphere sphere5 = new Sphere (10);
    sphere5.setMaterial(purpleMaterial);
    sphere5.setTranslateZ(-dept/2);
    sphere5.setTranslateX(width/2);
    sphere5.setTranslateY(-height/2);

    Sphere sphere6 = new Sphere (10);
    sphere6.setMaterial(purpleMaterial);
    sphere6.setTranslateZ(dept/2);
    sphere6.setTranslateX(-width/2);
    sphere6.setTranslateY(-height/2);

    Sphere sphere7 = new Sphere (10);
    sphere7.setMaterial(purpleMaterial);
    sphere7.setTranslateZ(dept/2);
    sphere7.setTranslateX(width/2);
    sphere7.setTranslateY(height/2);


    boxB.getChildren().addAll(box, sphere, sphere1, sphere2, sphere3, 
    						sphere4, sphere5, sphere6, sphere7);
	return boxB;
}

public Group constructBoxC (int width, int height, int dept){
	PhongMaterial redMaterial = new PhongMaterial();
    redMaterial.setDiffuseColor(Color.RED);
	Box box = new Box(width, height, dept);
	box.setMaterial(redMaterial);

		purpleMaterial.setDiffuseColor(Color.PURPLE);
	Group boxC = new Group();
    
    
    Sphere sphere = new Sphere (10);
    sphere.setMaterial(purpleMaterial);
    sphere.setTranslateZ(-dept/2);
    sphere.setTranslateX(-width/2);
    sphere.setTranslateY(-height/2);

    Sphere sphere1 = new Sphere (10);
    sphere1.setMaterial(purpleMaterial);
    sphere1.setTranslateZ(-dept/2);
    sphere1.setTranslateX(width/2);
    sphere1.setTranslateY(height/2);

    Sphere sphere2 = new Sphere (10);
    sphere2.setMaterial(purpleMaterial);
    sphere2.setTranslateZ(dept/2);
    sphere2.setTranslateX(-width/2);
    sphere2.setTranslateY(height/2);

    Sphere sphere3 = new Sphere (10);
    sphere3.setMaterial(purpleMaterial);
    sphere3.setTranslateZ(dept/2);
    sphere3.setTranslateX(width/2);
    sphere3.setTranslateY(-height/2);

    Sphere sphere4 = new Sphere (10);
    sphere4.setMaterial(purpleMaterial);
    sphere4.setTranslateZ(-dept/2);
    sphere4.setTranslateX(-width/2);
    sphere4.setTranslateY(height/2);

    Sphere sphere5 = new Sphere (10);
    sphere5.setMaterial(purpleMaterial);
    sphere5.setTranslateZ(-dept/2);
    sphere5.setTranslateX(width/2);
    sphere5.setTranslateY(-height/2);

    Sphere sphere6 = new Sphere (10);
    sphere6.setMaterial(purpleMaterial);
    sphere6.setTranslateZ(dept/2);
    sphere6.setTranslateX(-width/2);
    sphere6.setTranslateY(-height/2);

    Sphere sphere7 = new Sphere (10);
    sphere7.setMaterial(purpleMaterial);
    sphere7.setTranslateZ(dept/2);
    sphere7.setTranslateX(width/2);
    sphere7.setTranslateY(height/2);


    boxC.getChildren().addAll(box, sphere, sphere1, sphere2, sphere3, 
    						sphere4, sphere5, sphere6, sphere7);
	return boxC;
}

}