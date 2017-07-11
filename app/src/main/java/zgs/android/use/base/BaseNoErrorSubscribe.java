package zgs.android.use.base;

import android.util.Log;

/**
 * Created by zgsHighwin on 2017/6/28 0028.
 * <p>
 * Email zgshighwin@gmail.com
 * <p>
 * Description:
 */

public abstract class BaseNoErrorSubscribe<R> extends BaseSubscribe<R> {

    public abstract void onNext(R r);

    @Override
    public void onError(Throwable t) {
        Log.d("BaseNoErrorSubscribe", t.getLocalizedMessage());
    }
}
