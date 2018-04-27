package com.alkahshafqatar.sarahmohamed.alkashafqatar.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.alkahshafqatar.sarahmohamed.alkashafqatar.DataModel.Project;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.R;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.View.ProjectListFragment.OnListFragmentInteractionListener;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.View.dummy.DummyContent.DummyItem;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Project> mValues;
    private final OnListFragmentInteractionListener mListener;
    private ProjectListFragment listFragment;


    public MyItemRecyclerViewAdapter(List<Project> items, OnListFragmentInteractionListener listener, ProjectListFragment fragment) {
        mValues = items;
        mListener = listener;
        this.listFragment = fragment;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_project_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        holder.mItem = mValues.get(position);

        holder.mContentView.setText(mValues.get(position).name);
        //Glide implementation
        String imageName =mValues.get(position).name;
        String[] splited = imageName.split("\\s+");
        String url = splited[0];
        Glide
                .with(listFragment)
                .load("http://mycoreo.com/wp-content/uploads/2012/09/url.jpeg")
                .placeholder(R.drawable.dohafestivalcity)
                .error(R.drawable.dohafestivalcity)
                .crossFade()
                .into(holder.imgView);

        setFadeAnimation(holder.itemView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //setToRightAnimation(holder.itemView);
                    //setFadeAnimation(holder.itemView);
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public Project mItem;
        public ImageView imgView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            imgView = (ImageView) view.findViewById(R.id.icon);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(700);
        view.startAnimation(anim);
    }

    private void setToRightAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);

        anim.setDuration(1000);
        view.startAnimation(anim);
    }
}
