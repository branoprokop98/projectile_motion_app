package sk.stuba.fei.mtmp.projectilemotion.service;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sk.stuba.fei.mtmp.projectilemotion.MainViewModel;
import sk.stuba.fei.mtmp.projectilemotion.models.Motion;

public class HTTPService {

    private final MotionService motionService;

    public HTTPService() {
        Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("http://147.175.176.199:8080/")
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        motionService = retrofit.create(MotionService.class);
    }


    public MotionService getMotionService() {
        return motionService;
    }
}
