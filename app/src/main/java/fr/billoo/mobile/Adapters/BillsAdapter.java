package fr.billoo.mobile.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.billoo.mobile.Models.BillsModel;
import fr.billoo.mobile.R;

public class BillsAdapter extends RecyclerView.Adapter<BillsAdapter.ViewHolder> {

    private List<BillsModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public BillsAdapter(Context context, List<BillsModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.bills_list, viewGroup, false);
//        Toast.makeText(view.getContext(),"")
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillsAdapter.ViewHolder viewHolder, int i) {
        String title = mData.get(i).getTitle();
        viewHolder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView title,category,cost,date;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_bill_list);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    BillsModel getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
