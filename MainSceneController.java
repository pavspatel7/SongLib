package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
							int firstIndex = item.indexOf(",");
							int secondIndex = item.indexOf(",", firstIndex + 1);
							Label l = new Label(item.substring(0, secondIndex));
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
		String element = name.getText()+","+album.getText()+","+artist.getText()+","+year.getText();
		if(!name.getText().isEmpty() && !album.getText().isEmpty())
		{
			if(!songs.contains(element))
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
	}
	
	@FXML
	public void editsong(MouseEvent event)
	{
		int selectedID = songlist.getSelectionModel().getSelectedIndex();
		String element = name.getText()+","+album.getText()+","+artist.getText()+","+year.getText();
		if(selectedID != -1 && !name.getText().isEmpty() && !album.getText().isEmpty())
		{
			if(!songs.contains(element))
				songs.set(selectedID, element);
		}
		name.clear();
		album.clear();
		artist.clear();
		year.clear();
	}
}