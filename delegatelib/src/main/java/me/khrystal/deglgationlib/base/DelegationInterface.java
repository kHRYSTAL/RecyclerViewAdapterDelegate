package me.khrystal.deglgationlib.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/5/9
 * update time:
 * email: 723526676@qq.com
 */
public interface DelegationInterface<E> {

    int getItemViewType();

    /**
     * Deglate callback this method to judge holder type has added
     * @param items
     * @param position
     * @return
     */
    boolean isViewType(@NonNull List<E> items, int position);

    @NonNull
    DelegateViewHolder onCreateViewHolder(ViewGroup container);

    void onBindViewHolder(@NonNull List<E> items, int position, @NonNull DelegateViewHolder holder);
}
