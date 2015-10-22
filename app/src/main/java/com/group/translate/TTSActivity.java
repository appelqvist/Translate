package com.group.translate;

import org.ispeech.SpeechSynthesis;
import org.ispeech.SpeechSynthesisEvent;
import org.ispeech.error.BusyException;
import org.ispeech.error.InvalidApiKeyException;
import org.ispeech.error.NoNetworkException;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Anders on 2015-10-22.
 */
public class TTSActivity extends Activity{
    private static final String TAG = "iSpeech";
    private SpeechSynthesis synthesis;
    private Context context;
    private MainFragment mainFragment;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getApplicationContext();

        prepareTTSEngine();

        synthesis.setStreamType(AudioManager.STREAM_MUSIC);
        startSpeak();
    }

    private void prepareTTSEngine() {
        try {
            synthesis = SpeechSynthesis.getInstance(this);
            synthesis.setSpeechSynthesisEvent(new SpeechSynthesisEvent() {

                public void onPlaySuccessful() {
                    Log.i(TAG, "onPlaySuccessful");
                }

                public void onPlayStopped() {
                    Log.i(TAG, "onPlayStopped");
                }

                public void onPlayFailed(Exception e) {
                    Log.e(TAG, "onPlayFailed");


                    AlertDialog.Builder builder = new AlertDialog.Builder(TTSActivity.this);
                    builder.setMessage("Error[TTSActivity]: " + e.toString())
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
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
            Toast.makeText(context, "ERROR: Invalid API key", Toast.LENGTH_LONG).show();
        }
    }
    public void startSpeak(){
//        try {
//				//String ttsText = mainFragment.etPhrase.getText().toString();
//            String ttsText = "Hello";
//				byte [] b = synthesis.downloadByteArray(ttsText);
//
//				if (b!=null){
//					Log.d("DEBUG", "SUCESSSSSSSS!!!!!");
//					MediaPlayer mediaPlayer;
//					mediaPlayer = new MediaPlayer();
//
//					File tempMp3 = File.createTempFile("test", ".mp3", getCacheDir());
//	                FileOutputStream fos = new FileOutputStream(tempMp3);
//	                fos.write(b);
//	                fos.close();
//
//	                mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.fromFile(tempMp3));
//	                mediaPlayer.start();
//
//
//					//mediaPlayer.setDataSource();
//				}else{
//					Log.d("DEBUG", "FAILURE :( ");
//				}
//
//			} catch (BusyException e) {
//				Log.e(TAG, "SDK is busy");
//				e.printStackTrace();
//				Toast.makeText(context, "ERROR: SDK is busy", Toast.LENGTH_LONG).show();
//			} catch (NoNetworkException e) {
//				Log.e(TAG, "Network is not available\n" + e.getStackTrace());
//				Toast.makeText(context, "ERROR: Network is not available", Toast.LENGTH_LONG).show();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

            try {
                //String ttsText = mainFragment.etPhrase.getText().toString();
                String ttsText = "Cioa,Bella";
                synthesis.setVoiceType("euritalianmale");
                synthesis.speak(ttsText);


            } catch (BusyException e) {
                Log.e(TAG, "SDK is busy");
                e.printStackTrace();
                Toast.makeText(context, "ERROR: SDK is busy", Toast.LENGTH_LONG).show();
            } catch (NoNetworkException e) {
                Log.e(TAG, "Network is not available\n" + e.getStackTrace());
                Toast.makeText(context, "ERROR: Network is not available", Toast.LENGTH_LONG).show();
            }
        }
    }
