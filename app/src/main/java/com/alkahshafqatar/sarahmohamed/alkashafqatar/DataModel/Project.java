package com.alkahshafqatar.sarahmohamed.alkashafqatar.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Project implements Serializable{

    @SerializedName("proj_name")
    public String name;
    @SerializedName("discplns_list")
    public String description;
    @SerializedName("proj_client_id")
    public String id;
    @SerializedName("building_name")
    public String buildingName;
}
