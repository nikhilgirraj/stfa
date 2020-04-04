package com.journear.stfa;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder> {

    private ArrayList<NearbyDevices> deviceList;
    private Context context;
    public DeviceListAdapter(Context context, ArrayList<NearbyDevices> planetList) {
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
        NearbyDevices devices = deviceList.get(position);

        holder.deviceName.setText(Build.MANUFACTURER);
        holder.distance.setText("DANGER");
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public static class DeviceViewHolder extends RecyclerView.ViewHolder{

        protected TextView deviceName;
        protected TextView distance;

        public DeviceViewHolder(View itemView) {
            super(itemView);
            deviceName = itemView.findViewById(R.id.deviceName);
            distance = itemView.findViewById(R.id.distance);
        }
    }
}
