# SongLib by Pavitra Patel & Huzaif Mansuri

Important Notice: Spring 2023 course project for CS213 at Rutgers University. Please follow Rutgers University Academic Integrity Policy.

**Description:** Design and implement an application with a graphical user interface to manage a library of songs.

**Features:**

- A song is uniquely identified by a combination of name and artist (case insensitive, i.e. upper or lower case are the same).

- The Application have a SINGLE WINDOW with three functions:
	- **Song list display**, with the ability to select ONE song from the list. The list displays the name and artist ONLY for each song, in alphabetical order of names (and within duplicate names, by alphabetical order of artists). Unless the list is empty, one song is always pre-selected, and its details shown.
	- **Song detail**, displays the name, artist, album, and year of the song that is selected in the song list interface.
	- **Song Add/Delete/Edit**, for adding a new song, deleting a selected song, and editing a selected song:
	
		- **Add:** When a new song is added, the song name and artist are the required fields, whereas Year and album are optional. If the name and artist are the same as an existing song, the add function will not be allowed, a pop up dialog displays the error message within the main application window.  
		  The newly added song is automatically placed in the correct position in the alphabetical order in the list. Also, it is automatically selected, replacing the previously selected song, and hence its details shown.  

		- **Edit:** ANY of the fields can be changed using the Edit function. But neither of the song name or artist field is allowed to be empty. Also, if name and artist conflicts with that of an existing song, the edit will NOT be allowed, a pop up dialog displaying the error is generated within the main application window.  
			
		- **Delete:** Only a single selected song in the list can be deleted. After deletion, the next song in the list is auto-selected, and hence its details displayed. If there is no next song, the previous song is auto-selected, and if there is no previous song either, then the list is empty and hence the details info is all blanks.  
	- When the application is started, it should show the current list of songs in the library as a list, with the first song selected by default. (The first time the program is run, there should be nothing in the display as there won't be any songs in the library.)
- The song library data persists across different sessions of the program. This means any update the user makes to the songs in a run session is carried over into the next session when the user runs the app again.

**Technical Skills Implemented:**  
-	JavaFX Application Development.
-	Back-end Functionality Coding in Java.
-	Testing Application for a variety of Scenarios.
-	Understanding and Implementing Project Requirements.
