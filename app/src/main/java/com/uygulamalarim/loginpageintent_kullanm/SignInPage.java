package com.uygulamalarim.loginpageintent_kullanm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// this is actually login page.. i messed up at file name and xml files are incorrect too. But the program works just fine.
public class SignInPage extends AppCompatActivity {

    private EditText emailedittext,enterpassword;
    private Button buttonsignin;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        emailedittext=(EditText)findViewById(R.id.emailedittext);
        enterpassword=(EditText)findViewById(R.id.enterpassword);
        buttonsignin=(Button)findViewById(R.id.buttonsignin);

        DB= new DBHelper(this);


        buttonsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(emailedittext.getText().toString(),enterpassword.getText().toString());
            }
        });

    }

    // login
    public void check(String email,String pass){
        Boolean result=DB.checkusernamepassword(email,pass);

        if(result==true){
            // TODO: BUNDAN SONRA ARTIK SİPARİŞLER EKRANI YAPICAZ
            Toast.makeText(getApplicationContext(),"LOGIN SUCCESFULLY",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(SignInPage.this, AppInterface.class);
            startActivity(intent);
        }else
            Toast.makeText(getApplicationContext(),"EMAIL OR PASSWORD IS INVALID",Toast.LENGTH_LONG).show();
    }


}