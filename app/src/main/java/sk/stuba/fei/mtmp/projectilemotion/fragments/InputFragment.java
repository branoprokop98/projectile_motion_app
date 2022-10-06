package sk.stuba.fei.mtmp.projectilemotion.fragments;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import sk.stuba.fei.mtmp.projectilemotion.MainViewModel;
import sk.stuba.fei.mtmp.projectilemotion.R;
import sk.stuba.fei.mtmp.projectilemotion.models.Motion;
import sk.stuba.fei.mtmp.projectilemotion.service.HTTPService;
import sk.stuba.fei.mtmp.projectilemotion.utils.Computation;

public class InputFragment extends Fragment {
    private AppCompatButton localButton;
    private TextInputEditText speedEditText;
    private TextInputEditText angleEditText;
    private AppCompatButton serverButton;
    private Computation computation;

    private MainViewModel mainViewModel;

    public InputFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_input, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        serverButton = inflate.findViewById(R.id.server_button);
        localButton = inflate.findViewById(R.id.local_button);
        speedEditText = inflate.findViewById(R.id.speed_edit_text);
        angleEditText = inflate.findViewById(R.id.angle_edit_text);

        angleEditTextChangeListener();
        startComputation();

//        InputFragmentDirections.ActionInputFragmentToListFragment test = InputFragmentDirections.actionInputFragmentToListFragment().setTest(50);


        return inflate;
    }

    private void startComputation() {

        localButton.setOnClickListener(view -> {
            if (checkInput()) return;
            Computation computation = new Computation(Double.parseDouble(speedEditText.getText().toString()), Double.parseDouble(angleEditText.getText().toString()));
            MutableLiveData<List<Motion>> motions = new MutableLiveData<>();
            motions.postValue(computation.getResult());
            mainViewModel.setMotions(motions);
            Navigation.findNavController(view).navigate(R.id.action_inputFragment_to_listFragment);
        });

        serverButton.setOnClickListener(view -> {
            if (checkInput()) return;
            HTTPService httpService = new HTTPService(getContext());
            MutableLiveData<List<Motion>> result = httpService.getResult(Double.parseDouble(speedEditText.getText().toString()), Double.parseDouble(angleEditText.getText().toString()), getContext());
            mainViewModel.setMotions(result);
            Navigation.findNavController(view).navigate(R.id.action_inputFragment_to_listFragment);
        });
    }

    private boolean checkInput() {
        return speedEditText.getText().toString().isEmpty() || angleEditText.getText().toString().isEmpty();
    }

    private void angleEditTextChangeListener() {
        GradientDrawable drawable = (GradientDrawable) angleEditText.getBackground();
        angleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    return;
                }

                double angle = Double.parseDouble(editable.toString());
                if (angle > 90 || angle < 0) {
                    drawable.setStroke(5, Color.RED);
                    angleEditText.setBackground(drawable);
                    localButton.setEnabled(false);
                    serverButton.setEnabled(false);
                } else {
                    drawable.setStroke(5, Color.parseColor("#D3D3D3"));
                    angleEditText.setBackground(drawable);
                    localButton.setEnabled(true);
                    serverButton.setEnabled(true);
                }
            }
        });
    }

}
