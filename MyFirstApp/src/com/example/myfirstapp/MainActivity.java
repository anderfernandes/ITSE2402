package com.example.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button btn;
	private EditText edit;
	private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // My code
        edit = (EditText)findViewById(R.id.editText1);
        view = (TextView)findViewById(R.id.textView1);
        btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(btn==v){
			String msg = "Welcome to Android, " + edit.getText() + "!";
			Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
			toast.show();
			view.setText(msg);
		}
	}
    
}
