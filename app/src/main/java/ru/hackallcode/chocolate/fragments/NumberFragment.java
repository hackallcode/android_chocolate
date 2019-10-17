package ru.hackallcode.chocolate.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.hackallcode.chocolate.R;

public class NumberFragment extends Fragment {
    final private String NUMBER_KEY = "NUMBER";

    private int number;

    public NumberFragment() {
    }

    NumberFragment(int number) {
        this.number = number;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            number = savedInstanceState.getInt(NUMBER_KEY);
        }
        return inflater.inflate(R.layout.number_fragment, container, false);
    }

    @Override
    @SuppressLint("SetTextI18n")
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView textView = view.findViewById(R.id.number);
        textView.setText(Integer.toString(number));
        if (number % 2 == 0) {
            textView.setTextColor(getResources().getColor(R.color.red_text));
        } else {
            textView.setTextColor(getResources().getColor(R.color.blue_text));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NUMBER_KEY, number);
    }
}
