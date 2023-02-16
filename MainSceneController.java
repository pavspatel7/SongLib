/* Three things left:
 * Don't allow duplicates and show dialog box when duplicate detected
 * Dialog box for edit and delete
 * File I/O
 */

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
						if(!empty && item != null)
						{
							int firstIndex = item.indexOf("|");
							int secondIndex = item.indexOf("|", firstIndex + 1);
							Label l = new Label(item.substring(0, secondIndex));
							setGraphic(l);
						}
						else
						{
							setGraphic(null);
						}
					}
				};
			}	
		});
	}
	
	@FXML
	public void addsong(MouseEvent event)
	{
		String element = name.getText()+" | "+artist.getText()+" | "+album.getText()+" | "+year.getText();
		if(!name.getText().isEmpty() && !artist.getText().isEmpty())
		{
			int first = element.indexOf("|");
			int second = element.indexOf("|", first+1);
			String check = element.substring(0, second);
			
			if(!songs.toString().toLowerCase().contains(check.toLowerCase()))
			{
				songs.add(element);
				FXCollections.sort(songs);
				int index = songs.indexOf(element);
				songlist.getSelectionModel().select(index);
				displaysong();
			}
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
		if(selectedID != -1) {
			songs.remove(selectedID);
			if(selectedID>=songs.size())
				selectedID -= 1;
			if(!songs.isEmpty())
			{
				songlist.getSelectionModel().select(selectedID);
			}
			displaysong();
		}
	}
	
	@FXML
	public void editsong(MouseEvent event)
	{
		int selectedID = songlist.getSelectionModel().getSelectedIndex();
		String element = name.getText()+" | "+artist.getText()+" | "+album.getText()+" | "+year.getText();
		if(selectedID != -1 && !name.getText().isEmpty() && !artist.getText().isEmpty())
		{
			int first = element.indexOf("|");
			int second = element.indexOf("|", first+1);
			String check = element.substring(0, second);
			System.out.println(songs.toString());
			if(!songs.toString().toLowerCase().contains(check.toLowerCase()))
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
		Label label0 = new Label("Selected Song Details: ");
		Label label1 = new Label("Name: ");
	    Label label2 = new Label("Artist: ");
	    Label label3 = new Label("Album: ");
	    Label label4 = new Label("Year: ");
	    if(!songs.isEmpty()) {
	    	
			int selectedID = songlist.getSelectionModel().getSelectedIndex();
			StringBuilder element = new StringBuilder(songs.get(selectedID));
			
			int firstIndex = element.indexOf("|");
			int secondIndex = element.indexOf("|", firstIndex + 1);
			int thirdIndex = element.indexOf("|", secondIndex + 1);
			
			String first = element.substring(0, firstIndex);
			String second = element.substring(firstIndex + 1, secondIndex);
			String third = element.substring(secondIndex + 1, thirdIndex);
			String fourth = element.substring(thirdIndex + 1);
			fourth = fourth.substring(0, fourth.length());
			
			label0.setText("Selected Song Details: ");
			label1.setText("Name: " + first);
		    label2.setText("Artist: " + second);
		    label3.setText("Album: " + third);
		    label4.setText("Year: " + fourth);
	    }
	    display.getChildren().clear();
	    display.setSpacing(10);
	    display.getChildren().addAll(label0, label1, label2, label3, label4);
	}
}