package com.akbar.dev.pertemuan2asynctask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.akbar.dev.pertemuan2asynctask.model.MovieData;

import java.util.List;

/**
 * Created by akbar on 07/05/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterHolder> {

    private Context mContext;
    private List<MovieData.Result> mListMovie;

    public Adapter(Context mContext, List<MovieData.Result> mListMovie) {
        this.mContext = mContext;
        this.mListMovie = mListMovie;
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{

        public AdapterHolder(View itemView) {
            super(itemView);
        }
    }

    public Adapter.AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Adapter.AdapterHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
