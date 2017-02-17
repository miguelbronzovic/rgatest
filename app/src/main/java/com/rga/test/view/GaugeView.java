package com.rga.test.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.util.AttributeSet;
import android.view.View;

import com.rga.test.R;

/**
 * Created by Miguel Bronzovic.
 */
public final class GaugeView extends View {
    private final static float DEGREES_90 = 90;
    private final static float DEGREE_START = 5;

    private Path path;
    private int viewWidth, viewHeight;
    private Bitmap bitmap, bitmap2;
    private int bitmapWidth, bitmapHeight;

    private float angleStart;
    private float sweep;
    private boolean reset = false;

    public GaugeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        initialize();
    }

    private void initialize() {
        path = new Path();
        angleStart = DEGREES_90;
        sweep = DEGREE_START;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.daily_goal_color_meter);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.daily_goal_ring_blur);
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();
        this.resetGauge();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        viewWidth = this.getMeasuredWidth();
        viewHeight = this.getMeasuredHeight();

        canvas.drawBitmap(bitmap2, (viewWidth - bitmapWidth) / 2, (viewHeight - bitmapHeight) / 2, null);
        //create a path to clip the images
        //move into center of the circle
        path.moveTo(viewWidth / 2, viewHeight / 2);
        //add line from center to arc with specified angle
        path.lineTo((float)Math.cos(Math.toRadians(angleStart)) * (viewWidth / 2),
                (float)Math.sin(Math.toRadians(angleStart)) * (viewHeight / 2));
        //add arc from angleStart with specified sweep
        path.addArc(new RectF((viewWidth - bitmapWidth) / 2, (viewHeight - bitmapHeight) / 2,
                viewWidth - (viewWidth - bitmapWidth) / 2, viewHeight - (viewHeight - bitmapHeight) / 2), angleStart, sweep);
        //add line from end of arc to the center of circle
        path.lineTo(viewWidth / 2, viewHeight / 2);
        //set the clip, HERE IS THE MAGIC!!!
        canvas.clipPath(path, Op.REPLACE);
        canvas.drawBitmap(bitmap, (viewWidth - bitmapWidth) / 2, (viewHeight - bitmapHeight) / 2, null);
        if (reset) {
            path.reset();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * Sets the gauge value indicator. </br>
     *
     * @param value
     */
    public void setGaugeValue(float value) {
        sweep = value;
        invalidate();
    }

    /**
     * Resets the gauge indicator
     */
    public void resetGauge() {
        angleStart = DEGREES_90;
        sweep = DEGREE_START;
        reset = true;
        invalidate();
    }
}
