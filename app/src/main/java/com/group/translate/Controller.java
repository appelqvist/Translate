package com.group.translate;

import android.util.Log;

import java.io.IOException;
import java.net.URL;

/**
 * Created by andreasappelqvist on 2015-10-21.
 */

public class Controller {

    private String TAG = "Controller";
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

    public String translate(String inLang, String outLang, String phrase){
        //Översätter ordet
        //Returnerar de översatta ordet.
        return TAG+".translate";
    }

    public void translateAndSpeak(String inLang, String outLang, String swePhrase){
        Log.d(TAG, "InputLang: "+inLang);
        Log.d(TAG,"OutputLang: "+outLang);
        Log.d(TAG,"SwePhrase: "+swePhrase);


        //Göra en översättning på ordet swePhrase
        //Från Svenksa till Angivet språk
        //Tar det översatta ordet som använder playText(outLang, nyaOrdet)


        //Ska tas bort sen:
        playText(outLang, swePhrase);
    }

    public void playText(String lang, String phrase){
        speaker.speak(lang,phrase);
    }

}
