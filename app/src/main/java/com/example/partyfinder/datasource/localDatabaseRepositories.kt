package com.example.partyfinder.datasource

import kotlinx.coroutines.flow.Flow

class LocalUserRepository(private val localUserDao: LocalUserDao) {

    suspend fun upsert(user: LocalUser) {
        localUserDao.upsert(user)
    }

    suspend fun delete(user: LocalUser) {
        localUserDao.delete(user)
    }

    fun getUser(): String? {
        return localUserDao.getUser()
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

class GamerCallsRepository(private val gamerCallsDao: GamerCallsDao) {

    fun getAll(): Flow<List<GamerCalls>> {
        return gamerCallsDao.getAll()
    }

    fun insertAll(vararg gamerCalls: GamerCalls) {
        gamerCallsDao.insertAll(*gamerCalls)
    }
}

class ChatChannelsRepository(private val chatChannelsDao: ChatChannelsDao) {

    fun getAll(): Flow<List<ChatChannels>> {
        return chatChannelsDao.getAll()
    }

    fun insertAll(vararg chatChannels: ChatChannels) {
        chatChannelsDao.insertAll(*chatChannels)
    }
}

class ChatRepository(private val chatDao: ChatDao) {

    fun getAll(): Flow<List<Chat>> {
        return chatDao.getAll()
    }

    fun insertAll(vararg chat: Chat) {
        chatDao.insertAll(*chat)
    }
}

class UserPostRepository(private val userPostDao: UserPostDao) {

    fun getAll(): Flow<List<UserPost>> {
        return userPostDao.getAll()
    }

    fun insertAll(vararg userPost: UserPost) {
        userPostDao.insertAll(*userPost)
    }
}

class LiveGamerCallRepository(private val liveGamerCallDao: LiveGamerCallDao) {

    fun getAll(): Flow<List<LiveGamerCall>> {
        return liveGamerCallDao.getAll()
    }

    fun insertAll(vararg liveGamerCall: LiveGamerCall) {
        liveGamerCallDao.insertAll(*liveGamerCall)
    }
}

class CommunitiesRepository(private val communitiesDao: CommunitiesDao) {

    fun getAll(): Flow<List<Communities>> {
        return communitiesDao.getAll()
    }

    fun insertAll(vararg communities: Communities) {
        communitiesDao.insertAll(*communities)
    }
}

class CommunityRepository(private val communityDao: CommunityDao) {

    fun getAll(): Flow<List<Community>> {
        return communityDao.getAll()
    }

    fun insertAll(vararg community: Community) {
        communityDao.insertAll(*community)
    }
}
