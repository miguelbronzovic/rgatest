package com.rga.test.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ValueAnimator;
import com.rga.test.R;
import com.rga.test.view.GaugeView;

public final class MainActivity extends AppCompatActivity {
    private final static float DEGREES_0 = 0;
    private final static float DEGREES_360 = 360;
    private final static String RGA_FONT = "fonts/AkzidGrtskProBolCnd.otf";

    private GaugeView gaugeView;
    private TextView points;
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gaugeView = (GaugeView) findViewById(R.id.gauge);

        final Typeface typeFace = Typeface.createFromAsset(getAssets(), RGA_FONT);
        points = (TextView) findViewById(R.id.lbl_points);
        points.setTypeface(typeFace);
        points.setText("1769");

        start = (Button) findViewById(R.id.btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gaugeView.resetGauge();

                final ValueAnimator animator = ValueAnimator.ofFloat(DEGREES_0, DEGREES_360);
                animator.setInterpolator(new DecelerateInterpolator(0.55f));
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        gaugeView.setGaugeValue((float) valueAnimator.getAnimatedValue());
                    }
                });
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        start.setEnabled(false);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        start.setEnabled(true);
                    }
                });
                animator.start();
            }
        });
    }
}
