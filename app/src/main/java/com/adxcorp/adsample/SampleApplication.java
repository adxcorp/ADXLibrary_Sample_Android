package com.adxcorp.adsample;

import android.support.multidex.MultiDexApplication;

import com.mopub.nativeads.AppWallFactory;
import com.mopub.nativeads.NativeAdFactory;
import com.mopub.nativeads.ViewBinder;

/**
 * Created by god on 2017. 3. 13..
 */

public class SampleApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        AppWallFactory.init(this, "32687", "10247");

        NativeAdFactory.init(this);

        // for Native Ad
        NativeAdFactory.setViewBinder(DefineAdUnitId.NATIVE_AD_UNIT_ID, new ViewBinder.Builder(R.layout.layout_native_ad)
                .mainImageId(R.id.mainImageId)
                .iconImageId(R.id.iconImageId)
                .titleId(R.id.titleId)
                .callToActionId(R.id.callToActionId)
                .privacyInformationIconImageId(R.id.privacyInformationIconImageId)
                .build());
        NativeAdFactory.preloadAd(DefineAdUnitId.NATIVE_AD_UNIT_ID);
    }
}
