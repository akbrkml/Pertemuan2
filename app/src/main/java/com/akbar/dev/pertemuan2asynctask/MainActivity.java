package com.akbar.dev.pertemuan2asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.akbar.dev.pertemuan2asynctask.adapter.AdapterMovies;
import com.akbar.dev.pertemuan2asynctask.model.MovieData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MovieData mMovieData;
    private AdapterMovies mAdapterMovies;
    private String urlMovie;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvListMovie);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);


        urlMovie = AppConstant.MOVIE_URL + "popular" + "?api_key=" + AppConstant.API_KEY;
        JsonParser mJsonParser = new JsonParser();
        mJsonParser.execute(urlMovie, "execute");
    }

    public class JsonParser extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String movieJsonStr = null;

            try {
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                movieJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e("MainActivity", "Error ", e);
                return null;
            }
            return movieJsonStr;
        }



        @Override
        protected void onPostExecute(String s) {
            mMovieData = new Gson().fromJson(s, MovieData.class);

            List<MovieData.Result> mListMovie = new ArrayList<>();
            mListMovie.addAll(mMovieData.results);

            //memasukan data ke adapter movie
            mAdapterMovies = new AdapterMovies(MainActivity.this, mListMovie);
            mRecyclerView.setAdapter(mAdapterMovies);
        }
    }
}
