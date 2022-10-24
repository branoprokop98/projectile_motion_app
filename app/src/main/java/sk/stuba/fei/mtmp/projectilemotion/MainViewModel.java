package sk.stuba.fei.mtmp.projectilemotion;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import sk.stuba.fei.mtmp.projectilemotion.models.Motion;
import sk.stuba.fei.mtmp.projectilemotion.service.MotionRepository;
import sk.stuba.fei.mtmp.projectilemotion.utils.Computation;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Motion>> motions;

    public MutableLiveData<List<Motion>> getMotions() {
        return motions;
    }

    public void setMotions(MutableLiveData<List<Motion>> motions) {
        this.motions = motions;
    }

    public void computeMotionsLocal(double speed, double angle) {
        Computation computation = new Computation(speed, angle);
        motions = new MutableLiveData<>();
        motions.postValue(computation.getResult());
    }

    public MutableLiveData<List<Motion>> computeMotionsApi(double speed, double angle) {
        MotionRepository motionRepository = new MotionRepository();
        motions = motionRepository.getResults(speed, angle);
        return motionRepository.getResults(speed, angle);
    }
}
