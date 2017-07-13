package zgs.android.use.ui.recyclerview.edit;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import java.util.List;

import zgs.android.use.R;

/**
 * Created by zgsHighwin on 2017/7/13 0013.
 * <p>
 * Email zgshighwin@gmail.com
 * <p>
 * Description:
 */

public class EditAdapter extends RecyclerView.Adapter<EditAdapter.EditHolder> {

    private List<EditBean> mList;

    public EditAdapter(List<EditBean> list) {
        mList = list;
    }

    @Override
    public EditHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("EditAdapter", "onCreateViewHolder");
        EditHolder editHolder = new EditHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_check_box, parent, false));

        return editHolder;
    }

    @Override
    public void onBindViewHolder(EditHolder holder, int position) {
        Log.d("EditAdapter", "onBindViewHolder");
        holder.iv.setImageResource(mList.get(position).getImageHead());
        holder.cb.setChecked(mList.get(position).isCheck());
        holder.et.setText(mList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class EditHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private CheckBox cb;
        private AppCompatEditText et;

        public EditHolder(View itemView) {
            super(itemView);
            Log.d("EditHolder", "EditHolder");
            iv = (ImageView) itemView.findViewById(R.id.iv);
            cb = (CheckBox) itemView.findViewById(R.id.cb);
            et = (AppCompatEditText) itemView.findViewById(R.id.et);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mList.get(getAdapterPosition()).setCheck(isChecked);
                }
            });

            et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                        mList.get(getAdapterPosition()).setText(s.toString());
                }
            });

        }
    }
}
