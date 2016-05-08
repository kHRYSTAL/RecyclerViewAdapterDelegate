package me.khrystal.deglgationlib.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * usage: BaseViewHolder
 * author: kHRYSTAL
 * create time: 16/5/9
 * update time:
 * email: 723526676@qq.com
 */
public class DelegateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private SparseArray<View> mViews;
    private Context mContext;

    public DelegateViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mViews = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    private  <T extends View> T findViewById(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = itemView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    public View getView(int viewId){
        return findViewById(viewId);
    }

    public TextView getTextView(int viewId){
        return (TextView)getView(viewId);
    }

    public Button getButton(int viewId){
        return (Button)getView(viewId);
    }

    public ImageView getImageView(int viewId){
        return (ImageView)getView(viewId);
    }

    public ImageButton getImageButton(int viewId){
        return (ImageButton)getView(viewId);
    }

    public EditText getEditText(int viewId){
        return (EditText)getView(viewId);
    }

    public DelegateViewHolder setViewBackground(int viewId, int resId){
        View view = findViewById(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    public Context getContext(){
        return mContext;
    }

    public void showToast(String msg,int duration){
        Toast.makeText(mContext,msg,duration).show();
    }

    /**
     * need dependent on Android Support Design Library
     * @param msg
     */
    public void showSnackBar(String msg){

    }

    public void showProgress(){

    }

    public void hideProgress(){

    }

    @Override
    public void onClick(View v) {

    }
}
