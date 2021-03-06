package com.verkhovskygroup.HexBinApp;
import android.view.KeyEvent;
import android.widget.EditText;
import java.math.BigInteger;

public class Convert {
    int flag=0;
    public void convertOperation(EditText EditTextHex, EditText EditTextDec, EditText EditTextBin, EditText EditTextSign) {
        String Hex = EditTextHex.getText().toString();
        String Bin = EditTextBin.getText().toString();
        String Dec = EditTextDec.getText().toString();
        String Buff;

        Operations Op= new Operations();
        Op.Sign=EditTextSign.getText().toString();
        EditTextBin.setOnKeyListener((v, key, e) -> key >= KeyEvent.KEYCODE_2 && key <= KeyEvent.KEYCODE_Z);
        int SelectorBin;
        int SelectorDec;
        int SelectorHex;
        SelectorDec = EditTextDec.getSelectionStart();
        SelectorBin = EditTextBin.getSelectionStart();
        SelectorHex = EditTextHex.getSelectionStart();

        if(!Dec.isEmpty() && (EditTextDec.isFocused() || flag ==1))
        {
            EditTextDec.setText(Dec);
            EditTextBin.setText(Op.DecimalToBinary(Long.parseLong(Dec)));
            EditTextHex.setText(Op.DecimalToHex(Long.parseLong(Dec)));
            Hex="";
            Bin="";
        }
        if(!Bin.isEmpty() && (EditTextBin.isFocused() || flag ==1)) {
            if(Op.Sign.equals("-"))
                Buff= Long.toString((-1)*(new BigInteger("1" + Bin, 2).longValue()));
            else
                Buff= Long.toString(new BigInteger(Bin, 2).longValue());
            EditTextBin.setText(Bin);
            EditTextDec.setText(Buff);
            EditTextHex.setText(Op.DecimalToHex(Long.parseLong(EditTextDec.getText().toString())));
            Hex="";
        }
        if(!Hex.isEmpty() && (EditTextHex.isFocused() || flag ==1))
        {
            EditTextBin.setText(Op.DecimalToBinary(Long.parseLong(Op.HexToDec(Hex))));
            EditTextDec.setText(Op.HexToDec(Hex));
            EditTextHex.setText(Hex);
        }

        EditTextDec.setSelection(SelectorDec);
        EditTextBin.setSelection(SelectorBin);
        EditTextHex.setSelection(SelectorHex);
        flag=0;
    }
}
