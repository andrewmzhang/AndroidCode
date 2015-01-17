package com.bignerdranch.android.criminalIntent;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class ChoicePickerFragment extends DialogFragment {

    private int mChoice;
    public static String EXTRA_CHOICE = "com.bignerdranch.android.criminalintent.choice";
    public static int DATE_CHOICE = 1;
    public static int TIME_CHOICE = 2;

    public static ChoicePickerFragment newInstance(){

        return new ChoicePickerFragment();

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        return new AlertDialog.Builder(getActivity())
                .setPositiveButton(R.string.date_choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mChoice = DATE_CHOICE;
                        sendResult(Activity.RESULT_OK);

                    }
                })
                .setNegativeButton(R.string.time_choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mChoice = TIME_CHOICE;
                        sendResult(Activity.RESULT_OK);

                    }
                })
                .create();
    }

    private void sendResult(int resultCode) {

        if(getTargetFragment() == null)
            return;

        Intent i = new Intent();
        i.putExtra(EXTRA_CHOICE, mChoice);

        getTargetFragment()
            .onActivityResult(getTargetRequestCode(), resultCode, i);

    }


}