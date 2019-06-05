package fr.billoo.mobile.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.billoo.mobile.R;

public class BarCodeFragment extends BottomSheetDialogFragment
{

    public static BarCodeFragment newInstance() {
        return new BarCodeFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bar_code, container,
                false);

        // get the views and attach the listener

        return view;
    }
}
