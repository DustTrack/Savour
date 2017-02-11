package com.dusttrack.savour.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dusttrack.savour.vh.VHBase;

/**
 * Created by DustTrack on 2017/2/10.
 */
public class BaseAdapter extends RecyclerView.Adapter<VHBase>{

    /**
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public VHBase onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=new View(parent.getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,50));
        view.setBackgroundColor(Color.BLUE);
        return new VHBase(view);
    }

    @Override
    public void onBindViewHolder(VHBase holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
