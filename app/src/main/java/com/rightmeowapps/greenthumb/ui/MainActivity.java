package com.rightmeowapps.greenthumb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;
import com.rightmeowapps.greenthumb.GreenThumbApp;
import com.rightmeowapps.greenthumb.R;
import com.rightmeowapps.greenthumb.data.ActivityComponent;
import com.rightmeowapps.greenthumb.data.ApplicationComponent;
import com.rightmeowapps.greenthumb.data.DaggerActivityComponent;
import com.rightmeowapps.greenthumb.data.HasComponent;
import com.rightmeowapps.greenthumb.data.RealmManager;
import com.rightmeowapps.greenthumb.data.RealmModule;
import com.rightmeowapps.greenthumb.model.Auth;
import com.rightmeowapps.greenthumb.model.HeroCard;
import com.rightmeowapps.greenthumb.model.Project;
import com.rightmeowapps.greenthumb.service.CheckBuildingIntentService;
import com.rightmeowapps.navigationdrawer.drawer.model.DrawerItem;
import com.rightmeowapps.navigationdrawer.drawer.model.DrawerItemBuilder;
import com.rightmeowapps.navigationdrawer.drawer.ui.DrawerActivity;
import com.rightmeowapps.navigationdrawer.drawer.ui.DrawerAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import io.realm.RealmResults;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends DrawerActivity implements
        DrawerAdapter.DrawerItemListener,
        SwipeRefreshLayout.OnRefreshListener,
        Callback<Project>,
        HasComponent<ActivityComponent>{

    public final static String TAG = MainActivity.class.getSimpleName();


    @Bind(R.id.main_toolbar)
    Toolbar mToolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.drawer_recycler)
    RecyclerView mRecycler;

    @Bind(R.id.project_recycler)
    RecyclerView mProjectRecycler;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mRefresh;

    @Bind(R.id.add_fab)
    FloatingActionButton fab;


    @Inject
    RealmManager<Project> projectRealmManager;

    @Inject
    RealmManager<Auth> authRealmManager;

    @Override
    public ActivityComponent getComponent() {
        return null;
    }

    @Override
    public ActivityComponent buildComponent() {
        return  DaggerActivityComponent
                .builder()
                .realmModule(new RealmModule())
                .build();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    @Override
    public DrawerAdapter getDrawerAdapter() {
        return drawerAdapter;
    }

    @Override
    public Toolbar getToolBar() {
        return mToolbar;
    }

    private DrawerAdapter drawerAdapter;

    private RealmResults<Project> projects;


    private Project selectedProject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildComponent().inject(this);

        initDrawer();
        mRefresh.setColorSchemeResources(R.color.accent);
        mRefresh.setOnRefreshListener(this);

        populateDrawer();
    }

    @OnClick(R.id.add_fab)
    public void onAddNewProject(){
        YoYo.with(Techniques.Tada)
                .duration(400)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startActivity(new Intent(MainActivity.this, InitActivity.class));
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(fab);
    }

    @Override
    public void onResponse(Response<Project> response, Retrofit retrofit) {
        projectRealmManager.copyOrUpdate(response.body());

        if(!response.body().getLatestBuild().getStatus().equalsIgnoreCase("finished")){
            startBuildingService();
        }

        populateDrawer();

        mRefresh.setRefreshing(false);
    }

    private void startBuildingService() {
        Intent intent = new Intent(this, CheckBuildingIntentService.class);
        intent.putExtra(CheckBuildingIntentService.EXTRA_PROJECT_ID, selectedProject.getId());
        startService(intent);
    }


    @Override
    public void onFailure(Throwable t) {
        mRefresh.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mRefresh.setRefreshing(true);


        Auth auth = authRealmManager.findFirst("id", selectedProject.getId());


        GreenThumbApp.instance.getNewAPI(auth.getToken(), auth.getRelease())
                .getProject(selectedProject.getId())
                .enqueue(this);
    }

    private void populateDrawer(){

        projects = projectRealmManager.findAll();

        for(Project project: projects){

            String unicode = "\uF2DC";
            if(project.getPlatform().equalsIgnoreCase(getString(R.string.android))) {
                unicode = "\uF33A";
            }
            if(project.getPlatform().equalsIgnoreCase(getString(R.string.ios))){
                unicode = "\uF33C";
            }
            DrawerItem drawerItem = new DrawerItemBuilder(project.getName())
                    .setUnicode(unicode)
                    .setIsSelectable(true)
                    .build();

            if(!drawerAdapter.hasItem(drawerItem)) {
                drawerAdapter.addItem(drawerItem);
            }
        }

        drawerAdapter.setSelectedItem(1);
        onDrawerItemClicked(null, 1);

    }


    @Override
    public void onDrawerItemClicked(DrawerItem drawerItem, int i) {
        selectedProject = projects.get(i - 1);

        loadProject();
        closeDrawer();
    }

    private void loadProject() {
        ProjectAdapter projectAdapter = new ProjectAdapter(this);
        projectAdapter.setHeroCard(HeroCard.buildFromProject(selectedProject));
        projectAdapter.setDivider(getString(R.string.latest_build));

        mProjectRecycler.setAdapter(projectAdapter);
    }

    private void initDrawer(){
        drawerAdapter = new DrawerAdapter(this);

        drawerAdapter.setDrawerItemListener(this);

        drawerAdapter.addHeader(
                new DrawerItemBuilder(getString(R.string.app_name))
                        .setIsHeader(true)
                        .setHeaderTitle(this.getString(R.string.projects))
                        .setHeaderImageDrawable(getString(R.string.header_img_url))
                        .build()
        );


        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        mRecycler.setAdapter(drawerAdapter);


        mProjectRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
