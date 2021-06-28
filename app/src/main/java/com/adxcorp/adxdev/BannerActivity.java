package com.adxcorp.adxdev;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;

/**
 * Created by god on 2017. 3. 16..
 */

public class BannerActivity extends AppCompatActivity {

    private static final String TAG = "ADX:" + BannerActivity.class.getSimpleName();

    private MoPubView mMoPubView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        mMoPubView = (MoPubView) findViewById(R.id.adview);
        mMoPubView.setAdUnitId(DefineAdUnitId.BANNER_AD_UNIT_ID);
        mMoPubView.setTesting(true);
        mMoPubView.setBannerAdListener(new MoPubView.BannerAdListener() {
            @Override
            public void onBannerLoaded(MoPubView banner) {
                Log.d(TAG, "onBannerLoaded");
            }

            @Override
            public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
                Log.d(TAG, "onBannerFailed");
            }

            @Override
            public void onBannerClicked(MoPubView banner) {
                Log.d(TAG, "onBannerClicked");
            }

            @Override
            public void onBannerExpanded(MoPubView banner) {
                Log.d(TAG, "onBannerExpanded");
            }

            @Override
            public void onBannerCollapsed(MoPubView banner) {
                Log.d(TAG, "onBannerCollapsed");

            }
        });
        mMoPubView.loadAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMoPubView.destroy();
    }
}
