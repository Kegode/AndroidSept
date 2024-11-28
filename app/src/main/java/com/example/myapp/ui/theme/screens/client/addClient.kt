package com.example.myapp.ui.theme.screens.client

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.myapp.R
import com.example.myapp.data.ClientViewModel

@Composable
fun AddClientScreen(navController: NavController){
    val imageUri = rememberSaveable() {
        mutableStateOf<Uri?>(null)
    }
    val painter = rememberImagePainter(
        data = imageUri.value ?: R.drawable.placeholder,
        builder = {crossfade(true)})
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()){
            uri: Uri? ->
        uri?.let { imageUri.value = it }
    }
    val context = LocalContext.current
    var firstname by remember {
        mutableStateOf(value = "")
    }
    var lastname by remember {
        mutableStateOf(value = "")
    }
    var gender by remember {
        mutableStateOf(value = "")
    }
    var age by remember {
        mutableStateOf(value = "")
    }
    Scaffold (
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Settings, contentDescription = "settings")
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Email, contentDescription = "settings")
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /*TODO*/ },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = "Profile")
                    }
                }
            )


        }
    ){innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)) {
            Text(text = "ENTER NEW CLIENT",
                fontSize = 25.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.Magenta)
            )
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "BACK")
                }
                Button(onClick = {
                    val clientRepository = ClientViewModel()
                    clientRepository.saveClient(
                        firstname = firstname,
                        lastname = lastname,
                        gender = gender,
                        age = age,
                        navController = navController,
                        context = context
                    )
                }) {
                    Text(text = "SAVE")
                }
            }
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Card (
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(180.dp)
                ){
                    Image(painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(180.dp)
                            .clickable { launcher.launch("image/*") },
                        contentScale = ContentScale.Crop)
                }
                Text(text = "Attach a picture ")
            }
            OutlinedTextField(value = firstname,
                onValueChange = {newFirstname -> firstname = newFirstname},
                placeholder = { Text(text = "Enter First Name") },
                label = { Text(text = "Enter First Name") },
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = lastname,
                onValueChange = {newLastname -> lastname = newLastname},
                placeholder = { Text(text = "Enter Last Name")},
                label = { Text(text = "Enter Last Name")},
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = gender,
                onValueChange = {newGender -> gender = newGender},
                placeholder = { Text(text = "Enter Gender")},
                label = { Text(text = "Enter Your Gender")},
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = age,
                onValueChange = {newAge -> age = newAge},
                placeholder = { Text(text = "Enter Age")},
                label = { Text(text = "Enter Your Age")},
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddClientScreenPreview(){
    AddClientScreen(rememberNavController())
}