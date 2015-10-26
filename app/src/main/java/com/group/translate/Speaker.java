package com.group.translate;
import android.media.AudioManager;
import android.util.Log;
import org.ispeech.SpeechSynthesis;
import org.ispeech.SpeechSynthesisEvent;
import org.ispeech.error.BusyException;
import org.ispeech.error.InvalidApiKeyException;
import org.ispeech.error.NoNetworkException;

/**
 * Created by group on 2015-10-22.
 */

/**
 * Talar assistent
 */
public class Speaker {
    private SpeechSynthesis synthesis;
    private MainActivity activity;
    private String TAG = "Speaker";

    /**
     * Iniitiserar Speaker-klassen
     * @param activity
     */
    public Speaker(MainActivity activity){
        this.activity = activity;
        setUpSpeaker();
        synthesis.setStreamType(AudioManager.STREAM_MUSIC);
    }

    /**
     * Förbereder sig för att kunna tala.
     * @return
     */
    public boolean setUpSpeaker(){
        try {
            synthesis = SpeechSynthesis.getInstance(this.activity);
            synthesis.setSpeechSynthesisEvent(new SpeechSynthesisEvent() {

                public void onPlaySuccessful() {
                    Log.i(TAG, "onPlaySuccessful");
                }

                public void onPlayStopped() {
                    Log.i(TAG, "onPlayStopped");
                }

                public void onPlayFailed(Exception e) {
                    Log.e(TAG, "onPlayFailed");
                }

                public void onPlayStart() {
                    Log.i(TAG, "onPlayStart");
                }

                @Override
                public void onPlayCanceled() {
                    Log.i(TAG, "onPlayCanceled");
                }


            });


        } catch (InvalidApiKeyException e) {
            Log.e(TAG, "Invalid API key\n" + e.getStackTrace());
            return false;
        }
        return true;
    }


    /**
     * Utför talandet med angivet språk och fras
     * @param lang
     * @param phrase
     */
    public void speak(String lang, String phrase){
        String speakLang = null;

        if(lang.equals("Svenska")){
            speakLang = "swswedishfemale";
        }else if(lang.equals("English")){
            speakLang = "usenglishfemale";
        }else if(lang.equals("Italiano")){
            speakLang = "euritalianfemale";
        }else if(lang.equals("Francais")){
            speakLang = "eurfrenchfemale";
        }else if(lang.equals("Espanol")){
            speakLang = "eurspanishfemale";
        }else{
            Log.e(TAG,"Felinmatat språk");
        }

        synthesis.setVoiceType(speakLang);

        try {
            synthesis.speak(phrase);
        } catch (BusyException e) {
            e.printStackTrace();
        } catch (NoNetworkException e) {
            e.printStackTrace();
        }
    }
}
