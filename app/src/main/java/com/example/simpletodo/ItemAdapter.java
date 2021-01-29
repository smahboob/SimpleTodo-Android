package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

//adapter takes data and puts into view holder
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    //this is to help delete the item after long click through main activity
    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    public interface OnClickListener{
        void onItemClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener onClickListener;

    public ItemAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener onClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout inflater to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(todoView);
    }

    //responsible binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //grab the item
        String item = items.get(position);
        //bind the item to specified view holder
        holder.bind(item);
    }

    //how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }



    //new class
    //container to provide easy access to view that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //update the view inside of the view holder with the data
        public void bind(String item) {
            tvItem.setText(item);

            tvItem.setOnClickListener(v -> {
                onClickListener.onItemClicked(getAdapterPosition());
            });

            tvItem.setOnLongClickListener(v -> {
                //remove the item from the recycler view
                //this is just notifying of that which position was long pressed
                longClickListener.onItemLongClicked(getAdapterPosition());
                return true;
            });
        }
    }
}
