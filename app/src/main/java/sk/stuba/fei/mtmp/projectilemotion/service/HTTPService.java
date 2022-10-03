package sk.stuba.fei.mtmp.projectilemotion.service;

import android.app.AlertDialog;
import android.content.Context;
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
    private MutableLiveData<List<Motion>> motions;
    private Context context;

    public HTTPService(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://147.175.176.199:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        this.context = context;
        motionService = retrofit.create(MotionService.class);
        motions = new MutableLiveData<>();
    }

    public MutableLiveData<List<Motion>> getResult(double speed, double angle, Context context) {

        AlertDialog alertDialog = new AlertDialog.Builder(context)
            .setTitle("Loading")
            .setMessage("Downloading data")
            .show();

        Call<List<Motion>> result = motionService.getResult(speed, angle);
        result.enqueue(new Callback<List<Motion>>() {
            @Override
            public void onResponse(Call<List<Motion>> call, Response<List<Motion>> response) {

                motions.postValue(response.body());
                alertDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Motion>> call, Throwable t) {
                Log.e("result", t.getMessage());
                alertDialog.dismiss();
                result.cancel();
            }
        });

        return motions;
    }

    public MutableLiveData<List<Motion>> getMotions() {
        return motions;
    }

    public void setMotions(MutableLiveData<List<Motion>> motions) {
        this.motions = motions;
    }
}
