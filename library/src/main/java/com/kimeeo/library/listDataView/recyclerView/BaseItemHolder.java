package com.kimeeo.library.listDataView.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;



/**
 * Created by bhavinpadhiyar on 1/30/16.
 */
abstract public class BaseItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    abstract public void updateItemView(Object item,View itemView, int position);
    public OnItemHolderClick getOnItemHolderClick() {
        return onItemHolderClick;
    }



    public void setOnItemHolderClick(OnItemHolderClick onItemHolderClick) {
        this.onItemHolderClick = onItemHolderClick;
    }

    private OnItemHolderClick onItemHolderClick;

    public View getItemView()
    {
        return itemView;
    }
    public void updateItemView(Object item, int position)
    {
        updateItemView(item,itemView,position);
    }


    public BaseItemHolder(View itemView) {

        super(itemView);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onItemHolderClick.onItemHolderClick(this, getAdapterPosition());
    }

    public static interface OnItemHolderClick
    {
        void onItemHolderClick(BaseItemHolder itemHolder,int position);
    }
}
