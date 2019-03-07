import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

/**
 * This class elicits passwords from the user and runs several tests on the passwords to 
 * ensure that they meet the minimum requirements.  If they fails any checks, a report is
 * supposed to be printed out to tell the user what they did wrong in choosing their 
 * password.  If they pass the checks, a report will be printed out indicating as such.
 * 
 * @author Michael R. Boykin 
 * @version 08/27/2017
 */
public class Password
{
    // instance variables - replace the example below with your own
        private char[] passwordArray;
        boolean lengthOK, uppercaseOK, lowercaseOK, digitOK, whiteSpaceOK;
        Scanner userInput = new Scanner(System.in);
        StringBuffer toReportString = new StringBuffer();
        
    /**
     * The main method where the java compiler will start outside of an API.
     * 
     * @param  None.
     * @return None.
     */
    public static void main (String[] args)
    {
        //
        ArrayList<String> passwords = new ArrayList<String>();
        boolean userHasMorePasswordsToEnter = true;
        //StringBuffer toReportString = new StringBuffer();
        
        Password password1 = new Password();
        password1.greetUser();
        
        //loops until the user indicates they are finished entering passwords.
        do{
            passwords.add(password1.readPasswordFromUser());
            userHasMorePasswordsToEnter = password1.enterMorePasswords();
        }
        while(userHasMorePasswordsToEnter);
        
        for(int index = 0; index < passwords.size(); index++){
            char[] passwordArray = passwords.get(index).toCharArray();
            
            //must run these methods to set the boolean OK checks.
            password1.checkCase(passwordArray);
            password1.checkNumber(passwordArray);
            password1.checkLength(passwordArray);
            password1.checkWhiteSpace(passwordArray);
            
            //toReportString.append(
            password1.addToReport(password1.printPasswordValidity(passwords.get(index)));
            
        }
        password1.printReport();
    }
    
    /**
     * This method writes the password validity tests to a report variable for storage 
     * 		until printing:
     * password: <password>, <valid / invalid>
     *  -- <test method failed>
     * 
     * @param String password  a password entered by the user for testing.
     * @return boolean true or false; does the password have at least 1 upper and lower 
     * 			case character.
     */
    public void addToReport (StringBuffer x)
    {
        toReportString.append(x);
        //toReportString.append(System.getProperty("line.separator"));
        //return true;
    }
    
    /**
     * Checks each of the characters of the password for at least 1 upper and 1 lower 
     * 		case character.
     * 
     * @param String password  a password entered by the user for testing.
     * @return boolean true or false; does the password have at least 1 upper and lower 
     * 			case character.
     */
    public boolean checkCase (char[] passwordArray)
    {
        //voided in phase 6: boolean hasLowerCase = false, hasUpperCase = false;
        uppercaseOK = false; lowercaseOK = false;
        /**Since this loop starts with all conditions as false, they will only be changed 
        		if true, even once.
        */
        for(char checkChar : passwordArray){
            if(Character.isUpperCase(checkChar)){uppercaseOK = true;}
            if(Character.isLowerCase(checkChar)){lowercaseOK = true;}
        }
        
        /** 
         * All of this was voided in phase 6.
         * //Password does not have either uppercase or lowercase letters.
         * if(!hasUpperCase){System.out.println("-- no uppercase letter");}
         * if(!hasLowerCase){System.out.println("-- no lowercase letter");}
         *
         * //This check was deleted as it was superfluous data.
         * if(!hasLowerCase && !hasUpperCase)
         * 		{System.out.println("-- no letters in password");return;}
         *
         * //If passes the checkCase, true is returned.
         * if(hasUpperCase && hasLowerCase){System.out.println("Case OK"); return true;}
         */
       
        return (uppercaseOK && lowercaseOK);
    }
    
    /**
     * Checks the length of the password for validity (>4 characters long), verifying 
     * 		that it is long enough.
     * 
     * @param String password  a password entered by the user for testing.
     * @return boolean true or false; is the password long enough.
     */
    public boolean checkLength (char[] passwordArray)
    {
        //first iteration of checkLength method.
        //if(password.length() > 4){return true;}
        //return false;
        /**
         * Superceded in Phase 7.
         * System.out.println("Length OK");}
         * else{System.out.println("-- too short (minimum of 5 characters");}
         */
        
        lengthOK = false;
                
        if(passwordArray.length > 4){lengthOK = true;}
        return lengthOK;
    }
    
    /**
     * Checks each of the characters of the password for at least 1 number.
     * 
     * @param String password  a password entered by the user for testing.
     * @return boolean true or false; does the password have at least 1 number.
     */
    public boolean checkNumber (char[] passwordArray)
    {
        digitOK = false;
        /**Since this loop starts with all conditions as false, they will only be changed
         * 		if true, even once.
         */
        for(char checkChar : passwordArray){
            if(Character.isDigit(checkChar)){digitOK = true;}
        }
        return digitOK;
    }
    
    /**
     * Checks each of the characters of the password ensuring no <space>, <tab> or <CR> 
     * 		characters entered.
     * 
     * @param None.
     * @return boolean true or false; is the password clear of white spaces.
     */
    public boolean checkValidity ()
    {
        boolean validCheck = lengthOK && uppercaseOK && lowercaseOK && digitOK && 
        		whiteSpaceOK;
          
        return validCheck;
    }
    
    /**
     * Checks each of the characters of the password ensuring no <space>, <tab> or <CR> 
     * 		characters entered.
     * 
     * @param String password  a password entered by the user for testing.
     * @return boolean true or false; is the password clear of white spaces.
     */
    public boolean checkWhiteSpace (char[] passwordArray)
    {
        whiteSpaceOK = true;
        /**Since this loop starts with all conditions as false, they will only be changed
         * 		if true, even once.
         */
        for(char checkChar : passwordArray){
            if(Character.isWhitespace(checkChar)){whiteSpaceOK = false;}
        }
        return whiteSpaceOK;
    }
    
    /**
     * This method writes a brief explanation to the screen of what the program does.
     * 
     * @param   None
     * @return  None
     */
    public void greetUser ()
    {
        System.out.println("Password Checker Program");
        System.out.println("Purpose: To check the validity of user entered passwords "
        					+ "against the following critera:");
        System.out.println("-- At least 5 characters in length");
        System.out.println("-- At least 1 upper case letter");
        System.out.println("-- At least 1 lower case letter");
        System.out.println("-- At least 1 number");
        System.out.println("-- No white spaces allowed (i.e. SPACEBAR, TAB or RETURN)");
        System.out.println();

    }
    
    /**
     * This method writes the password validity tests on screen in the form of:
     * password: <password>, <valid / invalid>
     *  -- <test method failed>
     * 
     * @param   char[] The password is brought in from main.
     * @return  StringBuffer returns a StringBuffer of the whole test.
     */
    public StringBuffer printPasswordValidity (String outputPasswordStr)
    {
        //
        //String outputPasswordStr = new String(passwordArray);
        //StringBuffer validityCheckString = new StringBuffer
        StringBuffer validityCheckString = new StringBuffer();
        validityCheckString.append("password: " + outputPasswordStr + ", ");
        //System.out.print("password: " + outputPasswordStr + ", ");
        boolean passwordIsValid = checkValidity();
        
        if(passwordIsValid){
            validityCheckString.append("valid");
            validityCheckString.append(System.getProperty("line.separator"));
        }
        else{
            validityCheckString.append("invalid");
            validityCheckString.append(System.getProperty("line.separator"));
            if(!uppercaseOK){
                validityCheckString.append("  -- no uppercase letter");
                validityCheckString.append(System.getProperty("line.separator"));
            }
            if(!lowercaseOK){
                validityCheckString.append("  -- no lowercase letter");
                validityCheckString.append(System.getProperty("line.separator"));
            }
            if(!digitOK){
                validityCheckString.append("  -- no digit");
                validityCheckString.append(System.getProperty("line.separator"));
            }
            if(!lengthOK){
                validityCheckString.append("  -- too short (minimum of 5 characters");
                validityCheckString.append(System.getProperty("line.separator"));
            }
            if(!whiteSpaceOK){
                validityCheckString.append("  -- no white space characters allowed");
                validityCheckString.append(System.getProperty("line.separator"));
            }
        }
        validityCheckString.append(System.getProperty("line.separator"));;
        System.out.print(validityCheckString);
        //addToReport(validityCheckString);
        return validityCheckString;
    }
    
    /**
     * This method writes the password validity tests to a file in the form of:
     * password: <password>, <valid / invalid>
     *  -- <test method failed>
     * 
     * @param   None
     * @return  None
     */
    public void printReport ()
    {
        try{
            //File finalReport = new File("PasswordResults.txt");
            //FileWriter writeFinalReport = new FileWriter(finalReport);
            BufferedWriter writeFinalReport = new BufferedWriter(new FileWriter(new File
            		("PasswordResults.txt")));
            writeFinalReport.write(toReportString.toString());
            writeFinalReport.flush();
            writeFinalReport.close();
        }
        catch(Exception e) 
        	{System.err.println("There was a problem writing to PasswordResult.txt");}
        
        //System.out.print(toReportString);
    }

        /**
     * This method waits for the user to type a password.  No validation is done at this
     * 		point, just passes it on.
     * 
     * @param   None
     * @return  String the password that the user has chosen.
     */
    public String readPasswordFromUser ()
    {
        //Elicits password input from the user.
        System.out.print("Enter a new password:> ");
        String password = userInput.nextLine();
        return password;
    }

    /**
     * This method asks the user if they have more passwords to type. It will only accept
     * 		a y/n or yes/no answer.
     * Anything else will force it to loop again.  The response is case-insensitive
     * 
     * @param   None
     * @return  Boolean True or False if the user wants to type another password.
     */
    private boolean enterMorePasswords ()
    {
        /**We want a Yes/No or Y/N response(case insensitive) from this, so we will loop 
         * 		until we get it.
         */
        boolean inputInvalid = true;
        do{
            System.out.print("Do you wish to type a new password (Y/N)?> ");
            String newPasswordYN = userInput.nextLine().substring(0, 1).toUpperCase();
            
            //if the user types "y" or "yes" regardless of case, method will return true.
            if(newPasswordYN.equals("Y")){return true;}
            
            //If the user types "n" or "no" regardless of case, the loop will end.  
            if(newPasswordYN.equals("N")){break;}
            
            /**at this point the user has typed something not of a yes/no response and 
             * 		will be forced to loop.
             */
            System.out.println("Input invalid.");
        }
        while(inputInvalid);
        
        return false;
    }
}
