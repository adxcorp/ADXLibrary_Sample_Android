package com.adxcorp.adxdev;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideoManager;
import com.mopub.mobileads.MoPubRewardedVideos;

import java.util.Set;

public class RewardedVideoActivity extends AppCompatActivity {
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video);

        MoPubRewardedVideoManager.init(this);
        MoPubRewardedVideoManager.updateActivity(this);

        loadRewardedVideoDataMoPub();

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MoPubRewardedVideos.hasRewardedVideo(DefineAdUnitId.REWARDED_VIDEO_AD_UNIT_ID)) {
                    Log.d("eleanor", "isLoaded");
                    MoPubRewardedVideos.showRewardedVideo(DefineAdUnitId.REWARDED_VIDEO_AD_UNIT_ID);
                } else {
                    Log.d("eleanor", "isNOTLoaded");
                    loadRewardedVideoDataMoPub();
                }
            }
        });

        MoPubRewardedVideos.setRewardedVideoListener(new MoPubRewardedVideoListener() {
            @Override
            public void onRewardedVideoClicked(@NonNull String adUnitId) {
                Log.d("eleanor", "onRewardedVideoClicked");
            }

            @Override
            public void onRewardedVideoLoadSuccess(String adUnitId) {
                Log.d("eleanor", "onRewardedVideoLoadSuccess");
                Toast.makeText(RewardedVideoActivity.this, "onRewardedVideoLoadSuccess", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onRewardedVideoLoadFailure(String adUnitId, MoPubErrorCode errorCode) {
                Log.d("eleanor", "onRewardedVideoLoadFailure");
                Log.d("errorCode", errorCode.toString());
                Toast.makeText(RewardedVideoActivity.this, "onRewardedVideoLoadFailure", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedVideoStarted(String adUnitId) {
                Log.d("eleanor", "onRewardedVideoStarted");
            }

            @Override
            public void onRewardedVideoPlaybackError(String adUnitId, MoPubErrorCode errorCode) {
                Log.d("eleanor", "onRewardedVideoPlaybackError");
            }

            @Override
            public void onRewardedVideoClosed(String adUnitId) {
                Log.d("eleanor", "onRewardedVideoClosed");
//                loadRewardedVideoDataMoPub();
            }

            @Override
            public void onRewardedVideoCompleted(Set<String> adUnitIds, MoPubReward reward) {
                Log.d("eleanor", "onRewardedVideoCompleted");

            }
        });


    }

    private void loadRewardedVideoDataMoPub() {
        MoPubRewardedVideos.loadRewardedVideo(DefineAdUnitId.REWARDED_VIDEO_AD_UNIT_ID);
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
