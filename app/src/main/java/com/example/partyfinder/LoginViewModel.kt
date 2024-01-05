import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.partyfinder.RegisterationUIState
import com.example.partyfinder.UIEvent
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth



public class LoginViewModel : ViewModel() {

    var registerationUIState = mutableStateOf(RegisterationUIState())

    private var TAG = LoginViewModel::class.simpleName

    fun onEvent(event : UIEvent){
        when(event){
            is UIEvent.NameChanged -> {
                registerationUIState.value = registerationUIState.value.copy(
                    userName = event.userName )
                printState()
            }
            is UIEvent.EmailChanged -> {
                registerationUIState.value = registerationUIState.value.copy(
                    email = event.email )
                printState()
            }
            is UIEvent.PasswordChanged -> {
                registerationUIState.value = registerationUIState.value.copy(
                    password = event.password )
                printState()
            }
            is UIEvent.RegisterButtonClicked ->{

                Log.d(TAG,"***SignUp")

                Log.d(TAG,"InsideStack")
                Log.d(TAG, registerationUIState.value.toString())
                registerToFireBase()
            }
        }
    }

    private fun registerToFireBase(){
        Log.d(TAG,"InsideRegister")

        createUserInFireBase(
            email = registerationUIState.value.email,
            password = registerationUIState.value.password
        )
    }

    private fun printState(){
        Log.d(TAG,"InsideStack")
        Log.d(TAG, registerationUIState.value.toString())
    }

    private fun createUserInFireBase(email: String, password: String) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { Log.d(TAG,"isSuccessful = ${it.isSuccessful}") }
            .addOnFailureListener{ Log.d(TAG,"Failure") }
    }
}
