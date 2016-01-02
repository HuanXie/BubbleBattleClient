package com.example.huan.bubblebattle;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.huan.bubblebattle.AnimationListener.BubbleAnimationListener;
import com.example.huan.bubblebattle.Gesture.BubbleBattleGestureDetector;

import java.util.HashSet;
import java.util.Set;

public class GamingActivity extends AppCompatActivity {
    private FloatingActionButton fireButton;
    private ImageButton shooterButton;
    private Set<ImageView> myBubbles = new HashSet<>();
    private Set<ImageView> enemyBubbles = new HashSet<>();

    public boolean CheckCollision(View v1, View v2) {
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
        final GestureDetector gd = new GestureDetector(GamingActivity.this, new BubbleBattleGestureDetector(GamingActivity.this, shooterButton));
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
                startEnemyBubble(50);

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
                ValueAnimator va = ValueAnimator.ofFloat(hight, 0);
                va.setDuration(5000);
                va.addUpdateListener(new ValueAnimationListener(va, myBubble, enemyBubbles));
                va.addListener(new BubbleAnimationListener(GamingActivity.this, myBubble, myBubbles));
                va.start();
            }
        });
    }

    //This method should be called each time receiving a message from websocket with a X-coordinate
    private void startEnemyBubble(int xCoordinate) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_gaming);
        ImageView enemyBubble = new ImageView(GamingActivity.this);
        enemyBubbles.add(enemyBubble); //save the newly generated enemy bubble into the set
        enemyBubble.setImageResource(R.drawable.green_bubble);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50); //bubble size
        params.leftMargin = xCoordinate;
        params.topMargin = layout.getHeight();
        layout.addView(enemyBubble, params);

        ValueAnimator va = ValueAnimator.ofFloat(0, layout.getHeight());
        va.setDuration(5000);
        va.addUpdateListener(new ValueAnimationListener(va, enemyBubble, myBubbles));
        va.addListener(new BubbleAnimationListener(GamingActivity.this, enemyBubble, enemyBubbles));
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
}
