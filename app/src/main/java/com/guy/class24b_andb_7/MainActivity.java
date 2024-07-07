package com.guy.class24b_andb_7;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.guy.mylogger.MinDataHelper;
import com.guy.mylogger.MinLog;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final long HOUR = 1000l * 60 * 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        MinDataHelper.getInstance().addLog("myLogger", "Hiii");

        MinDataHelper.getInstance().getAllLogs(minLogs -> printLogs(minLogs));



    }

    private void printLogs(List<MinLog> minLogs) {
        for (MinLog minLog : minLogs) {
            Log.d("pttt", minLog.toString());
        }
    }
}