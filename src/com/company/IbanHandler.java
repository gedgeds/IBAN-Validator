package com.company;

import java.math.BigInteger;
import java.util.HashMap;

class IbanHandler {

    private HashMap<String, Integer> countryCodes = new HashMap<>();


    IbanHandler() {

        initCountyCodeLengths();
    }


    boolean isIbanValid(String iban) {

        // IBAN can't be shorter than 14 and longer than 34 symbols
        if (iban.length() < 14 || iban.length() > 34) {
            return false;
        }

        int ibanLength = getIbanLengthByCountry(iban.substring(0, 2));

        // Country code (first 2 symbols) are correct
        if (ibanLength != -1) {
            String firstIbanNumbers = getFirstIbanNumbers(iban);    // 1. Take COUNTRY CODE symbols and CHECK digits of IBAN and convert to numeric values;
            String ibanNumeric = getIbanConvertedToNumbers(iban);   // 2. Convert other IBAN symbols to numeric values;
            ibanNumeric += firstIbanNumbers;                        // 3. Append numeric values of COUNTRY CODE symbols and CHECK digits of IBAN to the end;
            return isIbanDividable(ibanNumeric);                    // 4. Check if IBAN is valid.
        }

        // Country code (first 2 symbols) are incorrect
        else {
            return false;
        }
    }


    private int getIbanLengthByCountry(String countryCode) {

        return countryCodes.getOrDefault(countryCode, -1);
    }

    private boolean isIbanDividable(String ibanNumericString) {

        // IBAN is validated by checking if the remainder of numeric values divided by 97 equals 1

        BigInteger ibanValueBigInteger = new BigInteger(ibanNumericString);
        BigInteger divider = new BigInteger("97");
        BigInteger remainder = new BigInteger("1");

        return ibanValueBigInteger.mod(divider).equals(remainder);
    }

    private String getFirstIbanNumbers(String iban) {

        StringBuilder firstValues = new StringBuilder();

        // Country code symbols
        for (int i = 0; i <= 1; i++)
            firstValues.append(Character.getNumericValue(iban.charAt(i)));

        // Check digits
        for(int j = 2; j <= 3; j++)
            firstValues.append(iban.charAt(j));

        return firstValues.toString();
    }

    private String getIbanConvertedToNumbers(String iban) {

        StringBuilder ibanValue = new StringBuilder();

        for (int i = 4; i < iban.length(); i++) {
            if (IsUppercaseAsciiLetter(iban.charAt(i)))
                ibanValue.append(Character.getNumericValue(iban.charAt(i)));
            else
                ibanValue.append(iban.charAt(i));
        }

        return ibanValue.toString();
    }


    private boolean IsUppercaseAsciiLetter(char c) {

        return (c >= 'A' && c <= 'Z');
    }

    private void initCountyCodeLengths() {

        /*
        Source: https://www.iban.com/structure
         */

        countryCodes.put("AD", 24);
        countryCodes.put("AE", 23);
        countryCodes.put("AL", 28);
        countryCodes.put("AT", 20);
        countryCodes.put("AZ", 28);
        countryCodes.put("BA", 20);
        countryCodes.put("BE", 16);
        countryCodes.put("BG", 22);
        countryCodes.put("BH", 22);
        countryCodes.put("BY", 28);
        countryCodes.put("BR", 29);
        countryCodes.put("CH", 21);
        countryCodes.put("CR", 21);
        countryCodes.put("CY", 28);
        countryCodes.put("CZ", 24);
        countryCodes.put("DE", 22);
        countryCodes.put("DK", 18);
        countryCodes.put("DO", 28);
        countryCodes.put("EE", 20);
        countryCodes.put("ES", 24);
        countryCodes.put("FI", 18);
        countryCodes.put("FO", 18);
        countryCodes.put("FR", 27);
        countryCodes.put("GB", 22);
        countryCodes.put("GE", 22);
        countryCodes.put("GI", 23);
        countryCodes.put("GL", 18);
        countryCodes.put("GR", 27);
        countryCodes.put("GT", 28);
        countryCodes.put("HR", 21);
        countryCodes.put("HU", 28);
        countryCodes.put("IE", 22);
        countryCodes.put("IL", 23);
        countryCodes.put("IQ", 23);
        countryCodes.put("IS", 26);
        countryCodes.put("IT", 27);
        countryCodes.put("JO", 30);
        countryCodes.put("KZ", 20);
        countryCodes.put("KW", 30);
        countryCodes.put("LB", 28);
        countryCodes.put("LC", 32);
        countryCodes.put("LI", 21);
        countryCodes.put("LT", 20);
        countryCodes.put("LU", 20);
        countryCodes.put("LV", 21);
        countryCodes.put("MC", 27);
        countryCodes.put("MD", 24);
        countryCodes.put("ME", 22);
        countryCodes.put("MK", 19);
        countryCodes.put("MR", 27);
        countryCodes.put("MT", 31);
        countryCodes.put("MU", 30);
        countryCodes.put("NL", 18);
        countryCodes.put("NO", 15);
        countryCodes.put("PK", 24);
        countryCodes.put("PS", 29);
        countryCodes.put("PL", 28);
        countryCodes.put("PT", 25);
        countryCodes.put("QA", 29);
        countryCodes.put("RO", 24);
        countryCodes.put("RS", 22);
        countryCodes.put("SA", 24);
        countryCodes.put("SC", 31);
        countryCodes.put("SE", 24);
        countryCodes.put("SI", 19);
        countryCodes.put("SK", 24);
        countryCodes.put("SM", 27);
        countryCodes.put("ST", 25);
        countryCodes.put("SV", 28);
        countryCodes.put("TL", 23);
        countryCodes.put("TN", 24);
        countryCodes.put("TR", 26);
        countryCodes.put("UA", 29);
        countryCodes.put("VA", 22);
        countryCodes.put("VG", 24);
        countryCodes.put("XK", 20);

    }
}