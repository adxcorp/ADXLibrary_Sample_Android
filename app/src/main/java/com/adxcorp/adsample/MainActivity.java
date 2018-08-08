package com.adxcorp.adsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.adxcorp.gdpr.ADXGDPR;
import com.mopub.nativeads.NativeAdFactory;
import com.mopub.nativeads.ViewBinder;

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

        final Context context = this;

        NativeAdFactory.init(context);
        // for Native Ad
        NativeAdFactory.setViewBinder(DefineAdUnitId.NATIVE_AD_UNIT_ID, new ViewBinder.Builder(R.layout.layout_native_ad)
                .mainImageId(R.id.mainImageId)
                .iconImageId(R.id.iconImageId)
                .titleId(R.id.titleId)
                .privacyInformationIconImageId(R.id.privacyInformationIconImageId)
                .callToActionId(R.id.callToActionId)
                .addExtra("ad_choices_container", R.id.ad_choices_container)
                .build());

        ADXGDPR.initWithShowAdxConsent(this, DefineAdUnitId.BANNER_AD_UNIT_ID, true, new ADXGDPR.ADXConsentListener() {
            @Override
            public void onResult(ADXGDPR.ADXConsentState adxConsentState) {
                NativeAdFactory.preloadAd(DefineAdUnitId.NATIVE_AD_UNIT_ID);
            }

        });


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
