package com.verkhovskygroup.HexBinApp;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.math.BigInteger;
import java.util.Objects;
import static java.lang.Long.parseLong;


public class MainActivity extends BaseActivity implements View.OnClickListener,View.OnFocusChangeListener  {
    Convert Convert= new Convert();
    Button Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine;
    Button A, B, C, D, E, F;
    Button Clear, Delete, Plus, Minus, Multiple, Divide, Equals;

    EditTextPasteFix EditTextHex;
    EditTextPasteFix EditTextDec;
    EditTextPasteFix EditTextBin;
    EditText EditTextSign;

    String Hex, Bin, Dec;

    Operations Op = new Operations();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hex_bin_layout);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        EditTextHex = findViewById(R.id.editTextHex);
        EditTextDec = findViewById(R.id.editTextDec);
        EditTextBin = findViewById(R.id.editTextBin);

        EditTextHex.setOnFocusChangeListener(this);
        EditTextDec.setOnFocusChangeListener(this);
        EditTextBin.setOnFocusChangeListener(this);

        EditTextSign = findViewById(R.id.Sign);
        EditTextSign.setOnFocusChangeListener(this);

        Zero = findViewById(R.id.But0);
        One = findViewById(R.id.But1);
        Six = findViewById(R.id.But6);
        Two = findViewById(R.id.But2);
        Three = findViewById(R.id.But3);
        Four = findViewById(R.id.But4);
        Five =  findViewById(R.id.But5);
        Six = findViewById(R.id.But6);
        Seven = findViewById(R.id.But7);
        Eight = findViewById(R.id.But8);
        Nine = findViewById(R.id.But9);
        Zero.setOnClickListener(this);
        One.setOnClickListener(this);
        Two.setOnClickListener(this);
        Three.setOnClickListener(this);
        Four.setOnClickListener(this);
        Five.setOnClickListener(this);
        Seven.setOnClickListener(this);
        Eight.setOnClickListener(this);
        Nine.setOnClickListener(this);

        A = findViewById(R.id.A);
        B = findViewById(R.id.B);
        C = findViewById(R.id.C);
        D = findViewById(R.id.D);
        E = findViewById(R.id.E);
        F = findViewById(R.id.F);
        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        E.setOnClickListener(this);
        F.setOnClickListener(this);

        Clear = findViewById(R.id.Clear);
        Delete = findViewById(R.id.Delete);
        Clear.setOnClickListener(this);
        Delete.setOnClickListener(this);

        Plus = findViewById(R.id.ButtonAdd);
        Minus = findViewById(R.id.ButtonMinus);
        Multiple = findViewById(R.id.ButtonMultiple);
        Divide = findViewById(R.id.ButtonDivide);
        Equals = findViewById(R.id.ButtonEquals);
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
                if (Objects.requireNonNull(EditTextDec.getText()).toString().length()==64)
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
                if(EditTextSign.getText().toString().equals("-") && !Objects.requireNonNull(EditTextDec.getText()).toString().equals("") && !Objects.requireNonNull(EditTextBin.getText()).toString().equals("0"))
                    EditTextBin.setText(Long.toBinaryString((-1) * parseLong(EditTextDec.getText().toString())).substring(1));
                else if(EditTextSign.getText().toString().equals("+") && !Objects.requireNonNull(EditTextDec.getText()).toString().equals("")) {
                    EditTextBin.setText(Long.toBinaryString(parseLong(EditTextDec.getText().toString())));
                }
            }
            @Override
            public void afterTextChanged(Editable arg0){}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
        });

        EditTextHex.addListener(() -> Convert.convertOperation(EditTextHex, EditTextDec, EditTextBin,EditTextSign));
        EditTextDec.addListener(() -> Convert.convertOperation(EditTextHex, EditTextDec, EditTextBin,EditTextSign));
        EditTextBin.addListener(() -> Convert.convertOperation(EditTextHex, EditTextDec, EditTextBin,EditTextSign));
    }
    @Override
    public void onClick(View view) {
        Hex = "";
        Bin = "";
        Dec = "";



        switch (view.getId()) {
            case R.id.But0:
                if((EditTextBin.getSelectionStart()!=0 && EditTextBin.isFocused()) || (EditTextDec.getSelectionStart()!=0 && EditTextDec.isFocused()) || (EditTextHex.getSelectionStart()!=0 && EditTextHex.isFocused()))
                    ButtonFunctionsNumbers("0");
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
                Dec = Objects.requireNonNull(EditTextDec.getText()).toString();
                Bin = Objects.requireNonNull(EditTextBin.getText()).toString();
                Hex = Objects.requireNonNull(EditTextHex.getText()).toString();

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
                Dec = Objects.requireNonNull(EditTextDec.getText()).toString();
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
                Dec = Objects.requireNonNull(EditTextDec.getText()).toString();
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
                Dec = Objects.requireNonNull(EditTextDec.getText()).toString();
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
                Dec = Objects.requireNonNull(EditTextDec.getText()).toString();
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
                Dec = Objects.requireNonNull(EditTextDec.getText()).toString();
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
            Objects.requireNonNull(EditTextBin.getText()).insert(EditTextBin.getSelectionStart(), PassedSymbol);
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus()) {
            Objects.requireNonNull(EditTextDec.getText()).insert(EditTextDec.getSelectionStart(), PassedSymbol);
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus()) {
            Objects.requireNonNull(EditTextHex.getText()).insert(EditTextHex.getSelectionStart(), PassedSymbol);
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert.convertOperation(EditTextHex, EditTextDec, EditTextBin, EditTextSign);
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
                if(EditTextSign.getText().toString().equals("-"))
                    BinBoolean(false);
                else if (!EditTextSign.getText().toString().equals("-"))
                    BinBoolean(true);
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
    public void BinBoolean(boolean Bl) {
        One.setEnabled(Bl);
        Zero.setEnabled(Bl);
    }

    public void saveData(int theme) {
        Utility.setTheme(getApplicationContext(), theme);
        Utility.setSign(getApplicationContext(), EditTextSign.getText().toString());
        Utility.setData(getApplicationContext(), Objects.requireNonNull(EditTextDec.getText()).toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        EditTextDec.setText(Utility.getData(getApplicationContext()));
        EditTextSign.setText(Utility.getSign(getApplicationContext()));
        Convert.flag=1;
        Convert.convertOperation(EditTextHex, EditTextDec, EditTextBin,EditTextSign);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.theme_button && !GetStatus()) {
            saveData(1);
            recreate();
        }
        else if (item.getItemId() == R.id.theme_button && GetStatus()) {
            saveData(2);
            recreate();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        saveData(Utility.getTheme(getApplicationContext()));
        if(Utility.getSign(getApplicationContext()).equals("-") && Objects.requireNonNull(EditTextDec.getText()).toString().equals(""))
            Utility.setSign(getApplicationContext(), "+");
    }
}