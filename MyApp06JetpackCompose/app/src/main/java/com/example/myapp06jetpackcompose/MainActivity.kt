package com.example.myapp06jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExample()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeExample() {

    var loginname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var place by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Login app", color = Color.White) }, // Nastaví barvu textu na bílou
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black,  // Nastaví pozadí na černé
                    //titleContentColor = Color.White // Nastaví barvu textu na bílou
                )
            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)  // padding kolem obsahu
                .padding(36.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            OutlinedTextField(
                value = loginname,
                onValueChange = {loginname = it},
                label = {Text ("Username")},
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = {Text ("Password (max 10 char)")},
                modifier = Modifier.fillMaxWidth()
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),

                horizontalArrangement = Arrangement.spacedBy(8.dp)


            ){
                Button(
                    modifier = Modifier.weight(1f).fillMaxHeight(),

                    onClick = {
                        resultText = "User $loginname logged in"
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)


                ) {
                    Text ("Login")
                }
                Button(
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    onClick = {
                        resultText = "No registration allowed at this moment"
                    }

                ) {
                    Text ("Signup")
                }



            }

            if (resultText.isNotEmpty()) {
                Text(
                    text = resultText,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }





        }


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeExample()
}