package com.example.sasham.testproject.model.db

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.model.User

@Entity(tableName = Constants.USER_TABLE)
class UserTable {

    @PrimaryKey
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


    /**
     * No args constructor for use in serialization
     *
     */
    @Ignore
    constructor() {
    }

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

    companion object {
        fun copy(it: User): UserTable {
            return UserTable(
                    it.id,
                    it.login,
                    it.userName,
                    it.birthday,
                    it.country,
                    it.country2,
                    it.city,
                    it.sex,
                    it.state,
                    it.email,
                    it.homepage,
                    it.icq,
                    it.about,
                    it.reputation,
                    it.regDate,
                    it.msgDate,
                    it.msgCount,
                    it.lastMsgTime,
                    it.lastIp,
                    it.avatar,
                    it.created
            )
        }

    }
}
