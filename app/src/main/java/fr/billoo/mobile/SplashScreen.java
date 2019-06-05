package fr.billoo.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(2500);

                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);

                    // Closing all the Activities
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);



                    startActivity(intent);

                    finish();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                } finally {

                }
            }
        };
        timer.start();
    }
}
