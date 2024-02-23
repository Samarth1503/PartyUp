package com.example.partyfinder.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.partyfinder.model.GamerCalls
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: LocalUser)

    @Delete
    suspend fun delete(user: LocalUser)

    @Query("SELECT userEmail FROM LocalUser")
    fun getUser(): String?
}

@Dao
interface UsersDao {
    @Query("SELECT * FROM Users")
    fun getAll(): Flow<List<Users>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg users: Users)

    @Update
    suspend fun update(users: Users)

    suspend fun updateData(users: Users) {
        upsert(users)
    }
}


@Dao
interface GamerCallsDao {
    @Query("SELECT * FROM GamerCalls")
    fun getAll(): Flow<List<GamerCalls>>

    @Insert
    fun insertAll(vararg gamerCalls: GamerCalls)
}

@Dao
interface ChatChannelsDao {
    @Query("SELECT * FROM ChatChannels")
    fun getAll(): Flow<List<ChatChannels>>

    @Insert
    fun insertAll(vararg chatChannels: ChatChannels)
}

@Dao
interface ChatDao {
    @Query("SELECT * FROM Chat")
    fun getAll(): Flow<List<Chat>>

    @Insert
    fun insertAll(vararg chat: Chat)
}

@Dao
interface UserPostDao {
    @Query("SELECT * FROM UserPost")
    fun getAll(): Flow<List<UserPost>>

    @Insert
    fun insertAll(vararg userPost: UserPost)
}

@Dao
interface LiveGamerCallDao {
    @Query("SELECT * FROM LiveGamerCall")
    fun getAll(): Flow<List<LiveGamerCall>>

    @Insert
    fun insertAll(vararg liveGamerCall: LiveGamerCall)
}

@Dao
interface CommunitiesDao {
    @Query("SELECT * FROM Communities")
    fun getAll(): Flow<List<Communities>>

    @Insert
    fun insertAll(vararg communities: Communities)
}

@Dao
interface CommunityDao {
    @Query("SELECT * FROM Community")
    fun getAll(): Flow<List<Community>>

    @Insert
    fun insertAll(vararg community: Community)
}