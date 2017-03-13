package com.sun.mystudy;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by stephensun on 2017/3/13.
 */
public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.SecondViewHolder> {
    private RecyclerListener mListener;
    private Context context;
    private List<String> List;
    public SecondAdapter(Context context, List<String> list) {
        this.context = context;
        this.List = list;
    }

    @Override
    public SecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        SecondViewHolder secondViewHolder = new SecondViewHolder(view);
        return secondViewHolder;
    }

    @Override
    public void onBindViewHolder(SecondViewHolder holder, final int position) {
        holder.text.setText(List.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.doListen(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class SecondViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public SecondViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

    interface RecyclerListener {
        void doListen(int postion);
    }

    void setRcyclerListener(RecyclerListener listener) {
        mListener = listener;
    }


}
