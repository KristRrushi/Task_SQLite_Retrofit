package com.example.mobdevtask.task1.database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.mobdevtask.task1.model.User;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

     private static UserDatabase instance;

     public abstract UserDao userDao();


     public static synchronized UserDatabase getInstance(Context context) {
         if(instance == null) {
             instance = Room.databaseBuilder(context.getApplicationContext(),
                        UserDatabase.class, "user_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)                                   //Callback below
                        .build();
         }
         return instance;
     }

     private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
         @Override
         public void onCreate(@NonNull SupportSQLiteDatabase db) {
             super.onCreate(db);
             new PopulateDbAsyncTask(instance).execute();
         }
     };

     private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
         private UserDao userDao;

         private PopulateDbAsyncTask(UserDatabase db) {
             this.userDao = db.userDao();
         }

         @Override
         protected Void doInBackground(Void... voids) {
             userDao.insert(new User("Krist", "123456789"));
             userDao.insert(new User("Krist1", "1236789"));
             userDao.insert(new User("Krist2", "126789"));
             return null;
         }
     }


}
