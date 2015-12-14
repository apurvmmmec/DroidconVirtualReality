package com.commonfloor.vrtutorial;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

import com.google.vrtoolkit.cardboard.CardboardActivity;
import com.google.vrtoolkit.cardboard.CardboardView;


public class MainActivity extends CardboardActivity {

    private static final String TAG = "MainActivity";
    private int score = 0;
    private Vibrator vibrator;

    private VRRenderer mRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardboardView cardboardView = (CardboardView) findViewById(R.id.cardboard_view);
        cardboardView.setRestoreGLStateEnabled(false);

        mRenderer = new VRRenderer(this);
        cardboardView.setRenderer(mRenderer);
        setCardboardView(cardboardView);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    }

    /**
     * Called when the Cardboard trigger is pulled.
     */
    @Override
    public void onCardboardTrigger() {
        Log.i(TAG, "onCardboardTrigger");

        if (mRenderer.isLookingAtObject()) {
            score++;
            mRenderer.hideObject();
        } else {

        }

        // Always give user feedback.
        vibrator.vibrate(50);
    }

}
