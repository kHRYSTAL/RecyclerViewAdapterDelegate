package me.khrystal.deglgationlib.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import me.khrystal.deglgationlib.DelegationManager;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/5/9
 * update time:
 * email: 723526676@qq.com
 */
public class DelegateAdapter<E> extends RecyclerView.Adapter<DelegateViewHolder>{

    private List<E> mDatas;

    private Context mContext;

    private DelegationManager<E> mDelegationManager;

    public DelegateAdapter(Context context, List<E> datas, boolean useKRecyclerView){
        mDelegationManager = new DelegationManager<>(useKRecyclerView);
        mContext = context;
        mDatas = datas;
    }

    public DelegateAdapter(Context context, List<E> datas){
        mDelegationManager = new DelegationManager<>();
        mContext = context;
        mDatas = datas;
    }

    public void addDelegation(DelegationInterface<E> delegation){
        mDelegationManager.addDelegation(delegation);
    }

    public void addDelegation(DelegationInterface<E> delegation, boolean allowReplacionDelegation){
        mDelegationManager.addDelegation(delegation, allowReplacionDelegation);
    }

    @Override
    public DelegateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegationManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(DelegateViewHolder holder, int position) {
        mDelegationManager.onBindViewHolder(mDatas, position, holder);
    }

    @Override
    public int getItemViewType(int position) {
        return mDelegationManager.getItemViewType(mDatas, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
