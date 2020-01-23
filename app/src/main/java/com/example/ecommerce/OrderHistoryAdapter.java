package com.example.ecommerce;
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

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.CustomViewHolder>
{
        int position;
    private TempOrders ordersList;
    CustomInterface customInterface;

    public OrderHistoryAdapter(TempOrders ordersList, CustomInterface customInterface) {
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
        holder.order_title.setText(ordersList.getCartProducts().get(position).getProductName());
        holder.order_price.setText(String.valueOf(ordersList.getCartProducts().get(position).getPrice()));
        holder.order_date.setText(ordersList.getOrderDate().toString());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customInterface.onClick(ordersList.getCartProducts().get(position));

            }
        });

        Glide.with(holder.orderImage.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_background)).load(ordersList.getCartProducts().get(position).getImage()).into(holder.orderImage);

    }

    @Override
    public int getItemCount() {
        return ordersList.getCartProducts().size();
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
        void onClick(OrderProductsItem catrItems);
    }
}