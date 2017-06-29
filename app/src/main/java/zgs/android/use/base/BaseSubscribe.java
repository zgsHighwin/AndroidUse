package zgs.android.use.base;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


/**
 * Created by zgsHighwin on 2017/6/28 0028.
 * <p>
 * Email zgshighwin@gmail.com
 * <p>
 * Description:
 */

public abstract class BaseSubscribe<R> implements Subscriber<R> {


    @Override
    public void onSubscribe(Subscription s) {
        if (s != null) {
            s.request(getRequestNum());
        }

    }

    private long getRequestNum() {
        return 1;
    }

    public abstract void onNext(R r);

    public abstract void onError(Throwable t);

    @Override
    public void onComplete() {

    }


}
