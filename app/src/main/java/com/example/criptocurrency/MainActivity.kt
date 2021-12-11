package com.example.criptocurrency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.criptocurrency.adapter.CurrencyAdaptor
import com.example.criptocurrency.model.CurrencyModel
import com.example.criptocurrency.services.CryptoApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var BASE_URL = "https://raw.githubusercontent.com/";
    private  var crytoArray : ArrayList<CurrencyModel>? = null
    private var currencyAdapter : CurrencyAdaptor?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        loadData()
    }
    private  fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoApi::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CurrencyModel>>{
            override fun onResponse(
                call: Call<List<CurrencyModel>>,
                response: Response<List<CurrencyModel>>
            ) {
                if(response.isSuccessful){

                    response.body()?.let {
                        crytoArray = ArrayList(it)
                        currencyAdapter = CurrencyAdaptor(crytoArray!!)
                        recyclerView.adapter = currencyAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<CurrencyModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}