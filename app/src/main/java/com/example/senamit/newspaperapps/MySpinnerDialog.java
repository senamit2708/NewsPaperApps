package com.example.senamit.newspaperapps;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.view.Window;

/**
 * Created by senamit on 21/10/17.
 */

public class MySpinnerDialog extends AlertDialog {


    protected MySpinnerDialog(@NonNull Context context) {
        super(context);
    }

    protected MySpinnerDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Dialog dialog = new Dialog (getContext());

        dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
        dialog.setContentView (R.layout.dialog_progress);
        dialog.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);

    }
    public void showSpinner(){

    }

}
