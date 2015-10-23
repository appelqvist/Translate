package com.group.translate;

import android.media.session.MediaSession;
import android.os.AsyncTask;
import android.util.Log;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

/**
 * Created by andreasappelqvist on 2015-10-21.
 */

public class Controller {

    private String transKey = "";
    private String TAG = "Controller";
    private MainActivity activity;
    private MainFragment mainFragment;
    private Speaker speaker;

    private final String secret = "MsmMRLHZ5DyF+lct2ghRBaqAqcngZciiq7mdRbLbt9A=";
    private final String id = "translate1337";

    public Controller(MainActivity activity) {
        this.activity = activity;
        this.speaker = new Speaker(activity);

        this.mainFragment = (MainFragment) activity.getFragmentManager().findFragmentByTag("mainFragment");
        if (this.mainFragment == null) {
            this.mainFragment = new MainFragment();
            this.activity.setFragment(mainFragment, "mainFragment", false);
            this.mainFragment.setController(this);
        }
    }

    public String translate(String inLang, String outLang, String phrase) {

        MyAsyncTask a = new MyAsyncTask();
        a.execute("asdasd");
        return null;
    }

    public void translateAndSpeak(String inLang, String outLang, String swePhrase) {
        Log.d(TAG, "InputLang: " + inLang);
        Log.d(TAG, "OutputLang: " + outLang);
        Log.d(TAG, "SwePhrase: " + swePhrase);

        //Göra en översättning på ordet swePhrase
        //Från Svenksa till Angivet språk
        //Tar det översatta ordet som använder playText(outLang, nyaOrdet)


        //Ska tas bort sen:
        playText(outLang, swePhrase);
    }

    public void playText(String lang, String phrase) {
        speaker.speak(lang, phrase);
    }

    private class MyAsyncTask extends AsyncTask<String, Void, String> {
       /* protected Boolean doInBackground(Void... arg0) {
            Translate.setClientId(id);
            Translate.setClientSecret(secret);
            try {
                translatedText = Translate.execute("I should probably set this to something a little less profane", Language.ENGLISH, Language.FRENCH);
                Log.d("",translatedText);
            } catch (Exception e) {
                translatedText = e.toString();
            }

            return null;
        }
        */

        @Override
        protected String doInBackground(String... params) {
            Translate.setClientSecret(secret);
            Translate.setClientId(id);

            try{
                Log.d("", Translate.execute("hej", Language.SWEDISH, Language.ENGLISH));
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

}
