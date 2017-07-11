package zgs.android.use.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import zgs.android.use.R;

public class BlurActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private boolean isBlur;
    private Bitmap mBitmap;
    private ImageView mIv;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);

        mIv = ((ImageView) findViewById(R.id.iv));
        mSeekBar = ((SeekBar) findViewById(R.id.seek_bar));
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_androiod);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    public void blur(View view) {
        mIv.setImageBitmap(!isBlur ? blurBitmap(mBitmap, 0) : blurBitmap(mBitmap, 25));
        isBlur = !isBlur;
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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            float value = progress * 1.0f / 10000;
            Log.d("BlurActivity", "progress:" + progress);
            Log.d("BlurActivity", "progress*1.0f/10000:" + value);
            mIv.setImageBitmap(blurBitmap(mBitmap, value == 0 ? 0.01f : value));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
