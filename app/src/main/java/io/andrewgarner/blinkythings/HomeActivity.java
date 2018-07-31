package io.andrewgarner.blinkythings;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;

/**
 * Simple Blink Activity.
 * Created by andrewgarner 7/31/18
 */
public class HomeActivity extends Activity {

    private static final String LOG_TAG = HomeActivity.class.getSimpleName();

    /**
     * This is pin 3 on Pi3.
     */
    private static final String LED_PIN = "BCM2";
    /**
     * Delay between blinks
     */
    public static final int BLINK_DELAY_MILLIS = 1000;

    private Gpio mLedGpio;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Hello Home!");

        PeripheralManager manager = PeripheralManager.getInstance();

        try {
            //Open GPIO to specified pin
            mLedGpio = manager.openGpio(LED_PIN);
            //Set as Output so that we can toggle LED on and off.
            // Initially LOW so that it starts as off.
            mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            //Start loop of blink
            mHandler.post(mBlinkRunnable);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error on GPIO", e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop blink loop
        mHandler.removeCallbacks(mBlinkRunnable);

        //Close LED Pin
        if (mLedGpio != null) {
            try {
                mLedGpio.close();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error on GPIO", e);
            }
        }
    }

    /**
     * Runnable that will blink the LED.
     */
    private Runnable mBlinkRunnable = new Runnable() {
        @Override
        public void run() {
            if (mLedGpio == null) {
                //If already closed, ignore
                Log.e(LOG_TAG, "GPIO already closed. Aborting.");
                return;
            }

            try {
                //Get current state of LED so that we may flip it.
                boolean currentValue = mLedGpio.getValue();
                mLedGpio.setValue(!currentValue);
                //Start another loop, after delay.
                mHandler.postDelayed(mBlinkRunnable, BLINK_DELAY_MILLIS);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error on GPIO", e);
            }
        }
    };
}
