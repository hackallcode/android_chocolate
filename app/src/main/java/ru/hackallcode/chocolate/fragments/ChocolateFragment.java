package ru.hackallcode.chocolate.fragments;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.hackallcode.chocolate.R;
import ru.hackallcode.chocolate.views.PieceView;

public class ChocolateFragment extends Fragment {
    final private String COUNT_KEY = "COUNT";
    final private int LANDSCAPE_WIDTH = 4;
    final private int PORTRAIT_WIDTH = 3;

    private int pieceCount = 0;
    private DataAdapter adapter;

    public ChocolateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adapter = new DataAdapter();
        if (savedInstanceState != null) {
            pieceCount = savedInstanceState.getInt(COUNT_KEY);
        }
        return inflater.inflate(R.layout.chocolate_fragment, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView grid = view.findViewById(R.id.chocolate);
        int orientation = getResources().getConfiguration().orientation;
        int spanCount = orientation == Configuration.ORIENTATION_PORTRAIT
                ? PORTRAIT_WIDTH
                : LANDSCAPE_WIDTH;
        grid.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        grid.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                adapter.notifyItemInserted(++pieceCount);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNT_KEY, pieceCount);
    }

    /* DataHolder */

    class DataHolder extends RecyclerView.ViewHolder {
        private final PieceView piece;
        private final TextView pieceText;

        DataHolder(View v) {
            super(v);
            piece = v.findViewById(R.id.piece);
            pieceText = v.findViewById(R.id.pieceText);
        }
    }

    class DataAdapter extends RecyclerView.Adapter<DataHolder> {
        @NonNull
        @Override
        public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.piece_view, parent, false);
            return new DataHolder(view);
        }

        @Override
        @SuppressLint("SetTextI18n")
        public void onBindViewHolder(@NonNull DataHolder holder, int position) {
            final int num = position + 1;
            holder.pieceText.setText(Integer.toString(num));
            if (num % 2 == 0) {
                holder.pieceText.setTextColor(getResources().getColor(R.color.red_text));
            } else {
                holder.pieceText.setTextColor(getResources().getColor(R.color.blue_text));
            }
            holder.piece.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NumberFragment numberFragment = new NumberFragment(num);
                    FragmentManager manager = getFragmentManager();
                    if (manager != null) {
                        manager
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.main_container, numberFragment)
                                .commit();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return pieceCount;
        }
    }
}

