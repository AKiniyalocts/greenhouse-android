package com.rightmeowapps.greenthumb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.rightmeowapps.greenthumb.R;
import com.rightmeowapps.greenthumb.model.Project;

import butterknife.Bind;
import io.realm.Realm;

public class SplashActivity extends ButterKnifeActivity {

    private final static int SPLASH_TIME_OUT = 3100;

    public final static String TAG = SplashActivity.class.getSimpleName();

    @Override
    public int getContentView() {
        return R.layout.activity_splash;
    }

    @Bind(R.id.splash_text)
    TextView splashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Realm.getDefaultInstance()
                        .executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {

                                int count = realm.where(Project.class).findAll().size();

                                if(count == 0) {

                                    startActivity(new Intent(SplashActivity.this, InitActivity.class));
                                }
                                else {

                                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                }

                                finish();
                            }
                        });

            }
        }, SPLASH_TIME_OUT);


        animate();
    }

    private void animate(){
        YoYo.with(Techniques.RollIn)
                .duration(2000)
                .playOn(splashText);
    }

}
