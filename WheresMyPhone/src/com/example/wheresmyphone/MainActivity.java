package com.example.wheresmyphone;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.SharedPreferences.Editor;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity implements OnClickListener{

	private Button start;
	private EditText pword;
	private EditText confirm;
	
	@Override
	public void onClick(View v){
		String pass = pword.getText().toString();
		String conf = confirm.getText().toString();
		// Check if the user typed a password
		if(pass.length()==0){
			Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
		pword.requestFocus();
		return;
		}
		if(pass.equals(conf)){
			// Field match, store password shared in preferences
			Editor passwdfile = getSharedPreferences("passwd", 0).edit();
			passwdfile.putString("passwd", pass);
			passwdfile.commit();
			finish();
		}
		else{ // password mismatch - start over
			Toast.makeText(this, "Passwords must match", Toast.LENGTH_SHORT).show();
			pword.setText("");
			confirm.setText("");
			pword.requestFocus();
			return;
		}
	}
	
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		start = (Button)findViewById(R.id.button1);
		pword = (EditText)findViewById(R.id.editText1);
		confirm = (EditText)findViewById(R.id.editText2);
		
		start.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
