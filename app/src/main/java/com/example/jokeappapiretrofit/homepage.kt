package com.example.jokeappapiretrofit

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Homepage() {
    var jokes by remember {
        mutableStateOf("jokes will show here...")
    }
    var createdtime by remember {
        mutableStateOf("")
    }
    var updatetime by remember {
        mutableStateOf("")
    }
    var isloading by remember {
        mutableStateOf(false)
    }
    var ID by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                Log.d("APICALL", "Button Clicked")
                isloading=true
                ApiCall().getjokes(this){
                    jokes = it.value
                    createdtime= it.created_at
                    updatetime= it.updated_at
                    ID=it.id
                    isloading=false
                }

            },
            colors = ButtonDefaults.buttonColors(Color(0,100,70), Color(255,255,255)),
            modifier = Modifier
                .padding(all = 10.dp)
                .height(60.dp)
                .width(150.dp)
        ) {
            if(isloading){
                CircularProgressIndicator(
                    color = Color(255,255,255),
                    modifier = Modifier
                        .size(40.dp)
                        .padding(all = 5.dp)
                )
            }
            if(isloading){
                Toast.makeText(LocalContext.current ,"Loading...",Toast.LENGTH_SHORT).show()
            }
            else{
                Text(
                    text = "Get Jokes",
                    fontSize = 15.sp
                    )
            }
        }

        Text(
            text = "Created At: $createdtime",
            color = Color(100,200,100),
            fontSize = 15.sp,
            modifier = Modifier
                .padding(top = 10.dp)
        )
        Text(
            text = "Updated At: $updatetime",
            color = Color(100,200,150),
            fontSize = 15.sp,
            modifier = Modifier
                .padding(top = 10.dp)
        )
        Text(
            text = "ID : $ID",
            color = Color(100,150,200),
            fontSize = 15.sp,
            modifier = Modifier
                .padding(top = 10.dp)
        )


        Text(
            text = jokes,
            color = Color(100,100,255),
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            modifier = Modifier
                .padding(top = 10.dp)
        )

//        if(isloading){
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .size(70.dp)
//                    .padding(all = 15.dp)
//            )
//        }

    }
}