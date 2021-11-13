package com.example.csc2005_team04.ui.payslips;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.csc2005_team04.databinding.FragmentPayslipsBinding;

public class PayslipsFragment extends Fragment {

    Activity context;

    private PayslipsViewModel payslipsViewModel;
    private FragmentPayslipsBinding binding;

    private Button previouspayslip;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();

        payslipsViewModel =
                new ViewModelProvider(this).get(PayslipsViewModel.class);

        binding = FragmentPayslipsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }


    public void onStart() {
        super.onStart();
        previouspayslip = (Button) context.findViewById(com.example.csc2005_team04.R.id.button3);
        previouspayslip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PreviousPayslips.class);
                startActivity(intent);
            }
        });
        Button btn = (Button) context.findViewById(com.example.csc2005_team04.R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LatestPayslip.class);
                startActivity(intent);
            }

        });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}