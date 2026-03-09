package com.example.ex5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements  FragA.FragAListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            getSupportFragmentManager().popBackStack(null,
                    getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onAnyClick(boolean up) {
        int orientation = getResources().getConfiguration().orientation;
        Fragment fragment;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragment = getSupportFragmentManager()
                    .findFragmentById((R.id.fragmentContainerView3));
            ((FragB)fragment).updateCounter(up);
        }
        else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragment = FragB.newInstance(up);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView2, fragment)
                    .addToBackStack("")
                    .commit();
            getSupportFragmentManager().executePendingTransactions();
            ((FragB) fragment).updateCounter(up);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.exit)
        {
            confirmAndExit();
        }
        return super.onOptionsItemSelected(item);
    }

    private void confirmAndExit()
    {
        new AlertDialog.Builder(this).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAndRemoveTask();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}