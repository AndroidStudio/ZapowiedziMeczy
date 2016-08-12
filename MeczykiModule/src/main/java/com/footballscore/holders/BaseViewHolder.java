package com.footballscore.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    private final View view;
    private final Context context;

    public BaseViewHolder(View view, Context context) {
        super(view);
        this.view = view;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public View findViewById(int id) {
        return view.findViewById(id);
    }

    public abstract void onBind(T model);
}
