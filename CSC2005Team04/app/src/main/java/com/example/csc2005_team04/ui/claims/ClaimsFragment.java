package com.example.csc2005_team04.ui.claims;

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

import com.example.csc2005_team04.databinding.FragmentClaimsBinding;

public class ClaimsFragment extends Fragment {

    Activity context;

    private ClaimsViewModel claimsViewModel;
    private FragmentClaimsBinding binding;

    private Button claimstatus;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();

        claimsViewModel =
                new ViewModelProvider(this).get(ClaimsViewModel.class);

        binding = FragmentClaimsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    public void onStart(){
        super.onStart();
        claimstatus = (Button) context.findViewById(com.example.csc2005_team04.R.id.button);
        claimstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ClaimApplicationStatus.class);
                startActivity(intent);
            }
        });
        Button btn = (Button) context.findViewById(com.example.csc2005_team04.R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ClaimApplication.class);
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