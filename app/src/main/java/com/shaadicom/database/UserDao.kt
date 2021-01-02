package com.shaadicom.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.shaadicom.data.Result

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

}