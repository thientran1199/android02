package com.example.doanandroid02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid02.ItemClickListener;
import com.example.doanandroid02.PrefConfig;
import com.example.doanandroid02.R;
import com.example.doanandroid02.activity.CartActivity;
import com.example.doanandroid02.activity.MainActivity;
import com.example.doanandroid02.models.Cart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ItemHolder> {
    List<Cart> carts;
    Context context;

    public CartAdapter(List<Cart> carts, Context context) {
        this.carts = carts;
        this.context = context;
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView textProductNameCrt, textGiaSpCrtquant, quant, textDelItem;
        private Button btTru, btCong;
        private ImageView img;
        private CardView cardView;
        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            quant = itemView.findViewById(R.id.quant);
            textProductNameCrt = itemView.findViewById(R.id.textProductNameCrt);
            textGiaSpCrtquant = itemView.findViewById(R.id.textGiaSpCrt);
            textDelItem = itemView.findViewById(R.id.delItem);
            cardView = itemView.findViewById(R.id.cardViewCart);
            img = itemView.findViewById(R.id.imgSanPhamCart);
            btTru = itemView.findViewById(R.id.btTru);
            btCong = itemView.findViewById(R.id.btCong);
            btCong.setOnClickListener(this);
            btTru.setOnClickListener(this);
            textDelItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
            return false;
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_cart_item, null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Cart cart = carts.get(position);
        holder.textProductNameCrt.setText(cart.tensp);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.textGiaSpCrtquant.setText(decimalFormat.format(cart.giasp) + "VND");
        holder.quant.setText(decimalFormat.format(cart.soluongsp));
        Picasso.with(context).load("http://192.168.1.7/Doan-Laravel/public/upload/" + cart.getImg())
                .into(holder.img);
        int soluong = Integer.parseInt(holder.quant.getText().toString());
        CartActivity.countPrice(context);
        if (soluong <= 1) {
            holder.btTru.setVisibility(View.INVISIBLE);
        } else {
            holder.btTru.setVisibility(View.VISIBLE);
        }

        if (soluong >= 10) {
            holder.btCong.setVisibility(View.INVISIBLE);
        } else {
            holder.btCong.setVisibility(View.VISIBLE);
        }

        holder.btTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluongmoi = Integer.parseInt((String) holder.quant.getText()) - 1;
                int soluonght = MainActivity.cartArrayList.get(position).getSoluongsp();
                long giaht = MainActivity.cartArrayList.get(position).getGiasp();
                MainActivity.cartArrayList.get(position).setSoluongsp(soluongmoi);

                PrefConfig.writeList(context, MainActivity.cartArrayList);

                // Giá mới sẽ bằng tích của giá hiện tại với số lượng mới rồi chia cho số lượng hiện tại
                long giamoinhat = (giaht * soluongmoi) / soluonght;
                MainActivity.cartArrayList.get(position).setGiasp(giamoinhat);

                //update lại giá sp
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.textGiaSpCrtquant.setText(decimalFormat.format(giamoinhat) + "VND");
                holder.quant.setText(decimalFormat.format(soluongmoi));
                CartActivity.countPrice(context);
                if (soluongmoi <= 1) {
                    holder.btTru.setVisibility(View.INVISIBLE);
                }
                if (soluongmoi < 10) {
                    holder.btCong.setVisibility(View.VISIBLE);
                }

            }
        });

        holder.btCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluongmoi = Integer.parseInt((String) holder.quant.getText()) + 1;
                int soluonght = MainActivity.cartArrayList.get(position).getSoluongsp();
                long giaht = MainActivity.cartArrayList.get(position).getGiasp();
                MainActivity.cartArrayList.get(position).setSoluongsp(soluongmoi);

                PrefConfig.writeList(context, MainActivity.cartArrayList);

                // Giá mới sẽ bằng tích của giá hiện tại với số lượng mới rồi chia cho số lượng hiện tại
                long giamoinhat = (giaht * soluongmoi) / soluonght;
                MainActivity.cartArrayList.get(position).setGiasp(giamoinhat);

                //update lại giá sp
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.textGiaSpCrtquant.setText(decimalFormat.format(giamoinhat) + "VND");
                holder.quant.setText(decimalFormat.format(soluongmoi));
                CartActivity.countPrice(context);
                if (soluongmoi > 1) {
                    holder.btTru.setVisibility(View.VISIBLE);
                }
                if (soluongmoi >= 10) {
                    holder.btCong.setVisibility(View.INVISIBLE);
                }

            }
        });

        holder.textDelItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.cartArrayList.remove(position);
                PrefConfig.writeList(context, MainActivity.cartArrayList);
                CartActivity.cartAdapter.notifyDataSetChanged();
                CartActivity.countPrice(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }
}
