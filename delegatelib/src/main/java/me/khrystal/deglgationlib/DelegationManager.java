package me.khrystal.deglgationlib;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.List;

import me.khrystal.deglgationlib.base.DelegateViewHolder;
import me.khrystal.deglgationlib.base.Delegation;
import me.khrystal.deglgationlib.base.DelegationInterface;

/**
 * usage: DelegationManager
 * author: kHRYSTAL
 * create time: 16/5/9
 * update time:
 * email: 723526676@qq.com
 */
public class DelegationManager<E> {

    SparseArrayCompat<DelegationInterface<E>> delegations = new SparseArrayCompat<>();

    public DelegationManager<E> addDelegation(@NonNull DelegationInterface<E> delegate) {
        return addDelegation(delegate, false);
    }

    public DelegationManager<E> addDelegation(@NonNull DelegationInterface<E> delegation,
                                            boolean allowReplacingDelegation){
        if (delegation == null)
            throw new NullPointerException("Delegation must be not null!");

        int viewType = delegation.getItemViewType();

        if (!allowReplacingDelegation && delegations.get(viewType) != null)
            throw new IllegalArgumentException("One Delegation was registered for this viewType = " + viewType +
                    "the Delegation's Name is " + delegations.get(viewType).getClass().getSimpleName());

        delegations.put(viewType,delegation);
        return this;
    }

    /**
     * @param delegation
     * @return
     */
    public DelegationManager<E> removeDelegation(@NonNull DelegationInterface<E> delegation){
        if (delegation == null)
            throw new NullPointerException("Delegation is null,cloud not remove");
        // TODO: 16/5/9 later: use viewType to judge equals
        DelegationInterface removeDelegation = delegations.get(delegation.getItemViewType());
        if (removeDelegation != null && removeDelegation == delegations)
            delegations.remove(delegation.getItemViewType());
        return this;
    }


    public DelegationManager<E> removeDelegation(int viewType) {
        delegations.remove(viewType);
        return this;
    }


    public int getItemViewType(@NonNull List<E> items, int position) {
        if (items == null)
            throw new NullPointerException("list must be not null");

        int delegatesCount = delegations.size();

        for (int i = 0; i < delegatesCount; i++) {
            DelegationInterface<E> delegate = delegations.valueAt(i);
            if (delegate.isViewType(items, position))
                return delegate.getItemViewType();
        }

        throw new IllegalArgumentException(
                "No Delegation added that matches position=" + position + " in items");
    }

    @NonNull
    public DelegateViewHolder onCreateViewHolder(ViewGroup container, int viewType) {
        DelegationInterface<E> delegation = delegations.get(viewType);

        if (delegation == null)
            throw new NullPointerException("No Delegation added for ViewType " + viewType);

        DelegateViewHolder vh = delegation.onCreateViewHolder(container);
        if (vh == null)
            throw new NullPointerException(
                    "ViewHolder returned from Delegation " + delegation + " for ViewType =" + viewType + " is null!");
        return vh;
    }

    public void onBindViewHolder(@NonNull List<E> items, int position, @NonNull DelegateViewHolder holder){
        DelegationInterface<E> delegation = delegations.get(holder.getItemViewType());
        if (delegation == null){}
            // TODO: 16/5/11 if use custom recyclerview to loadmore footerView is not delegation ,do nothing
//            throw new NullPointerException(
//                    "No Delegation added for ViewType " + holder.getItemViewType());

        else delegation.onBindViewHolder(items,position,holder);
    }

}
