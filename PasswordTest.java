import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PasswordTest.  This test class will open a text file with all of the password tests
 * and test results to load them into arrays to be accessed for use by the test methods.  Each test
 * method, will access a password from passwordChoice and load it's specific test result from either
 * passwordCheckLengths, Cases etc. and run the test to ensure that it passes.  This was done to make
 * less typing.  In the event that any test cases were changed, a great deal of work would have to be
 * done in order to update all test cases or to add more test cases.
 *
 * @author  (Michael R. Boykin)
 * @version (09/01/2017)
 */
public class PasswordTest
{
    private Password password1;
    private Scanner readFile;
    
    //These arrays will hold the password tests and test results to check for by each test method.
    //Passwords will be stored in this array.
    ArrayList<String> passwordChoice = new ArrayList<String>();
    
    //The expected result of the test methods are accessed from these arrays and checked to be T/F.
    ArrayList<Boolean> passwordCheckLengths = new ArrayList<Boolean>();
    ArrayList<Boolean> passwordCheckCases = new ArrayList<Boolean>();
    ArrayList<Boolean> passwordCheckDigits = new ArrayList<Boolean>();
    ArrayList<Boolean> passwordCheckSpaces = new ArrayList<Boolean>();
    ArrayList<Boolean> passwordResult = new ArrayList<Boolean>();
        
    /**
     * Default constructor for test class PasswordTest
     */
    public PasswordTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        password1 = new Password();
        
        //Checks to see if the file testCases.txt exists in the current directory.
        try{
            readFile = new Scanner(new File("testCases.txt"));
        }
        catch (Exception e){
            System.out.println("Could not find the file.  Check to see if testCases.txt is in the correct directory.");
        }
        
        //Reads each line of the testCase.txt file until it gets to the end
        int index = 0;
        while(readFile.hasNext()){
            String readFileContents = readFile.next().trim();
            //io.next() method counts spaces as an end of read, so the test file uses a signifier.
            if(readFileContents.contains("[spc]")){
                readFileContents = readFileContents.replace("[spc]", " ");
            }
            
            //Breaks each line into password and test results and stores them in arrays.
            String[] wordArray = readFileContents.split(",");
            passwordChoice.add(wordArray[0]);
            passwordCheckLengths.add(Boolean.valueOf(wordArray[1]));
            passwordCheckCases.add(Boolean.valueOf(wordArray[2]));
            passwordCheckDigits.add(Boolean.valueOf(wordArray[3]));
            passwordCheckSpaces.add(Boolean.valueOf(wordArray[4]));
            passwordResult.add(Boolean.valueOf(wordArray[5]));
            index += 1;
        }
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testLength()
    {
        for(int index = 1; index < passwordChoice.size(); index++){
            char[] passwordArray = passwordChoice.get(index).toCharArray();
            assertEquals(passwordCheckLengths.get(index), password1.checkLength(passwordArray));
        }
        /**I took one look at the length of the following code and then realized that I have to 
         * type it all out again for every test case.  Instead I put the passwords and results into
         * a text file and then direct the compiler to import the file to test.
         * 
         * assertEquals(true, password1.checkLength("D0g7\""));
         * assertEquals(true, password1.checkLength("D075\"t"));
         * assertEquals(true, password1.checkLength("D0g7 "));
         * assertEquals(true, password1.checkLength("DOGGOD"));
         * assertEquals(true, password1.checkLength("Pass Word1!"));
         * assertEquals(true, password1.checkLength("Abcd5"));
         * assertEquals(true, password1.checkLength("55wXwZ"));
         * assertEquals(true, password1.checkLength("hiThere"));
         * assertEquals(false, password1.checkLength("99xx"));
         * assertEquals(true, password1.checkLength("Kbc525"));
         * assertEquals(true, password1.checkLength("ABCd9"));
         * assertEquals(true, password1.checkLength("1990Oct5"));
         * assertEquals(false, password1.checkLength("Aa1"));
         * assertEquals(true, password1.checkLength("ball5"));
         * assertEquals(true, password1.checkLength("SUMMER17"));
         * assertEquals(false, password1.checkLength("T9T9"));
         * assertEquals(true, password1.checkLength("!F2@LL"));
         * assertEquals(true, password1.checkLength("!.-^/"));
         * assertEquals(true, password1.checkLength("B!g@2mX"));
         * assertEquals(true, password1.checkLength("Sx5xS"));
         * assertEquals(true, password1.checkLength("<Lf23>"));
         * assertEquals(false, password1.checkLength("@#$"));
         * assertEquals(true, password1.checkLength("BANANA"));
         * assertEquals(true, password1.checkLength("Dude 8"));
         * assertEquals(true, password1.checkLength("Hell0"));
         * assertEquals(true, password1.checkLength("Man_7"));
         * assertEquals(true, password1.checkLength("A?@!b_-7"));
         */

    }
    
    @Test
    public void testNumber()
    {
        for(int index = 1; index < passwordChoice.size(); index++){
            char[] passwordArray = passwordChoice.get(index).toCharArray();
            assertEquals(passwordCheckDigits.get(index), password1.checkNumber(passwordArray));
        }
    }
    
    @Test
    public void testCase()    
    {
        for(int index = 1; index < passwordChoice.size(); index++){
            char[] passwordArray = passwordChoice.get(index).toCharArray();
            assertEquals(passwordCheckCases.get(index), password1.checkCase(passwordArray));
        }
    }
    
    @Test
    public void testWhiteSpace()
    {
        for(int index = 1; index < passwordChoice.size(); index++){
            char[] passwordArray = passwordChoice.get(index).toCharArray();
            assertEquals(passwordCheckSpaces.get(index), password1.checkWhiteSpace(passwordArray));
        }
    }

    @Test
    public void testValidity()
    {
        for(int index = 1; index < passwordChoice.size(); index++){
            char[] passwordArray = passwordChoice.get(index).toCharArray();
            //run all validity checks to assign boolean values to OK checks. 
            password1.checkCase(passwordArray);
            password1.checkNumber(passwordArray);
            password1.checkLength(passwordArray);
            password1.checkWhiteSpace(passwordArray);
            
            assertEquals(passwordResult.get(index), password1.checkValidity());
        }
    }
    
}

