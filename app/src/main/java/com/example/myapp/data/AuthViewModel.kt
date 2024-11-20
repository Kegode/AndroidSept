package com.example.myapp.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.myapp.models.SignupModel
import com.example.myapp.navigation.ROUTE_LOGIN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel(){
    private val mAuth: FirebaseAuth=FirebaseAuth.getInstance()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun signup(userName:String,email: String,password: String,
               confirmPassword: String,navController: NavController,
               context: Context){
        if (userName.isBlank() || email.isBlank() || password.isBlank() ||
            confirmPassword.isBlank() ){
            showToast("Please fill all the fields",context)
            return
        }
        if (password != confirmPassword){
            showToast("Password do not match",context)
            return
        }
        _isLoading.value = true

        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful){
                    val userId = mAuth.currentUser?.uid ?: ""
                    val userData = SignupModel(userName = userName,
                        email=email,password = password,userId = userId)
                    saveUserToDatabase(userId,userData,navController,context)
                } else{
                    _errorMessage.value = task.exception?.message
                    showToast(task.exception?.message ?: "Registration failed",
                        context)
                }
            }
    }

    fun saveUserToDatabase(userId: String,userData: SignupModel,
                           navController: NavController,context: Context){
        val regRef = FirebaseDatabase.getInstance()
            .getReference("Users/$userId")
        regRef.setValue(userData).addOnCompleteListener { regRef ->
            if (regRef.isSuccessful){
                showToast("User Successfully Registered",context)
                navController.navigate(ROUTE_LOGIN)
            } else{
                _errorMessage.value = regRef.exception?.message
                showToast(regRef.exception?.message ?: "Database error",
                    context)
            }
        }
    }
    public fun showToast(message: String, context: Context){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }
    fun login(email: String,password: String,navController: NavController,
              context: Context){
        if (email.isBlank() || password.isBlank()){
            showToast("Email and password required",context)
            return
        }
        _isLoading.value = true

        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful){
                    showToast("User Successfully logged in",context)
                }else{
                    _errorMessage.value = task.exception?.message
                    showToast(task.exception?.message ?: "Login failed",context)

                }
            }
    }
}