package com.example.partyfinder

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.partyfinder.data.repositories.LocalUserRepository
import com.example.partyfinder.datasource.AppDatabase
import com.example.partyfinder.ui.theme.LoadingIndicator
import com.example.partyfinder.ui.theme.PartyFinderTheme
import com.example.partyfinder.ui.theme.ViewModels.LoginViewModel
import com.example.partyfinder.ui.theme.ViewModels.ProfileViewModel
import com.example.partyfinder.ui.theme.ViewModels.RegistrationViewModel
import com.example.partyfinder.ui.theme.ViewModels.chatScreenViewModel
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {
    val UID = MutableLiveData<String>()
    val Email = MutableLiveData<String>()

    fun updateUidInView(uid: String){
        UID.value = uid
    }
    fun updateEmailInView(email: String){
        Email.value = email
    }
}

class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch{
            FirebaseApp.initializeApp(this@MainActivity)
        }

        setContent{
            PartyFinderTheme{
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    var retrievedUserUID by remember { mutableStateOf<String?>(null) }
                    var dataRetrievalInProcess by remember { mutableStateOf(true) }
                    var userRepository by remember { mutableStateOf<LocalUserRepository?>(null) }

                    LaunchedEffect(Unit){
                        launch(Dispatchers.IO){
                            val localUserDao = AppDatabase.getDatabase(this@MainActivity).localUserDao()
                            retrievedUserUID = withContext(Dispatchers.IO){ localUserDao.getUserUID() }
                            Log.d("MainActivity LocalUserData TestCase", retrievedUserUID.toString())
                            userRepository = LocalUserRepository(localUserDao)
                            dataRetrievalInProcess = false
                        }
                    }

                    if(dataRetrievalInProcess){
                        LoadingIndicator()
                    } else {
                        userRepository?.let { nonNullUserRepository ->
                            Log.d("MainActivity Debug", "Before running PartyFinderApp")
                            val registrationViewModel = RegistrationViewModel(nonNullUserRepository)
                            val loginViewModel = LoginViewModel(nonNullUserRepository)
                            val ChatScreenViewModel = chatScreenViewModel()

                            Log.d("MainActivity LocalUserData TestCase 2", retrievedUserUID.toString())

                            PartyFinderApp(
                                localDBUserUID = retrievedUserUID,
                                userViewModel = userViewModel,
                                profileViewModel = ProfileViewModel(),
                                registrationViewModel = registrationViewModel,
                                loginViewModel = loginViewModel,
                                chatScreenViewModel = ChatScreenViewModel
                            )
                            Log.d("MainActivity Debug", "After running PartyFinderApp")
                        }
                        Log.d("MainActivity Debug", "Outside .let{}")
                    }
                }
            }
        }
    }
}








@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PartyFinderTheme {

    }
}

