package zgs.android.use;

import android.app.Application;
import android.content.Context;

/**
 * Created by zgsHighwin on 2017/6/28 0028.
 * <p>
 * Email zgshighwin@gmail.com
 * <p>
 * Description: 全局的application 需在Androidmainfest文件application配置android:name=".App"才可以用
 */

public class App extends Application {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }


    /**
     * @author zgsHighwin
     * created at 2017/6/28 0028 下午 3:25
     * Description: 返回Application实例
     */
    public static App getInstance() {
        return sInstance;
    }

    /**
     * @author zgsHighwin
     * created at 2017/6/28 0028 下午 3:26
     * Description: 获取全局的Context
     */
    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
}
