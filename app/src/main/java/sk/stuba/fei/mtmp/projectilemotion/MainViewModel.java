package sk.stuba.fei.mtmp.projectilemotion;

import androidx.lifecycle.ViewModel;

import java.util.List;

import sk.stuba.fei.mtmp.projectilemotion.models.Motion;

public class MainViewModel extends ViewModel {

    private List<Motion> motions;

    public List<Motion> getMotions() {
        return motions;
    }

    public void setMotions(List<Motion> motions) {
        this.motions = motions;
    }
}
