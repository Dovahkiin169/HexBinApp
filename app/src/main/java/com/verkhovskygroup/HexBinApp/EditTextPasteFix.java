package com.verkhovskygroup.HexBinApp;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;

public class EditTextPasteFix extends androidx.appcompat.widget.AppCompatEditText {
    ArrayList<EditTextPasteFixListener> Listener;

    public EditTextPasteFix(Context context) {
        super(context);
        Listener = new ArrayList<>();
    }

    public EditTextPasteFix(Context context, AttributeSet attrs) {
        super(context, attrs);
        Listener = new ArrayList<>();
    }

    public EditTextPasteFix(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Listener = new ArrayList<>();
    }

    public void addListener(EditTextPasteFixListener listener) {
        try {
            Listener.add(listener);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        boolean consumed = super.onTextContextMenuItem(id);
        switch (id) {
            case android.R.id.cut:
                onTextCut();
                break;
            case android.R.id.paste:
                onTextPaste();
                break;
            case android.R.id.copy:
                onTextCopy();
        }
        return consumed;
    }

    public void onTextCut() {
    }

    public void onTextCopy() {
    }

    public void onTextPaste() {
        for (EditTextPasteFixListener listener : Listener) {
            listener.onUpdate();
        }
    }
}