package ru.puchkova.homework43;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Button ok, selectStart, selectEnd;
    private CalendarView start, end;

    private long startDate;
    private String startDateTxt;
    private long endDate;
    private String endDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        selectStart = findViewById(R.id.selectStart);
        selectEnd = findViewById(R.id.selectEnd);
        start = findViewById(R.id.start);
        end = findViewById(R.id.end);
        ok = findViewById(R.id.ok);

        start.setVisibility(View.GONE);
        end.setVisibility(View.GONE);

        selectStart.setOnClickListener(oclStart);
        selectEnd.setOnClickListener(oclEnd);

        ok.setOnClickListener(oclOk);

    }


    View.OnClickListener oclStart = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            start.setVisibility(View.VISIBLE);
            end.setVisibility(View.GONE);
            start.setOnDateChangeListener(odclStart);
        }
    };

    View.OnClickListener oclEnd = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            end.setVisibility(View.VISIBLE);
            start.setVisibility(View.GONE);
            end.setOnDateChangeListener(odclEnd);
        }
    };

    CalendarView.OnDateChangeListener odclStart = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            startDateTxt = year+"-"+month+"-"+dayOfMonth;
            selectStart.setText("Дата-время старта задачи: " + startDateTxt);
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.set(year, month, dayOfMonth);
            startDate = gregorianCalendar.getTimeInMillis();
            view.setVisibility(View.GONE);
        }
    };

    CalendarView.OnDateChangeListener odclEnd = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            endDateTxt = year+"-"+month+"-"+dayOfMonth;
            selectEnd.setText("Дата-время окончания задачи: " + endDateTxt);
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.set(year, month, dayOfMonth);
            endDate = gregorianCalendar.getTimeInMillis();
            view.setVisibility(View.GONE);
        }
    };

    View.OnClickListener oclOk = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (startDate > endDate){
                Toast.makeText(MainActivity.this, "Ошибка", Toast.LENGTH_LONG).show();
                selectStart.setText("Дата-время старта задачи:");
                selectEnd.setText("Дата-время окончания задачи:");
            } else {
                Toast.makeText(MainActivity.this, "старт: " + startDateTxt + " окончаниe: " + endDateTxt, Toast.LENGTH_LONG).show();
            }
        }
    };
}
