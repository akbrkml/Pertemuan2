package com.akbar.dev.pertemuan2asynctask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akbar.dev.pertemuan2asynctask.R;
import com.akbar.dev.pertemuan2asynctask.model.MovieData;

import java.util.List;

/**
 * Created by akbar on 06/05/17.
 */

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.HolderMovies> {

    private Context mContext;
    private List<MovieData.Result> mListMovie;

    public AdapterMovies(Context mContext, List<MovieData.Result> listMovie) {
        this.mContext = mContext;
        this.mListMovie = listMovie;
    }


    public class HolderMovies extends RecyclerView.ViewHolder{

        TextView mTextMovieTitle;

        public HolderMovies(View itemView) {
            super(itemView);
            mTextMovieTitle = (TextView) itemView.findViewById(R.id.textMovieTitle);
        }
    }

    public HolderMovies onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(mContext).inflate(R.layout.list_item_movies, parent, false);
        HolderMovies holderMovies = new HolderMovies(rowView);
        return holderMovies;
    }

    @Override
    public void onBindViewHolder(HolderMovies holder, int position) {
        holder.mTextMovieTitle.setText(mListMovie.get(position).original_title);
    }

    @Override
    public int getItemCount() {
        return mListMovie.size();
    }
}
