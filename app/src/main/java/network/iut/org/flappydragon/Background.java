package network.iut.org.flappydragon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background {
    private int height;
    private int width;
    private Bitmap background1;
    private Bitmap background2;
    private Bitmap background3;
    private Bitmap background4;
    private Bitmap background5;
    private Bitmap pause;
    private Bitmap BoutonTire;

    public Background(Context context) {
        height = context.getResources().getDisplayMetrics().heightPixels;
        width = context.getResources().getDisplayMetrics().widthPixels;
        background1 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer1, width, height);
        background2 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer2, width, height);
        background3 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer3, width, height);
        background4 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer4, width, height);
        background5 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer5, width, height);
        pause = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.pause, width, height);
        BoutonTire = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.pizza, width, height);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(background1, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background2, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background3, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background4, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background5, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(pause, new Rect(0, 0, pause.getWidth(), pause.getHeight()), new Rect(1750, 50, 1850, 150), null);
        canvas.drawBitmap(BoutonTire, new Rect(0, 0, BoutonTire.getWidth(), BoutonTire.getHeight()), new Rect(1750, 850, 1850, 950), null);
    }
}
