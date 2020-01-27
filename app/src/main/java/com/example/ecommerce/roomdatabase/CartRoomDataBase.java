package com.example.ecommerce.roomdatabase;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ecommerce.dao.CartRoomDataBaseDao;
import com.example.ecommerce.entity.CartRoomEntity;


@Database(entities = {CartRoomEntity.class},version = 5,exportSchema = false)
public abstract  class CartRoomDataBase extends RoomDatabase {

    private static CartRoomDataBase instance;

    public static CartRoomDataBase  getRoomDataBase(final Context context)    // creating a singleton class to ensure that only one connection is opened
    {
        if(instance==null)
        {
            synchronized (CartRoomDataBase.class){
                if(instance==null)
                {
                    instance= Room.databaseBuilder(context.getApplicationContext(),CartRoomDataBase.class,
                            "CartRoomDataBaseObject").fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback).build();
                }
            }
        }

        return instance;
    }




    public abstract CartRoomDataBaseDao cartRoomDataBaseDao();

    private static RoomDatabase.Callback sRoomDatabaseCallback=new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            //new PopulateDbAsync(instance).execute();
        }
    };
//    private static class PopulateDbAsync extends AsyncTask<CartRoomEntity,Void,Void>{
//
//        private final CartRoomDataBaseDao cartRoomDataBaseDao;
//        String cartId="1";
//        String userId="34";
//        String productId="PA1";
//        String merchantId="m1";
//        double totalAmount=2000;
//        int  qty=2;
//        CartRoomEntity sampleEntity=new CartRoomEntity();
//
//        PopulateDbAsync(CartRoomDataBase cartRoomDataBase){
//            cartRoomDataBaseDao=cartRoomDataBase.cartRoomDataBaseDao();
//        }
//
//        @Override
//        protected Void doInBackground(CartRoomEntity... cartRoomEntities) {
////            cartRoomDataBaseDao.deleteAll();
//           // sampleEntity.setCartId(cartId);
//            //sampleEntity.setMerchantId(merchantId);
//            sampleEntity.setProductId(productId);
//            sampleEntity.setQty(qty);
//            //sampleEntity.setUserId(userId);
//            sampleEntity.setTotalAmount(totalAmount);
//  //          cartRoomDataBaseDao.insert(sampleEntity);
//            return null;
//        }
//    }
}
