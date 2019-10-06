package com.eduramza.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eduramza.local.model.LoginResponse

@Dao
interface UserDao {

    @Query("SELECT * FROM User_Table WHERE token = :token")
    fun getUserLogged(token: String): LoginResponse.User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: LoginResponse.User)

    @Query("SELECT token FROM User_Table WHERE id =  :id")
    fun getToken(id: String): String

    @Query("SELECT * FROM User_Table WHERE email = :email")
    fun getUserSinup(email: String): LoginResponse.User

    @Query("SELECT * FROM User_Table")
    fun getAllUsers() : List<LoginResponse.User>
}