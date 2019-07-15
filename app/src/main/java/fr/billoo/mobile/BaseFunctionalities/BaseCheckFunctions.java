package fr.billoo.mobile.BaseFunctionalities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseCheckFunctions
{

    public static String getCorrespondingCode(String incomingCode)
    {
        String codes[]={"URL_NOT_FOUND","INVALID_METHOD","INVALID_PARAMETER","EMAIL_EXISTS","UNKNOWN_ISSUE","PASSWORD_DOES_NOT_MATCH","ACCOUNT_NOT_EXIST",
                            "ACCOUNT_NOT_VERIFIED","ACCOUNT_VERIFIED"};

        for(String theCode:codes)
        {
            if(incomingCode.equals(theCode))
                return theCode;
        }
        return "Unknown Code";
    }
    /**
     *
     */
    public static final String URL_NOT_FOUND = "URL_NOT_FOUND";

    /**
     *
     */
    public static final String INVALID_METHOD = "INVALID_METHOD";

    /**
     *
     */
    public static final String INVALID_PARAMETER = "INVALID_PARAMETER";

    /**
     *
     */
    public static final String EMAIL_EXISTS = "EMAIL_EXISTS";

    /**
     *
     */
    public static final String UNKNOWN_ISSUE = "UNKNOWN_ISSUE";

    /**
     *
     */
    public static final String PASSWORD_DOES_NOT_MATCH = "PASSWORD_DOES_NOT_MATCH";

    /**
     *
     */
    public static final String ACCOUNT_NOT_EXIST = "ACCOUNT_NOT_EXIST";
    public static final String ACCOUNT_NOT_VERIFIED = "ACCOUNT_NOT_VERIFIED";
    public static final String ACCOUNT_VERIFIED = "ACCOUNT_VERIFIED";

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isMobileValid(String mobile) {
        boolean isValid = false;

        if (mobile.length()==10)
        {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isPasswordValid(String password,String confirmPassword) {
        boolean isValid = false;

        if (password.length()>6 && password.equals(confirmPassword))
        {
            isValid = true;
        }
        return isValid;
    }
}
