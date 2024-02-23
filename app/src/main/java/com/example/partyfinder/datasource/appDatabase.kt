package com.example.partyfinder.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

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
