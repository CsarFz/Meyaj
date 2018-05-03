package mx.itesm.cagm.meyaj;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class FechaFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        Button btnDate;

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year,month,day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }
}