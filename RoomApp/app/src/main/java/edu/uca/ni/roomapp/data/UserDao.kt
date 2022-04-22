package edu.uca.ni.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)


    @Update
    fun updateUser(user: User)


    @Query("SELECT * FROM user_table")
    fun readAllData(): LiveData<List<User>>

}