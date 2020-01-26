package com.example.ecommerce.order;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.cardview.widget.CardView;
        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.request.RequestOptions;
        import com.example.ecommerce.R;
import java.util.List;
public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.CustomViewHolder>
{
        int position;
    private List<TempOrders> ordersList;
    CustomInterface customInterface;

    public OrderHistoryAdapter(List<TempOrders> ordersList, CustomInterface customInterface) {
        this.ordersList = ordersList;
        this.customInterface = customInterface;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater=LayoutInflater.from(parent.getContext());
        view=mInflater.inflate(R.layout.orderhistoryview,parent,false);
        return new OrderHistoryAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        final TempOrders object=ordersList.get(position);
        holder.order_title.setText(object.getProductName());
        holder.order_price.setText(String.valueOf(object.getPrice()));
        holder.order_date.setText(object.getOrderDate().toString());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customInterface.onClick(object);

            }
        });

        Glide.with(holder.orderImage.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_background)).load(object.getImage()).into(holder.orderImage);

    }

    @Override
    public int getItemCount() {
       return ordersList.size();
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder
    {
        ImageView orderImage;
        CardView cardView;
        TextView order_title;
        TextView order_price;
        TextView order_date;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            order_title=(TextView) itemView.findViewById(R.id.order_prod_name);
            orderImage=(ImageView) itemView.findViewById(R.id.order_image);
            order_price=(TextView) itemView.findViewById(R.id.order_price_value);
            order_date=(TextView) itemView.findViewById(R.id.order_date_value);
            cardView=(CardView) itemView.findViewById(R.id.order_card_view);
            //rating=(RatingBar) itemView.findViewById(R.id.ratingbar);

        }
    }
    public interface CustomInterface
    {
        void onClick(TempOrders order);
    }
}