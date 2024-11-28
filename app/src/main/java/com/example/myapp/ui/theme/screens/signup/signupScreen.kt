package com.example.myapp.ui.theme.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapp.R
import com.example.myapp.data.AuthViewModel

@Composable
fun SignupScreen(navController: NavController){
    val context = LocalContext.current
    val authViewModel:AuthViewModel = viewModel()

    val isLoading by authViewModel.isLoading.collectAsState()

    var userName by remember {
        mutableStateOf(value = "")
    }
    var email by remember {
        mutableStateOf(value = "")
    }
    var password by remember {
        mutableStateOf(value = "")
    }
    var confirmPassword by remember {
        mutableStateOf(value = "")
    }
    
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center


    ) {
        Text(text = "Register Here",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.Black)
                .padding(20.dp)
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(10.dp))
        Image(modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .height(80.dp),
            painter = painterResource(id = R.drawable.logo) ,
            contentDescription = "Britam Logo")
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(modifier= Modifier
            .wrapContentWidth()
            .align(Alignment.CenterHorizontally),
            label = { Text(text = "Enter User Name") },
            placeholder = { Text(text = "Please enter username") },
            value = userName,
            onValueChange ={
                    NewuserName -> userName = NewuserName
            } )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(modifier = Modifier
            .wrapContentWidth()
            .align(Alignment.CenterHorizontally),
            label = { Text(text = "Enter Email") },
            placeholder = { Text(text = "Please Enter Email") },
            value = email,
            onValueChange ={
                    newEmail->email=newEmail
            } )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(modifier = Modifier
            .wrapContentWidth()
            .align(Alignment.CenterHorizontally),
            label = { Text(text = "Enter Password") },
            placeholder = { Text(text = "Please Password") },
            value = password,
            onValueChange ={
                    newPassword->password=newPassword
            } )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(modifier = Modifier
            .wrapContentWidth()
            .align(Alignment.CenterHorizontally),
            label = { Text(text = "Confirm Password") },
            placeholder = { Text(text = "Please Enter Password Again") },
            value = confirmPassword,
            onValueChange ={
                    newConfirmPassword->confirmPassword=newConfirmPassword
            } )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
            authViewModel.signup(userName, email, password,
                confirmPassword, navController, context)
            },
            enabled = !isLoading,
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            if (isLoading){
                CircularProgressIndicator(color = Color.Black, strokeWidth = 4.dp)
            }else{
            Text(
                modifier = Modifier.padding(10.dp),
                text = "REGISTER HERE")
        }


    } }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignupScreenPreview(){
    SignupScreen(rememberNavController())
}