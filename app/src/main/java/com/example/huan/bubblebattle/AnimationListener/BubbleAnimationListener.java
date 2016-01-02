package com.example.huan.bubblebattle.AnimationListener;

import android.animation.Animator;
import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.huan.bubblebattle.R;

import java.util.Set;

/**
 * Created by huan on 2016/1/2.
 */
public class BubbleAnimationListener implements Animator.AnimatorListener {
    private Activity activity;
    private ImageView bubble;
    private Set<ImageView> allBubbles;
    public BubbleAnimationListener(Activity activity, ImageView bubble, Set<ImageView> allBubbles) {
        this.activity = activity;
        this.bubble = bubble;
        this.allBubbles = allBubbles;
    }

    @Override
    public void onAnimationStart(Animator animation) {
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Log.d("animation", "onAnimationEnd");
        final RelativeLayout layout = (RelativeLayout) activity.findViewById(R.id.activity_gaming);
        layout.post(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
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
