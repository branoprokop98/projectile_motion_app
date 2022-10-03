package sk.stuba.fei.mtmp.projectilemotion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import sk.stuba.fei.mtmp.projectilemotion.R;
import sk.stuba.fei.mtmp.projectilemotion.models.Motion;
import sk.stuba.fei.mtmp.projectilemotion.views.AnimatedView;

public class AnimationActivity extends AppCompatActivity {

    private AnimatedView animatedView;
    private List<Motion> motions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        Bundle bundle = getIntent().getExtras();
        motions = bundle.getParcelableArrayList("motion_list");

        animatedView = findViewById(R.id.animated_view);
        animatedView.setMotions(motions);
    }
}
