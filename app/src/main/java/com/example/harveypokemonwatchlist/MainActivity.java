package com.example.harveypokemonwatchlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    EditText entry;
    Button searchButton;
    private String name;
    private CustomViewModel sharedModel;

    View.OnClickListener search = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            name = entry.getText().toString();

            //test to make sure entry is only letters or numbers
            boolean pass = true;
            for(int i = 0; i < name.length(); i++){
                Character c = name.charAt(i);
                if(!Character.isAlphabetic(c)&&!Character.isDigit(c)){
                    pass = false;
                }
            }

            if(pass == true){
                request(name);
            }else{
                Toast.makeText(getApplicationContext(),
                        "The search must be letters or numbers only", Toast.LENGTH_SHORT).show();
            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entry = findViewById(R.id.searchBar);
        searchButton = findViewById(R.id.searchButt);
        searchButton.setOnClickListener(search);
        sharedModel = new ViewModelProvider(this).get(CustomViewModel.class);


    }

    private void request(String name){
        ANRequest req = AndroidNetworking.get("https://pokeapi.co/api/v2/pokemon/{poke}")
                .addPathParameter("poke", name)
                .setPriority(Priority.LOW)
                .build();

        req.getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String name1 = response.getString("name");
                    String id = response.getString("id");
                    String weight = response.getString("weight");
                    String height = response.getString("height");
                    String xp = response.getString("base_experience");
                    String move = response.getJSONArray("moves").getJSONObject(0)
                            .getJSONObject("move").getString("name");
                    String ability = response.getJSONArray("abilities").getJSONObject(0)
                            .getJSONObject("ability").getString("name");

                    Pokemon pok = new Pokemon(name1, id, weight, height, xp, move, ability);
                    sharedModel.addPoke(pok);



                }catch(JSONException e){
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(getApplicationContext(),"Error on getting data ", Toast.LENGTH_LONG).show();
            }
        });
    }
}