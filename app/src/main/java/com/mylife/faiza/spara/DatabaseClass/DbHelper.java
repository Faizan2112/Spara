package com.mylife.faiza.spara.DatabaseClass;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mylife.faiza.spara.Constant.ConstantFile;

/**
 * Created by faizan on 02/09/2016.
 */

// here we will extend basic SQlhelperclass and implement there various methods
// itgives error if you give string type in constant
// here we have to implement two method onCreate and onUpgrade
public class DbHelper extends SQLiteOpenHelper{
    public DbHelper(Context context) {
        super(context, ConstantFile.DB_NAME,null,ConstantFile.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        try {

            db.execSQL(ConstantFile.CREATE_TABLE);
        }catch (SQLException e){e.printStackTrace();}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

      db.execSQL("DROP TABLE IF EXISTS" +ConstantFile.TABLE_MEMBER);
        onCreate(db);
    }
}
