package com.uygulamalarim.loginpageintent_kullanm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// bu sayfada sql sorguları yapacağız ve loginpage den alınan verileri mysql veritabanına eklicez ondan sonra ise siparişler sayfası
public class LogInPage extends AppCompatActivity {
// this is actually sign in page.. i messed up at file name and xml files names are incorrect too :P . But the program works just fine.

    private EditText isimgir,soyisimgir,emailgir,sifregir,sifredogrula;
    private Button sendbtn;
    //public SQLiteDatabase database;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);



        isimgir=(EditText)findViewById(R.id.isimgir);
        soyisimgir=(EditText)findViewById(R.id.soyisimgir);
        emailgir=(EditText)findViewById(R.id.emailgir);
        sifregir=(EditText)findViewById(R.id.sifregir);
        sifredogrula=(EditText)findViewById(R.id.sifredogrula);
        sendbtn=(Button)findViewById(R.id.sendbtn);
        DB=new DBHelper(this); // ADD OUR DATABASE IN OUR FILE

        // ALERT DIALOG
        AlertDialog.Builder alert=new AlertDialog.Builder(this); // declare the alert dialog
        alert.setCancelable(false); // set the alert cancelable
        alert.setIcon(R.drawable.warning); // alert icon
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() { // the "OK" button on the alert dialog
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });


        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if passwords does not match this "if" will be run
                if (!sifredogrula.getText().toString().equals(sifregir.getText().toString())) {
                    alert.setTitle("Error");
                    alert.setMessage("Passwords does not match");
                    alert.show();
                } else {
                    // even if a single edittext is empty, this "if" will be run
                    if (isimgir.getText().toString().equals("") || soyisimgir.getText().toString().equals("") || emailgir.getText().toString().equals("") || sifregir.getText().toString().equals("")) {
                        alert.setTitle("Error");
                        alert.setMessage("Fields cannot be empty");
                        alert.show();
                    }
                    else{
                        catchError(isimgir.getText().toString(),soyisimgir.getText().toString(),emailgir.getText().toString(),sifregir.getText().toString());

                    }
                }
                }
        });





    }


    private void catchError(String isim, String soyisim,String email,String sifre){
        // This method checks if fields are match the conditions or not
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setCancelable(false);
        alert.setIcon(R.drawable.warning);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        if (isim.length()<3){
            alert.setTitle("Error");
            alert.setMessage("Your name's length must be more than 3");
            alert.show();
        }
        else if (soyisim.length()<=1 ){
            alert.setTitle("Error");
            alert.setMessage("Your surname's length must be more than 2");
            alert.show();
        }
        else if (!email.contains("@hotmail.com") && (!email.contains("@gmail.com"))){
            alert.setTitle("Error");
            alert.setMessage("Currently supported email formats are:\n-gmail.com\n-hotmail.com");
            alert.show();
        }
        else if (sifre.length()<8 || (!sifre.contains("@") && !sifre.contains("!") && !sifre.contains("_") && !sifre.contains("-"))){
            alert.setTitle("Error");
            alert.setMessage("Your pasword must contain at least one of the special characters (! _ - @)");
            alert.show();
        }
        else {
            // this else block will add our info to a database if infos are exist, it will show us an error message
            Boolean checkuser=DB.checkusername(email);
            if (checkuser==false) {
                Boolean insert=DB.insertData(email,sifre,isim,soyisim);
                if (insert==true){
                    alert.setIcon(R.drawable.success);
                    alert.setTitle("Success");
                    alert.setMessage("Congratulations! you may now order the food you like.");
                    alert.show();
                }else
                    Toast.makeText(getApplicationContext(),"Sign in failed",Toast.LENGTH_LONG).show();


            }else
                Toast.makeText(getApplicationContext(),"EMAIL ALREADY EXISTS PLEASE LOG IN",Toast.LENGTH_LONG).show();







        }

    }


    }

