package com.journear.stfa;





import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class NearbyDevices implements Parcelable {
    private int id;
    private String deviceName;
    private String distance;

    public NearbyDevices(){

    }
    public NearbyDevices(String deviceName, String distance){
        this.deviceName = deviceName;
        this.distance = distance;

    }

    public NearbyDevices(String deviceName,String distance, String travelTime){
        this.deviceName = deviceName;
        this.distance = distance;


    }




    protected NearbyDevices(Parcel in) {

        deviceName = in.readString();
        distance = in.readString();


    }

    public static final Creator<NearbyDevices> CREATOR = new Creator<NearbyDevices>() {
        @Override
        public NearbyDevices createFromParcel(Parcel in) {
            return new NearbyDevices(in);
        }

        @Override
        public NearbyDevices[] newArray(int size) {
            return new NearbyDevices[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getdeviceName() {
        return deviceName;
    }

    public void setdeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getdistance() {
        return distance;
    }

    public void setdistance(String distance) {
        this.distance = distance;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(deviceName);
        dest.writeString(distance);


    }
}