package emke.comp2161.simplecalculatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;

import org.mariuszgromada.math.mxparser.*;


public class MainActivity extends AppCompatActivity {

    private TextView result;
    private TextView display;
    private String mem = "0";
    private boolean opFlag = false;
    private boolean calcFinished = false;
    private boolean watchFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();   //Remove action bar


        display = (TextView) findViewById(R.id.equation);
        result = (TextView) findViewById(R.id.result);

    }

    //Saves Instance state
    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        //Saves instance of display
        display = (TextView) findViewById(R.id.equation);
        String dis = display.getText().toString();
        savedInstanceState.putString("display", dis);

        //saves instance of result
        result = (TextView) findViewById(R.id.result);
        String res = result.getText().toString();
        savedInstanceState.putString("result", res);

        //Saves other variables
        savedInstanceState.putString("mem", mem);
        savedInstanceState.putBoolean("op", opFlag);
        savedInstanceState.putBoolean("calc", calcFinished);
        savedInstanceState.putBoolean("watch", watchFlag);
    }

    //Restores instance state
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //Restores instance of display
        display = (TextView) findViewById(R.id.equation);
        String newDisplay = savedInstanceState.getString("display");
        display.setText(newDisplay);

        //Restores instance of result
        result = (TextView) findViewById(R.id.result);
        String newResult = savedInstanceState.getString("result");
        result.setText(newResult);

        //Restores other variables
        mem = savedInstanceState.getString("mem");
        opFlag = savedInstanceState.getBoolean("op");
        calcFinished = savedInstanceState.getBoolean("calc");
        watchFlag = savedInstanceState.getBoolean("watch");
    }

    /*
            Attributes: String str
            Purpose: to add str to the end of the running calculation
             */
    private void updateText(String str){
        String oldStrCalc = "";

        //runs if equals has returned an answer
        if(watchFlag){
            //if first input is an operator then it takes the previous answer as first number
            if(str.equals("+") || str.equals("-") || str.equals("×") || str.equals("÷")
                    || str.equals("^(") || str.equals("^(2)")){
                oldStrCalc = result.getText().toString();
            }
            //Otherwise it resets the displays for a new calculation
            result.setText("");
            watchFlag = false;
            opFlag = false;
        }
        //Runs if equals has not returned an answer yet
        else{
            oldStrCalc = display.getText().toString();
        }

        //Does not allow unnecessary 0s to be added
        if(!(oldStrCalc.equals("0") && str.equals("0"))) {
            display.setText(String.format("%s%s", oldStrCalc, str));
        }
    }

    /*
    Attribute: String calc
    Purpose: Use mXParser to evaluate calc the current calculation
     */
    private void calculate(String calc){
        //Does calculation
        Expression exp = new Expression(calc);
        String answer = String.valueOf(exp.calculate());
        int length = answer.length();

        //Will remove redundant '.0' if exists
        String test = answer.substring(length-2, length);
        if(test.equals(".0")){
            answer = answer.substring(0, length-2);
        }

        //Is used to calculate intermediary values before equals pressed
        if(!answer.equals("NaN") && !calcFinished){result.setText(answer);}
        //Executes if equals pressed but invalid answer
        else if(answer.equals("NaN") && calcFinished){
            result.setText(answer);
            calcFinished = false;
        }
        //executes if equals pressed and displays answer, sets equation finished flag
        else if(!(answer.equals("NaN")) && calcFinished){
            watchFlag = true;
            calcFinished = false;
            result.setText(answer);
        }
    }

    //Listener for button 0, updates display
    public void zeroPushed(View v){
        updateText(getResources().getString(R.string.zero));
    }

    //Listener for button 1, updates display
    public void onePushed(View v){
        updateText(getResources().getString(R.string.one));
    }

    //Listener for button 2, updates display
    public void twoPushed(View v){
        updateText(getResources().getString(R.string.two));
    }

    //Listener for button 3, updates display
    public void threePushed(View v){
        updateText(getResources().getString(R.string.three));
    }

    //Listener for button 4, updates display
    public void fourPushed(View v){
        updateText(getResources().getString(R.string.four));
    }

    //Listener for button 5, updates display
    public void fivePushed(View v){
        updateText(getResources().getString(R.string.five));
    }

    //Listener for button 6, updates display
    public void sixPushed(View v){
        updateText(getResources().getString(R.string.six));
    }

    //Listener for button 7, updates display
    public void sevenPushed(View v){
        updateText(getResources().getString(R.string.seven));
    }

    //Listener for button 8, updates display
    public void eightPushed(View v){
        updateText(getResources().getString(R.string.eight));
    }

    //Listener for button 9, updates display
    public void ninePushed(View v){
        updateText(getResources().getString(R.string.nine));
    }

    //Listener for decimal button, updates display
    public void decimalPushed(View v){
        updateText(getResources().getString(R.string.decimal));
    }

    //Listener for multiply button
    public void multiplyPushed(View v){
        updateText(getResources().getString(R.string.multiply)); //updates display button text

        //Checks if intermediary calculation required, then calls calculate
        if(opFlag){
            int len = display.getText().toString().length();
            calculate(display.getText().toString().substring(0,len-1));
        }
        opFlag = true; //true means it is second operation and intermediary calc required
    }

    //Listener for divide button
    public void dividePushed(View v){
        updateText(getResources().getString(R.string.divide)); //updates display button text

        //Checks if intermediary calculation required, then calls calculate
        if(opFlag){
            int len = display.getText().toString().length();
            calculate(display.getText().toString().substring(0,len-1));
        }
        opFlag = true;//true means it is second operation and intermediary calc required
    }

    //Listener for subtract button
    public void subtractPushed(View v){
        updateText(getResources().getString(R.string.subtract)); //updates display button text

        //Checks if intermediary calculation required, then calls calculate
        if(opFlag){
            int len = display.getText().toString().length();
            calculate(display.getText().toString().substring(0,len-1));
        }
        opFlag = true;//true means it is second operation and intermediary calc required
    }

    //Listener for add button
    public void addPushed(View v){
        updateText(getResources().getString(R.string.add)); //updates display button text

        //Checks if intermediary calculation required, then calls calculate
        if(opFlag){
            int len = display.getText().toString().length();
            calculate(display.getText().toString().substring(0,len-1));
        }
        opFlag = true;//true means it is second operation and intermediary calc required
    }

    //listener for equals button, calls calculate function
    public void equalPushed(View v){
        calcFinished = true;    //flags calculation as finished
        calculate(display.getText().toString());
    }

    //listener for clear all button, clears result and display fields
    public void clearAllPushed(View v){
        display.setText("");
        result.setText("");
    }

    //listener for backspace button, removes last button input
    public void backspacePushed(View v){
        int textLength = display.getText().length();

        //Makes sure there is text to remove so no error
        if(textLength != 0){
            //removes last character in string
            String str = display.getText().toString().substring(0, textLength-1);
            display.setText(str);
        }
    }

    //listener for clear memory button
    public void memClearPushed(View v){
        mem = "0"; //resets memory back to 0
        Context context = getApplicationContext();

        Toast toast = Toast.makeText(context, "Memory is cleared", Toast.LENGTH_SHORT);
        toast.show();
    }

    //listener for recall memory
    public void memRecallPushed(View v){
        //If there is something stored in memory it will input it to display
        if(!mem.equals("0")) {
            updateText(mem);
        }
        //Lets user know no value stored in mem
        else{
            Context context = getApplicationContext();

            Toast toast = Toast.makeText(context, "Nothing is Stored!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //listener for memory subtract
    public void memSubtractPushed(View v){
        /*If there is something stored in memory it will subtract the current result from memory
        and store the new value in memory*/
        if(!mem.equals("0")) {
            String num = result.getText().toString();

            //Executes if there is a numerical value in result
            if(!num.equals("NaN") || num.equals("")){
                String calc = String.format("%s%s", mem, "-");
                calc = String.format("%s%s", calc, num);

                Expression exp = new Expression(calc);
                mem = String.valueOf(exp.calculate());

                //Lets user know memory has been updated
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Memory has been updated",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
            //Reports invalid entry if there is no numerical value in result
            else{
                Context context = getApplicationContext();

                Toast toast = Toast.makeText(context, "Invalid Entry!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        //Lets user know no value stored in mem
        else{
            Context context = getApplicationContext();

            Toast toast = Toast.makeText(context, "Nothing is Stored!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //listener for memory add
    public void memAddPushed(View v){
        //It will add the current result from memory and store the new value in memory
        String num = result.getText().toString();

        //Executes if there is a numerical value in result
        if(!(num.equals("NaN") || num.equals(""))){
            String calc = String.format("%s%s", mem, "+");
            calc = String.format("%s%s", calc, num);

            Expression exp = new Expression(calc);
            mem = String.valueOf(exp.calculate());

            //Lets user know memory has been updated
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Memory has been updated",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        //Reports invalid entry if there is no numerical value in result
        else{
            Context context = getApplicationContext();

            Toast toast = Toast.makeText(context, "Invalid Entry!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //Listener for toggle negative button
    public void toggleNegPushed(View v){
        //turns the last number into a negative or adds a negative sign
        String oldCalc = display.getText().toString();
        String calc = "";

        if(oldCalc.isEmpty()){
            calc = "-";
        }
        else {
            String[] tokens = oldCalc.split("[+×÷-]", -1);

            int length = tokens.length;
            int numLength = tokens[length-1].length();
            int calcLength = oldCalc.length();

            //if there is only 1 number in the calc it adds negative to beginning
            if (length == 1) {
                calc = String.format("%s%s", "-", oldCalc);
            }
            //inserts negative at beginning of last number or at start of new number
            else {
                StringBuffer str = new StringBuffer(oldCalc);
                str.insert(calcLength - numLength, "-");
                calc = str.toString();
            }
        }

        //removes double negatives for better clarity
        calc = calc.replace("---", "-");

        display.setText(calc);
        calculate(display.getText().toString());
    }

    //listener for Euler's number button, updates display with input
    public void eulerPushed(View v){
        updateText("e");
    }

    //listener for sine button, updates display with function
    public void sinePushed(View v){
        updateText("sin(");
    }

    //listener for cosine button, updates display with function
    public void cosinePushed(View v){
        updateText("cos(");
    }

    //listener for tangent button, updates display with function
    public void tangentPushed(View v){
        updateText("tan(");
    }

    //listener for left parentheses button, updates display with input
    public void leftBracketPushed(View v){
        updateText("(");
    }

    //listener for right parentheses button, updates display with input
    public void rightBracketPushed(View v){
        updateText(")");
    }

    //listener for square root button, updates display with function
    public void sqrtPushed(View v){
        updateText("sqrt(");
    }

    //listener for natural log button, updates display with function
    public void naturalLogPushed(View v){
        updateText("ln(");
    }

    //listener for log button, updates display with function
    public void logPushed(View v){
        updateText("lg(");
    }

    //listener for pi button, updates display with input
    public void piPushed(View v){
        updateText("pi");
    }

    //listener for exponent button, updates display with function
    public void exponentPushed(View v){
        updateText("^(");

        //Checks if intermediary calculation required, then calls calculate
        if(opFlag){
            int len = display.getText().toString().length();
            calculate(display.getText().toString().substring(0,len-1));
        }
        opFlag = true;//true means it is second operation and intermediary calc required
    }

    //listener for squared button, updates display with function
    public void squaredPushed(View v){
        updateText("^(2)");

        //Checks if intermediary calculation required, then calls calculate
        if(opFlag){
            int len = display.getText().toString().length();
            calculate(display.getText().toString().substring(0,len-1));
        }
        opFlag = true;//true means it is second operation and intermediary calc required
    }

}