package com.verkhovskygroup.HexBinApp;

public class Operations{
    String Sign;
    Long LastValue;
    String Flag;
    String OperationsFlag="";

    public String DecimalToBinary(long number) {
        if(Sign!= null && Sign.equals("-"))
            return Long.toBinaryString((-1)*number);
        else
            return Long.toBinaryString(number);
    }

    public String DecimalToHex(long d) {
        String digits = "0123456789ABCDEF";
        if (d <= 0)
            return "0";
        long base = 16;   // flexible to change in any base under 16
        StringBuilder hex = new StringBuilder();
        while (d > 0) {
            long Digit = d % base;              // rightmost digit
            int digit = (int)Digit;
            hex.insert(0, digits.charAt(digit));
            d = d / base;
        }
        return hex.toString();
    }

    public String HexToDec(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        long val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            long d = digits.indexOf(c);
            val = 16*val + d;
        }
        return Long.toString(val);
    }

    public void Plus(Long Number) {
        OperationsFlag="+";
        if(Sign != null && Sign.equals("-"))
            LastValue = -1*Number;
        else
            LastValue = Number;
    }

    public void Minus(Long Number) {
        OperationsFlag="-";
        if(Sign != null && Sign.equals("-"))
            LastValue = -1*Number;
        else
            LastValue = Number;
    }

    public void Multiple(Long Number) {
        OperationsFlag="*";
        if(Sign != null && Sign.equals("-"))
            LastValue = -1*Number;
        else
            LastValue = Number;
    }

    public void Divide(Long Number) {
        OperationsFlag="/";
        if(Sign != null && Sign.equals("-"))
            LastValue = -1*Number;
        else
            LastValue = Number;
    }

    public Long Equals(Long Number) {
        Flag = "";
        switch (OperationsFlag) {
            case "+":
                try {
                    if(Sign.equals("-"))
                        return LastValue + (-1*Number);
                    else
                        return LastValue + Number;
                }
                catch (NumberFormatException e) {
                    Flag = "Error";
                }
                break;
            case "-":
                try {
                    if(Sign.equals("-"))
                        return LastValue - (-1*Number);
                    else
                        return LastValue - Number;
                }
                catch (NumberFormatException e) {
                    Flag = "Error";
                }
                break;
            case "*":
                try {
                    if(Sign.equals("-"))
                        return LastValue * (-1*Number);
                    else
                        return LastValue * Number;
                }
                catch (NumberFormatException e) {
                    Flag = "Error";
                }
                break;
            case "/":
                try {
                    if(Sign.equals("-"))
                        return LastValue / (-1*Number);
                    else if(Number.equals((long)0))
                        return (long) 0;
                    else
                        return LastValue / Number;
                }
                catch (NumberFormatException e) {
                    Flag = "Error";
                }
                break;
        }
        OperationsFlag = "";
        return Number;
    }
}