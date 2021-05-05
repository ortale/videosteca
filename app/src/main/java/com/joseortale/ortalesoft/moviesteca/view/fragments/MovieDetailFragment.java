package com.joseortale.ortalesoft.moviesteca.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.joseortale.ortalesoft.moviesteca.R;
import com.joseortale.ortalesoft.moviesteca.model.Movie;
import com.joseortale.ortalesoft.moviesteca.viewmodel.MovieViewModel;

public class MovieDetailFragment extends Fragment {
    private Context context;

    private MovieViewModel movieViewModel;
    private ProgressBar progressCircular;
    private LinearLayout llData;

    private static final String MOVIE_ID = "MOVIE_ID";

    private Integer id;
    private Movie movie;

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment fragment = new MovieDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(MOVIE_ID, movie.getId());

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            id = bundle.getInt(MOVIE_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_video_detail, container, false);

        progressCircular = view.findViewById(R.id.progress_circular);
        llData = view.findViewById(R.id.ll_data);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.init(context);
        movieViewModel.getMovieById(context, id);
        movieViewModel.getMovieRepository().observe(this, movie -> {
            progressCircular.setVisibility(View.GONE);
            llData.setVisibility(View.VISIBLE);
            this.movie = movie;
            updateData(view);
            getCollections(movie.getId());
        });

        return view;
    }

    private void updateData(View view) {
        TextView tvHomepage = view.findViewById(R.id.tv_homepage);

        tvHomepage.setText(movie.getHomepage());
    }

    private void getCollections(Integer id) {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.init(context);
        movieViewModel.getAllCollectionsById(context, id);
        movieViewModel.getMovieRepository().observe(this, movie -> {
            progressCircular.setVisibility(View.GONE);
            this.movie = movie;
        });
    }
}
