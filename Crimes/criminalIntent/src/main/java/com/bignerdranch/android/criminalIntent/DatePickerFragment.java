package com.bignerdranch.android.criminalIntent;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";

    private Date mDay;

    public static DatePickerFragment newInstance(Date date) {

        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;

    }

    private void sendResult(int resultCode) {

        if (getTargetFragment() == null)
            return;

        Intent i = new Intent();
        i.putExtra(EXTRA_DATE, mDay);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, i);


    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mDay = (Date) getArguments().getSerializable(EXTRA_DATE);

        // Create a calendar to handle the Date timestamp
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDay);
        int yeari = calendar.get(Calendar.YEAR);
        int monthi = calendar.get(Calendar.MONTH);
        int dayi = calendar.get(Calendar.DAY_OF_MONTH);

        final int hour = calendar.get(Calendar.HOUR);
        final int minutes = calendar.get(Calendar.MINUTE);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mDay = new GregorianCalendar(year, monthOfYear, dayOfMonth, hour, minutes).getTime();
                    sendResult(Activity.RESULT_OK);
                }
            }, yeari, monthi, dayi);

            return dialog;

        } else {

            View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
            DatePicker datePicker = (DatePicker) v.findViewById(R.id.dialog_date_datePicker);
            datePicker.init(yeari, monthi, dayi, new DatePicker.OnDateChangedListener() {

                @Override
                public void onDateChanged(DatePicker view, int year, int month, int day) {

                    // Translate year, month, day into a Date object using a calendar
                    mDay = new GregorianCalendar(year, month, day, hour, minutes).getTime();

                    // Update arguments to preserve selected value on rotation
                    getArguments().putSerializable(EXTRA_DATE, mDay);

                }
            });




            return new AlertDialog.Builder(getActivity())
                    .setView(v)
                    .setTitle(R.string.date_picker_title)
                    .setPositiveButton(
                            android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    sendResult(Activity.RESULT_OK);

                                }
                            }
                    )
                    .create();


        }
    }



}