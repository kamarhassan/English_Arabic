package com.example.hassan.english_arabic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Hassan on 11/06/2017.
 */

public class Word extends SQLiteOpenHelper {

    public static final String BDname="data.db";


    public Word(Context context) {
        super(context, BDname, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table word( id INTEGER PRIMARY KEY AUTOINCREMENT ,english ,arabic TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists word");

    }

//    public int selct_id(String english){
//        Cursor cursor=null;
//            int id=0;
//        SQLiteDatabase database= this.getReadableDatabase();
//        cursor =database.rawQuery("select * from word "  + " where english"  + "=" + english  , null);
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                id=cursor.getInt(cursor.getColumnIndex(english));
//            }
//        }
//        return 0;
//    }

    public boolean insertdata(String name,String name1 )
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv =new ContentValues();

        cv.put("english",name);
        cv.put("arabic",name1);

        long result = db.insert("word",null,cv);
        if(result== -1)
            return false;
        else return  true;
    }

    public ArrayList getall(){
        ArrayList <String> arrayList=new ArrayList();
        SQLiteDatabase database= this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select english ,arabic from word ORDER BY english",null);
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false)
        {
            String englis_word=cursor.getString(0);
            String arabc_word =cursor.getString(1);
//            String s2 =cursor.getString(2);
            arrayList.add(englis_word+" -> "+arabc_word);
            cursor.moveToNext();
        }
        return arrayList;
    }



        public String[] select(String s){
            SQLiteDatabase db= this.getReadableDatabase();
            String[] s1 = new String[3];

            Cursor cursor= null;
            int id1 =-1;
            cursor= db.rawQuery("select * from word WHERE english = '"+s+"'",null);
//        cursor.moveToFirst();

//        if(cursor.moveToFirst())
//        {
//            id1 =9;
//        }
            if(cursor != null &&  cursor.getCount()!=0 && cursor.moveToPosition(0 )){
                s1[0]=cursor.getString(0);
                s1[1]=cursor.getString(1);
                s1[2]=cursor.getString(2);
//
            }


            return  s1;
        }




    public int update(String id, String name, String name1 ){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv =new ContentValues();
//
//        ListView lst ;
        cv.put("english",name);
        cv.put("arabic",name1);
//
        return  db.update("word",cv,"id"+" =? ", new String[]{id});



    }

    public int  delet(String id ){
        SQLiteDatabase db =this.getWritableDatabase();


//        return
//                db.execSQL("DELET word    " );
        return  db.delete("word","id"+" =? ",  new String[]{id} );
//        return  db.delete("word","name =   "+id,null ) ;


    }
    public int  count_()
        {       SQLiteDatabase db =this.getWritableDatabase();
              ContentValues cv =new ContentValues();
             Cursor cursor= null;
            int s1 = 0;
            cursor= db.rawQuery("select * from word ",null);
            if(cursor != null &&  cursor.getCount()!=0 ){
                s1=cursor.getCount();
            }
            return s1;
        }




        }