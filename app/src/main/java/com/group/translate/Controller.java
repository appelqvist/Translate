package com.group.translate;

import android.media.session.MediaSession;
import android.os.AsyncTask;
import android.util.Log;
import android.view.animation.LayoutAnimationController;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import org.json.JSONObject;

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

    private String translatedText;

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

        Language in, out;

        if (inLang.equals("Svenska")) {
            in = Language.SWEDISH;
        } else if (inLang.equals("English")) {
            in = Language.ENGLISH;
        } else if (inLang.equals("Italiano")) {
            in = Language.ITALIAN;
        } else if (inLang.equals("Francais")) {
            in = Language.FRENCH;
        } else if (inLang.equals("Espanol")) {
            in = Language.SPANISH;
        } else {
            in = Language.ARABIC;
        }

        if (outLang.equals("Svenska")) {
            out = Language.SWEDISH;
        } else if (outLang.equals("English")) {
            out = Language.ENGLISH;
        } else if (outLang.equals("Italiano")) {
            out = Language.ITALIAN;
        } else if (outLang.equals("Francais")) {
            out = Language.FRENCH;
        } else if (outLang.equals("Espanol")) {
            out = Language.SPANISH;
        } else {
            out = Language.ARABIC;
        }

        new MyAsyncTask() {
            protected void onPostExecute(Boolean result) {
                mainFragment.setTextTranslated(translatedText);
            }
        }.execute(in, out, phrase);
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


    class MyAsyncTask extends AsyncTask<Object, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Object... arg0) {
            Translate.setClientId(id);
            Translate.setClientSecret(secret);

            String phrase = (String) arg0[2];
            Language in = (Language) arg0[0];
            Language out = (Language) arg0[1];

            try {
                translatedText = Translate.execute(phrase, in, out);
                Log.d("", translatedText);
            } catch (Exception e) {
                translatedText = e.toString();
            }
            return true;
        }
    }

}
