package com.example.huan.bubblebattle;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.media.Image;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class GamingActivity extends AppCompatActivity {
    private FloatingActionButton fireButton;
    private ImageButton shooterButton;
    private Set<ImageView> myBubbles = new HashSet<>();
    private Set<ImageView> enemyBubbles = new HashSet<>();
    //private Handler handler = new Handler();

    /*private Runnable periodicTask = new Runnable() {
        @Override
        public void run() {
            Log.d("gaming", "periodic task");
            //set up a periodic event that checks bubble collisions
            for (final ImageView myBubble : myBubbles) {
                for (final ImageView enemyBubble : enemyBubbles) {
                    if (CheckCollision(myBubble, enemyBubble)) {
                        Log.d("xxxxxxxxxxxxxxxxxxxxxxX", "bubble collision");
                        myBubbles.remove(myBubble);
                        enemyBubbles.remove(enemyBubble);
                        if (myBubbles.isEmpty() && enemyBubbles.isEmpty()) {
                            handler.removeCallbacks(periodicTask);
                        }
                        //remove them from this activity
                        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_gaming);
                        layout.post(new Runnable() {
                            @Override
                            public void run() {
                                myBubble.animate().cancel();
                                enemyBubble.animate().cancel();
                                layout.removeView(myBubble);
                                layout.removeView(enemyBubble);
                            }
                        });
                    }
                }
            }
            handler.postDelayed(periodicTask, 10);
        }
    };*/

    public boolean CheckCollision(View v1, View v2) {
/*        int[] leftTop = new int[2];
        v1.getLocationOnScreen(leftTop);
        Rect rect = new Rect();
        v1.getLocalVisibleRect(rect);

        Rect R1=new Rect(leftTop[0], leftTop[1], leftTop[0] + v1.getWidth(), leftTop[1] + v1.getHeight());

        int[] leftTop2 = new int[2];
        v2.getLocationOnScreen(leftTop2);

        Rect R2=new Rect(leftTop2[0], leftTop2[1], leftTop2[0] + v2.getWidth(), leftTop2[1] + v2.getHeight());*/

        Rect R1=new Rect(v1.getLeft(), v1.getTop(), v1.getRight(), v1.getBottom());
        Rect R2=new Rect(v2.getLeft(), v2.getTop(), v2.getRight(), v2.getBottom());
        if (R1.intersect(R2)) {
            Log.d("XXXXXXXXXXXXXX", "" + R1.left + "," + R1.top+ "," + R1.right+ "," + R1.bottom);
            Log.d("XXXXXXXXXXXXXX", "" + R2.left + "," + R2.top+ "," + R2.right+ "," + R2.bottom);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaming);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireButton = (FloatingActionButton) findViewById(R.id.shoot);
        shooterButton = (ImageButton) findViewById(R.id.shooter);

        //add swipe gesture detector listener for shooter button
        final GestureDetector gd = new GestureDetector(GamingActivity.this, new ShooterGestureDetector());
        shooterButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gd.onTouchEvent(event);
                return true;
            }
        });

        fireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //periodicTask.run();
                //handler.postDelayed(periodicTask, 1000);
                /*
                Timer timer = new Timer();
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_gaming);
                        for (ImageView myBubble : myBubbles) {
                            for (ImageView enemyBubble : enemyBubbles) {
                                if (CheckCollision(myBubble, enemyBubble)) {
                                    Log.d("bubble", "bubble collision");
                                    myBubbles.remove(myBubble);
                                    enemyBubbles.remove(enemyBubble);

                                    //remove them from this activity
                                    //RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_gaming);
                                    myBubble.getAnimation().cancel();
                                    enemyBubble.getAnimation().cancel();
                                    //layout.removeView(myBubble);
                                    //layout.removeView(enemyBubble);
                                }
                            }
                        }

                        layout.post(new Runnable() {
                            @Override
                            public void run() {
                                GamingActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {

                                    }
                                });
                            }
                        });
                        Log.d("gaming", "periodic task");
                        //set up a periodic event that checks bubble collisions
                    }
                };*/
                //timer.schedule(tt, 50);
                startEnemyBubble();

                float x = fireButton.getX();
                float y = fireButton.getY();
                final RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_gaming);
                final ImageView myBubble = new ImageView(GamingActivity.this);
                myBubbles.add(myBubble);
                myBubble.setImageResource(R.drawable.green_bubble);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50); //bubble size
                params.leftMargin = (int) shooterButton.getX(); //positions
                params.topMargin = (int) shooterButton.getY();
                layout.addView(myBubble, params);

                final float hight = shooterButton.getY();
                //TranslateAnimation animation = new TranslateAnimation(0.0f, 0, hight, 0);
                //animation.setDuration(5000);
                //animation.setAnimationListener(new BubbleAnimationListener(myBubble, myBubbles));
                //myBubble.startAnimation(animation);

                //myBubble.animate().y(0).setDuration(5000);//.setListener(new BubbleAnimationListener(myBubble, myBubbles));

                ValueAnimator va = ValueAnimator.ofFloat(hight, 0);
                va.setDuration(5000);
                va.addUpdateListener(new ValueAnimationListener(va, myBubble, enemyBubbles));
                va.addListener(new BubbleAnimationListener(myBubble, myBubbles));
                va.start();
            }
        });
    }

    private void startEnemyBubble() {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_gaming);
        ImageView enemyBubble = new ImageView(GamingActivity.this);
        enemyBubbles.add(enemyBubble); //save the newly generated enemy bubble into the set
        enemyBubble.setImageResource(R.drawable.green_bubble);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50); //bubble size
        params.leftMargin = 50;
        params.topMargin = layout.getHeight();
        layout.addView(enemyBubble, params);

        //TranslateAnimation animation = new TranslateAnimation(50, 50, 0, layout.getHeight());
        //animation.setDuration(5000);
        //animation.setAnimationListener(new BubbleAnimationListener(enemyBubble, enemyBubbles));
        //enemyBubble.startAnimation(animation);

        //enemyBubble.animate().y(layout.getHeight()).setDuration(5000).setListener(new BubbleAnimationListener(enemyBubble, enemyBubbles));

        ValueAnimator va = ValueAnimator.ofFloat(0, layout.getHeight());
        va.setDuration(5000);
        va.addUpdateListener(new ValueAnimationListener(va, enemyBubble, myBubbles));
        va.addListener(new BubbleAnimationListener(enemyBubble, enemyBubbles));
        va.start();
    }

    public class ValueAnimationListener implements ValueAnimator.AnimatorUpdateListener {
        private ValueAnimator va;
        private ImageView myBubble;
        private Set<ImageView> bubblesToCheckCollision;

        public ValueAnimationListener(ValueAnimator va, ImageView bubble, Set<ImageView> bubblesToCheckCollision) {
            this.va = va;
            this.myBubble = bubble;
            this.bubblesToCheckCollision = bubblesToCheckCollision;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            //update myBubble's top margin to animate its vertical movement
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) myBubble.getLayoutParams();
            layoutParams.topMargin = (int) (float) animation.getAnimatedValue();
            myBubble.setLayoutParams(layoutParams);

            //check against other bubbles if they collide
            ImageView collidedEnemy = null;
            for (ImageView otherBubble : bubblesToCheckCollision) {
                if (CheckCollision(myBubble, otherBubble)) {
                    Log.e("xxxxxxxxxxxxxxxxxx", "collision!!!!!");
                    collidedEnemy = otherBubble;
                    bubblesToCheckCollision.remove(otherBubble);
                    //cancel the value animation
                    va.cancel(); //the cancel callback will remove it from the view
                    break;
                }
            }

            if (collidedEnemy != null) {
                bubblesToCheckCollision.remove(collidedEnemy);
            }
        }
    }

    public class BubbleAnimationListener implements Animation.AnimationListener, Animator.AnimatorListener {
        private ImageView bubble;
        private Set<ImageView> allBubbles;
        public BubbleAnimationListener(ImageView bubble, Set<ImageView> allBubbles) {
            this.bubble = bubble;
            this.allBubbles = allBubbles;
        }
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            final RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_gaming);
            layout.post(new Runnable() {
                @Override
                public void run() {
                    GamingActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            if (bubble == null) {
                                Log.d("bubble", "bubble already gone");
                            } else {
                                layout.removeView(bubble);
                                allBubbles.remove(bubble); //remove it from the set so that we don't check the its collision anymore
                            }
                        }
                    });
                }
            });
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            Log.d("animation", "onAnimationEnd");
            final RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_gaming);
            layout.post(new Runnable() {
                @Override
                public void run() {
                    GamingActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            if (bubble == null) {
                                Log.d("bubble", "bubble already gone");
                            } else {
                                layout.removeView(bubble);
                                allBubbles.remove(bubble); //remove it from the set so that we don't check the its collision anymore
                            }
                        }
                    });
                }
            });
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            Log.d("animation", "onAnimationCancel");
            onAnimationEnd(animation);
        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

    public class ShooterGestureDetector extends GestureDetector.SimpleOnGestureListener implements ValueAnimator.AnimatorUpdateListener {
        private final static float xVelocityThreshold = 0;

        //if offset > 0, move to the right, otherwise left
        private void moveShooter(float end, float start) {
            float offset = end - start;
            if (offset > 0) {
                Log.d("onFling", "left to right " + offset);
            } else {
                Log.d("onFling", "right to left " + offset);
            }

            RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_gaming);
            //Object animatedValue = animation.getAnimatedValue();
            //int newShooterX = (int)(float) animatedValue;

            int shooterX = (int) shooterButton.getX();
            int newShooterX = shooterX + (int)offset;

            //Limit the right boundary
            int max = layout.getWidth() - layout.getPaddingRight();
            if (newShooterX > max) {
                newShooterX = max;
            }

            //Limit the left boundary
            if (newShooterX < 0) {
                newShooterX = 0;
            }

            //make a copy of the layout params
            final RelativeLayout.LayoutParams rlParams = (RelativeLayout.LayoutParams) shooterButton.getLayoutParams();
            RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(rlParams);
            Log.d("original layout", "leftMargin="+ newParams.leftMargin + ", top margin=" +newParams.topMargin);

            //Change its left margin so that it appears that it moves
            newParams.leftMargin = newShooterX;
            Log.d("new layout", "leftMargin="+ newParams.leftMargin + ", top margin=" +newParams.topMargin);
//                params.topMargin = ((RelativeLayout.LayoutParams)(shooterButton.getLayoutParams())).topMargin;
            shooterButton.setLayoutParams(newParams);
            /*ValueAnimator animation = ValueAnimator.ofFloat(start, end);
            animation.addUpdateListener(this);
            animation.setDuration(100);
            animation.start();*/
            //calculate the new position as a result of the swiping gesture

        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (Math.abs(velocityX) >= xVelocityThreshold) {
                //int offset = (int) (e2.getX() - e1.getX());
                moveShooter(e2.getX(), e1.getX());
                return true;
            }
            return false;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_gaming);
            Object animatedValue = animation.getAnimatedValue();
            int newShooterX = (int)(float) animatedValue;

            int shooterX = (int) shooterButton.getX();
            //int newShooterX = shooterX + offset/5;

            //Limit the right boundary
            if (newShooterX > layout.getWidth()) {
                newShooterX = layout.getWidth();
            }

            //Limit the left boundary
            if (newShooterX < 0) {
                newShooterX = 0;
            }

            //make a copy of the layout params
            final RelativeLayout.LayoutParams rlParams = (RelativeLayout.LayoutParams) shooterButton.getLayoutParams();
            RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(rlParams);
            Log.d("original layout", "leftMargin="+ newParams.leftMargin + ", top margin=" +newParams.topMargin);

            //Change its left margin so that it appears that it moves
            newParams.leftMargin = newShooterX;
            Log.d("new layout", "leftMargin="+ newParams.leftMargin + ", top margin=" +newParams.topMargin);
//                params.topMargin = ((RelativeLayout.LayoutParams)(shooterButton.getLayoutParams())).topMargin;
            shooterButton.setLayoutParams(newParams);
        }
    }
}
