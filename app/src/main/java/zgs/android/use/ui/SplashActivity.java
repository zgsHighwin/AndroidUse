package zgs.android.use.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zgs.android.use.R;
import zgs.android.use.base.BaseSubscribe;

/**
 * @author zgsHighwin
 * @created at 2017/6/28 0028 下午 3:51
 * @Description: 启动闪屏页
 */
public class SplashActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //line: 19 Description: 设置窗口全屏化
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        mImageView = ((ImageView) findViewById(R.id.iv));


        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureDrop()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscribe<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        mImageView.setAlpha(aLong * 1.0f / 2000);
                        if (aLong == 2000) {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                });
     }
}
