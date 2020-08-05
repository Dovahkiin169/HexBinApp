package com.verkhovskygroup.HexBinApp;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.math.BigInteger;
import static java.lang.Long.parseLong;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnFocusChangeListener  {
    Convert Convert= new Convert();
    Button Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine;
    Button A, B, C, D, E, F;
    Button Clear, Delete, Plus, Minus, Multiple, Divide, Equals;

    EditText EditTextHex;
    EditText EditTextDec;
    EditText EditTextBin;
    EditText EditTextSign;

    String Hex, Bin, Dec;

    Operations Op = new Operations();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        EditTextHex = findViewById(R.id.editTextHex);
        EditTextDec = findViewById(R.id.editTextDec);
        EditTextBin = findViewById(R.id.editTextBin);

        EditTextHex.setOnFocusChangeListener(this);
        EditTextDec.setOnFocusChangeListener(this);
        EditTextBin.setOnFocusChangeListener(this);

        EditTextSign = findViewById(R.id.Sign);
        EditTextSign.setOnFocusChangeListener(this);

        Zero = (Button) findViewById(R.id.But0);
        One = (Button) findViewById(R.id.But1);
        Six = (Button) findViewById(R.id.But6);
        Two = (Button) findViewById(R.id.But2);
        Three = (Button) findViewById(R.id.But3);
        Four = (Button) findViewById(R.id.But4);
        Five = (Button) findViewById(R.id.But5);
        Six = (Button) findViewById(R.id.But6);
        Seven = (Button) findViewById(R.id.But7);
        Eight = (Button) findViewById(R.id.But8);
        Nine = (Button) findViewById(R.id.But9);
        Zero.setOnClickListener(this);
        One.setOnClickListener(this);
        Two.setOnClickListener(this);
        Three.setOnClickListener(this);
        Four.setOnClickListener(this);
        Five.setOnClickListener(this);
        Seven.setOnClickListener(this);
        Eight.setOnClickListener(this);
        Nine.setOnClickListener(this);

        A = (Button) findViewById(R.id.A);
        B = (Button) findViewById(R.id.B);
        C = (Button) findViewById(R.id.C);
        D = (Button) findViewById(R.id.D);
        E = (Button) findViewById(R.id.E);
        F = (Button) findViewById(R.id.F);
        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        E.setOnClickListener(this);
        F.setOnClickListener(this);

        Clear = (Button) findViewById(R.id.Clear);
        Delete = (Button) findViewById(R.id.Delete);
        Clear.setOnClickListener(this);
        Delete.setOnClickListener(this);

        Plus = (Button) findViewById(R.id.ButtonAdd);
        Minus = (Button) findViewById(R.id.ButtonMinus);
        Multiple = (Button) findViewById(R.id.ButtonMultiple);
        Divide = (Button) findViewById(R.id.ButtonDivide);
        Equals = (Button) findViewById(R.id.ButtonEquals);
        Plus.setOnClickListener(this);
        Minus.setOnClickListener(this);
        Multiple.setOnClickListener(this);
        Divide.setOnClickListener(this);
        Equals.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
            EditTextHex.setShowSoftInputOnFocus(false);
            EditTextDec.setShowSoftInputOnFocus(false);
            EditTextBin.setShowSoftInputOnFocus(false);
            EditTextSign.setShowSoftInputOnFocus(false);
        }
        else { // API 11-20
            EditTextHex.setTextIsSelectable(true);
            EditTextDec.setTextIsSelectable(true);
            EditTextBin.setTextIsSelectable(true);
            EditTextSign.setTextIsSelectable(true);
        }

        EditTextHex.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().matches(getString(R.string.HexRegex))) {
                    BigInteger bigInt = new BigInteger(s.toString(),16);
                    BigInteger LongHex = new BigInteger(getString(R.string.HexMax),16);
                    if(bigInt.compareTo(LongHex) > 0) {
                        ClearText();
                        EditTextHex.setError(getString(R.string.MaxQ)+getString(R.string.HexMax));
                    }
                }
                else if (!s.toString().equals(""))
                    EditTextHex.setText(Op.DecimalToHex(parseLong(String.valueOf(EditTextDec.getText()))));
            }
            @Override
            public void afterTextChanged(Editable arg0) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        EditTextDec.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().matches(getString(R.string.DecRegex))) {
                    BigInteger bigInt = new BigInteger(s.toString(), 10);
                    BigInteger LongHex = new BigInteger(String.valueOf(Long.MAX_VALUE), 10);
                    if (bigInt.compareTo(LongHex) > 0) {
                        ClearText();
                        EditTextDec.setError(getString(R.string.MaxQ) + getString(R.string.DecMax));
                    }
                }
                else if (!s.toString().equals(""))
                    EditTextDec.setText(Op.HexToDec(String.valueOf(EditTextHex.getText())));
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                if (EditTextDec.getText().toString().length()==64)
                {
                    EditTextDec.setText(Op.HexToDec(String.valueOf(EditTextHex.getText())));
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        EditTextBin.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String regex = getString(R.string.BinRegex);
                if(s.toString().matches(regex)) {
                    BigInteger bigInt = new BigInteger(s.toString(), 2);
                    BigInteger LongHex = new BigInteger(getString(R.string.BinMaxData), 2);
                    if (bigInt.compareTo(LongHex) > 0) {
                        ClearText();
                        EditTextBin.setError(getString(R.string.MaxQ) + getString(R.string.BinMax));
                    }
                }
                else if(!s.toString().equals(""))
                    EditTextBin.setText(Op.DecimalToBinary(parseLong(String.valueOf(EditTextDec.getText()))));
            }
            @Override
            public void afterTextChanged(Editable arg0) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        EditTextSign.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(EditTextSign.getText().toString().equals("-") && !EditTextDec.getText().toString().equals("") && !EditTextBin.getText().toString().equals("0"))
                    EditTextBin.setText(Long.toBinaryString((-1) * parseLong(EditTextDec.getText().toString())).substring(1));
                else if(EditTextSign.getText().toString().equals("+") && !EditTextDec.getText().toString().equals("")) {
                    EditTextBin.setText(Long.toBinaryString(parseLong(EditTextDec.getText().toString())));
                }
            }
            @Override
            public void afterTextChanged(Editable arg0){}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
        });
    }
    @Override
    public void onClick(View view) {
        Hex = "";
        Bin = "";
        Dec = "";

        switch (view.getId()) {
            case R.id.But0:
                if((EditTextBin.getSelectionStart()!=0 && EditTextBin.isFocused()) || (EditTextDec.getSelectionStart()!=0 && EditTextDec.isFocused()) || (EditTextHex.getSelectionStart()!=0 && EditTextHex.isFocused()))
                {
                    ButtonFunctionsNumbers("0");
                }
                break;
            case R.id.But1:
                ButtonFunctionsNumbers("1");
                break;
            case R.id.But2:
                ButtonFunctionsNumbers("2");
                break;
            case R.id.But3:
                ButtonFunctionsNumbers("3");
                break;
            case R.id.But4:
                ButtonFunctionsNumbers("4");
                break;
            case R.id.But5:
                ButtonFunctionsNumbers("5");
                break;
            case R.id.But6:
                ButtonFunctionsNumbers("6");
                break;
            case R.id.But7:
                ButtonFunctionsNumbers("7");
                break;
            case R.id.But8:
                ButtonFunctionsNumbers("8");
                break;
            case R.id.But9:
                ButtonFunctionsNumbers("9");
                break;
            case R.id.A:
                ButtonFunctionsNumbers("A");
                break;
            case R.id.B:
                ButtonFunctionsNumbers("B");
                break;
            case R.id.C:
                ButtonFunctionsNumbers("C");
                break;
            case R.id.D:
                ButtonFunctionsNumbers("D");
                break;
            case R.id.E:
                ButtonFunctionsNumbers("E");
                break;
            case R.id.F:
                ButtonFunctionsNumbers("F");
                break;

            case R.id.Clear:
                ClearText();
                break;
            case R.id.Delete: {
                Dec = EditTextDec.getText().toString();
                Bin = EditTextBin.getText().toString();
                Hex = EditTextHex.getText().toString();

                int cursorPositionHex = EditTextHex.getSelectionStart();
                int cursorPositionDec = EditTextDec.getSelectionStart();
                int cursorPositionBin = EditTextBin.getSelectionStart();

                if (cursorPositionHex > 0 && EditTextHex.hasFocus()) {
                    EditTextHex.setText(EditTextHex.getText().delete(cursorPositionHex - 1, cursorPositionHex));
                    EditTextHex.setSelection(cursorPositionHex-1);
                    Hex = EditTextHex.getText().toString();
                    if(!Hex.isEmpty())//Hex to Decimal
                        EditTextDec.setText(Op.HexToDec(Hex));
                    if(!Hex.isEmpty())//Hex to Bin
                        EditTextBin.setText(Op.DecimalToBinary(parseLong(Op.HexToDec(Hex))));
                    if(Hex.isEmpty()) {
                        EditTextBin.setText("");
                        EditTextDec.setText("");
                    }
                }
                if (cursorPositionDec > 0 && EditTextDec.hasFocus()) {
                    EditTextDec.setText(EditTextDec.getText().delete(cursorPositionDec - 1, cursorPositionDec));
                    EditTextDec.setSelection(cursorPositionDec-1);
                    Dec = EditTextDec.getText().toString();
                    if(!Dec.isEmpty()) //Decimal to Binary
                        EditTextBin.setText(Op.DecimalToBinary(parseLong(Dec)));
                    if(!Dec.isEmpty())//Decimal to Hex
                        EditTextHex.setText(Op.DecimalToHex(parseLong(Dec)));
                    if(Dec.isEmpty()) {
                        EditTextBin.setText("");
                        EditTextHex.setText("");
                    }
                }
                if (cursorPositionBin > 0 && EditTextBin.hasFocus()) {
                    if(!Bin.isEmpty() && EditTextSign.getText().toString().equals("-")){ //Binary to Decimal
                        String Res = "1" + (EditTextBin.getText().toString().substring(0, EditTextBin.getText().toString().length() - 1));
                        EditTextBin.setText(Res);
                        Bin = EditTextBin.getText().toString();
                        EditTextDec.setText(String.valueOf((-1)*parseLongBigInt("1"+Bin)));
                        EditTextHex.setText(Op.DecimalToHex((-1)*parseLongBigInt("1"+Bin)));
                    }
                    else if(!Bin.isEmpty()) {
                        EditTextBin.setText(EditTextBin.getText().delete(cursorPositionBin - 1, cursorPositionBin));
                        EditTextBin.setSelection(cursorPositionBin-1);
                        Bin = EditTextBin.getText().toString();

                        EditTextDec.setText((String.valueOf(parseLong(Bin,2))));
                        EditTextHex.setText(Op.DecimalToHex(parseLong(Long.toString(parseLong(Bin,2)))));
                    }
                    else {
                        EditTextHex.setText("");
                        EditTextDec.setText("");
                    }
                }
                Convert.convertOperation(EditTextHex, EditTextDec, EditTextBin,EditTextSign);
                if(EditTextHex.isFocused() && cursorPositionHex!=0)
                {
                    EditTextHex.setSelection(cursorPositionHex-1);
                }
                else if(EditTextDec.isFocused() && cursorPositionDec!=0)
                {
                    EditTextDec.setSelection(cursorPositionDec-1);
                }
                else if(EditTextBin.isFocused() && cursorPositionBin!=0 && !EditTextSign.getText().toString().equals("-"))
                {
                    EditTextBin.setSelection(cursorPositionBin-1);
                }
                else if(EditTextBin.isFocused() && cursorPositionBin!=0)
                {
                    EditTextBin.setSelection(cursorPositionBin);
                }
            }
                break;

            case R.id.ButtonAdd: {
                Dec = EditTextDec.getText().toString();
                if(!EditTextSign.getText().toString().equals(""))
                    Op.Sign = EditTextSign.getText().toString();
                if(EditTextSign.isFocused()) {
                    Op.Sign="+";
                    EditTextSign.setText("+");
                }
                else if (!Dec.isEmpty()) {
                    Op.Plus(parseLong(EditTextDec.getText().toString()));
                    ClearText();
                    EditTextSign.setText("");
                }
            }
                break;
            case R.id.ButtonMinus: {
                Dec = EditTextDec.getText().toString();
                if(!EditTextSign.getText().toString().equals(""))
                    Op.Sign = EditTextSign.getText().toString();
                if(EditTextSign.isFocused()){
                    Op.Sign="-";
                    EditTextSign.setText("-");
                }
                else if (!Dec.isEmpty()) {
                    Op.Minus(parseLong(EditTextDec.getText().toString()));
                    ClearText();
                    EditTextSign.setText("");
                }
                break;
            }
            case R.id.ButtonMultiple: {
                Dec = EditTextDec.getText().toString();
                if(!EditTextSign.getText().toString().equals(""))
                    Op.Sign = EditTextSign.getText().toString();
                if (!Dec.isEmpty()) {
                    Op.Multiple(parseLong(EditTextDec.getText().toString()));
                    ClearText();
                    EditTextSign.setText("");
                }
                break;
            }
            case R.id.ButtonDivide: {
                Dec = EditTextDec.getText().toString();
                if(!EditTextSign.getText().toString().equals(""))
                {
                    Op.Sign = EditTextSign.getText().toString();
                }
                if (!Dec.isEmpty()) {
                    Op.Divide(parseLong(EditTextDec.getText().toString()));
                    ClearText();
                    EditTextSign.setText("");
                }
                break;
            }
            case R.id.ButtonEquals: {
                Dec = EditTextDec.getText().toString();
                Op.Sign = EditTextSign.getText().toString();
                if (!Dec.isEmpty()) {
                    Long Result = Op.Equals(parseLong(EditTextDec.getText().toString()));
                    if(Op.Flag.equals("Error")) {
                        Toast.makeText(getApplicationContext(), "Sorry, result is too large", Toast.LENGTH_LONG).show();
                    }
                    else if(Result>=0) {
                        EditTextSign.setText("+");
                        EditTextHex.setText(Op.DecimalToHex(Result));
                        EditTextDec.setText(String.valueOf(Result));
                        EditTextBin.setText(Op.DecimalToBinary(Result));
                    }
                    else {
                        EditTextSign.setText("-");
                        EditTextHex.setText(Op.DecimalToHex( Math.abs(Result)));
                        EditTextDec.setText(String.valueOf( Math.abs(Result)));
                        EditTextBin.setText(Long.toBinaryString(Result).substring(1));
                    }
                }
                break;
            }
            default:
                break;
        }
    }

    private static long parseLongBigInt(String s) {
        return new BigInteger(s, 2).longValue();
    }

    public void ButtonFunctionsNumbers(CharSequence PassedSymbol) {

        if(EditTextBin.hasFocus()) {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), PassedSymbol);
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus()) {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), PassedSymbol);
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus()) {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), PassedSymbol);
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert.convertOperation(EditTextHex, EditTextDec, EditTextBin,EditTextSign);
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch(v.getId()){
            case R.id.editTextBin:
                SpecBoolean(true);
                if(A.isEnabled())
                    HexBoolean(false);
                if(Two.isEnabled())
                    DecBoolean(false);
                break;
            case R.id.editTextDec:
                SpecBoolean(true);
                if(A.isEnabled())
                    HexBoolean(false);
                if(!Two.isEnabled())
                    DecBoolean(true);
                break;
            case R.id.editTextHex:
                SpecBoolean(true);
                if(!A.isEnabled())
                    HexBoolean(true);
                if(!Two.isEnabled())
                    DecBoolean(true);
                break;
            case R.id.Sign:
                if(A.isEnabled())
                    HexBoolean(false);
                if(Two.isEnabled())
                    DecBoolean(false);
                SpecBoolean(false);
                break;
        }
    }
    public void ClearText() {
        EditTextBin.setText("");
        EditTextDec.setText("");
        EditTextHex.setText("");
    }
    public void HexBoolean(boolean Bl) {
        A.setEnabled(Bl);
        B.setEnabled(Bl);
        C.setEnabled(Bl);
        D.setEnabled(Bl);
        E.setEnabled(Bl);
        F.setEnabled(Bl);
    }
    public void DecBoolean(boolean Bl) {
        Two.setEnabled(Bl);
        Three.setEnabled(Bl);
        Four.setEnabled(Bl);
        Five.setEnabled(Bl);
        Six.setEnabled(Bl);
        Seven.setEnabled(Bl);
        Eight.setEnabled(Bl);
        Nine.setEnabled(Bl);
    }
    public void SpecBoolean(boolean Bl) {
        Clear.setEnabled(Bl);
        Delete.setEnabled(Bl);
        Divide.setEnabled(Bl);
        Multiple.setEnabled(Bl);
        One.setEnabled(Bl);
        Zero.setEnabled(Bl);
    }
}