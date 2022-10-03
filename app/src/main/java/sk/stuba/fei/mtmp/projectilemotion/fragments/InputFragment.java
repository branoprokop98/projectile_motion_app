package sk.stuba.fei.mtmp.projectilemotion.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

import sk.stuba.fei.mtmp.projectilemotion.MainViewModel;
import sk.stuba.fei.mtmp.projectilemotion.R;
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
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        serverButton = inflate.findViewById(R.id.server_button);
        localButton = inflate.findViewById(R.id.local_button);
        speedEditText = inflate.findViewById(R.id.speed_edit_text);
        angleEditText = inflate.findViewById(R.id.angle_edit_text);

        localButton.setOnClickListener(view -> {
            Computation computation = new Computation(Double.parseDouble(speedEditText.getText().toString()), Double.parseDouble(angleEditText.getText().toString()));
            mainViewModel.setMotions(computation.getResult());
            Navigation.findNavController(view).navigate(R.id.action_inputFragment_to_listFragment);
        });

        serverButton.setOnClickListener(view -> {
            HTTPService httpService = new HTTPService(getContext());
            httpService.getResult(Double.parseDouble(speedEditText.getText().toString()), Double.parseDouble(angleEditText.getText().toString()), view, mainViewModel);
//            mainViewModel.setMotions(httpService.getMotions());
        });

        return inflate;
    }
}
