import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.shape.Box; 
import javafx.stage.Stage; 
         
public class BoxExample extends Application { 
   @Override 
   public void start(Stage stage) { 
      //Drawing a Box 
      Box box = new Box();  
      
      //Setting the properties of the Box 
      box.setWidth(200.0); 
      box.setHeight(400.0);   
      box.setDepth(200.0); 
         
      //Creating a Group object  
      Group root = new Group(box); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);   
      
      //Setting title to the Stage 
      stage.setTitle("Drawing a Box"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }
   public static void main(String args[]){ 
      launch(args); 
   } 
}