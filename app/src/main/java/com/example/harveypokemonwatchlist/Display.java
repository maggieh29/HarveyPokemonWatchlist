package com.example.harveypokemonwatchlist;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.MalformedURLException;


public class Display extends Fragment {
    TextView name;
    TextView iD;
    TextView height;
    TextView weight;
    TextView xP;
    TextView move;
    TextView ability;
    TextView nameTitle;
    ImageView sprite;

    CustomViewModel sharedModel;

    Observer<Pokemon> observer = new Observer<Pokemon>() {
        @Override
        public void onChanged(Pokemon current) {
            current = sharedModel.getCurrent().getValue();
            name.setText(current.getName());
            iD.setText(current.getId());
            height.setText(current.getHeight());
            weight.setText(current.getWeight());
            xP.setText(current.getXp());
            move.setText(current.getMove());
            ability.setText(current.getAbility());
            nameTitle.setText(current.getName());

            prepID(current.getId());

        }
    };


    public Display(){}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedModel = new ViewModelProvider(getActivity()).get(CustomViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_display, container, false);
        name = v.findViewById(R.id.nameET);
        iD = v.findViewById(R.id.ID);
        height = v.findViewById(R.id.heightET);
        weight = v.findViewById(R.id.weightET);
        xP = v.findViewById(R.id.XP);
        move = v.findViewById(R.id.moveET);
        ability = v.findViewById(R.id.abilityET);
        nameTitle = v.findViewById(R.id.titleName);
        sprite = v.findViewById(R.id.picture);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedModel.getCurrent().observe(getViewLifecycleOwner(), observer);
    }

    public void prepID(String id){
        if(id.length() < 3){
            id = String.format("%03d", Integer.parseInt(id));
        }
        getImageRequest(id);
    }


    public void getImageRequest(String id){
            String imageUrl = "https://raw.githubusercontent.com/HybridShivam/Pokemon/master/assets/images/"
                    + id + ".png";

            Picasso.get().load(imageUrl).into(sprite);

    }

}