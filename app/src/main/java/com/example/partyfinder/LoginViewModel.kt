import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.partyfinder.Navigation.PartyUpRouterSam
import com.example.partyfinder.Navigation.Screens
import com.example.partyfinder.RegistrationUIState
import com.example.partyfinder.UIEvent
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


public class LoginViewModel : ViewModel() {

    private val _registrationUIState = MutableStateFlow(RegistrationUIState())
    val registrationUIState: StateFlow<RegistrationUIState> = _registrationUIState.asStateFlow()

    var registrationInProgress = mutableStateOf(false)

    private var TAG = LoginViewModel::class.simpleName

    fun onEvent(event : UIEvent){
        when(event){
            is UIEvent.NameChanged -> {
                _registrationUIState.update{ currentState -> currentState.copy(
                    userName = event.userName )}
                printState()
            }
            is UIEvent.EmailChanged -> {
                _registrationUIState.update{ currentState -> currentState.copy(
                    email = event.email )}
                printState()
            }
            is UIEvent.PasswordChanged -> {
                _registrationUIState.update{ currentState -> currentState.copy(
                    password = event.password )}
                printState()
            }
            is UIEvent.RegisterButtonClicked ->{

                Log.d(TAG,"**SignUp**")

                Log.d(TAG,"InsideStack")
                Log.d(TAG, registrationUIState.value.toString())
                registerToFireBase()
            }

            is UIEvent.PrivacyPolicyCheckBoxClicked -> {
                _registrationUIState.update{ currentState -> currentState.copy(
                    policyStatus = event.status) }
            }
        }
    }

    private fun registerToFireBase(){
        Log.d(TAG,"InsideRegister")

        createUserInFireBase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )
    }

    private fun printState(){
        Log.d(TAG,"InsideStack")
        Log.d(TAG, registrationUIState.value.toString())
    }

    private fun createUserInFireBase(email: String, password: String) {

        registrationInProgress.value = true

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { Log.d(TAG,"isSuccessful = ${it.isSuccessful}")
                registrationInProgress.value = false
                if (it.isSuccessful) {
                    PartyUpRouterSam.navigateTo(Screens.TermsAndConditionsScreen)
                }
            }

            .addOnFailureListener{ Log.d(TAG,"Failure") }
    }
}

