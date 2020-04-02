package com.journear.stfa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder> {

    ArrayList<String> deviceList;

    public DeviceListAdapter(ArrayList<String> planetList, Context context) {
        this.deviceList = planetList;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.device_row,parent,false);
        DeviceViewHolder viewHolder=new DeviceViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        //holder.image.setImageResource(R.drawable.planetimage);
        holder.text.setText(deviceList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public static class DeviceViewHolder extends RecyclerView.ViewHolder{

        protected TextView text;

        public DeviceViewHolder(View itemView) {
            super(itemView);
            text= (TextView) itemView.findViewById(R.id.text_id);
        }
    }
}
