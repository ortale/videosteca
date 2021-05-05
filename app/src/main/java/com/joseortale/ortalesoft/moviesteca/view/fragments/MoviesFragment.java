package com.joseortale.ortalesoft.moviesteca.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.joseortale.ortalesoft.moviesteca.R;
import com.joseortale.ortalesoft.moviesteca.model.Movie;
import com.joseortale.ortalesoft.moviesteca.view.activities.MainActivity;
import com.joseortale.ortalesoft.moviesteca.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {
    private Context context;

    private List<Movie> movieArrayList = new ArrayList<>();
    private GridLayout glVideos;
    private MovieViewModel movieViewModel;
    private ProgressBar progressCircular;

    private int i = 0;

    public static MoviesFragment newInstance() {
        MoviesFragment fragment = new MoviesFragment();

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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_videos, container, false);

        glVideos = view.findViewById(R.id.gv_movies);
        progressCircular = view.findViewById(R.id.progress_circular);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.init(context);
        movieViewModel.getAllMovies(context);
        movieViewModel.getMoviesRepository().observe(this, movieArrayList -> {
            progressCircular.setVisibility(View.GONE);
            this.movieArrayList = movieArrayList;
            setupGridLayout();
        });

        return view;
    }

    private void setupGridLayout() {
        glVideos.removeAllViews();

        glVideos.setColumnCount(1);
        glVideos.setRowCount(movieArrayList.size());

        for (Movie movie : movieArrayList) {
            TextView tvTitle = new TextView(context);
            tvTitle.setText(movie.getTitle());
            tvTitle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            GridLayout.Spec rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
            GridLayout.Spec colspan = GridLayout.spec(GridLayout.UNDEFINED, 1);
            GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(rowSpan, colspan);

            gridParam.setMargins(10, 10, 10, 10);

            glVideos.addView(tvTitle, gridParam);
        }

        int childCount = glVideos.getChildCount();

        for (i = 0; i < childCount; i++) {
            TextView container = (TextView) glVideos.getChildAt(i);
            container.setOnClickListener(view -> {
                ((MainActivity) context).setFragment(MovieDetailFragment.newInstance(movieArrayList.get(i-1)));
            });
        }
    }
}
