package com.adxcorp.adsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.mopub.nativeads.NativeAd;
import com.mopub.nativeads.NativeAdFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by god on 2017. 3. 16..
 */

public class NativeAdFactoryActivity extends AppCompatActivity {
    @BindView(R.id.content_main)
    LinearLayout mContentView;

    private View mAdView;
    private NativeAd mNativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad_factory);

        ButterKnife.bind(this);

        NativeAdFactory.addListener(mListener);
        NativeAdFactory.loadAd(DefineAdUnitId.NATIVE_AD_UNIT_ID);
    }

    @Override
    protected void onDestroy() {
        NativeAdFactory.removeListener(mListener);

        if (mNativeAd != null) {
            mNativeAd.destroy();
            mNativeAd = null;
        }

        super.onDestroy();
    }

    private NativeAdFactory.NativeAdListener mListener = new NativeAdFactory.NativeAdListener() {
        @Override
        public void onSuccess(String s, NativeAd nativeAd) {
            Log.d("eleanor","onSuccess");
            if (DefineAdUnitId.NATIVE_AD_UNIT_ID.equals(s)) {
                mNativeAd = nativeAd;
                mAdView = NativeAdFactory.getNativeAdView(NativeAdFactoryActivity.this, DefineAdUnitId.NATIVE_AD_UNIT_ID, mContentView, new NativeAd.MoPubNativeEventListener() {
                    @Override
                    public void onImpression(View view) {
                        Log.d("eleanor","onImpression");
                    }

                    @Override
                    public void onClick(View view) {
                        Log.d("eleanor","onClick");
                    }
                });
                mContentView.addView(mAdView);
            }
        }

        @Override
        public void onFailure(String s) {
            Log.d("eleanor","onFailure");
        }
    };
}
