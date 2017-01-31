package com.tuccle22.dtd.dailytodo.DayDetails;

import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.tuccle22.dtd.dailytodo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemActivity extends AppCompatActivity {

    @BindView(R.id.new_todo_title) TextInputEditText title_input;
    @BindView(R.id.circle_day_number) com.github.pavlospt.roundedletterview.RoundedLetterView circle_day_number;
    @BindView(R.id.weekday_title) TextView weekday_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);

        String day_title = getIntent().getExtras().getString("DAY_TITLE");
        weekday_title.setText(day_title);
        String day_of_month = getIntent().getExtras().getString("DAY_OF_MONTH");
        circle_day_number.setTitleText(day_of_month);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
