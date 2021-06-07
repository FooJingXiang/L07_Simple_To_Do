package sg.edu.rp.c346.id20011262.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Button btnAdd, btnClear,btnDelete;
    ListView lv;
    ArrayList<String> listArray;
    ArrayAdapter<String> aalist;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editTextTextPersonName);
        lv = findViewById(R.id.listview);
        btnAdd = findViewById(R.id.button2);
        btnClear = findViewById(R.id.button3);
        btnDelete = findViewById(R.id.button4);
        listArray = new ArrayList<String>();
        spinner = findViewById(R.id.spinner);

        listArray = new ArrayList<>();
        aalist = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listArray);
        lv.setAdapter(aalist);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = et.getText().toString();
                listArray.add(txt);
                et.setText("");
                aalist.notifyDataSetChanged();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listArray.clear();
                aalist.notifyDataSetChanged();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int inputvalue = et.getText().toString().length()-1;
                int arrayvalue = listArray.size();
                if(arrayvalue == 0) {
                    Toast.makeText(MainActivity.this,"You don't have any task to remove",Toast.LENGTH_SHORT).show();
                } else if(inputvalue > arrayvalue) {
                    Toast.makeText(MainActivity.this,"Wrong index number",Toast.LENGTH_SHORT).show();
                } else {
                    int pos = Integer.parseInt(et.getText().toString());
                    listArray.remove(pos);
                    aalist.notifyDataSetChanged();
                }

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        et.setHint("Type in a new task here");
                        btnDelete.setEnabled(false);
                        btnDelete.setTextColor(Color.RED);
                        btnAdd.setEnabled(true);
                        btnAdd.setTextColor(Color.GREEN);
                        btnClear.setTextColor(Color.GREEN);
                        break;
                    case 1:
                        et.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnAdd.setTextColor(Color.RED);
                        btnDelete.setEnabled(true);
                        btnDelete.setTextColor(Color.GREEN);
                        btnClear.setTextColor(Color.GREEN);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}