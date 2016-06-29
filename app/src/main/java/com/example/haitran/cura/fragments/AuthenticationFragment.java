package com.example.haitran.cura.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haitran.cura.R;
import com.example.haitran.cura.activities.HomeActivity;
import com.example.haitran.cura.activities.SplashActivity;

/**
 * Created by kha.phan on 6/28/2016.
 */
public class AuthenticationFragment extends Fragment implements View.OnClickListener{

    private String educatorName;
    private TextView mTextHello;
    private TextView mDigitFirst, mDigitSecond, mDigitThird,mDigitFourth;
    private TextView mKey_1, mKey_2, mKey_3, mKey_4, mKey_5, mKey_6,
            mKey_7, mKey_8, mKey_9, mKey_0;
    private TextView mKeyClear, mKeyTick;
    private String mDigitInput = "";
    private Toast mToast;

    public void setNameEducator(String educatorName) {
        this.educatorName = educatorName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
       AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.getSupportActionBar().setTitle("Authentication");
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        return inflater.inflate(R.layout.fragment_authentication, parent, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWidgets(view);
        addEvent();
        if (educatorName!=null) mTextHello.setText(mTextHello.getText() + " "+educatorName +",");
        else mTextHello.setText(mTextHello.getText() + " Mrs.Kha" +",");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mToast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
    }

    private void getWidgets(View view){
        mTextHello  = (TextView) view.findViewById(R.id.text_hello);
        mDigitFirst = (TextView) view.findViewById(R.id.text_digit_first);
        mDigitSecond = (TextView) view.findViewById(R.id.text_digit_second);
        mDigitThird = (TextView) view.findViewById(R.id.text_digit_third);
        mDigitFourth = (TextView) view.findViewById(R.id.text_digit_fourth);
        mKey_0 = (TextView) view.findViewById(R.id.text_keyboard_0);
        mKey_1 = (TextView) view.findViewById(R.id.text_keyboard_1);
        mKey_2 = (TextView) view.findViewById(R.id.text_keyboard_2);
        mKey_3 = (TextView) view.findViewById(R.id.text_keyboard_3);
        mKey_4 = (TextView) view.findViewById(R.id.text_keyboard_4);
        mKey_5 = (TextView) view.findViewById(R.id.text_keyboard_5);
        mKey_6 = (TextView) view.findViewById(R.id.text_keyboard_6);
        mKey_7 = (TextView) view.findViewById(R.id.text_keyboard_7);
        mKey_8 = (TextView) view.findViewById(R.id.text_keyboard_8);
        mKey_9 = (TextView) view.findViewById(R.id.text_keyboard_9);
        mKeyClear = (TextView) view.findViewById(R.id.text_clear);
        mKeyTick = (TextView) view.findViewById(R.id.text_tick);
    }
    private void addEvent(){
        mKey_0.setOnClickListener(this);
        mKey_1.setOnClickListener(this);
        mKey_2.setOnClickListener(this);
        mKey_3.setOnClickListener(this);
        mKey_4.setOnClickListener(this);
        mKey_5.setOnClickListener(this);
        mKey_6.setOnClickListener(this);
        mKey_7.setOnClickListener(this);
        mKey_8.setOnClickListener(this);
        mKey_9.setOnClickListener(this);
        mKeyClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDigitInput();
            }
        });
        mKeyTick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

    }



    @Override
    public void onClick(View v) {
        String tittle = ((TextView) v).getText().toString();
        switch (tittle) {
            case "1":
                inputDigit(1);
                break;
            case "2":
                inputDigit(2);
                break;
            case "3":
                inputDigit(3);
                break;
            case "4":
                inputDigit(4);
                break;
            case "5":
                inputDigit(5);
                break;
            case "6":
                inputDigit(6);
                break;
            case "7":
                inputDigit(7);
                break;
            case "8":
                inputDigit(8);
                break;
            case "9":
                inputDigit(9);
                break;
            case "0":
                inputDigit(0);
                break;
        }
    }

    private void inputDigit(int i) {
        int lengthPassCode = mDigitInput.length();
        switch (lengthPassCode){
            case 0:
                mDigitInput +=i;
                mDigitFirst.setText("*");
                break;
            case 1:
                mDigitInput +=i;
                mDigitSecond.setText("*");
                break;
            case 2:
                mDigitInput +=i;
                mDigitThird.setText("*");
                break;
            case 3:
                mDigitInput +=i;
                mDigitFourth.setText("*");
                break;
            case 4:
                mToast.setText("You are typed enough 4 digit");
                mToast.show();
                //Toast.makeText(getActivity(),"You are typed enough 4 digit",Toast.LENGTH_SHORT).show();
        }
    }
    private void clearDigitInput(){
        int lengthPassCode = mDigitInput.length();
        switch (lengthPassCode) {
            case 0:
                mToast.setText("You still not type any digit yet");
                mToast.show();
                //Toast.makeText(getActivity(), "You still not type any digit yet", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                mDigitInput = "";
                mDigitFirst.setText("");
                break;
            case 2:
                mDigitInput = mDigitInput.substring(0, lengthPassCode - 1);
                mDigitSecond.setText("");
                break;
            case 3:
                mDigitInput = mDigitInput.substring(0, lengthPassCode - 1);
                mDigitThird.setText("");
                break;
            case 4:
                mDigitInput = mDigitInput.substring(0, lengthPassCode - 1);
                mDigitFourth.setText("");
                break;
        }
    }
    private void logIn() {
        if (mDigitInput.length()<4) {
            mToast.setText("You need 4 digit for log in");
            mToast.show();
            //Toast.makeText(getActivity(),"You need 4 digit for log in",Toast.LENGTH_SHORT);
        }
        if(checkPassCode(mDigitInput,"2016")){
            Intent intent = new Intent(getActivity(),HomeActivity.class);
            startActivity(intent);
            getActivity().finish();
            mToast.setText("Log in success");
            mToast.show();
           // Toast.makeText(getActivity(),"Log in success",Toast.LENGTH_SHORT).show();
        }
        else {
            mToast.setText("Log in fail");
            mToast.show();
            //Toast.makeText(getActivity(),"Log in fail",Toast.LENGTH_SHORT).show();
            reInput();
        }
    }

    private void reInput() {
        mDigitInput = "";
        mDigitFirst.setText("");
        mDigitSecond.setText("");
        mDigitThird.setText("");
        mDigitFourth.setText("");
    }

    private boolean checkPassCode(String digitInput, String pass){
        if(digitInput.equals(pass)) return true;
        return false;
    }
    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("PAGE_2");
                    if (fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
