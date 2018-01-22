package com.adxcorp.adsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mopub.nativeads.AppWallFactory;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by god on 2017. 3. 16..
 */

public class AppWallActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_wall);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AppWallFactory.preloadAppWall();
    }

    @OnClick(R.id.btn_app_wall)
    public void onAppWall() {
        AppWallFactory.showAppWall(this);
    }
}
