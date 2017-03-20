package com.sun.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerviewManyItemActivity extends AppCompatActivity {
    private int[] arr = {1, 2, 3, 4, 8, 9, 15, 21, 27, 16, 32, 11, 11};
    private static final int ITEM_ONE = 1;
    private static final int ITEM_TWO = 2;
    private static final int ITEM_THREE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_many_item);

        init();
    }

    private void init() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new MyLayoutManager(RecyclerviewManyItemActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new ManyItemAdapter());
    }

    class ManyItemAdapter extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder;
            switch (viewType) {
                case ITEM_ONE:
                    holder = new ViewHolder(LayoutInflater.from(RecyclerviewManyItemActivity.this).inflate(R.layout.item1, parent, false));
                    break;
                case ITEM_TWO:
                     holder = new ViewHolder(LayoutInflater.from(RecyclerviewManyItemActivity.this).inflate(R.layout.item2, parent, false));
                    break;
                case ITEM_THREE:
                    holder = new ViewHolder(LayoutInflater.from(RecyclerviewManyItemActivity.this).inflate(R.layout.item3, parent, false));
                    break;
                default:
                    holder = null;
            }
//            ViewGroup.LayoutParams layoutParams_text = holder.text.getLayoutParams();
//            int height = layoutParams_text.height;
//            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
//            layoutParams.height = 80;
//            holder.itemView.setLayoutParams(layoutParams);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {


        }

        @Override
        public int getItemCount() {
            return arr.length;
        }

        @Override
        public int getItemViewType(int position) {
            int type;
            int item = arr[position];
            if (item % 2 == 0) {
                type = ITEM_TWO;
            } else if (item % 3 == 0) {
                type = ITEM_THREE;
            } else {
                type = ITEM_ONE;
            }
            return type;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
