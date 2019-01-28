package com.example.sasham.testproject.model.network

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GroupsWraper protected constructor(`in`: Parcel) : Parcelable {

    @SerializedName("base_url")
    @Expose
    var baseUrl: String? = null
    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("objects")
    @Expose
    var groupAnswers: List<GroupAnswer>? = null


    init {
        baseUrl = `in`.readString()
        val tmpSuccess = `in`.readByte()
        success = if (tmpSuccess.toInt() == 0) null else tmpSuccess.toInt() == 1
        groupAnswers = `in`.createTypedArrayList(GroupAnswer.CREATOR)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(baseUrl)
        dest.writeByte((if (success == null) 0 else if (success!!) 1 else 2).toByte())
        dest.writeTypedList(groupAnswers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<GroupsWraper> = object : Parcelable.Creator<GroupsWraper> {
            override fun createFromParcel(`in`: Parcel): GroupsWraper {
                return GroupsWraper(`in`)
            }

            override fun newArray(size: Int): Array<GroupsWraper?> {
                return arrayOfNulls(size)
            }
        }
    }
}
