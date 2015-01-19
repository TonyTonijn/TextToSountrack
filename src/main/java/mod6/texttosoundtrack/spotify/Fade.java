package mod6.texttosoundtrack.spotify;

import jahspotify.media.Loadable;
import jahspotify.services.MediaPlayer;

import java.util.Timer;

public class Fade implements Loadable{
    private MediaPlayer mediaPlayer = MediaPlayer.getInstance();
    private FadeTask fadeTask;
    private boolean faded = false;

    public Fade() {
    }

    public void fadeIn() {
        cancelRunning();
        fadeTask = new FadeTask(mediaPlayer, true);
        new Timer().scheduleAtFixedRate(fadeTask, 0, 10);
    }

    public void fadeOut() {
        cancelRunning();
        fadeTask = new FadeTask(mediaPlayer, false);
        new Timer().scheduleAtFixedRate(fadeTask, 0, 10);
    }

    private void cancelRunning() {
        if (fadeTask != null && !fadeTask.isFinished()) {
            fadeTask.cancel();
        }
    }

    @Override
    public boolean isLoaded() {
        return faded;
    }

    @Override
    public void setLoaded(boolean faded) {
        this.faded = faded;
    }
}
