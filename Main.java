package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application
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