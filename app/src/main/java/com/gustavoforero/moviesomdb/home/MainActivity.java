package com.gustavoforero.moviesomdb.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.gustavoforero.moviesomdb.R;
import com.gustavoforero.moviesomdb.util.KeyboardHelper;
import com.gustavoforero.moviesomdb.domain.Movie;
import com.gustavoforero.moviesomdb.net.OMDbApi;
import com.gustavoforero.moviesomdb.net.OMDbRestClient;
import com.gustavoforero.moviesomdb.net.responses.SearchResponse;
import com.jakewharton.rxbinding3.widget.RxTextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final long DELAY_IN_MILLIS = 500;
    public static final int MIN_LENGTH_TO_START = 3;
    private static final String TAG = "MainActivity";
    private static final String DEFAULT_SEARCH = "Scary";


    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView mAutoCompleteTextView;
    @BindView(R.id.rv_movies)
    RecyclerView mRvMovies;
    @BindView(R.id.img_no_results)
    ImageView mImgNoResults;

    private MoviesAdapter mMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        loadDefaultSearch();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }

    private void initViews() {
        mMoviesAdapter = new MoviesAdapter();
        mRvMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvMovies.setAdapter(mMoviesAdapter);
        addOnAutoCompleteTextViewTextChangedObserver(mAutoCompleteTextView);

    }

    private void loadDefaultSearch() {
        mAutoCompleteTextView.setText(DEFAULT_SEARCH);
        mAutoCompleteTextView.setSelection(DEFAULT_SEARCH.length());

    }

    private void updateMovies(ArrayList<Movie> list) {
        mMoviesAdapter.updateItemList(list);

    }

    private void addOnAutoCompleteTextViewTextChangedObserver(final AutoCompleteTextView autoCompleteTextView) {
        Observable<SearchResponse> autocompleteResponseObservable =
                RxTextView.textChangeEvents(autoCompleteTextView)
                        .debounce(DELAY_IN_MILLIS, TimeUnit.MILLISECONDS)
                        .map(textViewTextChangeEvent -> textViewTextChangeEvent.getText().toString())
                        .filter(s -> s.length() >= MIN_LENGTH_TO_START)
                        .observeOn(Schedulers.io())
                        .switchMap(s -> OMDbRestClient.INSTANCE.getOmDbApi().searchMovieByTitle(OMDbApi.Companion.getSearchUrl(s)))
                        .observeOn(AndroidSchedulers.mainThread())
                        .retry();

        mCompositeDisposable.add(
                autocompleteResponseObservable
                        .subscribe(
                                response -> {
                                    KeyboardHelper.Companion.hideKeyboard(this);
                                    if (response.getResponse()) {
                                        mImgNoResults.setVisibility(View.GONE);
                                        mRvMovies.setVisibility(View.VISIBLE);
                                        updateMovies(response.getSearch());
                                    } else {
                                        mImgNoResults.setVisibility(View.VISIBLE);
                                        mRvMovies.setVisibility(View.GONE);
                                    }

                                },
                                e -> Log.e(TAG, "onError", e),
                                () -> Log.i(TAG, "onCompleted")));
    }

}
