package sk.stuba.fei.mtmp.projectilemotion.service;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sk.stuba.fei.mtmp.projectilemotion.models.Motion;

public class MotionRepository {

    private final MutableLiveData<List<Motion>> motions = new MutableLiveData<>();

    public MutableLiveData<List<Motion>> getResults(double speed, double angle) {
        HTTPService httpService = new HTTPService();

        Call<List<Motion>> results = httpService.getMotionService().getResult(speed, angle);
        results.enqueue(new Callback<List<Motion>>() {
            @Override
            public void onResponse(@NonNull Call<List<Motion>> call, @NonNull Response<List<Motion>> response) {
                motions.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Motion>> call, @NonNull Throwable t) {
                Log.e("result", t.getMessage());
            }
        });

        return motions;
    }

}
