package com.jfaq.passmanager.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jfaq.passmanager.data.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("select * from user")
    public List<User> getAll();

    @Query("select * from user where email=:email")
    User findByEmail(String email);

    @Query("select count(*) from user")
    int countUser();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);





}
