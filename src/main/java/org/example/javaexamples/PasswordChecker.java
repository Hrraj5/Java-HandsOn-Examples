package org.example.javaexamples;

public class PasswordChecker {
    public static void main(String[] args) {
        String password  = "kjkfgn2312$";
        int upperCaseChar = 0;
        int lowerCaseChar = 0;
        int specialChar = 0;
        int digitCount = 0;
        for(int i=0;i<password.length();i++){
            if(Character.isUpperCase(password.charAt(i))) upperCaseChar++;
            else if(Character.isLowerCase(password.charAt(i))) lowerCaseChar++;
            else if(Character.isDigit(password.charAt(i))) digitCount++;
            else specialChar++;
        }
        int totalCharCount = upperCaseChar+lowerCaseChar+specialChar+digitCount;
        if(upperCaseChar > 0 && lowerCaseChar >0 && specialChar>0 && digitCount>0 && totalCharCount>=8){
            System.out.println("Strong password");
        }else{
            System.out.println("Weak Password");
        }
    }
}
