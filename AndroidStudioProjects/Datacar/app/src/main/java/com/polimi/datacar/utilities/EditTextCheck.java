package com.polimi.datacar.utilities;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.List;

public class EditTextCheck {

    public static boolean checkEditTextView(EditText editText) {
        return !TextUtils.isEmpty(editText.getText().toString());
    }
}
