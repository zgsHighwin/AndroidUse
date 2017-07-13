package zgs.android.use.ui.recyclerview.edit;

/**
 * Created by zgsHighwin on 2017/7/13 0013.
 * <p>
 * Email zgshighwin@gmail.com
 * <p>
 * Description:
 */

public class EditBean {

    private boolean isCheck;

    private int imageHead;

    private String text;


    int getImageHead() {
        return imageHead;
    }

     void setImageHead(int imageHead) {
        this.imageHead = imageHead;
    }

     boolean isCheck() {
        return isCheck;
    }

     void setCheck(boolean check) {
        isCheck = check;
    }

     String getText() {
        return text;
    }

     void setText(String text) {
        this.text = text;
    }
}
