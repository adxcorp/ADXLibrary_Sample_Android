package com.adxcorp.adsample;

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
import com.mopub.mobileads.MoPubRewardedVideos;

import java.util.Set;

public class RewardedVideoActivity extends AppCompatActivity {
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video);

        mButton = (Button) findViewById(R.id.button);

        MoPubRewardedVideos.initializeRewardedVideo(this);
        loadRewardedVideo();

        MoPub.onCreate(this);


        MoPubRewardedVideos.setRewardedVideoListener(new MoPubRewardedVideoListener() {
            @Override
            public void onRewardedVideoClicked(@NonNull String adUnitId) {
                Log.d("chiung.choi", "onRewardedVideoClicked");
                Toast.makeText(RewardedVideoActivity.this, "onRewardedVideoClicked", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedVideoLoadSuccess(String adUnitId) {
                // Called when the video for the given adUnitId has loaded. At this point you should be able to call MoPubRewardedVideos.showRewardedVideo(String) to show the video.
                Log.d("chiung.choi", "onRewardedVideoLoadSuccess");
                Toast.makeText(RewardedVideoActivity.this, "onRewardedVideoLoadSuccess", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedVideoLoadFailure(String adUnitId, MoPubErrorCode errorCode) {
                // Called when a video fails to load for the given adUnitId. The provided error code will provide more insight into the reason for the failure to load.
                Log.d("chiung.choi", "onRewardedVideoLoadFailure");
                Toast.makeText(RewardedVideoActivity.this, "onRewardedVideoLoadFailure", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedVideoStarted(String adUnitId) {
                // Called when a rewarded video starts playing.
                Log.d("chiung.choi", "onRewardedVideoStarted");
                Toast.makeText(RewardedVideoActivity.this, "onRewardedVideoStarted", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedVideoPlaybackError(String adUnitId, MoPubErrorCode errorCode) {
                //  Called when there is an error during video playback.
                Log.d("chiung.choi", "onRewardedVideoPlaybackError");
                Toast.makeText(RewardedVideoActivity.this, "onRewardedVideoPlaybackError", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedVideoClosed(String adUnitId) {
                // Called when a rewarded video is closed. At this point your application should resume.
                Log.d("chiung.choi", "onRewardedVideoClosed");
                Toast.makeText(RewardedVideoActivity.this, "onRewardedVideoClosed", Toast.LENGTH_LONG).show();
                loadRewardedVideo();
            }

            @Override
            public void onRewardedVideoCompleted(Set<String> adUnitIds, MoPubReward reward) {
                // Called when a rewarded video is completed and the user should be rewarded.
                // You can query the reward object with boolean isSuccessful(), String getLabel(), and int getAmount().
                Log.d("chiung.choi", "onRewardedVideoCompleted");
                Toast.makeText(RewardedVideoActivity.this, "onRewardedVideoCompleted", Toast.LENGTH_LONG).show();
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MoPubRewardedVideos.hasRewardedVideo(DefineAdUnitId.REWARDED_VIDEO_AD_UNIT_ID)) {
                    Log.d("chiung.choi", "hasRewardedVideo true. Play rewarded Video");
                    Toast.makeText(RewardedVideoActivity.this, "hasRewardedVideo true. Play rewarded Video", Toast.LENGTH_LONG).show();
                    MoPubRewardedVideos.showRewardedVideo(DefineAdUnitId.REWARDED_VIDEO_AD_UNIT_ID);
                } else {
                    Log.d("chiung.choi", "hasRewardedVideo false. Load rewarded Video");
                    Toast.makeText(RewardedVideoActivity.this, "hasRewardedVideo false. Load rewarded Video", Toast.LENGTH_LONG).show();
                    loadRewardedVideo();
                }
            }
        });
    }

    private void loadRewardedVideo() {
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
