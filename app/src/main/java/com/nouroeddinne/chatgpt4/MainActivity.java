package com.nouroeddinne.chatgpt4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editText;
    Button button;
    RecyclerView.Adapter adapter;
    List<ModelMsg> list = new ArrayList<ModelMsg>();;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiManager apiManager = new ApiManager();

        adapter = new Adapter(list,this);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                String jsonBody = "{\"messages\":[{\"role\":\"user\",\"content\":\"hello\"}],\"web_access\":false}";
                String messege = jsonBody.replace("hello",editText.getText().toString());
                apiManager.sendChatRequest(messege, new ApiManager.CallbackResponse() {
                    @Override
                    public void onSuccess(String text) {

                        JsonObject jsonObject = JsonParser.parseString(text).getAsJsonObject();
                        String result = jsonObject.get("result").getAsString();
                        list.add(new ModelMsg(result,"gpt"));
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                    }
                });
                list.add(new ModelMsg(editText.getText().toString(),"you"));
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
















    }












}