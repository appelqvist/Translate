package com.group.translate;

import java.io.IOException;
import java.net.URL;

/**
 * Created by andreasappelqvist on 2015-10-21.
 */

public class Controller {

    private MainActivity activity;
    private MainFragment mainFragment;
    private Speaker speaker;

    public Controller(MainActivity activity){
        this.activity = activity;
        this.speaker = new Speaker(activity);

        this.mainFragment = (MainFragment) activity.getFragmentManager().findFragmentByTag("mainFragment");
        if (this.mainFragment == null) {
            this.mainFragment = new MainFragment();
            this.activity.setFragment(mainFragment, "mainFragment", false);
            this.mainFragment.setController(this);
        }
    }

    public void playText(String lang, String phrase){
        speaker.speak(lang,phrase);
    }

}
