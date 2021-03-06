package com.example.haitran.cura.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hai.tran on 6/28/2016.
 */

public abstract class BaseAdapterEx<T> extends BaseAdapter {

    public Context context;
    private LayoutInflater inflater;
    public List<T> list;

    public BaseAdapterEx(Context context) {
        this(context, new ArrayList<T>());
    }

    public BaseAdapterEx(Context context, List<T> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    /**
     * @return An instance of {@link LayoutInflater}.
     */
    protected LayoutInflater getLayoutInflater() {
        return inflater;
    }


    /**
     * Append the given items to the Adapter's list.
     *
     * @param l The items to append.
     */
    public void appendList(List<T> l) {

        clearList();
        this.list.addAll(l);
        this.notifyDataSetChanged();
    }


    /**
     * Append the given item to the Adapter's list.
     *
     * @param item The item to append.
     */
    protected void append(T item) {
        if (item != null) {
            this.list.add(item);
            this.notifyDataSetChanged();
        }
    }


    /**
     * Remove an item from the Adapter's list.
     *
     * @param itemPosition Item's position/index to remove.
     */
    protected void remove(int itemPosition) {
        if (itemPosition >= 0 && itemPosition < getCount()) {
            list.remove(itemPosition);
        }
    }


    /**
     * Clear all items that are in the list.
     */
    protected void clearList() {
        this.list.clear();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    protected T get(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
