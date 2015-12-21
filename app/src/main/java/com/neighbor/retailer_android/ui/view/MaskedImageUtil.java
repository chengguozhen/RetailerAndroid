package com.neighbor.retailer_android.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by vic on 2015/8/18.
 */

public abstract class MaskedImageUtil extends ImageView {
    private static final Xfermode MASK_XFERMODE;
    private Bitmap mask;
    private Paint paint;

    //Xfermode is for rendering.
    static {
        //get common place between two layers and display this part of lower layer
        PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
        MASK_XFERMODE = new PorterDuffXfermode(localMode);
    }

    public MaskedImageUtil(Context paramContext) {
        super(paramContext);
    }

    public MaskedImageUtil(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public MaskedImageUtil(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    public abstract Bitmap createMask();

    /**
     * 1.get Drawable obj
     * 2.create a new layer
     * 3.draw image in the canvas
     * 4.create mask and put it in the canvas
     * 5.restore layer
     *
     * @param paramCanvas
     */
    protected void onDraw(Canvas paramCanvas) {
        //get drawable obj from setting(imageview.setImageSource(R.drawable.*))
        Drawable localDrawable = getDrawable();
        float f1 = getWidth();
        float f2 = getHeight();
        if (localDrawable == null)
            return;

        try {
            if (this.paint == null) {
                this.paint = new Paint();
                this.paint.setFilterBitmap(false);
                this.paint.setXfermode(MASK_XFERMODE);

            }

            //left top right bottom
        /*I suppose canvas use savelayer method to create a new layer and with size of
        drawable image in the canvas
            */
            int i = paramCanvas.saveLayer(0.0F, 0.0F, f1, f2, null, Canvas.ALL_SAVE_FLAG);
            //set drawable location
            localDrawable.setBounds(0, 0, (int)f1, (int)f2);
            //draw drawable object in the screen
            localDrawable.draw(paramCanvas);
            //if the mask obj is null or the mask has been recycled
            //then create a new obj for mask
            if ((this.mask == null) || (this.mask.isRecycled())) {
                Bitmap localBitmap1 = createMask();
                this.mask = localBitmap1;
            }

            paramCanvas.drawBitmap(this.mask, 0.0F, 0.0F, this.paint);
            //restore the bitmap to No.cnt layer.
            paramCanvas.restoreToCount(i);
            return;
        } catch (Exception localException) {
            StringBuilder localStringBuilder = new StringBuilder()
                    .append("Attempting to draw with recycled bitmap. View ID = ");
            System.out.println("localStringBuilder=="+localStringBuilder);
        }
    }
}