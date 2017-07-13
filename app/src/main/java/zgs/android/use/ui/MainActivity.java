package zgs.android.use.ui;

import android.support.v7.app.AppCompatActivity;

import java.util.List;

import zgs.android.use.ui.blur.BlurActivity;
import zgs.android.use.ui.list.ListActivity;
import zgs.android.use.ui.recyclerview.edit.EditActivity;


/**
 * @author zgsHighwin
 *         created at 2017/6/28 0028 下午 3:11
 *         Description: 用于显示列表的操作
 */
public class MainActivity extends ListActivity {
    @Override
    public List<Class<? extends AppCompatActivity>> addClassList() {
        //图片虚化
        mClassList.add(BlurActivity.class);
        //RecyclerView中EditText,CheckBox状态保存
        mClassList.add(EditActivity.class);
        return mClassList;
    }
}

