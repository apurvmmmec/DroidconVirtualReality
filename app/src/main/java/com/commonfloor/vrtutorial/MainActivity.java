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
    private CardboardOverlayView mOverlayView;

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
        mOverlayView = (CardboardOverlayView) findViewById(R.id.overlay);
        mOverlayView.show3DToast("Pull the magnet when you find an object.");

    }

    /**
     * Called when the Cardboard trigger is pulled.
     */
    @Override
    public void onCardboardTrigger() {
        Log.i(TAG, "onCardboardTrigger");

        if (mRenderer.isLookingAtObject()) {
            score++;
            mOverlayView.show3DToast("Found it! Look around for another one.\nScore = " + score);
            mRenderer.hideObject();
        } else {
            mOverlayView.show3DToast("Look around to find the object!");
        }

        // Always give user feedback.
        vibrator.vibrate(50);
    }

}
