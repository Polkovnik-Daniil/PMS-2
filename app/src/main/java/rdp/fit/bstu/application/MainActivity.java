package rdp.fit.bstu.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static Date ldsubtract = new Date(0), resultD = new Date(0);
    private static EditText edtSubtractFrom = null;
    private static TextView tview = null;
    private static String str = null;
    private static Date date = new Date(0);
    private static long resultL = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClickPlus(View view){
        calculate(true);
    }

    public void OnClickMinus(View view){
        calculate(false);
    }

    public void OnClickClr(View view){
        ldsubtract = new Date(0);
        resultL = 0;
    }

    private void calculate(boolean value) {
        edtSubtractFrom = (EditText)findViewById(R.id.subtract);
        tview = (TextView)findViewById(R.id.withdraw);
        try{
            str = edtSubtractFrom.getText().toString();
            date = formatter.parse(str);
            if(value){
                resultL = date.getTime() + ldsubtract.getTime();
                Display(resultL);
                return;
            }
            resultL = ldsubtract.getTime() -  date.getTime();
            Display(resultL);
            return;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            tview.setText("");
            tview.setText("Uncorrected value!");
        }
    }
    private void Display(long resultL){
        try {
            resultD = new Date(resultL);
            String resultStr = String.format("%d m, %d h, %d d, %d M, %d Y", resultD.getMinutes(), resultD.getHours(), resultD.getDay(), resultD.getMonth(), resultD.getYear());
            tview.setText(resultStr);
            ldsubtract = resultD;
            return;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            tview.setText("");
            tview.setText("Uncorrected value!");
        }
    }
}