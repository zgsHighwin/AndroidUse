package zgs.android.use.base;

/**
 * Created by zgsHighwin on 2017/6/28 0028.
 * <p>
 * Email zgshighwin@gmail.com
 * <p>
 * Description:
 */

public abstract class BaseNoErrorFlowableSubscribe<R> extends BaseFlowableSubscribe<R> {

    public abstract void onNext(R r);

}
