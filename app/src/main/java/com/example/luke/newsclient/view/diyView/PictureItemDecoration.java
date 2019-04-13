package com.example.luke.newsclient.view.diyView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class PictureItemDecoration extends RecyclerView.ItemDecoration{
    private Paint mDividePaint;

    public PictureItemDecoration(){
        mDividePaint = new Paint();
        mDividePaint.setColor(Color.parseColor("#e6e6e6"));
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = 25;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            int pos = parent.getChildLayoutPosition(parent.getChildAt(i));
            // 跳过这个item不画线
            if(i == 0 && pos == 0){
                continue;
            }
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + 25;
            c.drawRect(left, top, right, bottom, mDividePaint);
        }
    }
}
