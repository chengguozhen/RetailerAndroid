package com.neighbor.retailer_android.ui.view;

import android.widget.GridView;

/**
 * author:xu
 * date:2015-3-15
 * 重写onMeasure，计算高度，让ListView不能滚动，以便在scrollview中嵌套
 */

public class FtGridView extends GridView
{
    public FtGridView(android.content.Context context,
                      android.util.AttributeSet attrs)
    {
        super(context, attrs);
    }
    
    /**
     * 设置高度为child总高度
     * Measure specification mode: The child can be as large as it wants up
     * to the specified size.
     */

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

}