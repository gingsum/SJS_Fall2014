import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

 
public class Menu {
	 MusicLibrary newLib = new MusicLibrary();
	 long counter = 0;
	 
	public void mainMenu()
	{
		try{
		System.out.println("Select a Command: ");
		System.out.println("Enter 1 for Track Commands.");
		System.out.println("Enter 2 for Playlist Commands.");
		System.out.println("Enter q to quit Music Library Tool.");
		Scanner input = new Scanner(System.in);
		String newInput = new String(); 
		newInput = input.nextLine();	
		if(Integer.parseInt(newInput) == 1)
		{
			//input.close();
			trackCommand();
			return;
		}
		else if(Integer.parseInt(newInput) == 2)
			playlistCommand();
		else if(newInput.equals("q"))
			System.exit(0);
		while(!newInput.equals("1") || !newInput.equals("2") || !newInput.equals("q"))	
		{
			System.out.println("Command Error! ");
			System.out.println("Please enter 1, 2, or q.");
			newInput = input.nextLine();	
			if(Integer.parseInt(newInput) == 1)
				trackCommand();
			else if(Integer.parseInt(newInput) == 2)
				playlistCommand();
			else if(newInput.equals("q"))
				System.exit(0);
		}
	//	input.close();
		}
		catch(NumberFormatException | NoSuchElementException nfe){
			mainMenu();
		}
		System.out.println("end");
	}
	
	public void trackCommand()
	{
		System.out.println("Select a Track Command: ");
		System.out.println("Enter 1 to add new Track.");
		System.out.println("Enter 2 to display all Tracks.");
		System.out.println("Enter 3 to sort all Tracks.");
		System.out.println("Enter 4 to search a Track.");
		System.out.println("Enter 5 to add a Track to a Playlist.");
		System.out.println("Enter 6 to tag a Track.");
		System.out.println("Enter 0 to retun to Main Menu.");
		Scanner input = new Scanner(System.in);
		String newInput = input.nextLine();
		
		switch(Integer.parseInt(newInput))
		{
		case 0:	mainMenu();
			break;
		case 1:	addNewTrack(); 
			break;
		case 2: displayAllTrack();
			break;
		case 3: sortAllTrack();
			break;
		case 4: searchAllTracks();
			mainMenu();
			break;
		case 5: addToPlaylist();
			mainMenu();
			break;	
		case 6: tagATrack();
			mainMenu();
			break;
		default: mainMenu();
			break;
		}
		//input.close();
	}
	
	public void playlistCommand()
	{
		System.out.println("Select a Track Command: ");
		System.out.println("Enter 1 to add new Playlist.");
		System.out.println("Enter 2 to display all Playlist.");
		System.out.println("Enter 3 to open a Playlist.");
		System.out.println("Enter 0 to retun to Main Menu.");
		Scanner input = new Scanner(System.in);
		String newInput = input.nextLine();
		
		switch(Integer.parseInt(newInput))
		{
		case 0:	mainMenu();
			break;
		case 1: addNewPlaylist();
			break;
		case 2: displayAllPlaylist();
			break;
		case 3: openPlaylist();
			break;
		default: mainMenu();
			break;
		}
	//	input.close();
	}
	
	public void addNewTrack()
	{	
		System.out.println("Enter Track title: ");
		Scanner input = new Scanner(System.in);
		String name = input.nextLine();
		System.out.println("Enter Artist name:  ");
		String artist = input.nextLine();
		System.out.println("Add a Tag, enter q to stop: ");
		String tagName = input.nextLine();
		ArrayList<String> tag = new ArrayList<String>();
		while(!tagName.equals("q"))
		{
			tag.add(tagName);
			System.out.println("Add another Tag, enter q to stop: ");
			tagName = input.nextLine();
		}
		System.out.println("Enter Track's path: ");
		String path = input.nextLine();
		counter++;
		Track obj = new Track(counter, name, artist,tag ,path);
		newLib.tracktoLib(obj);
		System.out.println("The following new Track has been added to the Library: ");
		System.out.println("ID: " + obj.getID() + " Track Name: " + obj.getName() + " Track Artist: " 
				+ obj.getArtist() + " Track Tags: " + obj.getTag() + " Track Path: " + obj.getPath());
		System.out.println("Enter anything to return to Main Menu: ");
		input.nextLine();
	//	input.close();
		mainMenu();
	}
	
	public void displayAllTrack()
	{
		newLib.listAllTracks();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter anything to return to Main Menu: ");
		input.nextLine();
	//input.close();
		mainMenu();
	}

	public void sortAllTrack()
	{
		System.out.println("Enter 1 to sort all Tracks by Title. ");
		System.out.println("Enter 2 to sort all Tracks by Artist. ");
		Scanner input = new Scanner(System.in);
		String cmd = input.nextLine();
		newLib.sort(Integer.parseInt(cmd));
		if(cmd.equals("1") || cmd.equals("2"))
		{
			System.out.println("All Tracks have been sorted.");
			System.out.println("Enter anything to return to Main Menu: ");
			input.nextLine();
			//input.close();
			mainMenu();
		} else { 
			System.out.println("Enter anything to return to Main Menu: ");
			input.nextLine();
			//input.close();
			mainMenu();
		}
	}
	
	public void searchAllTracks()
	{
		System.out.println("Enter 1 to search track by name.");
		System.out.println("Enter 2 to search track by artist.");
		System.out.println("Enter 3 to search track by tag.");
		Scanner input = new Scanner(System.in);
		String newSearch = input.nextLine();
		System.out.println("Enter the keyword for your search: ");
		String newKeyword = input.nextLine();
		newLib.searchTrack(newSearch, newKeyword);
	//	input.close();
		mainMenu();
	}
	
	public void addToPlaylist()
	{
		System.out.println("Pick a Track from the following list to add to Playlist:");
		newLib.listAllTracks();
		System.out.println("Type in the name of the Track you wish to select: ");
		Scanner input = new Scanner(System.in);
		String selectedTrack = input.nextLine();
		System.out.println("Pick a Playlist from the following list to add your Track to:");
		newLib.listALLPlaylist();
		System.out.println("Type in the name of the Playlist you wish to select: ");
		String selectedPlaylist = input.nextLine();
		for(Track track : newLib.tracks)
			if(track.getName().equals(selectedTrack))
			{
				newLib.searchForPlaylist(selectedPlaylist).add(track);
				break;
			}
	}
	
	public void tagATrack()
	{
		newLib.listAllTracks();
		System.out.println("Enter the index of the track: ");
		Scanner input = new Scanner(System.in);
		String intInput = input.nextLine();
		int trackIndex = Integer.parseInt(intInput);
		System.out.println("Enter a tag.");
		String tagInput = input.nextLine();
		newLib.tracks.get(trackIndex-1).setTag(tagInput);// getTracks(trackIndex-1).setTag(tagInput);
		System.out.println("Tag has been added.");
		//input.close();
		mainMenu();
	}
	
	public void addNewPlaylist()
	{
		System.out.println("Enter new Playlist name: ");
		Scanner input = new Scanner(System.in);
		String cmd = input.nextLine();
		Playlist newList = new Playlist(null, cmd);
		newLib.addToPlayList(newList);
		System.out.println("The new Playlist: " + newList.getName() + " has been added to the Library.");
		System.out.println("Enter anything to return to Main Menu: ");
		input.nextLine();
	//	input.close();
		mainMenu();	
	}
	
	public void displayAllPlaylist()
	{
		newLib.listALLPlaylist();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter anything to return to Main Menu: ");
		input.nextLine();
		//input.close();
		mainMenu();
	}

	public void openPlaylist()
	{
		try{
		System.out.println("Select from the following list of Playlists to open:");
		newLib.listALLPlaylist();
		System.out.println("Enter the name of the Playlist you wish to open: ");
		Scanner input = new Scanner(System.in);
		String cmd = input.nextLine();
		Playlist theList = newLib.searchForPlaylist(cmd);
		if(theList.equals(null))
		{
			while(theList.equals(null))
			{
				System.out.println("Playlist does not exist in Music Library, please enter another name: ");
				cmd = input.nextLine();
				theList = newLib.searchForPlaylist(cmd);
			}
		}
		if(theList.getList().equals(null))
		{
			System.out.println("This Playlist is empty.");
			System.out.println("Enter anything to return to Main Menu: ");
			input.nextLine();
		//	input.close();
			mainMenu();
		}
		else
		{
			System.out.println("The following Playlist " + theList.getName() + " contains this list of Tracks: ");
			newLib.listTracksInPlaylist(theList);
			System.out.println("Enter anything to return to Main Menu: ");
			input.nextLine();
		//	input.close();
			mainMenu();
		}
		}catch(NullPointerException npe){ mainMenu();};
	}
}