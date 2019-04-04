package network.iut.org.flappydragon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Background {
    private int height;
    private int width;
    private Bitmap backgroundtest;
    private Bitmap background1;
    private Bitmap background2;
    private Bitmap background3;
    private Bitmap background4;
    private Bitmap background5;

    public Background(Context context) {
        height = context.getResources().getDisplayMetrics().heightPixels;
        width = context.getResources().getDisplayMetrics().widthPixels;
        backgroundtest = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layertest, width, height);
        Log.d("TEST", "h:"+height);
        Log.d("TEST", "w:"+width);
        /*background1 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer1, width, height);
        background2 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer2, width, height);
        background3 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer3, width, height);
        background4 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer4, width, height);
        background5 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer5, width, height);*/
    }

    public void draw(Canvas canvas) {
        if(height > width) {
            canvas.drawBitmap(backgroundtest, new Rect(0, 0, width, backgroundtest.getHeight()), new Rect(0, 0, width, height), null);
        } else {
            canvas.drawBitmap(backgroundtest, new Rect(0, 0, width + height, backgroundtest.getHeight()), new Rect(0, 0, width, height), null);
        }

        /*canvas.drawBitmap(background1, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background2, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background3, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background4, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background5, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);*/
    }
}
