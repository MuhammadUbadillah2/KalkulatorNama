package mobprog.kuliah3.MuhammadUbaidillah;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    String input = "", operator = "";
    double val1 = 0, val2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etInput = findViewById(R.id.etInput);

        setNumberButton(R.id.btn0, "0");
        setNumberButton(R.id.btn1, "1");
        setNumberButton(R.id.btn2, "2");
        setNumberButton(R.id.btn3, "3");
        setNumberButton(R.id.btn4, "4");
        setNumberButton(R.id.btn5, "5");
        setNumberButton(R.id.btn6, "6");
        setNumberButton(R.id.btn7, "7");
        setNumberButton(R.id.btn8, "8");
        setNumberButton(R.id.btn9, "9");

        setOperatorButton(R.id.btnPlus, "+");
        setOperatorButton(R.id.btnMinus, "-");
        setOperatorButton(R.id.btnMultiply, "x");
        setOperatorButton(R.id.btnDivide, "/");

        Button btnDot = findViewById(R.id.btnDot);
        btnDot.setOnClickListener(v -> {
            if (!input.contains(".")) {
                if (input.equals("")) {
                    input = "0.";
                } else {
                    input += ".";
                }
                etInput.setText(input);
            }
        });

        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(v -> {
            input = "";
            operator = "";
            val1 = 0;
            val2 = 0;
            etInput.setText("");
        });

        Button btnEqual = findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(v -> {
            if (!input.equals("") && !operator.equals("")) {
                val2 = Double.parseDouble(input);
                double result = 0;

                switch (operator) {
                    case "+":
                        result = val1 + val2;
                        break;
                    case "-":
                        result = val1 - val2;
                        break;
                    case "*":
                        result = val1 * val2;
                        break;
                    case "/":
                        if (val2 != 0) {
                            result = val1 / val2;
                        } else {
                            etInput.setText("Error");
                            return;
                        }
                        break;
                }

                if (result == (long) result) {
                    etInput.setText(String.valueOf((long) result));
                } else {
                    etInput.setText(String.valueOf(result));
                }

                // Reset setelah hasil ditampilkan
                input = "";
                operator = "";
            }
        });
    }

    void setNumberButton(int id, String value) {
        Button btn = findViewById(id);
        btn.setOnClickListener(v -> {
            input += value;
            etInput.setText(input);
        });
    }

    void setOperatorButton(int id, String op) {
        Button btn = findViewById(id);
        btn.setOnClickListener(v -> {
            if (!input.equals("")) {
                val1 = Double.parseDouble(input);
                operator = op;
                input = "";
                etInput.setText(op);
            }
        });
    }
}
