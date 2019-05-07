package com.adxcorp.adxdev;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.adxcorp.gdpr.ADXGDPR;
import com.adxcorp.nativead.NativeAdFactory;
import com.mopub.nativeads.ADXViewBinder;
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

        NativeAdFactory.setViewBinder(DefineAdUnitId.NATIVE_AD_UNIT_ID, new ViewBinder.Builder(R.layout.layout_native_ad)
                .mainImageId(R.id.mainImageId)
                .iconImageId(R.id.iconImageId)
                .titleId(R.id.titleId)
                .privacyInformationIconImageId(R.id.privacyInformationIconImageId)
                .callToActionId(R.id.callToActionId)
                .addExtra("ad_choices_container", R.id.ad_choices_container)
                .build());

        NativeAdFactory.setAdxViewBinder(DefineAdUnitId.NATIVE_AD_UNIT_ID, new ADXViewBinder.Builder(R.layout.layout_media_native_ad)
                .mediaViewContainerId(R.id.mediaContainerId)
                .adIconViewContainerId(R.id.adIconContainerId)
                .titleId(R.id.titleId)
                .adChoiceContainerId(R.id.adChoicesContainerId)
                .callToActionId(R.id.callToActionId)
                .build());

        ADXGDPR.initWithShowAdxConsent(this, DefineAdUnitId.BANNER_AD_UNIT_ID, false, new ADXGDPR.ADXConsentListener() {
            @Override
            public void onResult(ADXGDPR.ADXConsentState state) {
                Log.d("eleanor", "initWithShowAdxConsent result : " + state);
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

    @OnClick(R.id.btn_native_close_ad)
    void onNativeCloseAd() {
        Intent intent = new Intent(this, CloseNativeAdActivity.class);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("eleanor", "onDestroy");
    }
}
