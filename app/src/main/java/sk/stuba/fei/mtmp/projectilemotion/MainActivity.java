package sk.stuba.fei.mtmp.projectilemotion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import sk.stuba.fei.mtmp.projectilemotion.activities.MotionDataActivity;
import sk.stuba.fei.mtmp.projectilemotion.models.Motion;
import sk.stuba.fei.mtmp.projectilemotion.service.HTTPService;
import sk.stuba.fei.mtmp.projectilemotion.utils.Computation;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText speedEditText;
    private TextInputEditText angleEditText;
    private AppCompatButton localButton;
    private AppCompatButton serverButton;
    private Computation computation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speedEditText = findViewById(R.id.speed_edit_text);
        angleEditText = findViewById(R.id.angle_edit_text);
        localButton = findViewById(R.id.local_button);
        serverButton = findViewById(R.id.server_button);


        localButton.setOnClickListener(view -> {

            Intent intent = new Intent(this, MotionDataActivity.class);

            if (angleEditText.getText().toString().length() == 0 || speedEditText.getText().toString().length() == 0) {
                Toast.makeText(this,
                    "Invalid input",
                    Toast.LENGTH_SHORT).show();
                return;
            }
            computation = new Computation(Double.parseDouble(speedEditText.getText().toString()), Double.parseDouble(angleEditText.getText().toString()));
            List<Motion> motions = computation.getResult();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("motion_list", (ArrayList<? extends Parcelable>) motions);

            Toast.makeText(this,
                "Speed: " + speedEditText.getText() + ", Angle: " + angleEditText.getText(),
                Toast.LENGTH_SHORT).show();
            intent.putExtras(bundle);
            startActivity(intent);

        });

        serverButton.setOnClickListener(view -> {
            HTTPService httpService = new HTTPService(this);
            httpService.getResult(Double.parseDouble(speedEditText.getText().toString()), Double.parseDouble(angleEditText.getText().toString()));
            List<Motion> motions = httpService.getMotions();
        });
    }
}
