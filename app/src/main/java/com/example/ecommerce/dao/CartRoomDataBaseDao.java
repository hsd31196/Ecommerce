package com.example.ecommerce.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ecommerce.entity.CartRoomEntity;

import java.util.List;

@Dao
public interface CartRoomDataBaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartRoomEntity cartRoomDataBase);

    @Query("delete from CartRoomEntity where productId=:id")
    void deleteById(String id);

    @Query("select * from CartRoomEntity")
    LiveData<List<CartRoomEntity>> getAllCartItems();

    @Query("delete from CartRoomEntity")
    void deleteAll();

    @Query("select count(*) from CartRoomEntity where productId= :id")
    int getById(String id);

    @Query("update CartRoomEntity  set qty=qty+1 where productId=:id")
    void update(String id);

    @Query("update CartRoomEntity set qty=qty-1 where productId=:id")
    void updateDecrement(String id);


    @Query("delete from CartRoomEntity")
    void delete();
}
