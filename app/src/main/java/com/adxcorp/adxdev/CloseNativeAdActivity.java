package com.adxcorp.adxdev;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.adxcorp.nativead.CloseAdFactory;

public class CloseNativeAdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CloseAdFactory.init(this, DefineAdUnitId.NATIVE_AD_UNIT_ID, "");
        CloseAdFactory.preloadAd();
    }

    @Override
    public void onBackPressed() {
        CloseAdFactory.showCloseAd(this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("chiung.choi", "onClick positive button");

                finish();
            }
        }, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Log.d("chiung.choi", "onCancel");
            }
        });
    }

    @Override
    protected void onDestroy() {
        // Optional
        // CloseAdFactory.destroy();

        super.onDestroy();
    }
}
