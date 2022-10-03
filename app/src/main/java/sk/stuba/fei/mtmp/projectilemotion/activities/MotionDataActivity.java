package sk.stuba.fei.mtmp.projectilemotion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import sk.stuba.fei.mtmp.projectilemotion.R;
import sk.stuba.fei.mtmp.projectilemotion.adapters.MotionDataAdapter;
import sk.stuba.fei.mtmp.projectilemotion.models.Motion;

public class MotionDataActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_data);

        floatingActionButton = findViewById(R.id.simulate_button);

        Bundle bundle = getIntent().getExtras();
        List<Motion> motions = bundle.getParcelableArrayList("motion_list");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        MotionDataAdapter motionDataAdapter = new MotionDataAdapter(motions);
        recyclerView = findViewById(R.id.motion_data_list);
        recyclerView.setAdapter(motionDataAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotionDataActivity.this, ChartActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("motion_list", (ArrayList<? extends Parcelable>) motions);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        scrollListener();
    }

    private void scrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    floatingActionButton.show();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 || dy < 0 && floatingActionButton.isShown()) {
                    floatingActionButton.hide();
                }
            }
        });
    }
}
