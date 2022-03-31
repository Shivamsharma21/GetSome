package com.example.getsome.dashboard

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getsome.R
import com.example.getsome.api.ClothHelper
import com.example.getsome.api.ClothService
import com.example.getsome.model.ApiData
import com.example.getsome.repository.ClothRepository

import com.google.firebase.auth.FirebaseAuth

import kotlin.collections.ArrayList

class WelcomeScreen: AppCompatActivity() {

    var arrayList = ArrayList<ApiData>()
    lateinit var viewModel :ClothViewModel
    var state =1
    lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

         // this is on create
        val clothService = ClothHelper.getInstance().create(ClothService::class.java)
        val clothRepository = ClothRepository(clothService)

        viewModel = ViewModelProvider(this,ClothViewModelFactory(clothRepository)).get(ClothViewModel::class.java)
       viewModel.clothLiveData.observe(this, Observer {
               arrayList = it as ArrayList<ApiData>          // this data is not getting by next to it  //
             Log.d("Copyied -->",arrayList.toString())
          })
        setupADapter()
    }

    fun setupADapter(){
        var list = listOf("Nike","Romans","CK","Jordan","Kizumik","Hishida")

        var recyclerView = findViewById<RecyclerView>(R.id.cloths)
        Log.d("Passing ->",arrayList.toString())
        viewModel.clothLiveData.observe(this, Observer {
            arrayList = it as ArrayList<ApiData>          // this data is not getting by next to it  //
            Log.d("Copyied -->",arrayList.toString())
            recyclerView.adapter = ClothViewAdapter(arrayList)
        })

        recyclerView.layoutManager = GridLayoutManager(applicationContext,2)
    }
}
//  arrayList1 = arrayList.clone() as ArrayList<ClothModelItem>
//    Log.d("Data fetched ->", it.get(1).toString())
//     Log.d("List cloned",arrayList.toString())
//   Log.d("List cloned1",arrayList1.toString())

//          var intent = getIntent()
//        state = intent.getIntExtra("UserState",1)
//        Log.d("Intent State",state.toString())
//        firebaseAuth = FirebaseAuth.getInstance()


//    override fun onStart() {
//        super.onStart()
//        if(state ==0){
//            val intent = Intent(this,UserProfileActivity::class.java)
//            startActivity(intent)
//            Log.d("Going to UserProfile","OnStart")
//        }
//    }
//         viewModel.cloths.observe(this, Observer {
//             for (clothModle in it){
//               arrayList.add(clothModle)
//             }
