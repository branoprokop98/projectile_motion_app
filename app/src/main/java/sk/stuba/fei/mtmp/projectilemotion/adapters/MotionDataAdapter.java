package sk.stuba.fei.mtmp.projectilemotion.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import sk.stuba.fei.mtmp.projectilemotion.R;
import sk.stuba.fei.mtmp.projectilemotion.models.Motion;

public class MotionDataAdapter extends RecyclerView.Adapter<MotionDataAdapter.ViewHolder> {

    private List<Motion> data;

    public MotionDataAdapter(List<Motion> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.motion_data_row, parent, false);

        return new ViewHolder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DecimalFormat df = new DecimalFormat("###.##");
        DecimalFormat formatTime = new DecimalFormat("###.###");
        holder.x.setText("X: " + df.format(data.get(position).getX()));
        holder.y.setText("Y: " + df.format(data.get(position).getY()));
        holder.time.setText("t: " + formatTime.format(data.get(position).getTime()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView x;
        private final TextView y;
        private final TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            x = itemView.findViewById(R.id.text_x);
            y = itemView.findViewById(R.id.text_y);
            time = itemView.findViewById(R.id.text_time);
        }
    }
}
