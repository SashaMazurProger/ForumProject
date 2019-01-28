package com.example.sasham.testproject.model.network

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SectionsWraper : Parcelable {

    @SerializedName("base_url")
    @Expose
    var baseUrl: String? = null
    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("objects")
    @Expose
    var sections: List<SectionAnswer>? = null

    constructor(baseUrl: String, success: Boolean?, sections: List<SectionAnswer>) {
        this.baseUrl = baseUrl
        this.success = success
        this.sections = sections
    }

    protected constructor(`in`: Parcel) {
        baseUrl = `in`.readString()
        val tmpSuccess = `in`.readByte()
        success = if (tmpSuccess.toInt() == 0) null else tmpSuccess.toInt() == 1
        sections = `in`.createTypedArrayList(SectionAnswer.CREATOR)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(baseUrl)
        dest.writeByte((if (success == null) 0 else if (success!!) 1 else 2).toByte())
        dest.writeTypedList(sections)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Creator<SectionsWraper> = object : Creator<SectionsWraper> {
            override fun createFromParcel(`in`: Parcel): SectionsWraper {
                return SectionsWraper(`in`)
            }

            override fun newArray(size: Int): Array<SectionsWraper?> {
                return arrayOfNulls(size)
            }
        }
    }

}
