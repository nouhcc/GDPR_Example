package lonodev.gdprlib.com.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import lonodev.gdprlib.com.gdprlib.Consent_callback;
import lonodev.gdprlib.com.gdprlib.GDPRLib;


/**
 * Created by nouhcc on 07/06/2018.
 */



public class ActivitySplash extends AppCompatActivity implements Consent_callback {

        private GDPRLib custom;

        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                custom = new GDPRLib.Builder(this)
                        .addCallBack(this) //callBack to determinte what to do after the dialoge has ended
                        .addLayoutInflater(getLayoutInflater()) // the layout that should be use for the dialoge
                        .addPrivacyPolicy("http://your.policy.com/Policy.html") //your own privacy policy
                        .addPublisherId("pub-2307105849827608") // publisher id from Admob
                        .build();
                custom.checkConsentStatus(this);
        }

        private void startNextActivity() {
        // Go to main after the dialog is Close.
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
}

        @Override
        public void onDestroy(){
                if (custom.getDialog() != null && custom.getDialog().isShowing()) custom.getDialog().cancel(); //when the screen is rotated to prevent the bugs
                super.onDestroy();
        }

        @Override
        public void OnDialogClose() {
                startNextActivity();
        }
}
