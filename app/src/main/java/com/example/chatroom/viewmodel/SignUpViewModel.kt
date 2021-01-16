package com.example.chatroom.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.chatroom.model.User
import com.example.chatroom.services.signUpService.SignUpApiService
import com.example.chatroom.view.fragments.SignUpFragmentDirections
import com.example.marketmock.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class SignUpViewModel(application: Application) : BaseViewModel(application) {

    private val TAG = "tag : SignUp view model"
    private lateinit var view: Button
    private val context = getApplication<Application>().applicationContext
    val loading = MutableLiveData<Boolean>()

    private val signUpService = SignUpApiService()
    private val disposable = CompositeDisposable()

    private val user = MutableLiveData<User>()

    val username = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun onSignUpClicked(view: View) {
        this.view = view as Button
        user.value = User(
            username.value.toString(),
            email.value.toString(),
            password.value.toString()
        )
        createUserRequest()
    }

    private fun createUserRequest() {
        loading.value = true
        disposable.add(
            signUpService.singUp(user.value!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response<User>>() {
                    override fun onSuccess(response: Response<User>) {
                        if (response.isSuccessful) {
                            navigateToLogin()
                            Toast.makeText(
                                context,
                                "ثبت نام با موفقیت انجام شد ، لطفا وارد حساب کاربری خود شوید",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {

                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "error")
                    }
                })
        )
    }

    private fun navigateToLogin() {
        val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
        Navigation.findNavController(view).navigate(action)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}