package sk.stuba.fei.mtmp.projectilemotion.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.LiveData;

import java.util.List;

import sk.stuba.fei.mtmp.projectilemotion.R;
import sk.stuba.fei.mtmp.projectilemotion.models.Motion;

public class AnimatedView extends AppCompatImageView {

    private Context mContext;
    private Handler h;
    private final int FRAME_RATE = 30;
    private List<Motion> motions;
    private int i;

    public List<Motion> getMotions() {
        return motions;
    }

    public void setMotions(List<Motion> motions) {
        this.motions = motions;
    }


    public AnimatedView(@NonNull Context context) {
        super(context);
        initView(context, null);
    }

    public AnimatedView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public AnimatedView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        h = new Handler();
        i = 0;
        mContext = context;
    }

    private Runnable r = this::invalidate;

    protected void onDraw(Canvas c) {
        BitmapDrawable ball = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.ball);
        c.scale(1f, -1f, this.getWidth() / 2f, this.getHeight() / 2f);
        if (i < motions.size()) {
            c.drawBitmap(ball.getBitmap(), (float) motions.get(i).getX() * 10, (float) motions.get(i).getY() * 10, null);
        } else {
            c.drawBitmap(ball.getBitmap(), (float) motions.get(motions.size() - 1).getX() * 10, (float) motions.get(motions.size() - 1).getY() * 10, null);
        }
        i++;
        h.postDelayed(r, FRAME_RATE);
    }
}
