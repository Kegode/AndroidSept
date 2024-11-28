package com.example.myapp.ui.theme.screens.client

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapp.R
import com.example.myapp.data.ClientViewModel
import com.example.myapp.models.Client
import com.example.myapp.navigation.ROUTE_UPDATE_CLIENT

@Composable
fun ViewClients(navController: NavHostController){
    val context = LocalContext.current
    val clientRepository = ClientViewModel()
    val emptyUploadState = remember {
        mutableStateOf(
            Client("","","","","",""))
    }
    val emptyUploadListState = remember {
        mutableStateListOf<Client>()
    }
    val clients = clientRepository.viewClients(
        emptyUploadState,emptyUploadListState, context)
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All Clients",
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                color= Color.Black)
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(clients){
                    ClientItem(
                        firstname = it.firstname,
                        lastname = it.lastname,
                        gender = it.gender,
                        age = it.age,
                        id = it.id,
                        navController = navController,
                        clientRepository = clientRepository)
                }

            }
        }
    }
}
@Composable
fun ClientItem(firstname:String,lastname:String,gender:String,
               age: String,id: String,navController: NavHostController,
               clientRepository: ClientViewModel){
    val context = LocalContext.current
    Column (modifier = Modifier.fillMaxWidth()){
        Card (modifier = Modifier
            .padding(10.dp)
            .height(210.dp),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors
                (containerColor = Color.Gray))
        {
            Row {
                Column {
                    Image(painter = painterResource(id = R.drawable.placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(200.dp)
                            .height(150.dp)
                            .padding(10.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Button(onClick = {
                                         clientRepository.deleteClient(context,id,navController)

                        },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(Color.Red)
                        ) {
                            Text(text = "REMOVE",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp)
                        }
                        Button(onClick = {
                                         navController.navigate("$ROUTE_UPDATE_CLIENT/$id")
                        },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(Color.Green)
                        ) {
                            Text(text = "UPDATE",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp)
                        }
                    }

                }
                Column (modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 10.dp)
                    .verticalScroll(rememberScrollState())){
                    Text(text = "FIRSTNAME",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = firstname,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "LASTNAME",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = lastname,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "GENDER",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = gender,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "AGE",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = age,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
@Preview
@Composable
fun ViewClientsPreview(){
    ViewClients(rememberNavController())
}