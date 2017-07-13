package zgs.android.use.ui.blur;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import zgs.android.use.R;

public class BlurActivity extends AppCompatActivity {

    private boolean isBlur;
    private boolean isViewBlur;
    private Bitmap mBitmap;
    private ImageView mIv;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);

        mIv = ((ImageView) findViewById(R.id.iv));
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_androiod);
        mIv.setImageBitmap(mBitmap);
        mView = findViewById(R.id.view);
        mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mView.setBackground(new BitmapDrawable(getResources(), blurBitmap(getScreenshot(mView), 25)));
            }
        });
    }

    public void visible(View view) {
        mView.setVisibility(mView.getVisibility() == View.INVISIBLE ? View.VISIBLE : View.INVISIBLE);
    }

    public void blur(View view) {
        Log.d("BlurActivity", "isBlur:" + isBlur);
        mIv.setImageBitmap(!isBlur ? blurBitmap(mBitmap, 25) : mBitmap);
        isBlur = !isBlur;
    }

    public void blurView(View view){
        BitmapDrawable bitmapDrawable25 = new BitmapDrawable(getResources(), blurBitmap(getScreenshot(mView), 25));
        BitmapDrawable bitmapDrawable01 = new BitmapDrawable(getResources(), blurBitmap(getScreenshot(mView), 0.1f));
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#dd000000"));
        mView.setBackground(!isViewBlur? bitmapDrawable25 : bitmapDrawable01);
        isViewBlur = !isViewBlur;
    }


    private static Bitmap getScreenshot(View v) {
        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
    }

    /**
     * @author zgsHighwin
     * @created at 2017/7/11 0011 下午 5:26l
     * @Description: bitmap虚化 从0到25(不包括0)，虚化程度递增
     */
    public Bitmap blurBitmap(Bitmap bitmap, float radius) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            RenderScript renderScript = RenderScript.create(this);
            ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
            Allocation allIn = Allocation.createFromBitmap(renderScript, bitmap);
            Allocation allOut = Allocation.createFromBitmap(renderScript, outBitmap);
            scriptIntrinsicBlur.setRadius(radius);
            scriptIntrinsicBlur.setInput(allIn);
            scriptIntrinsicBlur.forEach(allOut);
            allOut.copyTo(outBitmap);
            renderScript.destroy();
            return outBitmap;
        } else {
            return bitmap;
        }

    }

}
