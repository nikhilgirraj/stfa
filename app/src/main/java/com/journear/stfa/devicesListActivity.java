package com.journear.stfa;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class devicesListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";
    private RecyclerView recyclerView;
    private DeviceListAdapter deviceListAdapter;
    private ArrayList<NearbyDevices> devicesList;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        Intent intent = getIntent();
        NearbyDevices nd = intent.getParcelableExtra("EXTRA");
//                Bundle args = intent.getBundleExtra("BUNDLE");
//        ArrayList<NearbyDevices> devicesList1 = (ArrayList<NearbyDevices>) intent.getSerializableExtra("ARRAYLIST");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_list);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        devicesList = new ArrayList<>();

        NearbyDevices dev = new NearbyDevices(nd.getdeviceName() , nd.getdistance() );
        devicesList.add(dev);


        //TODO Nikhil Sujit
        // devicesList = some source for the data.

        for (NearbyDevices devices : devicesList) {

            Log.d(TAG, "onCreate: " + devices.getdeviceName());

        }

        deviceListAdapter = new DeviceListAdapter(this, devicesList);
        recyclerView.setAdapter(deviceListAdapter);
        deviceListAdapter.notifyDataSetChanged();



    }

}
