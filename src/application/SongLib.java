//Pavitra Patel (php51), Huzaif Mansuri (htm23)

package application;
	
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

public class SongLib extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MainScene.fxml"));
			primaryStage.setTitle("SongLib");
			AnchorPane root = (AnchorPane)loader.load();
			MainSceneController msc = loader.getController();
			msc.start();
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			primaryStage.setOnCloseRequest(event -> {
	            event.consume();
	            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.CANCEL);
	            alert.showAndWait();
	            if (alert.getResult() == ButtonType.YES) {
	            	try {
	            		FileWriter fw = new FileWriter("input.txt");
	            		for(String song : msc.storeData())
	            			fw.write(song+"\n");
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                Platform.exit(); // Terminate the application
	            }
	        });
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}