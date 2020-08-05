package com.verkhovskygroup.myapplication;
import android.view.KeyEvent;
import android.widget.EditText;
import java.math.BigInteger;

public class Convert {
    public void convertOperation(EditText EditTextHex, EditText EditTextDec, EditText EditTextBin, EditText EditTextSign) {
        String Hex = EditTextHex.getText().toString();
        String Bin = EditTextBin.getText().toString();
        String Dec = EditTextDec.getText().toString();
        String Buff;
        String Buff2;

        Operations Op= new Operations();
        Op.Sign=EditTextSign.getText().toString();
        EditTextBin.setOnKeyListener((v, key, e) -> key >= KeyEvent.KEYCODE_2 && key <= KeyEvent.KEYCODE_Z);

        if(!Dec.isEmpty())
        {
            EditTextBin.setText(Op.DecimalToBinary(Long.parseLong(Dec)));
            EditTextHex.setText(Op.DecimalToHex(Long.parseLong(Dec)));
        }
        if(!Bin.isEmpty()) {
            if(Op.Sign.equals("-"))
                Buff= Long.toString((-1)*(new BigInteger("1" + Bin, 2).longValue()));
            else
                Buff= Long.toString(new BigInteger(Bin, 2).longValue());
            EditTextDec.setText(Buff);
            EditTextHex.setText(Op.DecimalToHex(Long.parseLong(EditTextDec.getText().toString())));
        }
        if(!Hex.isEmpty())
        {
            EditTextBin.setText(Op.DecimalToBinary(Long.parseLong(Op.HexToDec(Hex))));
            EditTextDec.setText(Op.HexToDec(Hex));
        }

    }
}
