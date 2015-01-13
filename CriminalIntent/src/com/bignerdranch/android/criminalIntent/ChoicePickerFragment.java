package com.bignerdranch.android.criminalIntent;

import android.app.*;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public class ChoicePickerFragment extends DialogFragment {

    public int mChoice;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setPositiveButton(R.string.date_choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mChoice = 1;
                        sendResult(Activity.RESULT_OK);

                    }
                });


    }
}