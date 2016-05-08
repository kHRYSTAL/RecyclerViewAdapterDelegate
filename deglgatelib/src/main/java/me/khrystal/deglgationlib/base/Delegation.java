package me.khrystal.deglgationlib.base;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.khrystal.deglgationlib.R;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/5/9
 * update time:
 * email: 723526676@qq.com
 */
public abstract class Delegation<E> implements DelegationInterface<E> {

    private int layoutId;

    private int viewType;

    public Delegation(@NonNull int layoutId){
        this.layoutId = layoutId;
    }

    public Delegation(@NonNull int layoutId, int viewType){
        this.layoutId = layoutId;
        this.viewType = viewType;
    }

    @NonNull
    @Override
    public DelegateViewHolder onCreateViewHolder(ViewGroup container) {
        View itemView = LayoutInflater.from(container.getContext()).inflate(layoutId,container,false);
        DelegateViewHolder holder = new DelegateViewHolder(container.getContext(),itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull List<E> items, int position, @NonNull DelegateViewHolder holder) {
        //maybe use Glide and itemView is ImageView
        holder.itemView.setTag(R.string.delegation_tag,position);
        E data = items.get(position);
        handle(holder,data,position);
    }

    @Override
    public int getItemViewType() {
        return viewType;
    }

    @Override
    public boolean isViewType(@NonNull List<E> items, int position) {
        if (items.get(position) instanceof DelegateBaseModel)
            return ((DelegateBaseModel)(items.get(position))).delegationViewType
                    == getItemViewType();
        else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (o instanceof Delegation)
            return ((Delegation)o).viewType == viewType;
        return false;
    }

    /**
     * when onBindViewHolder callback this methon
     * you can handle yourself business logic
     * @param holder
     * @param data
     */
    protected abstract void handle(DelegateViewHolder holder,E data,int position);

}
