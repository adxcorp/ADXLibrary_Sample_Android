package com.adxcorp.adsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.content_main)
    RelativeLayout mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_interstitial)
    void onInterstitial() {
        Intent intent = new Intent(this, InterstitialActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_banner)
    void onBanner() {
        Intent intent = new Intent(this, BannerActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_close_ad)
    void onCloseAd() {
        Intent intent = new Intent(this, CloseAdActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_native_ad_factory)
    void onNativeAdFactory() {
        Intent intent = new Intent(this, NativeAdFactoryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_native_ad_recycler_view)
    void onNativeRecyclerView() {
        Intent intent = new Intent(this, NativeAdRecyclerViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_app_wall)
    void onAppWall() {
        Intent intent = new Intent(this, AppWallActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_rewarded_video)
    void onRewardedVideo() {
        Intent intent = new Intent(this, RewardedVideoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_rewarded_video_adMob)
    void onRewardedVideoAdMob() {
        Intent intent = new Intent(this, RewardedVideoAdMobActivity.class);
        startActivity(intent);
    }
}
