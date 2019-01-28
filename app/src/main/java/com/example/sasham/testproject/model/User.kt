package com.example.sasham.testproject.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class User : Parcelable {

    var id: Int? = null
    var login: String? = null
    var userName: String? = null
    var birthday: String? = null
    var country: String? = null
    var country2: String? = null
    var city: String? = null
    var sex: String? = null
    var state: Int? = null
    var email: String? = null
    var homepage: String? = null
    var icq: Long? = null
    var about: String? = null
    var reputation: Int? = null
    var regDate: Int? = null
    var msgDate: Int? = null
    var msgCount: Int? = null
    var lastMsgTime: Int? = null
    var lastIp: String? = null
    var avatar: String? = null
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
        this.icq = `in`.readValue(Long::class.java.classLoader) as Long
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
    constructor(id: Int?, login: String?, userName: String?, birthday: String?, country: String?, country2: String?, city: String?, sex: String?, state: Int?, email: String?, homepage: String?, icq: Long?, about: String?, reputation: Int?, regDate: Int?, msgDate: Int?, msgCount: Int?, lastMsgTime: Int?, lastIp: String?, avatar: String?, created: Int?) : super() {
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
        val CREATOR: Parcelable.Creator<User> = object : Creator<User> {


            override fun createFromParcel(`in`: Parcel): User {
                return User(`in`)
            }

            override fun newArray(size: Int): Array<User?> {
                return arrayOfNulls(size)
            }

        }
    }

}
