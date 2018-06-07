package com.adxcorp.adsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

/**
 * Created by god on 2017. 3. 16..
 */

public class InterstitialActivity extends AppCompatActivity {
    private MoPubInterstitial mMoPubInterstitial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMoPubInterstitial = new MoPubInterstitial(this, DefineAdUnitId.INTERSTITIAL_AD_UNIT_ID);
        mMoPubInterstitial.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
            @Override
            public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                mMoPubInterstitial.show();
                Log.d("eleanor","onInterstitialLoaded");
            }

            @Override
            public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
                Log.d("eleanor","onInterstitialFailed");
            }

            @Override
            public void onInterstitialShown(MoPubInterstitial interstitial) {
                Log.d("eleanor","onInterstitialShown");

            }

            @Override
            public void onInterstitialClicked(MoPubInterstitial interstitial) {
                Log.d("eleanor","onInterstitialClicked");

            }

            @Override
            public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                Log.d("eleanor","onInterstitialDismissed");

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
