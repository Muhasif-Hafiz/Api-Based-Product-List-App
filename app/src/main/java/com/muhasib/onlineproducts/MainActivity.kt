package com.muhasib.onlineproducts

import ApiInterface
import MyAdapter
import MyData
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var myAdapter : MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerview= findViewById<RecyclerView>(R.id.recyclerView)

         val retrofitBuilder = Retrofit.Builder().baseUrl("https://dummyjson.com/").addConverterFactory(
             GsonConverterFactory.create()
         ).build().create(ApiInterface::class.java)


        val retrofitData= retrofitBuilder.getProducts()

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(p0: Call<MyData?>, p1: Response<MyData?>) {

                var responseBody=p1.body()
                val productList=responseBody?.products!!

                myAdapter=MyAdapter(this@MainActivity,productList)
                recyclerview.adapter=myAdapter
                recyclerview.layoutManager=LinearLayoutManager(this@MainActivity)


            }

            override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
               Log.d("Main Activity", "OnFailure" + p1.message)
            }
        })


    }
}