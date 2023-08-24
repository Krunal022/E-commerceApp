package com.example.internshipp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    String [] idArray ={"1","2","3"};

    String [] nameArray={"VARSITY JACKET","CHOLE BHATURE","GREEN KURTA",};

    int[] imageArray ={R.drawable.jecket,R.drawable.cholle,R.drawable.kurtta};

    String[] priceArray = {"4000","220","2500"};
    String[] descArray = {"A varsity jacket, also known as a letterman jacket,\nThe jacket is a staple in American jock culture and is both comfortable and fashionable.",
            "Chole bhature is a popular food dish in the Northern areas of the Indian subcontinent.\n It is a combination of chana masala, which is spicy white chickpeas, and bhatura / puri, a deep-fried bread made from maida.",
            "Kurta is a long tunic-style shirt that is traditionally worn by South Asian men, e specially Indians.\nKurtas for men are available in a variety of prints and designs. "
    };


    RecyclerView catagoryRecyclerView;
    String [] categoryNameArray ={"Health","Travel","Beauty","Fashion","Food"};
    int[] categoryImageArray={R.drawable.health,R.drawable.travel,R.drawable.beauty,R.drawable.fashion,R.drawable.food_drink};

    ArrayList<CategoryList> arrayList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.home_recyclerview);

         // Display Data in List:
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Display Data in Grid Format:
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        //Display Data in Horizontal Scroll
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());


       ProductAdapter adapter = new ProductAdapter(getActivity(),nameArray,imageArray,priceArray,descArray,idArray);
       recyclerView.setAdapter(adapter);

       //For Catagory:
        catagoryRecyclerView = view.findViewById(R.id.home_recyclerview_catagory);
        catagoryRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        catagoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        
        arrayList = new ArrayList<>();
        for (int i=0;i<categoryNameArray.length;i++){
            CategoryList list = new CategoryList();
            list.setName(categoryNameArray[i]);
            list.setImage(categoryImageArray[i]);
            arrayList.add(list);
        }

        CategoryAdapter catAdapter = new CategoryAdapter( getActivity(),arrayList);
        catagoryRecyclerView.setAdapter(catAdapter);

        return view;
    }
}