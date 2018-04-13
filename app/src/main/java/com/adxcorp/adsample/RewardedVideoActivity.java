package com.adxcorp.adsample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideos;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.vungle.mediation.VungleAdapter;
import com.vungle.mediation.VungleExtrasBuilder;

import java.util.Set;

public class RewardedVideoActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private RewardedVideoAd mRewardedVideoAd;
    private Button mButton;
    private boolean isMoPub = false;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video);

        if (isMoPub) {
            MoPubRewardedVideos.initializeRewardedVideo(this);
            MoPub.onCreate(this);

            loadRewardedVideoDataMoPub();
        } else {
            MobileAds.initialize(this, "ca-app-pub-7466439784264697~3084726285");

            mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
            mRewardedVideoAd.setRewardedVideoAdListener(this);

            loadRewardedVideoDataAdMob();
        }

        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isMoPub) {
                    if (MoPubRewardedVideos.hasRewardedVideo(DefineAdUnitId.REWARDED_VIDEO_AD_UNIT_ID)) {
                        Log.d("eleanor", "isLoaded");
                        MoPubRewardedVideos.showRewardedVideo(DefineAdUnitId.REWARDED_VIDEO_AD_UNIT_ID);
                    } else {
                        Log.d("eleanor", "isNOTLoaded");
                        loadRewardedVideoDataMoPub();
                    }
                } else {
                    if (mRewardedVideoAd.isLoaded()) {
                        Log.d("eleanor", "isLoaded");
                        mRewardedVideoAd.show();
                    } else {
                        Log.d("eleanor", "isNOTLoaded");
                        loadRewardedVideoDataAdMob();
                    }
                }

            }
        });

        MoPubRewardedVideos.setRewardedVideoListener(new MoPubRewardedVideoListener() {
            @Override
            public void onRewardedVideoClicked(@NonNull String adUnitId) {
                Log.d("chiung.choi", "onRewardedVideoClicked");
            }

            @Override
            public void onRewardedVideoLoadSuccess(String adUnitId) {
                Log.d("chiung.choi", "onRewardedVideoLoadSuccess");

            }

            @Override
            public void onRewardedVideoLoadFailure(String adUnitId, MoPubErrorCode errorCode) {
                Log.d("chiung.choi", "onRewardedVideoLoadFailure");
                Log.d("errorCode",errorCode.toString());
            }

            @Override
            public void onRewardedVideoStarted(String adUnitId) {
                Log.d("chiung.choi", "onRewardedVideoStarted");
            }

            @Override
            public void onRewardedVideoPlaybackError(String adUnitId, MoPubErrorCode errorCode) {
                Log.d("chiung.choi", "onRewardedVideoPlaybackError");
            }

            @Override
            public void onRewardedVideoClosed(String adUnitId) {
                Log.d("chiung.choi", "onRewardedVideoClosed");
            }

            @Override
            public void onRewardedVideoCompleted(Set<String> adUnitIds, MoPubReward reward) {
                Log.d("chiung.choi", "onRewardedVideoCompleted");
            }
        });


    }

    private void loadRewardedVideoDataMoPub() {
        MoPubRewardedVideos.loadRewardedVideo(DefineAdUnitId.REWARDED_VIDEO_AD_UNIT_ID);
    }

    private void loadRewardedVideoDataAdMob() {

        String[] placements = new String[3];
        placements[0] = "DEFAULT-0339375";
        placements[1] = "SAMPLE_ANDROID_INTERSTITIAL-0969912";
        placements[2] = "SAMPLE_ANDROID_REWARDED_VIDEO-3138664";
        Bundle extras = new VungleExtrasBuilder(placements).build();

        AdRequest request =  new AdRequest.Builder()
                .addNetworkExtrasBundle(VungleAdapter.class, extras)
                .addTestDevice("4004489C018AF66569C76C82351F84F3")
                .build();

        mRewardedVideoAd.loadAd("ca-app-pub-7466439784264697/2318439525", request);
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Log.d("eleanor", "onRewardedVideoAdLoaded");
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
    }

    @Override
    public void onPause() {
        super.onPause();
        MoPub.onPause(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MoPub.onResume(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        MoPub.onStart(this);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        MoPub.onRestart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        MoPub.onStop(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MoPub.onDestroy(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MoPub.onBackPressed(this);
    }
}
