package com.example.csc2005_team04.ui.leaves;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.csc2005_team04.R;
import com.example.csc2005_team04.databinding.FragmentLeavesBinding;

public class LeavesFragment extends Fragment {

    private LeavesViewModel leavesViewModel;
    private FragmentLeavesBinding binding;

    /*
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        leavesViewModel =
                new ViewModelProvider(this).get(LeavesViewModel.class);

        binding = FragmentLeavesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView textView = binding.textGallery;
        leavesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_leaves,
                container, false);

        Button button = (Button) view.findViewById(R.id.LeaveInfo);
        Button button1 = (Button) view.findViewById(R.id.LeaveStat);
        Button button2 = (Button) view.findViewById(R.id.LeaveApp);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
                openLeaveInfoPage();
            }

        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLeaveStatPage();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLeaveAppPage();
            }
        });





        return view;
    }






    /*
    public void LeaveAppOnClick(View v)
    {
        Button button = (Button) getView().findViewById(R.id.LeaveApp);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                openLeaveAppPage();
                System.out.println("openLeavePage() is done!");
            }
        });
    }
    public void LeaveInfoOnClick(View v)
    {
        Button button = (Button) getView().findViewById(R.id.LeaveInfo);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                openLeaveInfoPage();
            }
        });
    }
    public void LeaveStatOnClick(View v)
    {
        Button button = (Button) getView().findViewById(R.id.LeaveInfo);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                openLeaveStatPage();
            }
        });

    }
    */

    public void openLeaveAppPage()
    {

        Intent intent = new Intent(getActivity(), com.example.csc2005_leave.LeaveApplicationPage.class);
        startActivity(intent);
    }
    public void openLeaveInfoPage()
    {
        Intent intent = new Intent(getActivity(), com.example.csc2005_leave.LeaveInfoPage.class);

        startActivity(intent);
    }
    public void openLeaveStatPage()
    {
        Intent intent = new Intent(getActivity(), com.example.csc2005_leave.LeaveApplicationStatus.class);
        startActivity(intent);
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}