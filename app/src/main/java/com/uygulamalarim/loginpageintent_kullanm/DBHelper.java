package com.uygulamalarim.loginpageintent_kullanm;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;





public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Restaurant";
    public DBHelper(Context context) {
        super(context, "Restaurant", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table if not exists users (name VARCHAR,surname VARCHAR,mail VARCHAR,password VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String mail, String password,String name, String surname){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name", name);
        contentValues.put("surname", surname);
        contentValues.put("mail", mail);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String mail) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where mail = ?", new String[]{mail});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String mail, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where mail = ? and password = ?", new String[] {mail,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}