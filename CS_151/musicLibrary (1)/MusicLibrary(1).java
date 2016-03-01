import java.util.ArrayList;
//import java.util.List;


 class MusicLibrary  {
    
   public ArrayList<Track> tracks;
   public ArrayList<Playlist> playlist;
     
   public MusicLibrary()
   {
    	 tracks = new ArrayList<Track>();
         playlist = new ArrayList<Playlist>();
   }
   
   public void tracktoLib(Track newTrack)
   { 
      tracks.add(newTrack);
   }
   
   public void addToPlayList(Playlist tracks)
   {
       playlist.add(tracks);
   }
   
   public boolean removeTrack(String name)
   {
       for(int i =0; i<=tracks.size();i++)
       {
           Track currIndex= tracks.get(i);
           String currentSong = currIndex.getName();
           if(currentSong.equals(name))
           {
               tracks.remove(i);
           }
                  
       }
         return false;
   }
   
   public boolean removePlaylist(String playlName)
   {
	   for(int j=0; j<=playlist.size();j++)
       {
           Playlist currIndex= playlist.get(j);
           String currentSong = currIndex.getName();
           if(currentSong.equals(playlName))
           {
               tracks.remove(j);
           }
       }
	   return false;
   }
   
   public void listAllTracks()
   {
      // System.out.println("Track:");
       for (Track track: tracks)
       {
    	   System.out.println("ID: " + track.getID() + " Track Name: " + track.getName() + " Track Artist: " 
   				+ track.getArtist() + " Track Tags: " + track.getTag() + " Track Path: " + track.getPath());
       }    
   }
   
   public void listALLPlaylist()
   {
      // System.out.println("Playlist:");
       for(Playlist a: playlist)
       {
           System.out.println(a.getName());
       }
   }
   
   public void listTracksInPlaylist(Playlist thisList)
   {
	   for(Track track : thisList.getList())
		   System.out.println("ID: " + track.getID() + " Track Name: " + track.getName() + " Track Artist: " 
	   				+ track.getArtist() + " Track Tags: " + track.getTag() + " Track Path: " + track.getPath());
   }
   
   
   public Playlist searchForPlaylist(String name)
   {
	   for(Playlist thisList : playlist)
	   {
		   if(thisList.getName().equals(name))
			   return thisList;
	   }
	   return null;
   }
   
   public void searchTrack(String searchType, String keyword)
   {
	   if(searchType.equals("1"))
		   // search name
		   for(Track track : tracks)
		   {
			   if(track.getName().equals(keyword))
				   System.out.println("ID: " + track.getID() + " Track Name: " + track.getName() + " Track Artist: " 
			   				+ track.getArtist() + " Track Tags: " + track.getTag() + " Track Path: " + track.getPath());
		   }
	   else if (searchType.equals("2"))
		   // search artist
		   for(Track track : tracks)
		   {
			   if(track.getArtist().equals(keyword))
				   System.out.println("ID: " + track.getID() + " Track Name: " + track.getName() + " Track Artist: " 
			   				+ track.getArtist() + " Track Tags: " + track.getTag() + " Track Path: " + track.getPath());
		   }
	   else if (searchType.equals("3"))
		   //search tag
		   for(Track track : tracks)
		   {
			   for(String tag : track.getTag())
				   if(tag.equals(keyword))
					   System.out.println("ID: " + track.getID() + " Track Name: " + track.getName() + " Track Artist: " 
				   				+ track.getArtist() + " Track Tags: " + track.getTag() + " Track Path: " + track.getPath());
		   }
	   else
		   System.out.println("No such search type.");
   }
   // -- added sort method   
// sort by name or artist; 1 corresponds to name, 2 corresponds to artist
   	public void sort(int sortType)
	{
		Track temp;
		if(sortType == 1)
		{
			for(int i = 1; i < tracks.size(); i++)
			{
				for(int j = i; j > 0; j--)
				{
					if(tracks.get(i).getName().compareTo(tracks.get(j-1).getName()) < 0)
					{
						temp = tracks.get(j-1);
						tracks.set(j-1, tracks.get(j));
						tracks.set(j, temp);
					}
					else
					{
						j = 0;
					}
				}
			}
			listAllTracks();
		}
		else if (sortType == 2)
		{
			for(int i = 1; i < tracks.size(); i++)
			{
				for(int j = i; j > 0; j--)
				{
					if(tracks.get(i).getArtist().compareTo(tracks.get(j-1).getArtist()) < 0)
					{
						temp = tracks.get(j-1);
						tracks.set(j-1, tracks.get(j));
						tracks.set(j, temp);
					}
					else
					{
						j = 0;
					}
				}
			}
			listAllTracks();
		} 
		else
		{
			System.out.println("Wrong sort type.");
		}
		
	}
}
