package com.example.sasham.testproject.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UsersWrapper : Parcelable {

    @SerializedName("base_url")
    @Expose
    var baseUrl: String? = null
    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("objects")
    @Expose
    var users: List<User>? = null

    protected constructor(`in`: Parcel) {
        this.baseUrl = `in`.readValue(String::class.java.classLoader) as String
        this.success = `in`.readValue(Boolean::class.java.classLoader) as Boolean
        `in`.readList(this.users, User::class.java!!.getClassLoader())
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param baseUrl
     * @param users
     * @param success
     */
    constructor(baseUrl: String, success: Boolean?, users: List<User>) : super() {
        this.baseUrl = baseUrl
        this.success = success
        this.users = users
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(baseUrl)
        dest.writeValue(success)
        dest.writeList(users)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Creator<UsersWrapper> = object : Creator<UsersWrapper> {


            override fun createFromParcel(`in`: Parcel): UsersWrapper {
                return UsersWrapper(`in`)
            }

            override fun newArray(size: Int): Array<UsersWrapper?> {
                return arrayOfNulls(size)
            }

        }
    }

}
