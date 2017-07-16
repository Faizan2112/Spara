package com.mylife.faiza.spara.Constant;

/**
 * Created by faizan on 31/08/2016.
 */


public class ConstantFile {

    // contants for db sysytem / properties

  /*  public static final String db_name = "register.db";
    public static final String db_table ="reg_info";
    public static final int db_version =1;

    // contants for db coloums
    public static final String Row_id = "_id";
    public static final String name = "Expense_name";
    public static final String cost  = "Expense_cost";

 // Database Query
  public static final String CREATE_TABLE = "create table" + db_table +
         "(" +Row_id + "INTEGER PRIMARY KEY AUTOINCREAMENT," +
         name +"TEXT NOT NULL,"+
         cost +"int);";*/

    public static final String TABLE_MEMBER = "reg";
    public static final String MEMBER_ID = "id";
    public static final String EXPENSENAME = "expenseName";
    public static final String EXPENSECOST = "expenseCost";
    // table information

   public static final String DB_NAME = "MEMBER.DB";
    public static final int DB_VERSION = 1;

//database Information

    public static final String CREATE_TABLE = "create table " + TABLE_MEMBER
            + "(" + MEMBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EXPENSENAME + " TEXT NOT NULL ," +EXPENSECOST
            + " TEXT NOT NULL);";
}
