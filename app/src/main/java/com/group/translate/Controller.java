package com.group.translate;

import java.io.IOException;
import java.net.URL;

/**
 * Created by andreasappelqvist on 2015-10-21.
 */

public class Controller {

    private MainActivity activity;
    private MainFragment mainFragment;

    public Controller(MainActivity activity){
        this.activity = activity;

        this.mainFragment = (MainFragment) activity.getFragmentManager().findFragmentByTag("mainFragment");
        if (this.mainFragment == null) {
            this.mainFragment = new MainFragment();
            this.activity.setFragment(mainFragment, "mainFragment", false);
            this.mainFragment.setController(this);
        }
    }

    public void test(){
        try{
            URL google = new URL("http://rate-exchange.appspot.com/currency?from=USD&to=EUR");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
