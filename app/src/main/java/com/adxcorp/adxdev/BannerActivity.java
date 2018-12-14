package com.adxcorp.adxdev;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;

/**
 * Created by god on 2017. 3. 16..
 */

public class BannerActivity extends AppCompatActivity {
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
                Log.d("eleanor", "onBannerLoaded");
            }

            @Override
            public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
                Log.d("eleanor", "onBannerFailed");
            }

            @Override
            public void onBannerClicked(MoPubView banner) {
                Log.d("eleanor", "onBannerClicked");
            }

            @Override
            public void onBannerExpanded(MoPubView banner) {
                Log.d("eleanor", "onBannerExpanded");
            }

            @Override
            public void onBannerCollapsed(MoPubView banner) {
                Log.d("eleanor", "onBannerCollapsed");

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
