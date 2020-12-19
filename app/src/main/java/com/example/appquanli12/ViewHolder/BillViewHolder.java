package com.example.appquanli12.ViewHolder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanli12.Common.Common;
import com.example.appquanli12.Interface.ItemClickListener;
import com.example.appquanli12.R;

public class BillViewHolder extends RecyclerView.ViewHolder implements
        View.OnLongClickListener,
        View.OnClickListener,
        View.OnCreateContextMenuListener
{

    public TextView txtbillid ,txtDate, txtTotal;
    private ItemClickListener itemClickListener;

    public BillViewHolder(@NonNull View itemView) {
        super(itemView);
        txtbillid =(TextView)itemView.findViewById(R.id.txtidbill);
        txtDate =(TextView)itemView.findViewById(R.id.txtDate);
        txtTotal =(TextView) itemView.findViewById(R.id.txtTotal);

        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Select the action");

        contextMenu.add(0,0,getAdapterPosition(), Common.UPDATE);
        contextMenu.add(0,0,getAdapterPosition(), Common.DELETE);

    }

    @Override
    public boolean onLongClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),true);
        return true;
    }
}

