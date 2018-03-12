package com.verkhovskygroup.myapplication;


import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.view.KeyEvent;
import java.math.BigInteger;



public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        { // API 21
            EditTextHex.setShowSoftInputOnFocus(false);
            EditTextDec.setShowSoftInputOnFocus(false);
            EditTextBin.setShowSoftInputOnFocus(false);
        }
        else
            { // API 11-20
            EditTextHex.setTextIsSelectable(true);
            EditTextDec.setTextIsSelectable(true);
            EditTextBin.setTextIsSelectable(true);
        }



        EditTextHex.addTextChangedListener(new TextWatcher()
        {
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.toString().equals(""))
                {
                    s="0";
                }
                BigInteger bigInt = new BigInteger(s.toString(),16);
                BigInteger LongHex = new BigInteger("7FFFFFFFFFFFFFFF",16);
                System.out.println(bigInt);
                if(bigInt.compareTo(LongHex) > 0)
                {
                    EditTextHex.setText("");
                    EditTextHex.setError("Maximum quantity = 7FFF FFFF FFFF FFFF");
                }
            }
            @Override
            public void afterTextChanged(Editable arg0) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        EditTextDec.addTextChangedListener(new TextWatcher()
        {
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.toString().equals(""))
                {
                    s="0";
                }
                BigInteger bigInt = new BigInteger(s.toString(),10);
                BigInteger LongHex = new BigInteger(String.valueOf(Long.MAX_VALUE),10);
                System.out.println(bigInt);
                if(bigInt.compareTo(LongHex) > 0)
                {
                    EditTextDec.setText("");
                    EditTextDec.setError("Maximum quantity = 922 3372 0368 5477 5807");
                }
            }
            @Override
            public void afterTextChanged(Editable arg0) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        EditTextBin.addTextChangedListener(new TextWatcher()
        {
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.toString().equals(""))
                {
                    s="0";
                }
                BigInteger bigInt = new BigInteger(s.toString(),2);
                BigInteger LongHex = new BigInteger("111111111111111111111111111111111111111111111111111111111111111",2);
                System.out.println("Long"+LongHex);
                System.out.println("BigInt"+bigInt);
                if(bigInt.compareTo(LongHex) > 0)
                {
                    EditTextBin.setText("");
                    EditTextBin.setError("Maximum quantity = 111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111");
                }
            }
            @Override
            public void afterTextChanged(Editable arg0) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
    }


    public void Convert(View view)
    {

        String Hex = new String();
        String Bin = new String();
        String Dec = new String();
        String Buff = new String();
        String Buff2 = new String();

        Operations Op= new Operations();

        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        EditTextBin.setOnKeyListener((v, key, e) ->
        {
            return key >= KeyEvent.KEYCODE_2 && key <= KeyEvent.KEYCODE_Z ? true : false;
        });


        Dec = EditTextDec.getText().toString();
        Bin = EditTextBin.getText().toString();
        Hex = EditTextHex.getText().toString();


        if(!Dec.isEmpty()) /**Decimal to Binary*/
        {
            Buff = Op.DecimalToBinary(Long.valueOf(Dec));
            EditTextBin.setText(Buff);
        }
         if(!Bin.isEmpty()) /**Binary to Decimal*/
        {
            long decimal=Long.parseLong(Bin,2);
            Buff= Long.toString(decimal);
            EditTextDec.setText(Buff);
        }
        if(!Dec.isEmpty()) //Decimal to Hex
        {
            Buff= Op.DecimalToHex(Long.valueOf(Dec));
            EditTextHex.setText(Buff);
        }
        if(!Hex.isEmpty()) //Hex to Decimal
        {
            Buff= Op.HexToDec(Hex);
            EditTextDec.setText(Buff);
        }
        if(!Hex.isEmpty()) //Hex to Bin
        {
            Buff = Op.HexToDec(Hex);
            Buff2 = Op.DecimalToBinary(Long.valueOf(Buff));
            EditTextBin.setText(Buff2);
        }
        if(!Bin.isEmpty()) //Bin to Hex
        {
            Buff= Long.toString(Long.parseLong(Bin,2));
            Buff2 = Op.DecimalToHex(Long.valueOf(Buff));
            EditTextHex.setText(Buff2);
        }
    }

    public class Operations
    {
        public String DecimalToBinary(long number)
        {
            long binary[] = new long[64];
            int index = 0;
            String bin="";
            while(number > 0)
            {
                binary[index++] = number%2;
                number = number/2;
            }
            for(int i = index-1;i >= 0;i--)
            {
                bin+=binary[i];
            }
            return bin;
        }
        public  String DecimalToHex(long d)
        {
            String digits = "0123456789ABCDEF";
            if (d <= 0)
            {
                return "0";
            }
            long base = 16;   // flexible to change in any base under 16
            String hex = "";
            while (d > 0)
            {
                long Digit = d % base;              // rightmost digit
                int digit = (int)Digit;

                hex = digits.charAt(digit) + hex;  // string concatenation
                d = d / base;
            }
            return hex;
        }
        public  String HexToDec(String s)
        {
            String digits = "0123456789ABCDEF";
            s = s.toUpperCase();
            long val = 0;
            for (int i = 0; i < s.length(); i++)
            {
                char c = s.charAt(i);
                long d = digits.indexOf(c);
                val = 16*val + d;
            }
            String str = Long.toString(val);
            return str;
        }
        public String Keep(String s)
        {
            String k = s;
            return k;
        }
    }

    public void Clear(View view)
    {
        String Hex = new String();
        String Bin = new String();
        String Dec = new String();

        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        Bin="";
        Dec="";
        Hex="";

        EditTextBin.setText(Bin);
        EditTextDec.setText(Dec);
        EditTextHex.setText(Hex);
    }
    public void But1(View view)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);


        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "1");
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "1");
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "1");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(view);
    }
    public void But2(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "2");
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "2");
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "2");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void But3(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "3");
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "3");
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "3");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void But4(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "4");
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "4");
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "4");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void But5(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "5");
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "5");
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "5");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void But6(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "6");
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "6");
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "6");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void But7(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "7");
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "7");
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "7");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void But8(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "8");
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "8");
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "8");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void But9(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "9");
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "9");
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "9");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void ButA(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "A");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void ButB(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "B");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void ButC(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "C");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void ButD(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "D");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void ButE(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "E");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void ButF(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "F");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void But0(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "0");
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "0");
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "0");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
        Convert(vieW);
    }
    public void ButAdd(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);
        Operations Op = new Operations();

        String Hex = new String();
        String Bin = new String();
        String Dec = new String();
        String Buff = new String();

        Dec = EditTextDec.getText().toString();
        Bin = EditTextBin.getText().toString();
        Hex = EditTextHex.getText().toString();

        if(EditTextBin.hasFocus() && !Bin.isEmpty())
        {
            Buff = Bin;
            Op.Keep(Buff);
        }
        if(EditTextDec.hasFocus() && !Dec.isEmpty())
        {
            Buff = Dec;
            Op.Keep(Buff);
        }
        if(EditTextHex.hasFocus() && !Hex.isEmpty())
        {
            Buff = Hex;
            Op.Keep(Buff);
        }
    }
    public void ButMinus(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "0");
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "0");
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "0");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
    }
    public void ButDivide(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "0");
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "0");
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "0");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
    }
    public void ButMultiply(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        if(EditTextBin.hasFocus())
        {
            EditTextBin.getText().insert(EditTextBin.getSelectionStart(), "0");
            EditTextDec.setText("");
            EditTextHex.setText("");
        }
        if(EditTextDec.hasFocus())
        {
            EditTextDec.getText().insert(EditTextDec.getSelectionStart(), "0");
            EditTextBin.setText("");
            EditTextHex.setText("");
        }
        if(EditTextHex.hasFocus())
        {
            EditTextHex.getText().insert(EditTextHex.getSelectionStart(), "0");
            EditTextBin.setText("");
            EditTextDec.setText("");
        }
    }
    public void ButEquals(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);
        Operations Op = new Operations();

        String Hex = new String();
        String Bin = new String();
        String Dec = new String();
        String Buff = new String();

        Dec = EditTextDec.getText().toString();
        Bin = EditTextBin.getText().toString();
        Hex = EditTextHex.getText().toString();

        if(EditTextBin.hasFocus() && !Bin.isEmpty())
        {
            Buff = Op.Keep();
            Op.Keep(Buff);
        }
        if(EditTextDec.hasFocus() && !Dec.isEmpty())
        {
            Buff = Dec;
            Op.Keep(Buff);
        }
        if(EditTextHex.hasFocus() && !Hex.isEmpty())
        {
            Buff = Hex;
            Op.Keep(Buff);
        }
    }
    public void Delete(View vieW)
    {
        EditText EditTextHex = findViewById(R.id.editTextHex);
        EditText EditTextDec = findViewById(R.id.editTextDec);
        EditText EditTextBin = findViewById(R.id.editTextBin);

        String Hex = new String();
        String Bin = new String();
        String Dec = new String();
        String Buff = new String();
        String Buff2 = new String();

        Dec = EditTextDec.getText().toString();
        Bin = EditTextBin.getText().toString();
        Hex = EditTextHex.getText().toString();

        Operations Op= new Operations();

        int cursorPositionHex = EditTextHex.getSelectionStart();
        int cursorPositionDec = EditTextDec.getSelectionStart();
        int cursorPositionBin = EditTextBin.getSelectionStart();

        if (cursorPositionHex > 0 && EditTextHex.hasFocus())
        {
            EditTextHex.setText(EditTextHex.getText().delete(cursorPositionHex - 1, cursorPositionHex));
            EditTextHex.setSelection(cursorPositionHex-1);
            Hex = EditTextHex.getText().toString();
            if(!Hex.isEmpty()) //Hex to Decimal
            {
                Buff= Op.HexToDec(Hex);
                EditTextDec.setText(Buff);
            }
            if(!Hex.isEmpty()) //Hex to Bin
            {
                Buff = Op.HexToDec(Hex);
                Buff2 = Op.DecimalToBinary(Long.valueOf(Buff));
                EditTextBin.setText(Buff2);
            }
            if(Hex.isEmpty())
            {
                EditTextBin.setText("");
                EditTextDec.setText("");
            }
        }
        if (cursorPositionDec > 0 && EditTextDec.hasFocus())
        {
            EditTextDec.setText(EditTextDec.getText().delete(cursorPositionDec - 1, cursorPositionDec));
            EditTextDec.setSelection(cursorPositionDec-1);
            Dec = EditTextDec.getText().toString();
            if(!Dec.isEmpty()) //Decimal to Binary
            {
                Buff = Op.DecimalToBinary(Long.valueOf(Dec));
                EditTextBin.setText(Buff);
            }

            if(!Dec.isEmpty()) //Decimal to Hex
            {
                Buff= Op.DecimalToHex(Long.valueOf(Dec));
                EditTextHex.setText(Buff);
            }
            if(Dec.isEmpty())
            {
                EditTextBin.setText("");
                EditTextHex.setText("");
            }
        }
        if (cursorPositionBin > 0 && EditTextBin.hasFocus())
        {
            EditTextBin.setText(EditTextBin.getText().delete(cursorPositionBin - 1, cursorPositionBin));
            EditTextBin.setSelection(cursorPositionBin-1);
            Bin = EditTextBin.getText().toString();
            if(!Bin.isEmpty()) //Binary to Decimal
            {
                long decimal=Long.parseLong(Bin,2);
                Buff= Long.toString(decimal);
                EditTextDec.setText(Buff);
            }
            if(!Bin.isEmpty()) //Bin to Hex
            {
                Buff= Long.toString(Long.parseLong(Bin,2));
                Buff2 = Op.DecimalToHex(Long.valueOf(Buff));
                EditTextHex.setText(Buff2);
            }
            if(Bin.isEmpty())
            {
                EditTextHex.setText("");
                EditTextDec.setText("");
            }
        }
    }

}