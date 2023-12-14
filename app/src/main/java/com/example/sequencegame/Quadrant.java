package com.example.sequencegame;

// Quadrant.java
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class Quadrant extends View {

    private Paint paint;
    private int color;
    private OnClickListener quadrantClickListener;

    public Quadrant(Context context) {
        super(context);
        init();
    }

    public Quadrant(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Quadrant(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        color = Color.RED; // Set a default color

        quadrantClickListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click for the quadrant
                // You can add logic here if needed
            }
        };

        setOnClickListener(quadrantClickListener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the quadrant with the specified color
        paint.setColor(color);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }

    public void setColor(int color) {
        this.color = color;
        invalidate(); // Request a redraw
    }

    public void setQuadrantClickListener(OnClickListener listener) {
        setOnClickListener(listener);
    }
}
