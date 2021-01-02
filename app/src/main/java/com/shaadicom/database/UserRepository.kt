package com.shaadicom.database

import androidx.lifecycle.LiveData
import com.shaadicom.data.Result


class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
    suspend fun updateUser(user: User){
        userDao.addUser(user)
    }

}