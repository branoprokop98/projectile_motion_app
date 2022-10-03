package sk.stuba.fei.mtmp.projectilemotion.service;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.navigation.Navigation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sk.stuba.fei.mtmp.projectilemotion.MainViewModel;
import sk.stuba.fei.mtmp.projectilemotion.R;
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

    public void getResult(double speed, double angle, View view, MainViewModel mainViewModel) {
        Call<List<Motion>> result = motionService.getResult(speed, angle);
        result.enqueue(new Callback<List<Motion>>() {
            @Override
            public void onResponse(Call<List<Motion>> call, Response<List<Motion>> response) {

                Navigation.findNavController(view).navigate(R.id.action_inputFragment_to_listFragment);

                mainViewModel.setMotions(response.body());
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
