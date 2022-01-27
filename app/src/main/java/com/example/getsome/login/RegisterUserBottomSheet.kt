package com.example.getsome.login

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.getsome.R
import com.example.getsome.databinding.RegisteredUserBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class RegisterUserBottomSheet : BottomSheetDialogFragment() {

    var firebaseAuth = FirebaseAuth.getInstance()
     var bottomSheetlister: BottomsheetListner? = null

    lateinit var viewBinding :RegisteredUserBottomsheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
           viewBinding = RegisteredUserBottomsheetBinding.bind(inflater.inflate(R.layout.registered_user_bottomsheet,container,false))

             viewBinding.rgbutton.setOnClickListener(View.OnClickListener {
                 Log.d("BottomSheet","Clicked")
                   viewBinding.rgbutton.startLoading()
                 //  viewBinding.rgbutton.isClickable = false

               newAccountcreater()
             })


             return viewBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
          bottomSheetlister  =  if (context is BottomsheetListner){
              context
          }else{
              throw RuntimeException(
                  context.toString()+"Method Must Implemented"
              )
          }
    }

    interface BottomsheetListner{
        fun onclicklistnerBS(value:String)
    }

        fun newAccountcreater(){
            if (TextUtils.isEmpty(viewBinding.newEmail.text)&&
                (TextUtils.isEmpty(viewBinding.newPassword.text))){
             viewBinding.newEmail.setError("InValid Input")}
        else{
            firebaseAuth.createUserWithEmailAndPassword(viewBinding.newEmail.text.toString()
            ,viewBinding.newPassword.text.toString()).addOnCompleteListener {
                 if (it.isSuccessful){
                     Log.d("RegisteredSc","Listener")
                     val handler = Handler()
                     viewBinding.rgbutton.doResult(isSucceed = true)
                     handler.postDelayed(Runnable { // Do something after 5s = 5000ms
                         bottomSheetlister?.onclicklistnerBS("yes")
                     }, 2000)


                 }else{
                    Log.d("EX",it.exception.toString())
                 }
            }

        }

        }
}
