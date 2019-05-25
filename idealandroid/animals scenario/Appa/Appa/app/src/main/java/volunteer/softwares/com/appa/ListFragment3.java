package volunteer.softwares.com.appa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import volunteer.softwares.com.Utility.RecycleViewItemClickListener;



public class ListFragment3 extends Fragment implements View.OnClickListener {
    ImageView imageView,imageView1;
    RecyclerView recyclerView;
    List<Integer> selections= new ArrayList<Integer>();
    Adapter adapter;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    FloatingActionButton floatingActionButton;
    int[] houses={
            R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,R.drawable.h6,R.drawable.dh};
    String[] houseNames={"h1","h2","h3","h4","h5","h6","dh"};
    private int selectedItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        selections.clear();
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedpreferences =  this.getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        floatingActionButton=(FloatingActionButton)view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
        floatingActionButton.setVisibility(View.GONE);
        imageView=(ImageView)view.findViewById(R.id.image);
        imageView1=(ImageView)view.findViewById(R.id.image1);
        recyclerView=view.findViewById(R.id.recycler);
        int numberOfColumns = 4;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter=new Adapter(houses, getActivity(), new RecycleViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                imageView.setVisibility(View.GONE);
                imageView1.setVisibility(View.GONE);
                selections.add(position);
                if(selections.size()==2)
                {
                    imageView.setVisibility(View.VISIBLE);
                    imageView1.setVisibility(View.VISIBLE);
//                    imageView.setImageResource(houses[selections.get(0)]);
//                    imageView1.setImageResource(houses[selections.get(1)]);
                    ClipDrawable clipDrawable = new ClipDrawable(getResources().getDrawable(houses[selections.get(0)]), Gravity.LEFT, ClipDrawable.HORIZONTAL);
                    clipDrawable.setLevel(5000);
                    imageView.setBackground(clipDrawable);
                    ClipDrawable clipDrawable2 = new ClipDrawable(getResources().getDrawable(houses[selections.get(1)]), Gravity.RIGHT, ClipDrawable.HORIZONTAL);
                    clipDrawable2.setLevel(5000);
                    imageView1.setBackground(clipDrawable2);


                    if(selections.get(0)==selections.get(1))
                    {
                        floatingActionButton.setVisibility(View.VISIBLE);
                        selectedItem = houses[selections.get(1)];
                        selections.clear();
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Both selections should be same", Toast.LENGTH_SHORT).show();
                        selections.clear();
                    }
                }
                else {

                    imageView.setVisibility(View.VISIBLE);
                    ClipDrawable clipDrawable = new ClipDrawable(getResources().getDrawable(houses[selections.get(0)]),
                            Gravity.LEFT, ClipDrawable.HORIZONTAL);
                    clipDrawable.setLevel(5000);
                    imageView.setBackground(clipDrawable);
                    floatingActionButton.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Select the second item", Toast.LENGTH_SHORT).show();
                }

            }
        });
        recyclerView.setAdapter(adapter);
    }

    public void addToPos(int pos, Object jsonObj, JSONArray jsonArr) throws JSONException {
        for (int i = jsonArr.length(); i > pos; i--){
            jsonArr.put(i, jsonArr.get(i-1));
        }
        jsonArr.put(pos, jsonObj);
    }

    private void addImage(){

        try{
            JSONArray images = new JSONArray(sharedpreferences.getString("house", "[]"));

            //addToPos(0, animals[selections.get(1)], images);
            JSONArray r = new JSONArray();
            for(int i = 0; i < images.length(); i++){
                r.put(images.get(i));
            }

            r.put(selectedItem);

            //images = r;
            sharedpreferences.edit().putString("house", r.toString()).apply();
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        addImage();
        startActivity(new Intent(getActivity(), Main2Activity.class));
        getActivity().finish();

        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new ListFragment2(),ListFragment2.class.getName()).commit();
    }
}
