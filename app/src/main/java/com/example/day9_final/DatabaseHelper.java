package com.example.day9_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;
  private static final String DATABASE_NAME = "spinner_database";
    private static final String TABLE_NAME = "spinner_data";
    private static final String KEY_NAME = "food_name";
    private static final String KEY_ID = "id";




    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addFood(Food food){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName());
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    // if not working, revise this
    public Food getFood(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID,KEY_NAME},KEY_ID + " =?", new String[]{String.valueOf(id)}, null, null, null, null );
        if (cursor != null){
            cursor.moveToFirst();
        }
          Food food = new Food(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
            return food;
    }

 // if not working revise this this.getReadableDatabase();
    public List<Food> getAllFood(){
      List<Food> foods = new ArrayList<>();

      String selectQuery = "SELECT * FROM " + TABLE_NAME;
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor = db.rawQuery(selectQuery,null);



      if(cursor.moveToFirst()){
          do{
            Food food = new Food();
            food.setId(Integer.parseInt(cursor.getString(0)));
            food.setName(cursor.getString(1));

            foods.add(food);
          }while (cursor.moveToNext());

      }

       return foods;
    }


    public List<String> getAllFoodNames(){
        List<String> foodNames = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);


            if (cursor.moveToFirst()) {

                do {
                   Food food = new Food();
                    food.setId(Integer.parseInt(cursor.getString(0)));
                    food.setName(cursor.getString(1));

                    foodNames.add(food.getName());
                } while (cursor.moveToNext());

            }


        return foodNames;
    }


}
