package com.sust.bup_project.loan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sust.bup_project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoanFragment extends Fragment {


    public LoanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loan, container, false);
    }

}
