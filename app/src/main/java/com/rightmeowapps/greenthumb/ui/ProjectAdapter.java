package com.rightmeowapps.greenthumb.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rightmeowapps.greenthumb.R;
import com.rightmeowapps.greenthumb.model.DescCard;
import com.rightmeowapps.greenthumb.model.HeroCard;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by anthonykiniyalocts on 11/2/15.
 */
public class ProjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static int VIEW_TYPE_HERO = 0;

    public final static int VIEW_TYPE_DESC = 1;

    public final static int VIEW_TYPE_DIVIDER = 2;

    private Context context;

    private LayoutInflater layoutInflater;

    private String divider;

    private HeroCard heroCard;

    private DescCard descCard;

    public ProjectAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return VIEW_TYPE_HERO;
        }
        else if(position == 1){
            return VIEW_TYPE_DIVIDER;
        }
        return VIEW_TYPE_DESC;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        if(viewType == VIEW_TYPE_HERO){
            itemView = layoutInflater.inflate(R.layout.project_hero_item, parent, false);

            return new ProjectHeroVH(itemView);
        }
        else if (viewType == VIEW_TYPE_DIVIDER){
            itemView = layoutInflater.inflate(R.layout.project_divider_item, parent, false);

            return new DividerVH(itemView);
        }
        else {
            itemView = layoutInflater.inflate(R.layout.project_description_item, parent, false);
            return new DescVH(itemView);
        }

    }

    public void setHeroCard(HeroCard heroCard) {
        this.heroCard = heroCard;
        notifyItemInserted(0);
    }

    public void setDescCard(DescCard descCard) {
        this.descCard = descCard;
        notifyItemInserted(1);
    }

    public void setDivider(String divider) {
        this.divider = divider;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_HERO){

            ProjectHeroVH projectHeroVH = ((ProjectHeroVH) holder);
            projectHeroVH.mTitle.setText(heroCard.getProjectTitle());
            projectHeroVH.mPlatform.setText(heroCard.getProjectPlatform());

            Picasso.with(context)
                    .load(heroCard.getProjectIconUrl())
                    .fit()
                    .centerCrop()
                    .into(projectHeroVH.mImage);
        }
        else if(getItemViewType(position) == VIEW_TYPE_DIVIDER){
            DividerVH dividerVH = ((DividerVH) holder);

            dividerVH.mTitle.setText(divider);
        }
        else {
            DescVH descVH = ((DescVH) holder);

            descVH.mBuild.setText("Version: " + heroCard.getLatestBuild().getVersionName());

            descVH.mStart.setText("Started at: " + heroCard.getLatestBuild().getStartedAt());

            descVH.mEnd.setText(context.getString(R.string.started_at) +  heroCard.getLatestBuild().getFinishedAt());
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    class ProjectHeroVH extends RecyclerView.ViewHolder{

        @Bind(R.id.hero_image)
        ImageView mImage;

        @Bind(R.id.hero_title)
        TextView mTitle;

        @Bind(R.id.hero_platform)
        TextView mPlatform;


        public ProjectHeroVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class DescVH extends RecyclerView.ViewHolder{

        @Bind(R.id.build_number)
        TextView mBuild;

        @Bind(R.id.started_at)
        TextView mStart;

        @Bind(R.id.ended_at)
        TextView mEnd;

        public DescVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class DividerVH extends RecyclerView.ViewHolder{

        @Bind(R.id.project_divider_text)
        TextView mTitle;

        public DividerVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
