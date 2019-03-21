package com.visualteknologi.validateusermvvm.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.visualteknologi.validateusermvvm.model.User;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public LiveData<User> inputUser() {
        MutableLiveData data = new MutableLiveData();
        User user = new User();
        user.setLoginBerhasil("Login Berhasil");
        data.setValue(user);

        return data;
    }
}
