package com.example.jimmy.unitconverter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.EditText;
import android.widget.TextView.OnEditorActionListener;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Locale;


public class UnitConverterActivity extends Activity {
    //Reference variables for widgets
    private Spinner lengthUnitSpinner;
    private Spinner lengthUnitSpinner2;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private SeekBar decimalSeekBar;
    private TextView seekBarStatusView;
    private EditText convertAmountEditText;
    private TextView convertedAmountTextView;
    private SharedPreferences savedPrefs;

    //Instance variables that will be used to store and save important data
    private String lengthSelected = "Centimeter";
    private String lengthSelected2 = "Centimeter";
    private int decimalPlaces = 2;
    double convertedAmount = 0.00;
    String convertedAmountString = "";
    String convertAmountString = "";
    int selectedUnitIndex = 0;
    int selectedUnitIndex2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_converter);

        //Initialize widget reference variables
        lengthUnitSpinner = findViewById(R.id.lengthUnitSpinner);
        lengthUnitSpinner2 = findViewById(R.id.lengthUnitSpinner2);
        radioGroup = findViewById(R.id.radioGroup);
        decimalSeekBar = findViewById(R.id.decimalSeekBar);
        seekBarStatusView = findViewById(R.id.seekBarStatusView);
        convertAmountEditText = findViewById(R.id.convertAmountEditText);
        convertedAmountTextView = findViewById(R.id.convertedAmountTextView);

        // Create an ArrayAdapter for our spinner
        ArrayAdapter<CharSequence> lengthUnitSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_options, android.R.layout.simple_spinner_item);
        lengthUnitSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> lengthUnitSpinnerAdapter2 = ArrayAdapter.createFromResource(this, R.array.spinner_options, android.R.layout.simple_spinner_item);
        lengthUnitSpinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Set adapter to Spinner widget variables
        lengthUnitSpinner.setAdapter(lengthUnitSpinnerAdapter);
        lengthUnitSpinner2.setAdapter(lengthUnitSpinnerAdapter2);
        //Set the event listeners for the widgets
        lengthUnitSpinner.setOnItemSelectedListener(new SpinnerListener());
        lengthUnitSpinner2.setOnItemSelectedListener(new SpinnerListener2());

        decimalSeekBar.setOnSeekBarChangeListener(new SeekBarListener());

        convertAmountEditText.setOnEditorActionListener(new ConvertAmountListener());

        savedPrefs = getSharedPreferences( "TipCalcPrefs", MODE_PRIVATE );
    }
    //Private method that handles the unit conversions
    private void calculate() {
        //Unit Conversion arrays for each unit
        double[] cmArray = {1, 0.01, 0.00001, 0.39370078740157, 0.032808398950131, 0.0000062137119223733};
        double[] mArray = {100, 1, 0.001, 39.370078740157, 3.2808398950131, 0.00062137119223733};
        double[] kmArray = {100000, 1000, 1, 39370.078740157, 3280.8398950131, 0.62137119223733};
        double[] inArray = {2.54, 0.0254, 0.0000254, 1, 0.083333333333333, 0.000015782828282828};
        double[] ftArray = {30.48, 0.3048, 0.0003048, 12, 1, 0.00018939393939394};
        double[] miArray = {160934.4, 1609.344, 1.609344, 63360, 5280, 1};

        convertAmountString = convertAmountEditText.getText().toString();
        //Get the user's input and store it as a double
        double convertAmount = Double.parseDouble(convertAmountString);

        if (lengthSelected2.equals("Centimeter") && lengthSelected2 != null){
            switch(lengthSelected){
                case("Centimeter"):
                    convertedAmount = convertAmount / cmArray[0];
                    break;
                case("Meter"):
                    convertedAmount = convertAmount / cmArray[1];
                    break;
                case("Kilometer"):
                    convertedAmount = convertAmount / cmArray[2];
                    break;
                case("Inch"):
                    convertedAmount = convertAmount / cmArray[3];
                    break;
                case("Foot"):
                    convertedAmount = convertAmount / cmArray[4];
                    break;
                default:
                    convertedAmount = convertAmount / cmArray[5];
                    break;
            }
        }
        else if (lengthSelected2.equals("Meter") && lengthSelected2 != null){
            switch(lengthSelected){
                case("Centimeter"):
                    convertedAmount = convertAmount / mArray[0];
                    break;
                case("Meter"):
                    convertedAmount = convertAmount / mArray[1];
                    break;
                case("Kilometer"):
                    convertedAmount = convertAmount / mArray[2];
                    break;
                case("Inch"):
                    convertedAmount = convertAmount / mArray[3];
                    break;
                case("Foot"):
                    convertedAmount = convertAmount / mArray[4];
                    break;
                default:
                    convertedAmount = convertAmount / mArray[5];
                    break;
            }
        }
        else if (lengthSelected2.equals("Kilometer") && lengthSelected2 != null){
            switch(lengthSelected){
                case("Centimeter"):
                    convertedAmount = convertAmount / kmArray[0];
                    break;
                case("Meter"):
                    convertedAmount = convertAmount / kmArray[1];
                    break;
                case("Kilometer"):
                    convertedAmount = convertAmount / kmArray[2];
                    break;
                case("Inch"):
                    convertedAmount = convertAmount / kmArray[3];
                    break;
                case("Foot"):
                    convertedAmount = convertAmount / kmArray[4];
                    break;
                default:
                    convertedAmount = convertAmount / kmArray[5];
                    break;
            }
        }
        else if (lengthSelected2.equals("Inch") && lengthSelected2 != null){
            switch(lengthSelected){
                case("Centimeter"):
                    convertedAmount = convertAmount / inArray[0];
                    break;
                case("Meter"):
                    convertedAmount = convertAmount / inArray[1];
                    break;
                case("Kilometer"):
                    convertedAmount = convertAmount / inArray[2];
                    break;
                case("Inch"):
                    convertedAmount = convertAmount / inArray[3];
                    break;
                case("Foot"):
                    convertedAmount = convertAmount / inArray[4];
                    break;
                default:
                    convertedAmount = convertAmount / inArray[5];
                    break;
            }
        }
        else if (lengthSelected2.equals("Foot") && lengthSelected2 != null){
            switch(lengthSelected){
                case("Centimeter"):
                    convertedAmount = convertAmount / ftArray[0];
                    break;
                case("Meter"):
                    convertedAmount = convertAmount / ftArray[1];
                    break;
                case("Kilometer"):
                    convertedAmount = convertAmount / ftArray[2];
                    break;
                case("Inch"):
                    convertedAmount = convertAmount / ftArray[3];
                    break;
                case("Foot"):
                    convertedAmount = convertAmount / ftArray[4];
                    break;
                default:
                    convertedAmount = convertAmount / ftArray[5];
                    break;
            }
        }
        else if (lengthSelected2.equals("Mile") && lengthSelected2 != null){
            switch(lengthSelected){
                case("Centimeter"):
                    convertedAmount = convertAmount / miArray[0];
                    break;
                case("Meter"):
                    convertedAmount = convertAmount / miArray[1];
                    break;
                case("Kilometer"):
                    convertedAmount = convertAmount / miArray[2];
                    break;
                case("Inch"):
                    convertedAmount = convertAmount / miArray[3];
                    break;
                case("Foot"):
                    convertedAmount = convertAmount / miArray[4];
                    break;
                default:
                    convertedAmount = convertAmount / miArray[5];
                    break;
            }
        }
        //Call the format method to change the number
        format();
    }
    //Private method that handles the formatting of the final converted unit
    private void format() {
        /*If the radiobutton that is selected is decimal, control the decimal places depending
        on the decimal places seek bar */
        if (radioButton.getText().toString().equals("Decimal")){
            switch(decimalPlaces){
                case(0):
                    convertedAmountString = String.format("%.0f", convertedAmount);
                    convertedAmountTextView.setText(convertedAmountString);
                    break;
                case(1):
                    convertedAmountString = String.format("%.1f", convertedAmount);
                    convertedAmountTextView.setText(convertedAmountString);
                    break;
                case(2):
                    convertedAmountString = String.format("%.2f", convertedAmount);
                    convertedAmountTextView.setText(convertedAmountString);
                    break;
                case(3):
                    convertedAmountString = String.format("%.3f", convertedAmount);
                    convertedAmountTextView.setText(convertedAmountString);
                    break;
                case(4):
                    convertedAmountString = String.format("%.4f", convertedAmount);
                    convertedAmountTextView.setText(convertedAmountString);
                    break;
                default:
                    convertedAmountString = String.format("%.5f", convertedAmount);
                    convertedAmountTextView.setText(convertedAmountString);
                    break;
            }
            convertedAmountTextView.setText(convertedAmountString);
        }
        /*If the radiobutton is not the decimal, then it must be the scientific notation.
        Convert the number to scientific notation with 2 decimal places */
        else {
            convertedAmountString = String.format("%.2e", convertedAmount);
            convertedAmountTextView.setText(convertedAmountString);
        }
    }
    @Override
    public void onPause() {
        Editor prefsEditor = savedPrefs.edit();
        prefsEditor.putString("convertedAmountString", convertedAmountString);
        prefsEditor.putString("convertAmountString", convertAmountString);
        prefsEditor.putString("lengthSelected", lengthSelected);
        prefsEditor.putString("lengthSelected2", lengthSelected2);
        prefsEditor.putInt("decimalPlaces", decimalPlaces);
        prefsEditor.putInt("selectedUnitIndex", selectedUnitIndex);
        prefsEditor.putInt("selectedUnitIndex2", selectedUnitIndex2);

        prefsEditor.commit();

        super.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();

        convertedAmountString = savedPrefs.getString("convertedAmountString", convertedAmountString);
        convertAmountString = savedPrefs.getString("convertAmountString", convertAmountString);
        lengthSelected = savedPrefs.getString("lengthSelected", lengthSelected);
        lengthSelected2 = savedPrefs.getString("lengthSelected2", lengthSelected2);
        decimalPlaces = savedPrefs.getInt("decimalPlaces", 2);
        selectedUnitIndex = savedPrefs.getInt("selectedUnitIndex", selectedUnitIndex);
        selectedUnitIndex2 = savedPrefs.getInt("selectedUnitIndex2", selectedUnitIndex2);

        convertedAmountTextView.setText(convertedAmountString);
        convertAmountEditText.setText(convertAmountString);
        lengthUnitSpinner.setSelection(selectedUnitIndex);
        lengthUnitSpinner2.setSelection(selectedUnitIndex2);
        seekBarStatusView.setText(Integer.toString(decimalPlaces));
        decimalSeekBar.setProgress(decimalPlaces);
    }
    //Method that gets which button has been checked and initializes the radiobutton
    public void radioGroupClick(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

    }
    //Inner class that will handle the spinner
    class SpinnerListener implements OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //Store the value of which spinner item has been selected
            selectedUnitIndex = position;
            lengthSelected = lengthUnitSpinner.getItemAtPosition(position).toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            //Not Used / Implemented
        }
    }
    //Inner class that will handle the second spinner
    class SpinnerListener2 implements OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //Store the value of which spinner item has been selected
            selectedUnitIndex2 = position;
            lengthSelected2 = lengthUnitSpinner2.getItemAtPosition(position).toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            //Not Used/ Implemented
        }
    }
    //Inner class that handles the seek bar
    class SeekBarListener implements OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //Store the value of the progress, so that it can be used to change the formatting
            decimalPlaces = progress;
            //Set the text of the seekBarStatusView so that the user knows how many decimal places has been chosen
            seekBarStatusView.setText(Integer.toString(progress));
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // Called when the user starts changing the SeekBar
            // Not Used / Implemented
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // Called when the user finishes changing the SeekBar
            // Not Used / Implemented
        }
    }
    //Inner class that handles changes to the editText widget
    class ConvertAmountListener implements OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if ( actionId == EditorInfo.IME_ACTION_DONE ) {
                calculate();
            }
            return false;
        }
    }
}
