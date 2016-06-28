package com.example.haitran.cura.views.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haitran.cura.R;
import com.example.haitran.cura.objects.Personal;

import java.util.List;

/**
 * Created by hai.tran on 6/28/2016.
 */
public class ListviewSignInAdapter extends BaseAdapterEx {
    public ListviewSignInAdapter(Context context, List<Personal> list)
    {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        final ViewHolder viewHolder;
        if (convertView == null) {
            row = getLayoutInflater().inflate(R.layout.item_listview_signin, parent, false);
            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        } else {
            row = convertView;
            viewHolder = (ViewHolder) row.getTag();
        }

        if (viewHolder != null) {
            viewHolder.setData((Personal)get(position));
        }

        return row;
    }

}

class ViewHolder
{
    private ImageView imageView;
    private TextView textViewName, textViewSub;
    public ViewHolder (View v)
    {
        imageView =(ImageView) v.findViewById(R.id.signin_image);
        textViewName = (TextView) v.findViewById(R.id.signin_textName);
        textViewSub = (TextView) v.findViewById(R.id.signin_textSub);
    }

    public void setData(Personal personal)
    {
        textViewName.setText(personal.getName());
        textViewSub.setText(personal.getLevel());
    }
}