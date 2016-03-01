
import java.util.ArrayList;

public class Playlist {
	private ArrayList<Track> playlist;
	String playlistName;
	
	Playlist(ArrayList<Track> newPlaylist, String newName)
	{
		playlist = newPlaylist;
		playlistName = newName;
	}

	// Accessors
	public String getName(){ return playlistName;}
	public ArrayList<Track> getList(){ return playlist;}
	
	// Mutators
	public void setName(String newListName){ playlistName= newListName;}
	
	// add/delete tracks from list
	public void add(Track newTrack){ playlist.add(newTrack);}
	public void deleteTrack(String trackName)
	{ 
		for(Track removeMe : playlist)
		{
			if(removeMe.getName().equals(trackName))
				playlist.remove(removeMe);
		}
	}
	
}

