package com.rightmeowapps.greenthumb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.rightmeowapps.greenthumb.GreenThumbApp;
import com.rightmeowapps.greenthumb.R;
import com.rightmeowapps.greenthumb.data.RealmManager;
import com.rightmeowapps.greenthumb.model.Auth;
import com.rightmeowapps.greenthumb.model.Project;
import com.rightmeowapps.greenthumb.util.UrlUtil;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by anthonykiniyalocts on 10/30/15.
 */
public class InitActivity extends ButterKnifeActivity implements Callback<Project>{

    public final static String TAG = InitActivity.class.getSimpleName();

    @Bind(R.id.frame)
    RelativeLayout mFrame;

    @Bind(R.id.project_url)
    EditText mProjectUrl;

    private String path, token, release;

    @Override
    public int getContentView() {
        return R.layout.activity_init;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @OnClick(R.id.check_id_btn)
    public void onVerfiyID(){

        if(!TextUtils.isEmpty(mProjectUrl.getText())) {

            path = UrlUtil.getProjectIdFromPath(mProjectUrl.getText().toString());

            token = UrlUtil.getQueryFromUrl(mProjectUrl.getText().toString(), "token");

            release = UrlUtil.getQueryFromUrl(mProjectUrl.getText().toString(), "release");

            if(path != null && token != null && release != null) {
                GreenThumbApp.instance.getNewAPI(token, release)
                        .getProject(path)
                        .enqueue(this);
            }
            else {
                onFailure(null);
            }
        }
    }


    @Override
    public void onResponse(final Response<Project> response, Retrofit retrofit) {

        Auth auth = new Auth();
        auth.setId(path);
        auth.setToken(token);
        auth.setRelease(release);

        RealmManager<Auth> authRealmManager = new RealmManager<>(Auth.class);

        authRealmManager.copyOrUpdate(auth);

        Snackbar.make(mFrame, "Url validated! Would you like to save this project?", Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.save_project, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        RealmManager<Project> projectRealmManager = new RealmManager<Project>(Project.class);

                        projectRealmManager.copyOrUpdate(response.body());

                        Intent intent = new Intent(InitActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        finish();
                    }
                })
                .show();

    }

    @Override
    public void onFailure(Throwable t) {
        Snackbar.make(mFrame, "Problem validating URL for project. Please double check your url and try again.", Snackbar.LENGTH_INDEFINITE)
                .show();

        if(t != null) {
            t.printStackTrace();
        }
    }
}
