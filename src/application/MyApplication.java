package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class MyApplication extends Application {
    private static Stage primaryStage;
	
    @Override
    public void start(Stage primaryStage) {
        try {
            // Read file fxml and draw interface.
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            primaryStage.setTitle("Space Gurdian");
            primaryStage.setScene(new Scene(root));
//            primaryStage.setMaximized(true);
//            primaryStage.setFullScreen(true);
            primaryStage.show();
         
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage primaryStage) {
		MyApplication.primaryStage = primaryStage;
	}
    
}