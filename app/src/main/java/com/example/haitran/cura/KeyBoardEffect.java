package com.example.haitran.cura;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kha.phan on 6/30/2016.
 */
public class KeyBoardEffect {
    public void setButtonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        if(v.getId() == R.id.text_clear||v.getId() == R.id.text_tick) v.setBackgroundColor(Color.WHITE);
                        v.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                        ((TextView)v).setTextColor(Color.RED);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        if(v.getId() == R.id.text_clear||v.getId() == R.id.text_tick) {
                            v.setBackgroundResource(R.drawable.boder_bottom_kb);
                        }
                        v.getBackground().clearColorFilter();
                        ((TextView)v).setTextColor(Color.WHITE);
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
}
