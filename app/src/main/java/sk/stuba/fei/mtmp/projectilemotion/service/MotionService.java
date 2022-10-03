package sk.stuba.fei.mtmp.projectilemotion.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sk.stuba.fei.mtmp.projectilemotion.models.Motion;

public interface MotionService {

    @GET("motion/{speed}/{angle}")
    Call<List<Motion>> getResult(@Path("speed") double speed, @Path("angle") double angle);
}
