package com.drivex.Utility;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * Created by sujeetmehta on 12/12/15.
 */
public class AnimUtils {


    public static RotateAnimation startRotation(float from, float to) {
        RotateAnimation rotateAnimation =
                new RotateAnimation(from, to, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f);
        rotateAnimation.setDuration(400);
        return rotateAnimation;
    }
}
