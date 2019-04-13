package com.example.luke.newsclient.view.diyView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    private List<Line> mLineList = new ArrayList<>();
    private Line mLine;

    private float interval_horizental = 50;
    private float interval_vertical = 10;

    private int mMaxWidth;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mLineList.clear();
        mLine = null;

        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        mMaxWidth = width - getPaddingLeft() - getPaddingRight();

        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 测量孩子
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            // 测量完需要将孩子添加到管理行的孩子的集合中，将行添加到管理行的集合中

            if (mLine == null) {
                // 初次添加第一个孩子的时候
                mLine = new Line(mMaxWidth, interval_horizental);

                // 添加孩子
                mLine.addView(childView);
                // 添加行
                mLineList.add(mLine);

            } else {
                // 行中有孩子的时候，判断时候能添加
                if (mLine.canAddView(childView)) {
                    // 继续往该行里添加
                    mLine.addView(childView);
                } else {
                    //  添加到下一行
                    mLine = new Line(mMaxWidth, interval_horizental);
                    mLine.addView(childView);
                    mLineList.add(mLine);
                }
            }
        }

        if (mLineList.size() == 0){
            setMeasuredDimension(0, 0);
        }
        int height = getPaddingTop() + getPaddingBottom();
        for (int i = 0; i < mLineList.size(); i++) {
            // 所有行的高度
            height += mLineList.get(i).height;
        }
        // 所有竖直的间距
        height += (mLineList.size() - 1) * interval_vertical;

        // 测量
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        l = getPaddingLeft();
        t = getPaddingTop();
        for (int i = 0; i < mLineList.size(); i++) {
            // 获取行
            Line line = mLineList.get(i);
            // 管理
            line.layout(t, l);

            // 更新高度
            t += line.height;
            if (i != mLineList.size() - 1) {
                // 不是最后一条就添加间距
                t += interval_vertical;
            }
        }
    }

    public class Line{
        private List<View> mViewList = new ArrayList<>();
        private int maxWidth;
        private int usedWidth;
        private int height;
        private float interval;

        public Line(int maxWidth,float interval){
            this.maxWidth = maxWidth;
            this.interval = interval;
        }

        public void addView(View view){
            int childWidth = view.getMeasuredWidth();
            int childHeight = view.getMeasuredHeight();
            if (mViewList.size() == 0) {
                // 集合里没有孩子的时候
                if (childWidth > maxWidth) {
                    usedWidth = maxWidth;
                    height = childHeight;
                } else {
                    usedWidth = childWidth;
                    height = childHeight;
                }
            } else {
                usedWidth += childWidth + interval;
                height = childHeight > height ? childHeight : height;
            }

            // 添加孩子到集合
            mViewList.add(view);
        }

        public boolean canAddView(View view) {
            // 集合里没有数据可以添加
            if (mViewList.size() == 0) {
                return true;
            }

            // 最后一个孩子的宽度大于剩余宽度就不添加
            if (view.getMeasuredWidth() > (maxWidth - usedWidth - interval)) {
                return false;
            }

            // 默认可以添加
            return true;
        }

        public void layout(int t, int l) {

            // 循环指定孩子位置
            for (View view : mViewList) {

                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                int top = t;
                int left = l;
                int right = measuredWidth + left;
                int bottom = measuredHeight + top;
                // 指定位置
                view.layout(left, top, right, bottom);

                // 更新数据
                l += measuredWidth + interval;
            }
        }
    }
}
