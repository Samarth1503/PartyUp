package com.example.partyfinder.data.repositories

import com.example.partyfinder.data.GamerCalls


interface GamerCallsRepository{
 suspend fun getGamerCalls():GamerCalls
}



