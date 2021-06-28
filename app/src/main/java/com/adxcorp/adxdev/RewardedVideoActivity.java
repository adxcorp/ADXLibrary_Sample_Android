package com.adxcorp.adxdev;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedAdListener;
import com.mopub.mobileads.MoPubRewardedAdManager;
import com.mopub.mobileads.MoPubRewardedAds;

import java.util.Set;

public class RewardedVideoActivity extends AppCompatActivity {

    private static final String TAG = "ADX:" + RewardedVideoActivity.class.getSimpleName();

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video);

        MoPubRewardedAdManager.init(this);
        MoPubRewardedAdManager.updateActivity(this);

        loadRewardedVideoDataMoPub();

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MoPubRewardedAds.hasRewardedAd(DefineAdUnitId.REWARDED_VIDEO_AD_UNIT_ID)) {
                    Log.d(TAG, "isLoaded");
                    MoPubRewardedAds.showRewardedAd(DefineAdUnitId.REWARDED_VIDEO_AD_UNIT_ID);
                } else {
                    Log.d(TAG, "isNOTLoaded");
                    loadRewardedVideoDataMoPub();
                }
            }
        });

        MoPubRewardedAds.setRewardedAdListener(new MoPubRewardedAdListener() {
            @Override
            public void onRewardedAdLoadSuccess(String adUnitId) {
                Log.d(TAG, "onRewardedAdLoadSuccess");
                Toast.makeText(RewardedVideoActivity.this, "onRewardedAdLoadSuccess", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedAdLoadFailure(String adUnitId, MoPubErrorCode moPubErrorCode) {
                Log.d(TAG, "onRewardedVideoLoadFailure : " + moPubErrorCode.toString());
                Toast.makeText(RewardedVideoActivity.this, "onRewardedVideoLoadFailure", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedAdStarted(String adUnitId) {
                Log.d(TAG, "onRewardedVideoStarted");
            }

            @Override
            public void onRewardedAdShowError(String adUnitId, MoPubErrorCode moPubErrorCode) {
                Log.d(TAG, "onRewardedAdShowError");
            }

            @Override
            public void onRewardedAdClicked(String adUnitId) {
                Log.d(TAG, "onRewardedAdClicked");
            }

            @Override
            public void onRewardedAdClosed(String adUnitId) {
                Log.d(TAG, "onRewardedAdClosed");
            }

            @Override
            public void onRewardedAdCompleted(Set<String> set, MoPubReward moPubReward) {
                Log.d(TAG, "onRewardedAdCompleted");
            }
        });
    }

    private void loadRewardedVideoDataMoPub() {
        MoPubRewardedAds.loadRewardedAd(DefineAdUnitId.REWARDED_VIDEO_AD_UNIT_ID);
        Log.d(TAG, "rewardvideo load");
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
