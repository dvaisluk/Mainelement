package com.example.mainelement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, AdapterView.OnItemClickListener {

    TextView mainTextView;
    Button mainButton;
    EditText mainEditText;
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList mNameList = new ArrayList();
    Button ok_btn, cnc_btn;
    String new_name;
    String del_name;
    int pos = -1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTextView = findViewById(R.id.main_textview);
        mainTextView.setText("Set in Java!");
        mainButton = findViewById(R.id.main_button);
        mainButton.setOnClickListener(this);
        mainEditText = (EditText) findViewById(R.id.main_edittext);
        mainListView = findViewById(R.id.main_listview);
        mArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                mNameList);
        mainListView.setAdapter(mArrayAdapter);
        mainListView.setOnItemClickListener(this);
        ok_btn = findViewById(R.id.ok_btn);
        cnc_btn = findViewById(R.id.cnc_btn);
        ok_btn.setOnClickListener(oclBtn);
        cnc_btn.setOnClickListener(oclBtn);


    }

    @SuppressLint("SetTextI18n")
    public void onClick(View v) {
        mainTextView.setText(mainEditText.getText().toString()
                + " is learning Android development!");
        new_name = mainEditText.getText().toString();
        mNameList.add(new_name);
        mArrayAdapter.notifyDataSetChanged();
        // удаление дублированных элементов
        Set<String> set = new HashSet<>(mNameList);
        mNameList.clear();
        mNameList.addAll(set);
        // Сортирова 
        Collections.sort(mNameList);

    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("omg android", position + ": " + mNameList.get(position));
        mainTextView.setText(mNameList.get(position).toString()
                + " is learning Android development!");
        // Сохраняем выбранный элемент в переменную
        del_name = mNameList.get(position).toString();
        // сохраняем индекс элемента из  списска
        pos = mNameList.indexOf(del_name);



    }

    View.OnClickListener oclBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // по id определеяем кнопку, вызвавшую этот обработчик
            switch (v.getId()) {
                case R.id.ok_btn:
                    // кнопка удаления элемента и само удаление элемента
                    Toast.makeText(getApplicationContext(), "Элемент удалён!", Toast.LENGTH_LONG).show();
                    for (int i = 0; i < mNameList.size(); i++)
                        if (i == pos) {
                            mNameList.remove(i);
                            mArrayAdapter.notifyDataSetChanged();
                            break;
                        }
                    break;
                case R.id.cnc_btn:
                    // кнопка Cancel
                    finish();
                    System.exit(0);
            }
        }
    };

}
