package com.stanislavmikheyev.raincanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class RainView extends View {

    private Paint paint = new Paint();

    private ArrayList<RainDrop> rainDropsList;
    private int rainDropsSpeed;
    private int rainDropsSpawnRate;
    private int time = 0;
    private float rainDropLength = 50f;
    private Random random = new Random();

    public RainView(Context context) {
        super(context);
        init(null, 0);
    }

    public RainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public RainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        paint.setAntiAlias(true);
        rainDropsList = new ArrayList<RainDrop>();
        rainDropsSpeed = 30;
        rainDropsSpawnRate = 2;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), paint);

        if (time <= 0) {
            time = rainDropsSpawnRate;
            RainDrop rainDrop = new RainDrop();
            rainDrop.setX(random.nextInt(this.getWidth()));
            rainDrop.setY(0);
            rainDrop.setColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            rainDropsList.add(rainDrop);
        }
        time--;

        for (RainDrop rainDrop : rainDropsList) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(rainDrop.getColor());
            paint.setStrokeWidth(10);
            canvas.drawLine(
                    rainDrop.getX(), rainDrop.getY(),
                    rainDrop.getX(), rainDrop.getY() - rainDropLength,
                    paint);
            rainDrop.setY(rainDrop.getY() + rainDropsSpeed);
        }
        Iterator<RainDrop> it = rainDropsList.iterator();
        while (it.hasNext()) {
            RainDrop rainDrop = it.next();
            if (rainDrop.getY() > this.getHeight()) {
                it.remove();
            }
        }

    }
}
