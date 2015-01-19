package mod6.texttosoundtrack.echonest;

import mod6.texttosoundtrack.spotify.SpotifyHandler;

public class EchonestHandler {
    private SpotifyHandler spotifyHandler;

    public static void main(String[] args) {
        EchonestHandler echonestHandler = new EchonestHandler();
        //echonestHandler.searchSong();
        //System.out.println(echonestHandler.searchSong());
        echonestHandler.findTrack(EchonestMood.ACTION);
    }

    public EchonestHandler() {
        spotifyHandler = new SpotifyHandler();
    }

    public void findTrack(EchonestMood mood) {
        System.out.println("Starting findTrack: " + System.currentTimeMillis());
        Thread thread = new Thread(new FindTrack(spotifyHandler, mood));
        thread.start();
        System.out.println("Done with findtrack: " + System.currentTimeMillis());
        /*if (mood != null) {
            try {
                URL url = new URL("http://developer.echonest.com/api/v4/song/search?api_key=CGV11LMHK97XRE10T&format=json&" +
                        "mood=" + mood.getEchonestMood() + "&min_instrumentalness=0.95&bucket=id:spotify&bucket=tracks");
                URLConnection urlConnection = url.openConnection();
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);
                //new JSONObject(new JSONParser().parse(responseStrBuilder.toString()));
                JSONObject json = (JSONObject) JSONValue.parse(responseStrBuilder.toString());
                JSONArray songArray = (JSONArray) ((JSONObject) json.get("response")).get("songs");
                for (int i = 0; i < songArray.size(); i++) {
                    JSONObject song = (JSONObject) songArray.get(i);
                    JSONArray tracks = (JSONArray) song.get("tracks");
                    if (!tracks.isEmpty()) {
                        String trackId = (String) ((JSONObject) tracks.get(0)).get("foreign_id");
                        //Try to play track
                        if (spotifyHandler.playTrack(trackId)) {
                            return true;
                        }
                    }

                }
                streamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;*/
    }
}
