package com.example.mobdevtask.task1.database;

import com.example.mobdevtask.task1.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

@androidx.room.Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAllUsers();

    @Query("DElETE  FROM user_table")
    void deleteAllUsers();

}
