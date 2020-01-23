package com.example.ecommerce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class HomeAdapter  extends RecyclerView.Adapter<HomeAdapter.Viewholder> {

    List<Products> listItems;
public HomeAdapter(List<Products> list)
{
    listItems=list;
}

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("in the viewhler of on create");
        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.displayproductscardview,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
    Products object=listItems.get(position);
        Glide.with(holder.imageView.getContext()).load(object.getImgurls().get(0)).into(holder.imageView);
        holder.productName.setText(object.getProductName());
        holder.productPrice.setText(Double.toString(object.getPrice()));
        holder.ratingBar.setRating((float) object.getRating());

    }



    @Override
    public int getItemCount() {
    if(listItems!=null)
        return listItems.size();
    else
        return 0;
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView productName;
        TextView productPrice;
        RatingBar ratingBar;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.display_image);
            productName=itemView.findViewById(R.id.display_prod_name);
            productPrice=itemView.findViewById(R.id.display_price);
            ratingBar=itemView.findViewById(R.id.ratingId);
        }
    }

    public interface CustomInterface{

    }
}
