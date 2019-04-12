package network.iut.org.flappydragon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class Macron {
    public static Bitmap globalBitmap;
    private final Bitmap bitmap;
    private final byte frameTime;
    private int frameTimeCounter;
    private final int width;
    private final int height;
    private int x;
    private int y;
    private float speedX;
    private float speedY;
    private Context context;

    public Macron(Context context, int y) {

        this.context = context;

        int height = context.getResources().getDisplayMetrics().heightPixels;
        int width = context.getResources().getDisplayMetrics().widthPixels;

        if(globalBitmap == null) {
            Log.e("TEST", "Height : " + height + ", width : " + width);
            globalBitmap = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.gilet_jaune, Float.valueOf(height / 10f).intValue(), Float.valueOf(width / 10f).intValue());
        }
        this.bitmap = globalBitmap;
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
        this.frameTime = 1;		// the frame will change every 3 runs
        this.y = y;

        this.x = width;
    }

    public void move() {
        changeToNextFrame();

        this.speedX = -50;
        this.speedY = 0;
        this.x += speedX;
        this.y += speedY;
    }

    protected void changeToNextFrame(){
        this.frameTimeCounter++;
        if(this.frameTimeCounter >= this.frameTime){
            //TODO Change frame
            this.frameTimeCounter = 0;
        }
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() { return this.x; }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y , null);
    }
}
