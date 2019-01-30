package com.e_trans.virtualtourism.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * @author MarkQiao
 * @create 2018/12/24
 * @Describe
 */
public class ClickAnimationUtils {
    /**
     * 点击放缩动画
     *
     * @param mView
     *            控件
     * @param mDuration
     *            动画持续时间     ClickAnimationUtils.ClickScaleAnim(View, 200);
     */
    public static void ClickScaleAnim(View mView, final int mDuration) {
        mView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        AnimatorSet set = new AnimatorSet();

                        set.playTogether(
                                ObjectAnimator.ofFloat(v, "scaleX", 1, 0.95f),
                                ObjectAnimator.ofFloat(v, "scaleY", 1, 0.95f));
                        set.setDuration(mDuration).start();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_CANCEL:
                        AnimatorSet set1 = new AnimatorSet();
                        set1.playTogether(
                                ObjectAnimator.ofFloat(v, "scaleX", 0.95f, 1f),
                                ObjectAnimator.ofFloat(v, "scaleY", 0.95f, 1f));
                        set1.setDuration(mDuration).start();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 点击延Y（0,0）旋转动画
     *
     * @param mView
     *            控件
     * @param mDuration
     *            动画持续时间
     */
    public static void ClickRotationYAnim(final View mView, final int mDuration) {
        // mView.setPivotX(0f);
        // mView.setPivotY(mView.getHeight());
        mView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(ObjectAnimator.ofFloat(v, "rotationY", 0,
                                5), ObjectAnimator.ofFloat(v, "pivotY",
                                mView.getHeight() / 2, mView.getHeight() / 2));
                        set.setDuration(mDuration).start();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_CANCEL:
                        AnimatorSet set1 = new AnimatorSet();
                        set1.playTogether(ObjectAnimator.ofFloat(v, "rotationY", 5,
                                0), ObjectAnimator.ofFloat(v, "pivotY",
                                mView.getHeight() / 2, mView.getHeight() / 2));
                        set1.setDuration(mDuration).start();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 点击延X（0,0）旋转动画
     *
     * @param mView
     *            控件
     * @param mDuration
     *            动画持续时间
     */
    public static void ClickRotationXAnim(final View mView, final int mDuration) {
        // mView.setPivotX(0);
        // mView.setPivotY(0);
        mView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(
                                ObjectAnimator.ofFloat(v, "rotationX", 0, -10),
                                ObjectAnimator.ofFloat(v, "pivotX",
                                        mView.getWidth() / 2, mView.getWidth() / 2));
                        set.setDuration(mDuration).start();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_CANCEL:
                        AnimatorSet set1 = new AnimatorSet();
                        set1.playTogether(
                                ObjectAnimator.ofFloat(v, "rotationX", -10, 0),
                                ObjectAnimator.ofFloat(v, "pivotX",
                                        mView.getWidth() / 2, mView.getWidth() / 2));
                        set1.setDuration(mDuration).start();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
