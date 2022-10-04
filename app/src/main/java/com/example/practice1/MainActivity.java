package com.example.practice1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText inputA;
    EditText inputB;
    EditText inputX;
    TextView answer;
    Button solve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputA = findViewById(R.id.inputA);
        inputB = findViewById(R.id.inputB);
        inputX = findViewById(R.id.inputX);
        answer = findViewById(R.id.answer);
        solve =  findViewById(R.id.button);

        if (savedInstanceState != null) {
            answer.setText(savedInstanceState.getString("y"));
            solve.setEnabled( savedInstanceState.getBoolean("vButton") );
        }
        else{
            solve.setEnabled(false);
        }

        View.OnKeyListener myKeyListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (inputA.getText().toString().trim().equals("") ||
                        inputB.getText().toString().trim().equals("") ||
                        inputX.getText().toString().trim().equals(""))  {
                    solve.setEnabled(false);
                } else {
                    solve.setEnabled(true);
                }
                return false;
            }
        };
        inputA.setOnKeyListener(myKeyListener);
        inputB.setOnKeyListener(myKeyListener);
        inputX.setOnKeyListener(myKeyListener);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Сохранение нужных нам значений компонент при перевороте экрана
        outState.putString("y", answer.getText().toString());
        outState.putBoolean("vButton", solve.isEnabled());
    }
    public void onClick(View V) {
        try {
            float a = Float.parseFloat(inputA.getText().toString());
            float b = Float.parseFloat(inputB.getText().toString());
            float x = Float.parseFloat(inputX.getText().toString());
            float y;
            if (x >= 4) {
                y = (10 * (x + a * a))/(b+a);
            } else {
                y = 5 * (x + a * a + b);
            }
            if (!(Double.isNaN(y)) && (!Double.isInfinite(y))) {
                answer.setText("Ответ: y = " + y); // Вывод ответа
            } else {
                answer.setText("Нет решения!"); // Нет решения
            }
        }
        catch(Exception e){
            answer.setText("Неправильно введены данные");
        }
    }


}