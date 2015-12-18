package com.rightmeowapps.greenthumb.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.format.DateUtils;
import android.util.Log;

import com.rightmeowapps.greenthumb.GreenThumbApp;
import com.rightmeowapps.greenthumb.R;
import com.rightmeowapps.greenthumb.model.Auth;
import com.rightmeowapps.greenthumb.model.Project;

import io.realm.Realm;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by anthonykiniyalocts on 11/2/15.
 */
public class CheckBuildingIntentService extends IntentService implements Callback<Project>{

    private final static String TAG = CheckBuildingIntentService.class.getSimpleName();

    public final static String EXTRA_PROJECT_ID = "extra::project::id";

    public final static int NOTIFICATION_ID = 1001;

    public CheckBuildingIntentService(){
        super("CheckBuildingIntentService");
    }

    private String projectId;


    @Override
    protected void onHandleIntent(Intent intent) {

        if(intent != null){

            projectId = intent.getStringExtra(EXTRA_PROJECT_ID);

            if(projectId != null) {
                Realm.getDefaultInstance().beginTransaction();
                Auth auth = Realm.getDefaultInstance().where(Auth.class).equalTo("id", projectId).findFirst();
                Realm.getDefaultInstance().commitTransaction();

                GreenThumbApp.instance.getNewAPI(auth.getToken(), auth.getRelease())
                        .getProject(auth.getId())
                        .enqueue(this);
            }
        }
    }

    @Override
    public void onResponse(Response<Project> response, Retrofit retrofit) {
        Project project = response.body();


        if(!project.getLatestBuild().getStatus().equalsIgnoreCase("finished")){

            startNotfication(project);
            scheduleNextUpdate();
        }
        else {
            Log.d(TAG, "no longer building");
            endingNotification(project);
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }


    private void scheduleNextUpdate()
    {
        Intent intent = new Intent(this, this.getClass());
        intent.putExtra(EXTRA_PROJECT_ID, projectId);

        PendingIntent pendingIntent =
                PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long currentTimeMillis = System.currentTimeMillis();
        long nextUpdateTimeMillis = currentTimeMillis + 45 * DateUtils.SECOND_IN_MILLIS; //update every 45 seconds

        Log.d(TAG, "Scheduling update :" + nextUpdateTimeMillis);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, nextUpdateTimeMillis, pendingIntent);
    }

    private void startNotfication(Project project){
        Notification notification = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle(project.getName())
                .setContentText("Status: " + project.getLatestBuild().getStatus())
                .setColor(getResources().getColor(R.color.primary))
                .setSmallIcon(R.drawable.ic_bug_report)
                .addAction(R.drawable.ic_cancel, getString(R.string.stop_refreshing), null)
                .build();

        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(NOTIFICATION_ID, notification);
    }

    private void endingNotification(Project project){
        Notification notification = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle(project.getName())
                .setContentText("Status: " + project.getLatestBuild().getStatus())
                .setColor(getResources().getColor(R.color.primary))
                .setSmallIcon(R.drawable.ic_bug_report)
                .build();

        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(NOTIFICATION_ID, notification);
    }

}
