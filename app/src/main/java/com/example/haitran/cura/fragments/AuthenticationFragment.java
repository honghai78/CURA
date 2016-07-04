package com.example.haitran.cura.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haitran.cura.KeyBoardEffect;
import com.example.haitran.cura.R;
import com.example.haitran.cura.activities.HomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kha.phan on 6/28/2016.
 */
public class AuthenticationFragment extends Fragment implements View.OnClickListener{

    private String educatorName;
    private TextView mTextHello;
    private TextView mDigitFirst, mDigitSecond, mDigitThird,mDigitFourth;
    private List<TextView> mListDigitInput;
    private TextView mKey_1, mKey_2, mKey_3, mKey_4, mKey_5, mKey_6,
            mKey_7, mKey_8, mKey_9, mKey_0;
    private View mLayout;
    private TextView mResendCode,mKeyClear, mKeyTick;
    private int[] mDigitInput = {-1,-1,-1,-1};
    private int mTextDigitSelected = 1;
    private Toast mToast;
    private KeyBoardEffect mKeyBoardEffect;


    public void setNameEducator(String educatorName) {
        this.educatorName = educatorName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
       AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.getSupportActionBar().show();
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
        addEffectKeyBoard();
        changeBgTextViewSelected(mTextDigitSelected);
        if (educatorName!=null) mTextHello.setText(mTextHello.getText() + " "+educatorName +",");
        else mTextHello.setText(mTextHello.getText() + " Mrs.Kha" +",");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mToast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
    }

    private void getWidgets(View view){
        mLayout = (View) view.findViewById(R.id.layout_full);
        mTextHello  = (TextView) view.findViewById(R.id.text_hello);
        mDigitFirst = (TextView) view.findViewById(R.id.text_digit_first);
        mDigitSecond = (TextView) view.findViewById(R.id.text_digit_second);
        mDigitThird = (TextView) view.findViewById(R.id.text_digit_third);
        mDigitFourth = (TextView) view.findViewById(R.id.text_digit_fourth);
        mListDigitInput = new ArrayList<TextView>();
        mListDigitInput.add(mDigitFirst);
        mListDigitInput.add(mDigitSecond);
        mListDigitInput.add(mDigitThird);
        mListDigitInput.add(mDigitFourth);
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
        mResendCode = (TextView) view.findViewById(R.id.text_resend_code);
        mKeyClear = (TextView) view.findViewById(R.id.text_clear);
        mKeyTick = (TextView) view.findViewById(R.id.text_tick);
    }
    private void addEffectKeyBoard(){
        mKeyBoardEffect = new KeyBoardEffect();
        mKeyBoardEffect.setButtonEffect(mKey_0);
        mKeyBoardEffect.setButtonEffect(mKey_1);
        mKeyBoardEffect.setButtonEffect(mKey_2);
        mKeyBoardEffect.setButtonEffect(mKey_3);
        mKeyBoardEffect.setButtonEffect(mKey_4);
        mKeyBoardEffect.setButtonEffect(mKey_5);
        mKeyBoardEffect.setButtonEffect(mKey_6);
        mKeyBoardEffect.setButtonEffect(mKey_7);
        mKeyBoardEffect.setButtonEffect(mKey_8);
        mKeyBoardEffect.setButtonEffect(mKey_9);
        mKeyBoardEffect.setButtonEffect(mKeyClear);
        mKeyBoardEffect.setButtonEffect(mKeyTick);

    }
    private void addEvent(){
        mDigitFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDigitFirst.setText("");
                mTextDigitSelected = 1;
                mDigitInput[mTextDigitSelected-1]=-1;
                changeBgTextViewSelected(mTextDigitSelected);
            }
        });
        mDigitSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDigitSecond.setText("");
                mTextDigitSelected = 2;
                mDigitInput[mTextDigitSelected-1]=-1;
                changeBgTextViewSelected(mTextDigitSelected);
            }
        });
        mDigitThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDigitThird.setText("");
                mTextDigitSelected = 3;
                mDigitInput[mTextDigitSelected-1]=-1;
                changeBgTextViewSelected(mTextDigitSelected);
            }
        });
        mDigitFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDigitFourth.setText("");
                mTextDigitSelected = 4;
                mDigitInput[mTextDigitSelected-1]=-1;
                changeBgTextViewSelected(mTextDigitSelected);
            }
        });
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
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
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
        mResendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private void changeBgTextViewSelected(int textDigitSelected){
        for (int i =0; i<mListDigitInput.size(); i++){
            int index = i+1;
            if (index == textDigitSelected) {
                mListDigitInput.get(i).setBackgroundResource(R.drawable.bg_textview_selected);
            }
            else mListDigitInput.get(i).setBackgroundResource(R.drawable.bg_dashed_line);
        }

    }

    private void inputDigit(int i) {
        if (lengthDigitInput()==4) {
            Toast.makeText(getActivity(),"You are typed enough 4 digit",Toast.LENGTH_SHORT).show();
        }
        switch (mTextDigitSelected){
            case 1:
                mDigitInput[0] = i;
                mDigitFirst.setText("*");
                mTextDigitSelected = nextInput();
                changeBgTextViewSelected(mTextDigitSelected);
                break;
            case 2:
                mDigitInput[1] = i;
                mDigitSecond.setText("*");
                mTextDigitSelected= nextInput();
                changeBgTextViewSelected(mTextDigitSelected);
                break;
            case 3:
                mDigitInput[2] = i;
                mDigitThird.setText("*");
                mTextDigitSelected= nextInput();
                changeBgTextViewSelected(mTextDigitSelected);
                break;
            case 4:
                mDigitInput[3] = i;
                mDigitFourth.setText("*");
                mTextDigitSelected= nextInput();
                Log.d("+++backInput", ",Input: +" + mTextDigitSelected);
                changeBgTextViewSelected(mTextDigitSelected);
                break;
        }
    }
    private void clearDigitInput(){
        if(mTextDigitSelected==1&&lengthDigitInput()==0){
            mToast.setText("You still not type any number yet");
            mToast.show();
            return;
        }
        if(mTextDigitSelected == 5){
            mTextDigitSelected =4;

        }
        else {
            mTextDigitSelected = backInput();
        }
        mDigitInput[mTextDigitSelected-1] = -1;
        mListDigitInput.get(mTextDigitSelected-1).setText("");
        changeBgTextViewSelected(mTextDigitSelected);

    }
    private void logIn() {
        if (lengthDigitInput()<4){
            mToast.setText("Please input 4 digit for login");
            mToast.show();
            return;
        }
        String passInput ="";
        for(int code: mDigitInput){
            passInput += code;
        }
        if(checkPassCode(passInput,"2016")){
            Intent intent = new Intent(getActivity(),HomeActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        else {
            reInput();
        }

    }
    private int lengthDigitInput(){
        int length = 0;
        for (int digit : mDigitInput){
            if(digit >=0) length ++;
        }
        return length;
    }
    private int nextInput(){
        int index = 5;
        for(int i = mTextDigitSelected-1; i< mDigitInput.length ; i++) {
            if (mDigitInput[i]<0) return i+1;
        }
        for(int i=0; i<mTextDigitSelected-1;i++){
            if (mDigitInput[i]<0) return i+1;
        }
        return index;
    }
    private int backInput(){
        int index = 1;
        Log.d("+++backInput", ",TextDigitSelected: +" + mTextDigitSelected);
        for(int i = mTextDigitSelected-1; i>=0 ; i--) {
            if (mDigitInput[i]>=0) return i+1;
        }
        for (int i = mDigitInput.length-1; i>mTextDigitSelected-1; i-- ){
            if (mDigitInput[i]>=0) return i+1;
        }
        return index;

    }
    private void reInput() {
        for(int i=0; i<mDigitInput.length;i++){
            mDigitInput[i]=-1;
        }
        mTextDigitSelected= 1;
        changeBgTextViewSelected(mTextDigitSelected);
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
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.transition.sli_re_in, R.transition.sli_re_out);
                        fragmentTransaction.remove(fragment).commit();
                        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
                    }
                    return true;
                }
                return false;
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
}
