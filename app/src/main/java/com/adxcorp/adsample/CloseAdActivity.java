package com.adxcorp.adsample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;

/**
 * Created by god on 16. 3. 14..
 */
public class CloseAdActivity extends AppCompatActivity {
    private MoPubView mCloseMoPubView;
    private CloseAdDialog mCloseDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            if (mCloseMoPubView == null) {
                mCloseMoPubView = new MoPubView(this);
                mCloseMoPubView.setAdUnitId(DefineAdUnitId.SQUARE_AD_UNIT_ID);
                mCloseMoPubView.setBannerAdListener(new MoPubView.BannerAdListener() {

                    @Override
                    public void onBannerLoaded(MoPubView arg0) {
                        mCloseMoPubView.setTag(1);
                        Log.d("eleanor", "onBannerLoaded");
                    }

                    @Override
                    public void onBannerFailed(MoPubView arg0, MoPubErrorCode arg1) {
                        Log.d("eleanor", "onBannerFailed");
                    }

                    @Override
                    public void onBannerExpanded(MoPubView arg0) {
                        Log.d("eleanor", "onBannerExpanded");
                    }

                    @Override
                    public void onBannerCollapsed(MoPubView arg0) {
                        Log.d("eleanor", "onBannerCollapsed");
                    }

                    @Override
                    public void onBannerClicked(MoPubView arg0) {
                        Log.d("eleanor", "onBannerClicked");
                    }
                });

                mCloseMoPubView.loadAd();

                mCloseDialog = new CloseAdDialog.Builder(this).setMoPubView(mCloseMoPubView).create();
                mCloseDialog.setPositiveButtonClickListener(new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (mCloseDialog == null) {
            super.onBackPressed();
        } else {
            mCloseDialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        if (mCloseMoPubView != null) {
            mCloseMoPubView.destroy();
            mCloseMoPubView = null;
        }

        super.onDestroy();
    }
}
