package emke.comp2161.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button convert;
    private TextView result;
    private TextView unitResult;
    private EditText value;
    private Spinner fromUnit;
    private Spinner toUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        convert = (Button) findViewById(R.id.convertButton);
        result = (TextView) findViewById(R.id.convertResult);
        unitResult = (TextView) findViewById(R.id.resultUnit);
        value = (EditText) findViewById(R.id.valueEntry);
        fromUnit = (Spinner) findViewById(R.id.fromSpinner);
        toUnit = (Spinner) findViewById(R.id.toSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.unit_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnit.setAdapter(adapter);
        toUnit.setAdapter(adapter);

        convert.setOnClickListener((view -> {
            unitResult.setText(toUnit.getSelectedItem().toString());
            if(fromUnit.getSelectedItem().toString().equals(toUnit.getSelectedItem().toString())){
                Toast.makeText(getBaseContext(), "No unit conversion needed", Toast.LENGTH_SHORT).show();
                result.setText(value.getText().toString());
                if(value.getText().toString().isEmpty()){
                    unitResult.setText("");
                }
            }
            else{
                if(value.getText().toString().isEmpty() || (Double.valueOf(value.getText().toString())).isNaN()){
                    Toast.makeText(getBaseContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
                    result.setText("NULL");
                    unitResult.setText("");
                }
                else {
                    double value_double = Double.valueOf(value.getText().toString());
                    double convert_result = value_double;
                    double temp;
                    if(toUnit.getSelectedItem().toString().equals("Celsius")){
                        temp = (value_double - 32) * 5 / 9;
                    }
                    else{
                        temp = (value_double * (9 / 5)) + 32;
                    }
                    result.setText(String.format(java.util.Locale.US,"%.2f", temp));
                }
            }

        }));
    }
}