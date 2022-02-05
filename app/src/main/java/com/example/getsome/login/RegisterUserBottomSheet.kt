package com.example.getsome.login

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.getsome.R
import com.example.getsome.databinding.RegisterNewUserBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth


/// this is the bottom sheet used to help user to get register  ///

class RegisterUserBottomSheet : BottomSheetDialogFragment() {


    lateinit var viewBinding:RegisterNewUserBottomsheetBinding
    var firebaseAuth = FirebaseAuth.getInstance()
    var bottomSheetlister: BottomsheetListner? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
         viewBinding = RegisterNewUserBottomsheetBinding.bind(inflater.inflate(R.layout.register_new_user_bottomsheet
         ,container,false))
             viewBinding.registerBtn.setOnClickListener(View.OnClickListener {
                 Log.d("BottomSheet","Clicked")
                   viewBinding.registerBtn.startLoading()
          viewBinding.registerBtn.isClickable = false
               newAccountcreater()
             })

        return viewBinding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        bottomSheetlister = if (context is BottomsheetListner) {
            context
        } else {
            throw RuntimeException(
                context.toString() + "Method Must Implemented"
            )
        }
    }

    interface BottomsheetListner{
        fun onclicklistnerBS(value:String)
    }

       // help to create a new account of a user  //
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
                     viewBinding.registerBtn.doResult(isSucceed = true)
                     handler.postDelayed(Runnable { // Do something after 5s = 5000ms
                         bottomSheetlister?.onclicklistnerBS("yes")
                     }, 2000)
                 }else{
                    Log.d("EX",it.exception.toString())
                 }
            }

        }

        }
    override fun getTheme() = R.style.CustomBottomSheetDialogTheme
}
