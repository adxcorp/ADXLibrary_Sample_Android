package com.adxcorp.adxdev;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

/**
 * Created by god on 2017. 3. 16..
 */

public class InterstitialActivity extends AppCompatActivity {

    private static final String TAG = "ADX:" + InterstitialActivity.class.getSimpleName();

    private MoPubInterstitial mMoPubInterstitial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMoPubInterstitial = new MoPubInterstitial(this, DefineAdUnitId.INTERSTITIAL_AD_UNIT_ID);
        mMoPubInterstitial.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
            @Override
            public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                mMoPubInterstitial.show();
                Log.d(TAG, "onInterstitialLoaded");
            }

            @Override
            public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
                Log.d(TAG, "onInterstitialFailed");
            }

            @Override
            public void onInterstitialShown(MoPubInterstitial interstitial) {
                Log.d(TAG, "onInterstitialShown");

            }

            @Override
            public void onInterstitialClicked(MoPubInterstitial interstitial) {
                Log.d(TAG, "onInterstitialClicked");

            }

            @Override
            public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                Log.d(TAG, "onInterstitialDismissed");

            }
        });

        mMoPubInterstitial.load();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMoPubInterstitial.destroy();
    }
}
