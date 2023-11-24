package com.example.harveypokemonwatchlist;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;


public class Watchlist extends Fragment {


    ListView list;
    CustomViewModel mViewModel;


    public Watchlist() {// Required empty public constructor
         }


    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Pokemon selected = (Pokemon) list.getItemAtPosition(i);
            //Toast.makeText(getActivity(), selected, Toast.LENGTH_LONG).show();
            mViewModel.setCurrent(selected);


        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_watchlist, container, false);
        list = v.findViewById(R.id.list);
        list.setOnItemClickListener(listener);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel =  new ViewModelProvider(getActivity()).get(CustomViewModel.class);
        mViewModel.getPokes().observe(getViewLifecycleOwner(), new Observer<LinkedList<Pokemon>>() {
            @Override
            public void onChanged(LinkedList<Pokemon> pokes) {
                ArrayAdapter<Pokemon> adapter =  new ArrayAdapter<Pokemon>(getActivity(), android.R.layout.simple_list_item_1, mViewModel.getPokes().getValue());
                list.setAdapter(adapter);
            }
        });
    }
}