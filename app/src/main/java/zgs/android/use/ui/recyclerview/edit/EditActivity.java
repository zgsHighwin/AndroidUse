package zgs.android.use.ui.recyclerview.edit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import zgs.android.use.R;

public class EditActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private List<EditBean> mEditBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mEditBeanList = new ArrayList<>();
        mRecyclerView = ((RecyclerView) findViewById(R.id.recycler_view));

        EditAdapter editAdapter = new EditAdapter(mEditBeanList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(editAdapter);
        initData();
        editAdapter.notifyDataSetChanged();
    }


    private List<EditBean> initData() {
        if (mEditBeanList == null) {
            mEditBeanList = new ArrayList<>();
        }
        mEditBeanList.clear();

        for (int i = 0; i < 100; i++) {
            EditBean editBean = new EditBean();
            editBean.setCheck(false);
            editBean.setText(""+i);
            editBean.setImageHead(R.mipmap.icon_androiod);
            mEditBeanList.add(editBean);
        }

        return mEditBeanList;
    }
}
