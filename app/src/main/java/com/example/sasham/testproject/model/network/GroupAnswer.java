
package com.example.sasham.testproject.model.network;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupAnswer implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("order_index")
    @Expose
    private Integer orderIndex;
    public final static Creator<GroupAnswer> CREATOR = new Creator<GroupAnswer>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GroupAnswer createFromParcel(Parcel in) {
            return new GroupAnswer(in);
        }

        public GroupAnswer[] newArray(int size) {
            return (new GroupAnswer[size]);
        }

    }
    ;

    protected GroupAnswer(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.orderIndex = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public GroupAnswer() {
    }

    /**
     * 
     * @param id
     * @param orderIndex
     * @param description
     * @param name
     */
    public GroupAnswer(Integer id, String name, String description, Integer orderIndex) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.orderIndex = orderIndex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(description);
        dest.writeValue(orderIndex);
    }

    public int describeContents() {
        return  0;
    }

}
