package com.example.sasham.testproject.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User : Parcelable {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("login")
    @Expose
    var login: String? = null
    @SerializedName("user_name")
    @Expose
    var userName: String? = null
    @SerializedName("birthday")
    @Expose
    var birthday: String? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("country2")
    @Expose
    var country2: String? = null
    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("sex")
    @Expose
    var sex: String? = null
    @SerializedName("state")
    @Expose
    var state: Int? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("homepage")
    @Expose
    var homepage: String? = null
    @SerializedName("icq")
    @Expose
    var icq: Int? = null
    @SerializedName("about")
    @Expose
    var about: String? = null
    @SerializedName("reputation")
    @Expose
    var reputation: Int? = null
    @SerializedName("reg_date")
    @Expose
    var regDate: Int? = null
    @SerializedName("msg_date")
    @Expose
    var msgDate: Int? = null
    @SerializedName("msg_count")
    @Expose
    var msgCount: Int? = null
    @SerializedName("last_msg_time")
    @Expose
    var lastMsgTime: Int? = null
    @SerializedName("last_ip")
    @Expose
    var lastIp: String? = null
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null
    @SerializedName("created")
    @Expose
    var created: Int? = null

    protected constructor(`in`: Parcel) {
        this.id = `in`.readValue(Int::class.java.classLoader) as Int
        this.login = `in`.readValue(String::class.java.classLoader) as String
        this.userName = `in`.readValue(String::class.java.classLoader) as String
        this.birthday = `in`.readValue(String::class.java.classLoader) as String
        this.country = `in`.readValue(String::class.java.classLoader) as String
        this.country2 = `in`.readValue(String::class.java.classLoader) as String
        this.city = `in`.readValue(String::class.java.classLoader) as String
        this.sex = `in`.readValue(String::class.java.classLoader) as String
        this.state = `in`.readValue(Int::class.java.classLoader) as Int
        this.email = `in`.readValue(String::class.java.classLoader) as String
        this.homepage = `in`.readValue(String::class.java.classLoader) as String
        this.icq = `in`.readValue(Int::class.java.classLoader) as Int
        this.about = `in`.readValue(String::class.java.classLoader) as String
        this.reputation = `in`.readValue(Int::class.java.classLoader) as Int
        this.regDate = `in`.readValue(Int::class.java.classLoader) as Int
        this.msgDate = `in`.readValue(Int::class.java.classLoader) as Int
        this.msgCount = `in`.readValue(Int::class.java.classLoader) as Int
        this.lastMsgTime = `in`.readValue(Int::class.java.classLoader) as Int
        this.lastIp = `in`.readValue(String::class.java.classLoader) as String
        this.avatar = `in`.readValue(String::class.java.classLoader) as String
        this.created = `in`.readValue(Int::class.java.classLoader) as Int
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param birthday
     * @param sex
     * @param country2
     * @param regDate
     * @param about
     * @param state
     * @param msgDate
     * @param icq
     * @param avatar
     * @param homepage
     * @param country
     * @param city
     * @param id
     * @param lastIp
     * @param created
     * @param email
     * @param reputation
     * @param lastMsgTime
     * @param userName
     * @param login
     * @param msgCount
     */
    constructor(id: Int?, login: String, userName: String, birthday: String, country: String, country2: String, city: String, sex: String, state: Int?, email: String, homepage: String, icq: Int?, about: String, reputation: Int?, regDate: Int?, msgDate: Int?, msgCount: Int?, lastMsgTime: Int?, lastIp: String, avatar: String, created: Int?) : super() {
        this.id = id
        this.login = login
        this.userName = userName
        this.birthday = birthday
        this.country = country
        this.country2 = country2
        this.city = city
        this.sex = sex
        this.state = state
        this.email = email
        this.homepage = homepage
        this.icq = icq
        this.about = about
        this.reputation = reputation
        this.regDate = regDate
        this.msgDate = msgDate
        this.msgCount = msgCount
        this.lastMsgTime = lastMsgTime
        this.lastIp = lastIp
        this.avatar = avatar
        this.created = created
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(id)
        dest.writeValue(login)
        dest.writeValue(userName)
        dest.writeValue(birthday)
        dest.writeValue(country)
        dest.writeValue(country2)
        dest.writeValue(city)
        dest.writeValue(sex)
        dest.writeValue(state)
        dest.writeValue(email)
        dest.writeValue(homepage)
        dest.writeValue(icq)
        dest.writeValue(about)
        dest.writeValue(reputation)
        dest.writeValue(regDate)
        dest.writeValue(msgDate)
        dest.writeValue(msgCount)
        dest.writeValue(lastMsgTime)
        dest.writeValue(lastIp)
        dest.writeValue(avatar)
        dest.writeValue(created)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Creator<User> = object : Creator<User> {


            override fun createFromParcel(`in`: Parcel): User {
                return User(`in`)
            }

            override fun newArray(size: Int): Array<User?> {
                return arrayOfNulls(size)
            }

        }
    }

}
