package com.journear.stfa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 9;


    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private BluetoothAdapter bluetoothAdapter;
    private SensorManager mSensorManager;
    private Sensor mSensor;

    //Utkarsh Addition

    private RecyclerView recyclerView;
    private DeviceListAdapter deviceListAdapter;
    private List<NearbyDevices> devicesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_devices);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        findViewById(R.id.buttonStartDiscovery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkProximity();
            }
        });

    }

    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address

                NearbyDevices nd = new NearbyDevices();
                int  rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                nd.setdeviceName(deviceName);
                nd.setdistance(Integer.toString(rssi));

                final Intent intent1 = new Intent(MainActivity.this, DevicesListActivity.class);
                intent1.putExtra("EXTRA", nd);
                startActivity(intent1);


                Log.i("STFA", "Discovered:  " + deviceName);

                Log.i("STFA",deviceName + "  RSSI: " + rssi + "dBm");
//                Toast.makeText(getApplicationContext(),"  RSSI: " + rssi + "dBm", Toast.LENGTH_SHORT).show();
//                adapter = new DeviceListAdapter(deviceList, getApplicationContext());
//                recyclerView.setAdapter(adapter);
            }
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
            {
                Log.d("STFA", "Discovery Stopped");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(receiver);
    }

    private void checkProximity() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            return;
        }
        else if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        // Register for broadcasts when a device is discovered.
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);
        IntentFilter filter1 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(receiver, filter1);

        Boolean started = bluetoothAdapter.startDiscovery();
        Log.i("STFA", "Discovery Started - " + started);
    }

}
