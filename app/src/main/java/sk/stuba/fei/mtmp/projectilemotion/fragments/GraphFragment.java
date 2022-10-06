package sk.stuba.fei.mtmp.projectilemotion.fragments;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import sk.stuba.fei.mtmp.projectilemotion.MainViewModel;
import sk.stuba.fei.mtmp.projectilemotion.R;


public class GraphFragment extends Fragment {

    private MainViewModel mainViewModel;
    FloatingActionButton floatingActionButton;
    LineChart volumeReportChart;

    public GraphFragment() {
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
        View inflate = inflater.inflate(R.layout.fragment_graph, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        volumeReportChart = inflate.findViewById(R.id.reportingChart);
        floatingActionButton = inflate.findViewById(R.id.animation_button);
        volumeReportChart.setTouchEnabled(true);
        volumeReportChart.setPinchZoom(true);

        LimitLine ll1 = new LimitLine(30f, "Title");
        ll1.setLineColor(R.color.purple_500);
        ll1.setLineWidth(5f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll1.setTextSize(10f);

        XAxis xAxis = volumeReportChart.getXAxis();

        XAxis.XAxisPosition position = XAxis.XAxisPosition.BOTTOM;
        xAxis.setPosition(position);

        volumeReportChart.getDescription().setEnabled(false);

        ArrayList<Entry> values = new ArrayList<>();

        mainViewModel.getMotions().getValue().forEach(f -> {
            values.add(new Entry((float) f.getTime(), (float) f.getY()));
        });


        LineDataSet set1;
        if (volumeReportChart.getData() != null &&
            volumeReportChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) volumeReportChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            volumeReportChart.getData().notifyDataChanged();
            volumeReportChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, "Total volume");
            set1.setDrawCircles(false);
            set1.enableDashedLine(3f, 0f, 0f);
            set1.enableDashedHighlightLine(3f, 0f, 0f);
            set1.setColor(R.color.purple_500);
            set1.setCircleColor(R.color.purple_200);
            set1.setLineWidth(5f);//line size
            set1.setCircleRadius(5f);
            set1.setValueTextSize(10f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(5f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{3f, 1f}, 0f));
            set1.setFormSize(5.f);

            if (Utils.getSDKInt() >= 18) {
                set1.setFillColor(Color.WHITE);

            } else {
                set1.setFillColor(Color.WHITE);
            }
            set1.setDrawValues(false);
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            LineData data = new LineData(dataSets);

            volumeReportChart.setData(data);
        }

        floatingActionButton.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_graphFragment_to_animationFragment);
        });


        return inflate;
    }
}
