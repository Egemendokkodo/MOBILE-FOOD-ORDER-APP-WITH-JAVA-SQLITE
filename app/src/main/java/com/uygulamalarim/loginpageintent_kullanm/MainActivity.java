package com.uygulamalarim.loginpageintent_kullanm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button signinbtn,loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signinbtn=(Button)findViewById(R.id.signinbtn);
        loginbtn=(Button)findViewById(R.id.loginbtn);

    }

    public void signinpage(View v){
        // switches to sign in page
        Intent intent=new Intent(MainActivity.this,LogInPage.class);
        startActivity(intent);

    }
    public void loginpage(View v){
        // switches to log in page
        Intent intent=new Intent(MainActivity.this,SignInPage.class);
        startActivity(intent);
    }


}