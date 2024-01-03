import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class RegisterViewModel : ViewModel() {
    private val auth = Firebase.auth

    fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Registration successful
                    val user = auth.currentUser
                    // You can perform additional actions after successful registration
                } else {
                    // Registration failed
                    val exception = task.exception
                    exception?.let {
                        // Handle the exception, log, or show an error message
                    }
                }
            }
    }
}
