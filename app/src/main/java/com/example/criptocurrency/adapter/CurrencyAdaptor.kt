package com.example.criptocurrency.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.criptocurrency.R
import com.example.criptocurrency.model.CurrencyModel
import kotlinx.android.synthetic.main.recycler_row.view.*

class CurrencyAdaptor(val cryptoList : ArrayList<CurrencyModel>) : RecyclerView.Adapter<CurrencyAdaptor.CurrencyHolder>(){

   class CurrencyHolder(view : View) : RecyclerView.ViewHolder(view) {

       fun bind(currencyModel : CurrencyModel){

           itemView.currency.text = currencyModel.currency
           itemView.price.text = currencyModel.price
       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return  CurrencyHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {

        holder.bind(cryptoList[position])
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }
}