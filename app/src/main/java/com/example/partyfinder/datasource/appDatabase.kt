package com.example.partyfinder.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.partyfinder.model.GamerCalls
import com.example.partyfinder.model.local.Chat
import com.example.partyfinder.model.local.ChatChannels
import com.example.partyfinder.model.local.ChatChannelsDao
import com.example.partyfinder.model.local.ChatDao
import com.example.partyfinder.model.local.Communities
import com.example.partyfinder.model.local.CommunitiesDao
import com.example.partyfinder.model.local.Community
import com.example.partyfinder.model.local.CommunityDao
import com.example.partyfinder.model.local.Converters
import com.example.partyfinder.model.local.GamerCallsDao
import com.example.partyfinder.model.local.LiveGamerCall
import com.example.partyfinder.model.local.LiveGamerCallDao
import com.example.partyfinder.model.local.LocalUser
import com.example.partyfinder.model.local.LocalUserDao
import com.example.partyfinder.model.local.UserPost
import com.example.partyfinder.model.local.UserPostDao
import com.example.partyfinder.model.local.Users
import com.example.partyfinder.model.local.UsersDao

@Database(entities = [LocalUser::class, Users::class, GamerCalls::class, ChatChannels::class, Chat::class, UserPost::class, LiveGamerCall::class, Communities::class, Community::class], version = 1)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun localUserDao(): LocalUserDao
    abstract fun usersDao(): UsersDao
    abstract fun gamerCallsDao(): GamerCallsDao
    abstract fun chatChannelsDao(): ChatChannelsDao
    abstract fun chatDao(): ChatDao
    abstract fun userPostDao(): UserPostDao
    abstract fun liveGamerCallDao(): LiveGamerCallDao
    abstract fun communitiesDao(): CommunitiesDao
    abstract fun communityDao(): CommunityDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    suspend fun updateAll() {
    }
}
