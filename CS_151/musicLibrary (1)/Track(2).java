
import java.util.ArrayList;
//import java.util.Comparator;


public class Track /*implements Comparator<String>*/  {
	
	//private ArrayList<String> myTrack;
	private long TrackID;
	private String TrackName;
	private String TrackArtist;
	private String TrackPath;
	private ArrayList <String> Tags;
    
	Track(long id, String newName, String newArtist,ArrayList<String> newTags,String path) 
	{
		this.TrackID = id;
		this.TrackName = newName;
		this.TrackArtist = newArtist;
		this.Tags = newTags;
		this.TrackPath = path;
	}
      
	// Accessors
	public void setId(long id)
    {
        this.TrackID = id;
    }
    public void setName(String trackName)
    {
        this.TrackName = trackName;
    }
    public void setArtist(String trackArtist)
    {
        this.TrackArtist = trackArtist;
    }
 
    public void setTag(String addTag)
    {
        this.Tags.add(addTag);
    }
    public void setPath(String addPath)
    {
    	this.TrackPath = addPath;
    }
    
    public long getID(){return TrackID;}
   	public String getName(){return TrackName;}
	public String getArtist(){return TrackArtist;}

	public ArrayList<String> getTag(){return Tags;}
	public String getPath(){return TrackPath;}

	@Override
    public String toString()
    {
        return "Track Name: " + TrackName+".mp3" +"\n" + "Artist: " + TrackArtist +"\n" + "id: " + TrackID + " \n"+ "Tags: " + Tags + "\n" +"Path: "+ TrackPath;
    }
/*
	public int compare(String track1, String track2) {
		// TODO Auto-generated method stub
		if(track1.compareTo(track2) >= 0)
			return 1;
		else
			return 0;
	}*/
}
	

