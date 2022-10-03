package sk.stuba.fei.mtmp.projectilemotion;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import sk.stuba.fei.mtmp.projectilemotion.models.Motion;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Motion>> motions;

    public MutableLiveData<List<Motion>> getMotions() {
        return motions;
    }

    public void setMotions(MutableLiveData<List<Motion>> motions) {
        this.motions = motions;
    }
}
