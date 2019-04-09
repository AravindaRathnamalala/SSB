package com.epic.ssb.util;

import android.content.Context;

import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDatePicker {
    public static void showDatePicker(Context context, final DatePickerEvent listener) {
        new SpinnerDatePickerDialogBuilder()
                .context(context)
                .callback(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(com.tsongkha.spinnerdatepicker.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        listener.onDatePicked(dayOfMonth + "-" + formatMonth(String.valueOf(monthOfYear + 1)) + "-" + year);
//                        listener.onDatePicked(dateLongFormat(year + "-" + monthOfYear + "-" + dayOfMonth));
                    }
                })
                .showTitle(true)
                .showDaySpinner(true)
                .defaultDate(2000, 0, 1)
                .maxDate(2020, 0, 1)
                .minDate(1930, 0, 1)
                .build()
                .show();
    }

    private static String formatMonth(String month) {
        try {
            SimpleDateFormat monthParse = new SimpleDateFormat("MM");
            SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
            return monthDisplay.format(monthParse.parse(month));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String dateLongFormat(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");
        Date date = null;
        try {
            date = format.parse(dateString);
            String fDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null)
            return "" + date.getTime();
        else
            return "";
    }

//    private static String longFormat(String month) {
//        try {
//            SimpleDateFormat monthParse = new SimpleDateFormat("MMMM");
//            SimpleDateFormat monthDisplay = new SimpleDateFormat("MM");
//            String dateString = monthDisplay.format(monthParse.parse(month));
//            Date date = null;
//            try {
//                date = monthDisplay.parse(dateString);
//                System.out.println(date);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            return "" + date.getTime();
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return "";
//        }
//    }

    public interface DatePickerEvent {
        void onDatePicked(String date);
    }
}
