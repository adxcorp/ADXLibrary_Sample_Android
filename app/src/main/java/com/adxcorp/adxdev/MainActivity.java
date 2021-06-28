package com.adxcorp.adxdev;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.adxcorp.gdpr.ADXGDPR;
import com.adxcorp.nativead.NativeAdFactory;
import com.adxcorp.util.ADXLogUtil;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.mopub.nativeads.ADXViewBinder;
import com.mopub.nativeads.ViewBinder;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ADX:" + MainActivity.class.getSimpleName();

    @BindView(R.id.content_main)
    RelativeLayout mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 애드몹 테스트 디바이스 설정
        RequestConfiguration configuration = new RequestConfiguration.Builder()
                .setTestDeviceIds(
                        Arrays.asList("9E928D273E90AF6642DB99539DB71311",
                                "9279398731BF4A37D46CBDC590AACE5B")
                ).build();
        MobileAds.setRequestConfiguration(configuration);

        // 애드몹 초기화
        MobileAds.initialize(this);

        // ADX Library Debug 설정
        ADXLogUtil.setLogEnable(true);

        // ADX Native AD 초기화
        NativeAdFactory.init(this);

        NativeAdFactory.setViewBinder(DefineAdUnitId.NATIVE_AD_UNIT_ID, new ViewBinder.Builder(R.layout.layout_native_ad)
                .mainImageId(R.id.mainImageId)
                .iconImageId(R.id.iconImageId)
                .titleId(R.id.titleId)
                .privacyInformationIconImageId(R.id.privacyInformationIconImageId)
                .callToActionId(R.id.callToActionId)
                .addExtra("ad_choices_container", R.id.ad_choices_container)
                .build());

        // ADX Native AD ViewBinder 설정
        NativeAdFactory.setAdxViewBinder(DefineAdUnitId.NATIVE_AD_UNIT_ID, new ADXViewBinder.Builder(R.layout.layout_media_native_ad)
                .mediaViewContainerId(R.id.mediaContainerId)
                .adIconViewContainerId(R.id.adIconContainerId)
                .titleId(R.id.titleId)
                .adChoiceContainerId(R.id.adChoicesContainerId)
                .callToActionId(R.id.callToActionId)
                .build());

        // ADX Library 초기화
        ADXGDPR.initWithShowAdxConsent(this, DefineAdUnitId.BANNER_AD_UNIT_ID, false, new ADXGDPR.ADXConsentListener() {
            @Override
            public void onResult(ADXGDPR.ADXConsentState state) {
                Log.d(TAG, "initWithShowAdxConsent result : " + state);
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
        Log.e(TAG, "onDestroy");
    }
}
