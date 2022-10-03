package sk.stuba.fei.mtmp.projectilemotion.service;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sk.stuba.fei.mtmp.projectilemotion.activities.MotionDataActivity;
import sk.stuba.fei.mtmp.projectilemotion.models.Motion;

public class HTTPService {

    private final MotionService motionService;
    private List<Motion> motions;
    private Context context;

    public HTTPService(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://147.175.176.199:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        this.context = context;
        motionService = retrofit.create(MotionService.class);
    }

    public void getResult(double speed, double angle) {
        Call<List<Motion>> result = motionService.getResult(speed, angle);
        result.enqueue(new Callback<List<Motion>>() {
            @Override
            public void onResponse(Call<List<Motion>> call, Response<List<Motion>> response) {
                motions = response.body();
                Intent intent = new Intent(context, MotionDataActivity.class);
                intent.putParcelableArrayListExtra("motion_list", (ArrayList<? extends Parcelable>) motions);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<Motion>> call, Throwable t) {
                Log.e("result", t.getMessage());
                result.cancel();
            }
        });
    }

    public List<Motion> getMotions() {
        return motions;
    }

    public void setMotions(List<Motion> motions) {
        this.motions = motions;
    }
}
