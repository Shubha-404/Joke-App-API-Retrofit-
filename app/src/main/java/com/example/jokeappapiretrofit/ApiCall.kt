package com.example.jokeappapiretrofit

import android.util.Log
import androidx.compose.foundation.layout.ColumnScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiCall {
    fun getjokes(context: ColumnScope, callback:(DataModel) -> Unit){

        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.chucknorris.io/").addConverterFactory(GsonConverterFactory.create()).build()
        val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)
        val call: Call<DataModel> = service.GetJokeFromAPI()
        call.enqueue(object : Callback<DataModel>{
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if(response!!.isSuccessful){
                    val data: DataModel = response.body() as DataModel
                    callback(data)
                }
                Log.d("APICALL", "Success")
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
//                Toast.makeText(context,"Request Failed",Toast.LENGTH_SHORT).show()
                Log.d("APICALL", "Failed")
            }

        })
    }
}