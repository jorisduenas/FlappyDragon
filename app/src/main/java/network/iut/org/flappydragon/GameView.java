package network.iut.org.flappydragon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameView extends SurfaceView implements Runnable {
    public static final long UPDATE_INTERVAL = 50; // = 20 FPS
    private SurfaceHolder holder;
    private boolean paused = true;
    private Timer timer = new Timer();
    private TimerTask timerTask;
    private Player player;
    private Background background;
    private ArrayList<Pizza> pizzas;
    private ArrayList<Macron> macrons;
    private int i = 0;

    public GameView(Context context) {
        super(context);
        player = new Player(context, this);
        background = new Background(context);
        pizzas = new ArrayList<Pizza>();
        macrons = new ArrayList<Macron>();
        holder = getHolder();
        new Thread(new Runnable() {
            @Override
            public void run() {
                GameView.this.run();
            }
        }).start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();

        //Log.i("X", event.getX() + "");
        //Log.i("Y", event.getY() + "");
        if (event.getX() < 1850 & event.getX() > 1750 & event.getY() < 950 & event.getY() > 850  & event.getAction() == MotionEvent.ACTION_DOWN) {
            createPizza();
        }

        else if (event.getX() < 1850 & event.getX() > 1750 & event.getY() < 150 & event.getY() > 50) {
            paused = true;
            stopTimer();
        }
        else if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(paused) {
                resume();
            } else {
                Log.i("PLAYER", "PLAYER TAPPED");
                this.player.onTap();
            }
        }
        return true;
    }

    private void createPizza(){
        Pizza pizza = new Pizza(getContext(), this.player.getY());
        this.pizzas.add(pizza);
        Log.i("pizzas", pizzas + "");
    }

    private void createMacron(boolean positif){
        Random r = new Random();
        int y = 0;
        if (positif) {
            y = this.player.getY() + r.nextInt(300-10);
        } else {
            y = this.player.getY() - r.nextInt(300-10);
        }
        Macron macron = new Macron(getContext(), y);
        this.macrons.add(macron);
    }

    private void resume() {
        paused = false;
        startTimer();
    }

    private void restart() {

    }

    private void startTimer() {
        Log.i("TIMER", "START TIMER");
        setUpTimerTask();
        timer = new Timer();
        timer.schedule(timerTask, UPDATE_INTERVAL, UPDATE_INTERVAL);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        if (timerTask != null) {
            timerTask.cancel();
        }
    }

    private void setUpTimerTask() {
        stopTimer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                GameView.this.run();
            }
        };
    }

    @Override
    public void run() {
        player.move();
        if (pizzas != null){
            for (Pizza pizza : this.pizzas){
                pizza.move();
            }
        }
        if (macrons != null){
            for (Macron macron : this.macrons){
                macron.move();
            }
        }
        this.collisionPizzaMacron();
        this.collisionDragon();
        i += 1;
        if (i == 10) {
            GameView.this.createMacron(false);
        } else if (i == 20) {
            GameView.this.createMacron(true);
            i = 0;
        }

        draw();
    }
    private void collisionPizzaMacron(){
        ArrayList<Pizza> removePizzas = new ArrayList<>();
        ArrayList<Macron> removeMacrons = new ArrayList<>();
        if(this.macrons != null && this.pizzas != null){
            for (Macron macron : this.macrons){
                for (Pizza pizza : this.pizzas){
                    boolean coliX = false;
                    boolean coliY = false;
                    int xPizza = pizza.getX();
                    int xMacron = macron.getX();
                    int yPizza = pizza.getY();
                    int yMacron = macron.getY();
                    boolean xPizzaMoreFar = xPizza >= xMacron;
                    boolean yPizzaMoreFar = yPizza >= yMacron;

                    coliX = xPizzaMoreFar ? xMacron + macron.getWidth() >= xPizza : xPizza + pizza.getWidth() >= xMacron;

                    coliY = yPizzaMoreFar ? yMacron + macron.getHeight() >= yPizza : yPizza + pizza.getHeight() >= yMacron;

                    if (coliX && coliY) {
                        removeMacrons.add(macron);
                        removePizzas.add(pizza);
                        //colision
                    }
                }
            }
            macrons.removeAll(removeMacrons);
            pizzas.removeAll(removePizzas);
        }
    }

    private void collisionDragon(){
        ArrayList<Macron> removeMacrons = new ArrayList<>();
        if(this.macrons != null && this.pizzas != null){
            for (Macron macron : this.macrons){
                boolean coliX = false;
                boolean coliY = false;
                int xPlayer = player.getX();
                int xMacron = macron.getX();
                int yPlayer = player.getY();
                int yMacron = macron.getY();
                boolean xPizzaMoreFar = xPlayer >= xMacron;
                boolean yPizzaMoreFar = yPlayer >= yMacron;

                coliX = xPizzaMoreFar ? xMacron + macron.getWidth() >= xPlayer : xPlayer + player.getWidth() >= xMacron;

                coliY = yPizzaMoreFar ? yMacron + macron.getHeight() >= yPlayer : yPlayer + player.getHeight() >= yMacron;

                if (coliX && coliY) {
                    restart();
                }
            }

        }
    }

    private void draw() {
        while(!holder.getSurface().isValid()){
            /*wait*/
            try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            drawCanvas(canvas);
        }
        try {
            holder.unlockCanvasAndPost(canvas);
        } catch (IllegalStateException e) {
            // Already unlocked
        }
    }

    private void drawCanvas(Canvas canvas) {

        background.draw(canvas, 20);
        player.draw(canvas);

        if (pizzas != null) {
            for (Pizza pizza : this.pizzas){
                pizza.draw(canvas);
            }
        }
        if(macrons != null){
            for (Macron macron : this.macrons){
                macron.draw(canvas);
            }
        }
        if (paused) {
            canvas.drawText("PAUSED", canvas.getWidth() / 2, canvas.getHeight() / 2, new Paint());
        }
    }

}
