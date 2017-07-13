package zgs.android.use.ui.editcheckboxinrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import zgs.android.use.R;

public class EditCheckBoxActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_check_box);

        mRecyclerView = ((RecyclerView) findViewById(R.id.recycler_view));
    }
}
