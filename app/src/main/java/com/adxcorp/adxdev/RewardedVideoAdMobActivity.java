package com.adxcorp.adxdev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.adxcorp.gdpr.ADXGDPR;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class RewardedVideoAdMobActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private RewardedVideoAd mRewardedVideoAd;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video_ad_mob);

        MobileAds.initialize(this);

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        mButton = (Button)findViewById(R.id.buttonAdMob);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRewardedVideoAd.isLoaded()) {
                    Log.d("eleanor", "isLoaded");
                    mRewardedVideoAd.show();
                } else {
                    Log.d("eleanor", "isNOTLoaded");
                    if (mRewardedVideoAd.isLoaded() == false) {
                        loadRewardedVideoDataAdMob();
                    }
                }
            }
        });

        loadRewardedVideoDataAdMob();
    }

    private void loadRewardedVideoDataAdMob() {


        Bundle extras = new Bundle();

        if (ADXGDPR.ADXConsentState.values()[ADXGDPR.getResultGDPR(this)] == ADXGDPR.ADXConsentState.ADXConsentStateDenied) {
            extras.putString("npa", "1");
        }

        AdRequest request =  new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();
        request.isTestDevice(this);

        mRewardedVideoAd.loadAd("ca-app-pub-7325474360708943/1610082143", request);
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Log.d("eleanor", "onRewardedVideoAdLoaded : " + mRewardedVideoAd.getMediationAdapterClassName());

        Toast.makeText(RewardedVideoAdMobActivity.this, "onRewardedVideoAdLoaded", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Log.d("eleanor", "onRewardedVideoAdOpened");
    }

    @Override
    public void onRewardedVideoStarted() {
        Log.d("eleanor", "onRewardedVideoStarted");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.d("eleanor", "onRewardedVideoAdClosed");
    }

    @Override
    public void onRewarded(RewardItem var1) {
        Log.d("eleanor", "onRewarded");
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Log.d("eleanor", "onRewardedVideoAdLeftApplication");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int var1) {
        Log.d("eleanor", "onRewardedVideoAdFailedToLoad");
        Toast.makeText(RewardedVideoAdMobActivity.this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
        Log.d("eleanor", "onRewardedVideoCompleted");
    }

    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }
}
