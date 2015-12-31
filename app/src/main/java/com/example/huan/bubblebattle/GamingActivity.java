package com.example.huan.bubblebattle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GamingActivity extends AppCompatActivity {
    private FloatingActionButton shootButton;
    private FloatingActionButton leftButton;
    private FloatingActionButton rightButton;
    private ImageButton shooterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaming);
    }

    @Override
    protected void onResume() {
        super.onResume();
        shootButton = (FloatingActionButton) findViewById(R.id.shoot);
        leftButton = (FloatingActionButton) findViewById(R.id.left);
        rightButton = (FloatingActionButton) findViewById(R.id.right);
        shooterButton = (ImageButton) findViewById(R.id.shooter);

        shootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = shootButton.getX();
                float y = shootButton.getY();
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_gaming);
                ImageView bubble = new ImageView(GamingActivity.this);
                bubble.setImageResource(R.drawable.green_bubble);
                //layout.addView(bubble);

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50); //bubble size
                params.leftMargin = (int) shooterButton.getX(); //positions
                params.rightMargin = (int) shooterButton.getY();
                layout.addView(bubble, params);

                float hight = shooterButton.getY();
                //FIXME: replace with the hight of the view
                TranslateAnimation animation = new TranslateAnimation(0.0f, 0, hight, 0);
                animation.setDuration(5000);
                animation.setRepeatCount(Animation.INFINITE);
                animation.setRepeatMode(2);
                animation.setFillAfter(true);
                bubble.startAnimation(animation);
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_gaming);
                    //move the shooter to the right
                   int shooterX = (int) shooterButton.getX();
                   int shooterY = (int) shooterButton.getY();
                   int newShooterX = shooterX + 20;
                   if (newShooterX > layout.getWidth()) {
                       newShooterX = layout.getWidth();
                   }
                   RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(shooterButton.getWidth(), shooterButton.getHeight());
                   params.leftMargin = newShooterX;
                   params.rightMargin = shooterY;
                   shooterButton.setLayoutParams(params);
               }
           }
        );
    }
}
