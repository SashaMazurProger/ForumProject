
package com.example.sasham.testproject.model.network;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SectionAnswer implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("group_id")
    @Expose
    private Integer groupId;
    @SerializedName("order_index")
    @Expose
    private Integer orderIndex;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    public final static Creator<SectionAnswer> CREATOR = new Creator<SectionAnswer>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SectionAnswer createFromParcel(Parcel in) {
            return new SectionAnswer(in);
        }

        public SectionAnswer[] newArray(int size) {
            return (new SectionAnswer[size]);
        }

    }
    ;

    protected SectionAnswer(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.groupId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.orderIndex = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public SectionAnswer() {
    }

    /**
     * 
     * @param id
     * @param groupId
     * @param description
     * @param orderIndex
     * @param name
     */
    public SectionAnswer(Integer id, Integer groupId, Integer orderIndex, String name, String description) {
        super();
        this.id = id;
        this.groupId = groupId;
        this.orderIndex = orderIndex;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(groupId);
        dest.writeValue(orderIndex);
        dest.writeValue(name);
        dest.writeValue(description);
    }

    public int describeContents() {
        return  0;
    }

}
