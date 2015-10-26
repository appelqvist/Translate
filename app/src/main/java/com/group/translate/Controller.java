package com.group.translate;

import android.os.AsyncTask;
import android.util.Log;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Created by grupp on 2015-10-21.
 */

/**
 * Klassen sköter logiken i programmet och används som en vidarbefodrare.
 */
public class Controller {

    private String TAG = "Controller";
    private MainActivity activity;
    private MainFragment mainFragment;
    private Speaker speaker;

    private final String secret = "MsmMRLHZ5DyF+lct2ghRBaqAqcngZciiq7mdRbLbt9A=";
    private final String id = "translate1337";

    /**
     * Initisierar klassen
     * Och startar hem-fragmentet
     * @param activity
     */
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

    /**
     * Konventerar String lang till ett Language-obj
     * @param strLang
     * @return Language-obj
     */
    public Language stringtoLang(String strLang){
        Language lang;

        if (strLang.equals("Svenska")) {
            lang = Language.SWEDISH;
        } else if (strLang.equals("English")) {
            lang = Language.ENGLISH;
        } else if (strLang.equals("Italiano")) {
            lang = Language.ITALIAN;
        } else if (strLang.equals("Francais")) {
            lang = Language.FRENCH;
        } else if (strLang.equals("Espanol")) {
            lang = Language.SPANISH;
        } else {
            lang = Language.ARABIC;
        }
        return lang;
    }

    /**
     * Översätter ordet och skriver ut det i en textview
     * @param inLang
     * @param outLang
     * @param phrase
     */
    public void translate(String inLang, String outLang, String phrase) {

        Language in, out;

        in = stringtoLang(inLang);
        out = stringtoLang(outLang);

        new MyAsyncTask() {
            protected void onPostExecute(String result) {
                mainFragment.setTextTranslated(result);
            }
        }.execute(in, out, phrase);

    }

    /**
     * Översätter frasen samt pratar ut frasen.
     * @param inLang
     * @param outLang
     * @param phrase
     */
    public void translateAndSpeak(String inLang, final String outLang, String phrase) {

        Language in, out;
        in = stringtoLang(inLang);
        out = stringtoLang(outLang);

        new MyAsyncTask() {
            protected void onPostExecute(String result) {
                mainFragment.setTextTranslated(result);
                playText(outLang,result);
            }
        }.execute(in, out, phrase);
    }

    /**
     * Talar ut bestämd fras.
     * @param lang
     * @param phrase
     */
    public void playText(String lang, String phrase) {
        speaker.speak(lang, phrase);
    }


    /**
     * Klassen representerar en översättningsapparat
     * Översätter fraser som skickas in med inLang och outLang
     */
    class MyAsyncTask extends AsyncTask<Object, String, String> {
        @Override
        protected String doInBackground(Object... arg0) {
            Translate.setClientId(id);
            Translate.setClientSecret(secret);

            String phrase = (String) arg0[2];
            Language in = (Language) arg0[0];
            Language out = (Language) arg0[1];

            String translatedText0;
            try {
                translatedText0 = Translate.execute(phrase, in, out);
            } catch (Exception e) {
                translatedText0 = e.toString();
            }
            return translatedText0;
        }
    }

}
