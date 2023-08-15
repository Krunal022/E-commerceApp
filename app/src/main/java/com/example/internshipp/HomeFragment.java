package com.example.internshipp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    String [] nameArray={"person 1 ","person 2","person 3",};

    int[] imageArray ={R.drawable.ic_home,R.drawable.ic_user,R.drawable.contactpic};

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.home_recyclerview);

       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       recyclerView.setItemAnimator(new DefaultItemAnimator());


       ProductAdapter adapter = new ProductAdapter(getActivity(),nameArray,imageArray);
       recyclerView.setAdapter(adapter);

        return view;
    }
}