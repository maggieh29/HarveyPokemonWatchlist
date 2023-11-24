package com.example.harveypokemonwatchlist;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;

public class CustomViewModel extends ViewModel {
public MutableLiveData<LinkedList<Pokemon>> pokes;
public LinkedList<Pokemon> pokeList;
public MutableLiveData<Pokemon> current;


    public LiveData<LinkedList<Pokemon>> getPokes() {
        if (pokes == null) {
            pokes = new MutableLiveData<LinkedList<Pokemon>>();
            loadPokes();
        }
        return pokes;
    }

    public void addPoke(Pokemon p){
        if (pokes == null){
            pokes = new MutableLiveData<>();
        }
        if(pokeList == null) {
            pokeList = new LinkedList<>();
        }
        loadPokes();
        pokeList = pokes.getValue();
        pokeList.add(p);
        pokes.setValue(pokeList);
        setCurrent(p);
    }

    private void loadPokes() {
        if(pokeList == null) {
            pokeList = new LinkedList<>();
        }
        pokes.setValue(pokeList);
    }

    public MutableLiveData<Pokemon> getCurrent(){
        Pokemon p = new Pokemon("ditto", "132", "40", "3", "101",
                "transform", "limber");
        if(current == null){
            current = new MutableLiveData<>();
            current.setValue(p);
        }
        return current;
    }

    public void setCurrent(Pokemon newCurrent){
        if(current == null){
            current = new MutableLiveData<Pokemon>();
        }
        current.setValue(newCurrent);
    }



}
