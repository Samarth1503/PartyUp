package com.example.partyfinder.data.repositories

import com.example.partyfinder.model.GamerCalls
import com.example.partyfinder.model.local.Chat
import com.example.partyfinder.model.local.ChatChannels
import com.example.partyfinder.model.local.ChatChannelsDao
import com.example.partyfinder.model.local.ChatDao
import com.example.partyfinder.model.local.Communities
import com.example.partyfinder.model.local.CommunitiesDao
import com.example.partyfinder.model.local.Community
import com.example.partyfinder.model.local.CommunityDao
import com.example.partyfinder.model.local.GamerCallsDao
import com.example.partyfinder.model.local.LiveGamerCall
import com.example.partyfinder.model.local.LiveGamerCallDao
import com.example.partyfinder.model.local.LocalUser
import com.example.partyfinder.model.local.LocalUserDao
import com.example.partyfinder.model.local.UserPost
import com.example.partyfinder.model.local.UserPostDao
import com.example.partyfinder.model.local.Users
import com.example.partyfinder.model.local.UsersDao
import kotlinx.coroutines.flow.Flow

class LocalUserRepository(private val localUserDao: LocalUserDao) {

    suspend fun upsert(user: LocalUser) {
        localUserDao.upsert(user)
    }

    suspend fun delete(user: LocalUser) {
        localUserDao.delete(user)
    }

    fun getUser(): String {
        return localUserDao.getUser()
    }

    fun getUserUID(): String {
        return localUserDao.getUserUID()
    }
}

class UsersRepository(private val usersDao: UsersDao) {

    fun getAll(): Flow<List<Users>> {
        return usersDao.getAll()
    }

    suspend fun upsert(vararg users: Users) {
        usersDao.upsert(*users)
    }

    suspend fun update(users: Users) {
        usersDao.update(users)
    }

    suspend fun updateData(users: Users) {
        usersDao.updateData(users)
    }
}

class GamerCallsLocalRepository(private val gamerCallsDao: GamerCallsDao) {

    fun getAll(): Flow<List<GamerCalls>> {
        return gamerCallsDao.getAll()
    }

    fun insertAll(vararg gamerCalls: GamerCalls) {
        gamerCallsDao.insertAll(*gamerCalls)
    }
}

class ChatChannelsLocalRepository(private val chatChannelsDao: ChatChannelsDao) {

    fun getAll(): Flow<List<ChatChannels>> {
        return chatChannelsDao.getAll()
    }

    fun insertAll(vararg chatChannels: ChatChannels) {
        chatChannelsDao.insertAll(*chatChannels)
    }
}

class ChatLocalRepository(private val chatDao: ChatDao) {

    fun getAll(): Flow<List<Chat>> {
        return chatDao.getAll()
    }

    fun insertAll(vararg chat: Chat) {
        chatDao.insertAll(*chat)
    }
}

class UserPostLocalRepository(private val userPostDao: UserPostDao) {

    fun getAll(): Flow<List<UserPost>> {
        return userPostDao.getAll()
    }

    fun insertAll(vararg userPost: UserPost) {
        userPostDao.insertAll(*userPost)
    }
}

class LiveGamerCallLocalRepository(private val liveGamerCallDao: LiveGamerCallDao) {

    fun getAll(): Flow<List<LiveGamerCall>> {
        return liveGamerCallDao.getAll()
    }

    fun insertAll(vararg liveGamerCall: LiveGamerCall) {
        liveGamerCallDao.insertAll(*liveGamerCall)
    }
}

class CommunitiesLocalRepository(private val communitiesDao: CommunitiesDao) {

    fun getAll(): Flow<List<Communities>> {
        return communitiesDao.getAll()
    }

    fun insertAll(vararg communities: Communities) {
        communitiesDao.insertAll(*communities)
    }
}

class CommunityLocalRepository(private val communityDao: CommunityDao) {

    fun getAll(): Flow<List<Community>> {
        return communityDao.getAll()
    }

    fun insertAll(vararg community: Community) {
        communityDao.insertAll(*community)
    }
}
