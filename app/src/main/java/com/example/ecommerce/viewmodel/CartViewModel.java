package com.example.ecommerce.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import com.example.ecommerce.entity.CartRoomEntity;
import com.example.ecommerce.repository.CartRepository;

public class CartViewModel  extends AndroidViewModel {
private CartRepository cartRepository;
private LiveData<List<CartRoomEntity>> allItems;
    public CartViewModel(@NonNull Application application) {
        super(application);
        cartRepository=new CartRepository(application);
        allItems=cartRepository.getAllCartItems();
    }
   public LiveData<List<CartRoomEntity>> getAllItems(){
        return allItems;
    }
    public void insert(CartRoomEntity cartRoomEntity){
        cartRepository.insert(cartRoomEntity);
    }
    public int getById(String id)
    {
        return cartRepository.getById(id);
    }

    public void update(String id){
        cartRepository.update(id);
    }



}
