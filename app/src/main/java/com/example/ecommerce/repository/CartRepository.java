package com.example.ecommerce.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.core.widget.ListViewAutoScrollHelper;
import androidx.lifecycle.LiveData;

import com.example.ecommerce.dao.CartRoomDataBaseDao;
import com.example.ecommerce.entity.CartRoomEntity;
import com.example.ecommerce.roomdatabase.CartRoomDataBase;

import java.sql.CallableStatement;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartRepository {

    private CartRoomDataBaseDao cartRoomDataBaseDao;
    private LiveData<List<CartRoomEntity>> allItems;
    public CartRepository(Application application)
    {
        CartRoomDataBase db=CartRoomDataBase.getRoomDataBase(application);
        cartRoomDataBaseDao=db.cartRoomDataBaseDao();
        allItems=cartRoomDataBaseDao.getAllCartItems();


    }
    public LiveData<List<CartRoomEntity>> getAllCartItems()
    {

        System.out.println(" in the repository get items");
        System.out.println(allItems);
        return allItems;
    }

    public void insert(CartRoomEntity cartRoomEntity)
    {
        new insertAsyncTask(cartRoomDataBaseDao).execute(cartRoomEntity);
    }

    public int getById(String id)
    {
        try {
            return new getByIdAsyncTask(cartRoomDataBaseDao).execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void update(String id)
    {
        new updateAsyncTask(cartRoomDataBaseDao).execute(id);
    }

    public void updateDecrement(String  id)
    {
        new updateDecrementAsyncTask(cartRoomDataBaseDao).execute(id);
    }
    public void delById(String  id)
    {
        new delAsyncTask(cartRoomDataBaseDao).execute(id);
    }

    public void delete(){
        new deleteAsyncTask(cartRoomDataBaseDao).execute();
    }
    private class deleteAsyncTask  extends AsyncTask<String,Void,Void> {

        private CartRoomDataBaseDao myCartDao;
        public deleteAsyncTask(CartRoomDataBaseDao cartRoomDataBaseDao) {
            myCartDao=cartRoomDataBaseDao;
        }


        @Override
        protected Void doInBackground(String... strings) {
            myCartDao.delete();
            return null;
        }
    }

    private class delAsyncTask  extends AsyncTask<String,Void,Void> {

        private CartRoomDataBaseDao myCartDao;
        public delAsyncTask(CartRoomDataBaseDao cartRoomDataBaseDao) {
            myCartDao=cartRoomDataBaseDao;
        }


        @Override
        protected Void doInBackground(String... strings) {
            myCartDao.deleteById(strings[0]);
            return null;
        }
    }

    private class updateDecrementAsyncTask  extends AsyncTask<String,Void,Void> {

        private CartRoomDataBaseDao myCartDao;
        public updateDecrementAsyncTask(CartRoomDataBaseDao cartRoomDataBaseDao) {
            myCartDao=cartRoomDataBaseDao;
        }


        @Override
        protected Void doInBackground(String... strings) {
            myCartDao.updateDecrement(strings[0]);
            return null;
        }
    }


    private class updateAsyncTask  extends AsyncTask<String,Void,Void> {

        private CartRoomDataBaseDao myCartDao;
        public updateAsyncTask(CartRoomDataBaseDao cartRoomDataBaseDao) {
            System.out.println("going to call dao");
            myCartDao=cartRoomDataBaseDao;
        }


        @Override
        protected Void doInBackground(String... strings) {
            myCartDao.update(strings[0]);
            System.out.println(strings[0]);
            return null;
        }
    }


    private class getByIdAsyncTask  extends AsyncTask<String,Void,Integer> {

        private CartRoomDataBaseDao myCartDao;
        public getByIdAsyncTask(CartRoomDataBaseDao cartRoomDataBaseDao) {
            myCartDao=cartRoomDataBaseDao;
        }


        @Override
        protected Integer doInBackground(String... strings) {
            return myCartDao.getById(strings[0]);
        }
    }


    private class insertAsyncTask  extends AsyncTask<CartRoomEntity,Void,Void> {

        private CartRoomDataBaseDao myCartDao;
        public insertAsyncTask(CartRoomDataBaseDao cartRoomDataBaseDao) {
            myCartDao=cartRoomDataBaseDao;
        }


        @Override
        protected Void doInBackground(CartRoomEntity... cartRoomEntities) {
                myCartDao.insert(cartRoomEntities[0]);
            return null;
        }
    }
}
