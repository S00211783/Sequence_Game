package com.example.sequencegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;


public class QuadrantView extends View {

    private Paint paint;
    private int[] colors;

    public QuadrantView(Context context) {
        super(context);
        init();
    }

    public QuadrantView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuadrantView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        colors = new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW}; // Set default colors
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // Rotate the canvas to make the square look like a diamond
        canvas.rotate(45, width / 2f, height / 2f);

        // Draw the square with four rotated quadrants
        int quadrantSize = Math.min(width, height) / 2;

        paint.setColor(colors[0]);
        canvas.drawRect(new Rect(0, 0, quadrantSize, quadrantSize), paint);

        paint.setColor(colors[1]);
        canvas.drawRect(new Rect(quadrantSize, 0, 2 * quadrantSize, quadrantSize), paint);

        paint.setColor(colors[2]);
        canvas.drawRect(new Rect(0, quadrantSize, quadrantSize, 2 * quadrantSize), paint);

        paint.setColor(colors[3]);
        canvas.drawRect(new Rect(quadrantSize, quadrantSize, 2 * quadrantSize, 2 * quadrantSize), paint);
    }


    public void setColors(int[] colors) {
        this.colors = colors;
        invalidate(); // Request a redraw
    }
}
