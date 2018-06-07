package com.adxcorp.adsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.adxcorp.gdpr.ADXGDPR;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.vungle.mediation.VungleAdapter;
import com.vungle.mediation.VungleExtrasBuilder;

public class RewardedVideoAdMobActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private RewardedVideoAd mRewardedVideoAd;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video_ad_mob);

        MobileAds.initialize(this, "ca-app-pub-7466439784264697~3084726285");

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

        String[] placements = new String[3];
        placements[0] = "DEFAULT-0339375";
        placements[1] = "SAMPLE_ANDROID_INTERSTITIAL-0969912";
        placements[2] = "SAMPLE_ANDROID_REWARDED_VIDEO-3138664";
        Bundle extras = new VungleExtrasBuilder(placements).build();

        if (ADXGDPR.ADXConsentState.values()[ADXGDPR.getResultGDPR(this)] == ADXGDPR.ADXConsentState.ADXConsentStateDenied) {
            extras.putString("npa", "1");
        }

        AdRequest request =  new AdRequest.Builder()
                .addTestDevice("97E619D7296064A9130A9014FC1734D5")
                .addNetworkExtrasBundle(VungleAdapter.class, extras)
                .build();

        mRewardedVideoAd.loadAd("ca-app-pub-7466439784264697/2318439525", request);
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Log.d("eleanor", "onRewardedVideoAdLoaded");
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

    }

}
