package com.example.chatroom.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.chatroom.model.Token
import com.example.chatroom.model.User
import com.example.chatroom.services.loginService.LoginApiService
import com.example.chatroom.view.fragments.LoginFragmentDirections
import com.example.marketmock.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class LoginViewModel(application: Application) : BaseViewModel(application) {
    private val TAG: String = "tag login view model : "
    private lateinit var view: View

    val service = LoginApiService()
    val disposable = CompositeDisposable()
    private val context = getApplication<Application>().applicationContext

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun login(view: View) {
        this.view = view as Button
        validation()
    }

    private fun validation() {
        disposable.add(
            service.login(username.value!!, password.value!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response<Token>>() {
                    override fun onSuccess(response: Response<Token>) {
                        if (response.isSuccessful) {
                            navigateToHome()
                            Log.d(TAG, response.body()?.token.toString())
                        } else {

                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "error")
                    }
                })
        )
    }

    private fun navigateToHome() {
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        Navigation.findNavController(view).navigate(action)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}