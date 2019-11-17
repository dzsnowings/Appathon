package com.example.studybuddyfinder.ui.browse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.studybuddyfinder.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<ArrayList<String>> groups;
    private LayoutInflater inflater;
    private ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView classtv, locationtv, timetv, typetv;

        public ViewHolder(View itemView) {
            super(itemView);
            classtv = itemView.findViewById(R.id.classTV);
            locationtv = itemView.findViewById(R.id.locationTV);
            timetv = itemView.findViewById(R.id.timeTV);
            typetv = itemView.findViewById(R.id.typeTV);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(Context context, ArrayList<ArrayList<String>> data) {
        inflater = LayoutInflater.from(context);
        groups = data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_group, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.textView.setText(mDataset[position]);

        String className = groups.get(position).get(0);
        String location = groups.get(position).get(1);
        String time = groups.get(position).get(2);
        String type = groups.get(position).get(3);

        holder.classtv.setText(className);
        holder.locationtv.setText(location);
        holder.timetv.setText(time);
        holder.typetv.setText(type);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return groups.size();
    }

    void setItemClickListener(ItemClickListener icl) {
        this.itemClickListener = icl;
    }


}
