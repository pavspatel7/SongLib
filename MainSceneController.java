package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainSceneController
{

	@FXML
	private TextField album;

	@FXML
	private TextField artist;

	@FXML
	private TextField name;

	@FXML
	private ListView<String> songlist;
	
	private ObservableList<String> songs;
	
	@FXML
	private TextField year;
	
	@FXML
	private VBox display;

	public void start()
	{
		songs = FXCollections.observableArrayList();
		songlist.setItems(songs);
		
		songlist.setCellFactory(new Callback<ListView<String>, ListCell<String>>()
		{
			@Override
			public ListCell<String> call(ListView<String> arg0) 
			{
				return new ListCell<String>()
				{
					protected void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						if(empty || item == null)
						{
							setGraphic(null);
						}
						else
						{
							StringBuilder sb = new StringBuilder(item);
							if(sb.charAt(0) == '.')
							{
								sb.delete(0, 3);
							}
							int firstIndex = sb.indexOf(",");
							int secondIndex = sb.indexOf(",", firstIndex + 1);
							Label l = new Label(sb.substring(0, secondIndex));
							setGraphic(l);
						}
					}
				};
			}	
		});
	}
	
	@FXML
	public void addsong(MouseEvent event)
	{
		String element="";
		if(songs.isEmpty())
		{
			element = "., ";
		}
		element += name.getText()+", "+artist.getText()+", "+album.getText()+", "+year.getText()+".";
		if(!name.getText().isEmpty() && !artist.getText().isEmpty())
		{
			int first = element.indexOf(",");
			int second = element.indexOf(",", first+1);
			String check = "., " + element.substring(0, second);
			if(!songs.toString().contains(check))
				songs.add(element);
		}
		name.clear();
		album.clear();
		artist.clear();
		year.clear();
	}
	
	@FXML
	public void deletesong(MouseEvent event)
	{
		int selectedID = songlist.getSelectionModel().getSelectedIndex();
		if(selectedID != -1)
			songs.remove(selectedID);
		display.getChildren().clear();
	}
	
	@FXML
	public void editsong(MouseEvent event)
	{
		int selectedID = songlist.getSelectionModel().getSelectedIndex();
		String element = name.getText()+", "+artist.getText()+", "+album.getText()+", "+year.getText()+".";
		if(selectedID != -1 && !name.getText().isEmpty() && !artist.getText().isEmpty())
		{
			int first = element.indexOf(",");
			int second = element.indexOf(",", first+1);
			String check = "., " + element.substring(0, second);
			System.out.println(songs.toString());
			if(!songs.toString().contains(check))
				songs.set(selectedID, element);
		}
		name.clear();
		album.clear();
		artist.clear();
		year.clear();
		displaysong();
	}
	
	@FXML
	public void displaysong()
	{
		int selectedID = songlist.getSelectionModel().getSelectedIndex();
		StringBuilder element = new StringBuilder(songs.get(selectedID));
		if(element.charAt(0)== '.')
		{
			element.delete(0, 3);
		}
		
		int firstIndex = element.indexOf(",");
		int secondIndex = element.indexOf(",", firstIndex + 1);
		int thirdIndex = element.indexOf(",", secondIndex + 1);
		
		String first = element.substring(0, firstIndex);
		String second = element.substring(firstIndex + 1, secondIndex);
		String third = element.substring(secondIndex + 1, thirdIndex);
		String fourth = element.substring(thirdIndex + 1);
		fourth = fourth.substring(0, fourth.length()-1);
		
		Label label0 = new Label("Selected Song Details: ");
		Label label1 = new Label("Name: " + first);
	    Label label2 = new Label("Artist: " + second);
	    Label label3 = new Label("Album: " + third);
	    Label label4 = new Label("Year: " + fourth);
	    
	    display.getChildren().clear();
	    display.setSpacing(10);
	    display.getChildren().addAll(label0, label1, label2, label3, label4);
	}
}