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
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            timer.start();
        }
    }
