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
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class RewardedVideoAdMobActivity extends AppCompatActivity {

    private static final String TAG = "ADX:" + RewardedVideoAdMobActivity.class.getSimpleName();

    private Button mButton;

    private RewardedAd mRewardedAd;

    private final String adUnitID = "ca-app-pub-7466439784264697/2318439525";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video_ad_mob);

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
        Bundle extras = new Bundle();

        if (ADXGDPR.ADXConsentState.values()[ADXGDPR.getResultGDPR(this)] == ADXGDPR.ADXConsentState.ADXConsentStateDenied) {
            extras.putString("npa", "1");
        }
        AdRequest adRequest = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();

        RewardedAd.load(this, adUnitID, adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                // Ad successfully loaded.
                Log.d(TAG, "onAdLoaded");
                Toast.makeText(RewardedVideoAdMobActivity.this, "onRewardedVideoAdLoaded", Toast.LENGTH_LONG).show();

                mRewardedAd = rewardedAd;

                mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        // Ad failed to display.
                        Log.d(TAG, "onRewardedAdFailedToShow : " + adError.getCode());
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Ad opened.
                        mRewardedAd = null;
                        Log.d(TAG, "onAdShowedFullScreenContent");
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Ad closed.
                        Log.d(TAG, "onAdDismissedFullScreenContent");
                        loadAd();
                    }

                    @Override
                    public void onAdImpression() {
                        // Ad Impression
                        Log.d(TAG, "onAdImpression");
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Ad failed to load.
                Log.d(TAG, "onRewardedAdFailedToLoad : " + loadAdError.getCode());
                Toast.makeText(RewardedVideoAdMobActivity.this, "onRewardedAdFailedToLoad : " + loadAdError.getCode(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showAd() {

        if (mRewardedAd != null) {
            mRewardedAd.show(this, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // User earned reward.
                    Log.d(TAG, "onUserEarnedReward");
                }
            });
        } else {
            Log.d(TAG, "The rewarded ad wasn't loaded yet.");
            loadAd();
        }
    }

    @Override
    protected void onDestroy() {
        mRewardedAd = null;

        super.onDestroy();
    }
}
