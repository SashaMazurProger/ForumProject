package com.example.sasham.testproject.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.model.User

@Entity(tableName = Constants.USER_TABLE)
data class UserTable constructor(

        @PrimaryKey
        var id: Int? = null,

        var login: String? = null,
        var userName: String? = null,
        var birthday: String? = null,
        var country: String? = null,
        var country2: String? = null,
        var city: String? = null,
        var sex: String? = null,
        var state: Int? = null,
        var email: String? = null,
        var homepage: String? = null,
        var icq: Long? = null,
        var about: String? = null,
        var reputation: Int? = null,
        var regDate: Int? = null,
        var msgDate: Int? = null,
        var msgCount: Int? = null,
        var lastMsgTime: Int? = null,
        var lastIp: String? = null,
        var avatar: String? = null,
        var created: Int? = null
) {
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
