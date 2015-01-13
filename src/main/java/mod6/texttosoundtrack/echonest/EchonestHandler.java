package mod6.texttosoundtrack.echonest;

import com.echonest.api.v4.*;
import mod6.texttosoundtrack.SpotifyHandler;

import java.util.List;

public class EchonestHandler {
    private EchoNestAPI echoNest;
    private SpotifyHandler spotifyHandler;

    public static void main(String[] args) throws EchoNestException {
        EchonestHandler echonestHandler = new EchonestHandler();
        echonestHandler.searchSong();
        //System.out.println(echonestHandler.searchSong());
    }

    public EchonestHandler(){
        spotifyHandler = new SpotifyHandler();
        echoNest = new EchoNestAPI("CGV11LMHK97XRE10T");
    }

    public boolean searchSong() {
        SongParams params = new SongParams();
        params.add("mood", "happy");
        params.set("max_speechiness", "0.9");
        params.add("bucket", "id:spotify");
        params.add("bucket", "tracks");
        try {
            List<Song> songs = echoNest.searchSongs(params);
            for (Song song : songs) {
                System.out.println(song);
                Track track = song.getTrack("spotify-WW");
                //Try to play track
                if (spotifyHandler.playTrack(track.getForeignID())) {
                    return true;
                }
            }
            return false;
        } catch (EchoNestException e) {
            e.printStackTrace();
        }
        return false;
    }
}