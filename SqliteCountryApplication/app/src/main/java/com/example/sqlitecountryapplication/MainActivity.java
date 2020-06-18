package com.example.sqlitecountryapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/* EZGİ GÖNÜL 15070001022*/
public class MainActivity extends AppCompatActivity {

    final Context context = this;
    Database db;
    TextView text;
    ListView list;
    EditText country_edx, currency_edx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Database(context);
        text = (TextView) findViewById(R.id.txt);


        if (db.getRowCount() == 0) {
            text.setText("No record found !");
        } else {
            list = (ListView) findViewById(R.id.list_view);
            ArrayAdapter<Data> dataAdapter = new ArrayAdapter<Data>
                    (this, android.R.layout.simple_list_item_2, android.R.id.text1, db.ListData());
            list.setAdapter(dataAdapter);
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Data obj = (Data)parent.getAdapter().getItem(position);
                String obj_id=String.valueOf(obj.getId());
                String obj_country=String.valueOf(obj.getCountry());
                String obj_currency= String.valueOf(obj.getCurrency());
                openModifyDialogue(obj_id,obj_country,obj_currency);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Action Bar içinde kullanılacak menü öğelerini inflate edelim
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Action Bar öğelerindeki basılmaları idare edelim
        switch (item.getItemId()) {
            case R.id.add:
                openAddDialogue();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createList() {
        list = (ListView) findViewById(R.id.list_view);
        ArrayAdapter<Data> dataAdapter = new ArrayAdapter<Data>
                (this, android.R.layout.simple_list_item_2, android.R.id.text1, db.ListData());
        list.setAdapter(dataAdapter);
    }

    private void openAddDialogue() {
        final View mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_add_dialog, null);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Add Record");
        mBuilder.show();
        Button add_record = (Button) mDialogView.findViewById(R.id.dialogAddBtn);


        add_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country_edx = (EditText) mDialogView.findViewById(R.id.dialogCountryEt);
                currency_edx = (EditText) mDialogView.findViewById(R.id.dialogCurrencyEt);
                String country_name = country_edx.getText().toString();
                String currency_name = currency_edx.getText().toString();
                db.addRecord(country_name, currency_name);
                text.setVisibility(View.GONE);
                createList();
                Toast.makeText(context, "Successfully Added.", Toast.LENGTH_SHORT).show();
                country_edx.getText().clear();
                currency_edx.getText().clear();
            }
        });
    }

    private void openModifyDialogue(final String id_record, String country, String currency) {
        final View mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_delete_modify_dialog, null);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Modify Record");
        mBuilder.show();
        country_edx = (EditText) mDialogView.findViewById(R.id.dialogCountryEtx);
        currency_edx = (EditText) mDialogView.findViewById(R.id.dialogCurrencyEtx);

        country_edx.setText(country);
        currency_edx.setText(currency);

        Button modify_record = (Button) mDialogView.findViewById(R.id.dialogModifyBtn);
        Button delete_record = (Button) mDialogView.findViewById(R.id.dialogDeleteBtn);

        modify_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country_name = country_edx.getText().toString();
                String currency_name = currency_edx.getText().toString();

                db.updateRecord(country_name,currency_name,Integer.parseInt(id_record));
                createList();

                Toast.makeText(context, "Successfully Modified.", Toast.LENGTH_SHORT).show();

            }
        });

        delete_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteRecord(Integer.parseInt(id_record));
                createList();

                Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
