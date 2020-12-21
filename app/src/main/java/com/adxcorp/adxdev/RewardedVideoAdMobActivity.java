package com.adxcorp.adxdev;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.adxcorp.gdpr.ADXGDPR;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class RewardedVideoAdMobActivity extends AppCompatActivity {

    private static final String TAG = "eleanor";

    private Button mButton;

    private RewardedAd rewardedAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video_ad_mob);

        // 메인 액티비티에서 1회만 실행 필요
        MobileAds.initialize(this);

        mButton = (Button) findViewById(R.id.buttonAdMob);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAd();
            }
        });

        loadAd();
    }

    public void loadAd() {
        rewardedAd = createAndLoadRewardedAd();
    }

    public void showAd() {
        if (rewardedAd != null && rewardedAd.isLoaded()) {
            RewardedAdCallback adCallback = new RewardedAdCallback() {
                @Override
                public void onRewardedAdOpened() {
                    // Ad opened.
                    Log.d(TAG, "onRewardedAdOpened");
                }

                @Override
                public void onRewardedAdClosed() {
                    // Ad closed.
                    Log.d(TAG, "onRewardedAdClosed");
                    loadAd();
                }

                @Override
                public void onUserEarnedReward(@NonNull RewardItem reward) {
                    // User earned reward.
                    Log.d(TAG, "onUserEarnedReward");
                }

                @Override
                public void onRewardedAdFailedToShow(int errorCode) {
                    // Ad failed to display.
                    Log.d(TAG, "onRewardedAdFailedToShow : " + errorCode);
                }
            };

            rewardedAd.show(this, adCallback);
        } else {
            Log.d(TAG, "The rewarded ad wasn't loaded yet.");
            loadAd();
        }
    }

    @Override
    protected void onDestroy() {
        rewardedAd = null;

        super.onDestroy();
    }

    public RewardedAd createAndLoadRewardedAd() {

        RewardedAd rewardedAd = new RewardedAd(this, "ca-app-pub-7466439784264697/2318439525");

        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
                Log.d(TAG, "onRewardedAdLoaded");
                Toast.makeText(RewardedVideoAdMobActivity.this, "onRewardedVideoAdLoaded", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
                Log.d(TAG, "onRewardedAdFailedToLoad : " + errorCode);
                Toast.makeText(RewardedVideoAdMobActivity.this, "onRewardedAdFailedToLoad : " + errorCode, Toast.LENGTH_LONG).show();

            }
        };

        Bundle extras = new Bundle();

        if (ADXGDPR.ADXConsentState.values()[ADXGDPR.getResultGDPR(this)] == ADXGDPR.ADXConsentState.ADXConsentStateDenied) {
            extras.putString("npa", "1");
        }

        AdRequest request = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();
        request.isTestDevice(this);

        rewardedAd.loadAd(request, adLoadCallback);
        return rewardedAd;
    }
}
