package me.ruygonzalez.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import org.parceler.Parcels;

import me.ruygonzalez.flicks.models.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    // the movie to display
    Movie movie;

    // the view objects
    TextView tvTitle;
    TextView tvOverview;
    TextView tvReleaseDate;
    RatingBar rbVoteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        // resolve the view objects
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));
        // set the title and overview
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        String rd = movie.getReleaseDate();
        String year = rd.substring(0, rd.indexOf("-"));
        rd = rd.substring(rd.indexOf("-") + 1);
        year += " ";
        int m = Integer.parseInt(rd.substring(0, rd.indexOf("-")));
        String[] months = new String[] {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November"};
        String month = months[m];
        rd = rd.substring(rd.indexOf("-") + 1);
        String day = rd;
        String date = "Premiere: " + month + " " + day + ", " + year; // sorry for not using String.format and %s
        //tvReleaseDate.setText(movie.getReleaseDate());
        tvReleaseDate.setText(date);
        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);

    }
}
