package com.blundell.tests;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blundell.tests.controller.ContactsProvider;
import com.blundell.tests.model.Contact;
import com.blundell.tests.model.Contacts;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MyActivity extends Activity {

    private static final String TAG = "MyActivity";

    @InjectView(R.id.my_hello_text_view)
    TextView my_hello_text_view;

    @InjectView(R.id.button)
    Button button;

    @InjectView(R.id.button2)
    Button button2;

    private ContactsProvider contactsProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);

        contactsProvider = new ContactsProvider(getString(R.string.contacts_json_endpoint));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.button)
    public void ButtonClick(View view)
    {
        Resources rs = getResources();
        my_hello_text_view.setText(rs.getString(R.string.button_clicked));
    }

    @OnClick(R.id.button2)
    public void Button2Click(View view)
    {
        contactsProvider.fetchContacts(new ContactsProvider.ContactProviderListener() {
            @Override
            public void onContacts(Contacts contacts) {
                Log.d(TAG, String.format("fetched and parsed %d contacts: ", contacts.getContacts().size()));
                updateContacts(contacts);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "failed fetching json data", throwable);
            }
        });
    }

    private void updateContacts(final Contacts contacts) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(contacts.getContacts().size() > 0) {

                    my_hello_text_view.setText("");
                    for (Contact c : contacts.getContacts()) {
                        my_hello_text_view.append(c.toString() + "\n");
                    }
                }
            }
        });
    }
}
