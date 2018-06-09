package lonodev.gdprlib.com.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import lonodev.gdprlib.com.gdprlib.GDPRLib;


public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private Button  button2;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        prepareAdsInterstitialAd();
        prepareAds();
        textView = (TextView) findViewById(R.id.text);
        if(GDPRLib.isFromEU){
            if(GDPRLib.getYourPerfernce(this)){
                textView.setText("this user is from EU and has chosen show ads that are irrelevant "+ GDPRLib.getYourPerfernce(this));
            }else{
                textView.setText("this user is from EU and has chosen to see relevant ads return "+ GDPRLib.getYourPerfernce(this));
            }
        }else{
            textView.setText("this user is not for EU doesn't need to see the dialoge ");
        }

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(GDPRLib.isFromEU){
            if(GDPRLib.getYourPerfernce(this)){
                textView.setText("this user is from EU and has chosen show ads that are irrelevant "+ GDPRLib.getYourPerfernce(this));
            }else{
                textView.setText("this user is from EU and has chosen to see relevant ads return "+ GDPRLib.getYourPerfernce(this));
            }
        }else{
            textView.setText("this user is not for EU doesn't need to see the dialoge ");
        }
        prepareAdsInterstitialAd();
    }
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(), ActivitySettings.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }



    // prepare ADS FOR THIS PAGE
    private void prepareAds() {
        AdView mAdView = (AdView) findViewById(R.id.ad_view);
            ((RelativeLayout) findViewById(R.id.banner_layout)).setVisibility(View.VISIBLE);
            AdRequest adRequest = GDPRLib.getAdRequest(this);
            // Start loading the ad in the background.
            mAdView.loadAd(adRequest);
    }
    private void prepareAdsInterstitialAd() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2307105849827608/3530935186");
        AdRequest adRequest2 =  GDPRLib.getAdRequest(this);
        mInterstitialAd.loadAd(adRequest2);
    }

    /**
     * show ads Interstitial
     */
    public void showInterstitial() {
        // Show the ad if it's ready
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

}
