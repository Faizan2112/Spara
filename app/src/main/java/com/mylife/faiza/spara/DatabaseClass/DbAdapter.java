package com.mylife.faiza.spara.DatabaseClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.mylife.faiza.spara.Constant.ConstantFile;

/**
 * Created by faizan on 02/09/2016.
 */

// first make context for this class
// so that we can accesss this method s from othr classes
//here we will create add deleete update methods for data
// we will do various basic operation like open close also
//we have to open SQLiteDatabase
// with the help of DBhelper object get writable
// with the help of content values enter data into database

public class DbAdapter {
    Context context;
    SQLiteDatabase sqLiteDatabase;
    DbHelper helper;

    // create constructer for it
    public DbAdapter(Context ctx) {
        this.context = ctx;


    }

    public DbAdapter openDB() {
        try {
            helper = new DbHelper(context);
            sqLiteDatabase = helper.getWritableDatabase();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void close() {
        try {
            helper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long add(String name, String cost) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(ConstantFile.EXPENSENAME, name);
            cv.put(ConstantFile.EXPENSECOST, cost);
            return sqLiteDatabase.insert(ConstantFile.TABLE_MEMBER, ConstantFile.MEMBER_ID,cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Cursor getallPlayers()
    {
        // create string files to store name id cost
        String [] coloums = {ConstantFile.MEMBER_ID,ConstantFile.EXPENSENAME,ConstantFile.EXPENSECOST};
       //
        return sqLiteDatabase.query(ConstantFile.TABLE_MEMBER,coloums,null,null,null,null,null);

    }
public long update(int id ,String name ,String cost )
{
  try {
      ContentValues cv = new ContentValues();
      cv.put(ConstantFile.EXPENSENAME,name);
      cv.put(ConstantFile.EXPENSECOST,cost);
      return sqLiteDatabase.update(ConstantFile.TABLE_MEMBER,cv,ConstantFile.MEMBER_ID+"=?",new String[]{String.valueOf(id)});
  }catch (SQLException e){e.printStackTrace();}
    return 0 ;
}
    public long delete(int id ) {
        try {
            return sqLiteDatabase.delete(ConstantFile.TABLE_MEMBER, ConstantFile.MEMBER_ID + "=?", new String[]{String.valueOf(id)});
// we just need id for deleting the item
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;
    }

}
