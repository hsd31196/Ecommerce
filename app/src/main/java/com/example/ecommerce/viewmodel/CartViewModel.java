package com.example.ecommerce.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
        System.out.println("in the view model");
        cartRepository.update(id);
    }

    public void updateDecrement(String id)
    {
        cartRepository.updateDecrement(id);
    }

    public void delById(String  id)
    {
        cartRepository.delById(id);
    }



}
