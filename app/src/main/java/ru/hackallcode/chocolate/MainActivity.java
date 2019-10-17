package ru.hackallcode.chocolate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.hackallcode.chocolate.fragments.ChocolateFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            ChocolateFragment fragment = new ChocolateFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, fragment).commit();
        }
    }
}
