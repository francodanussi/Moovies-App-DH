package a3.m1mo.mobjav.a816.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import a3.m1mo.mobjav.a816.myapplication.R;
import io.fabric.sdk.android.Fabric;

public class PantallaInicio extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "QV5X1rQeYXeRFMYlCc99QdQJV";
    private static final String TWITTER_SECRET = "Xi5J2oqfP01kb4maNFz1szuCOOVGVgbHsk6eqoFWxjvZAmC68n";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.serieopelicula);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("HOME");
        toolbar.setVisibility(View.INVISIBLE);
    }

    public void holaPeliculas(View view) {
        Button imageButton = (Button) view.findViewById(R.id.holaaPeliculas);
        Intent unIntent = new Intent(this, MainScreenPelicula.class);
        startActivity(unIntent);

    }

    public void holaSeries(View view) {
        Button imageButton = (Button) view.findViewById(R.id.holaaSeries);
        Intent otroIntent = new Intent(this, MainScreenSerie.class);
        startActivity(otroIntent);
    }
}
