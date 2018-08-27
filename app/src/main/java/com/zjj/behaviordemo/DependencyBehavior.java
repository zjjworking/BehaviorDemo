package com.zjj.behaviordemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by zjj on 2018/7/18.
 */
public class DependencyBehavior extends CoordinatorLayout.Behavior<View> {
    public DependencyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 用来决定需要监听哪儿些控件或者容器的状态 监听who 什么状态change
     * @param parent 父容器
     * @param child  子控件 具体代表监听dependency的View 观察者
     * @param dependency 被监听的View  被观察者
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof Button;
    }
    /**
     * layoutDependsOn 返回true时被回调
     * 当被监听的View发生改变的时候回调 可在此设置相应联动动画等效果
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int offset = dependency.getTop();
        ViewCompat.offsetTopAndBottom(child, -offset);
        return true;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, View child, View dependency) {
        super.onDependentViewRemoved(parent, child, dependency);
    }
}
