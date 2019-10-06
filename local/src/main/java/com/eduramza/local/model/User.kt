package com.eduramza.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("user")
    val user: User
) {
    @Entity(tableName = "User_Table")
    data class User(

        @SerializedName("createdAt")
        @ColumnInfo(name = "createdAt")
        val createdAt: String,

        @SerializedName("email")
        @ColumnInfo(name = "email")
        val email: String,

        @SerializedName("_id")
        @ColumnInfo(name = "id")
        @PrimaryKey
        val id: String,

        @SerializedName("token")
        @ColumnInfo(name = "token")
        val token: String,

        @SerializedName("updatedAt")
        @ColumnInfo(name = "updatedAt")
        val updatedAt: String,

        @SerializedName("__v")
        @ColumnInfo(name = "v")
        val v: Int
    )
}