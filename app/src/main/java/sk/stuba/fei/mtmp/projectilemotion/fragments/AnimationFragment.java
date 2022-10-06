package sk.stuba.fei.mtmp.projectilemotion.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sk.stuba.fei.mtmp.projectilemotion.MainViewModel;
import sk.stuba.fei.mtmp.projectilemotion.R;
import sk.stuba.fei.mtmp.projectilemotion.views.AnimatedView;


public class AnimationFragment extends Fragment {

    private AnimatedView animatedView;
    private MainViewModel mainViewModel;

    public AnimationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_animation, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        animatedView = inflate.findViewById(R.id.animated_view);
        animatedView.setMotions(mainViewModel.getMotions());

        return inflate;
    }
}
