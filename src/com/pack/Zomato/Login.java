package com.pack.Zomato;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener
{
	EditText txtUser;
	EditText txtPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		Button saveButton=(Button) findViewById(R.id.saveButton);
		saveButton.setOnClickListener(this);
		
		txtUser=(EditText) findViewById(R.id.userName);
		txtPassword=(EditText) findViewById(R.id.userPassword);
		
		 SharedPreferences setLogin=getSharedPreferences("mypref", MODE_WORLD_WRITEABLE);
		 SharedPreferences.Editor registerEditor=setLogin.edit();
		 
		 registerEditor.putString("USER","webo");
		 registerEditor.putString("PASSWORD","webo");
		 registerEditor.commit();
		 
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		 SharedPreferences LoginData = getSharedPreferences("mypref",MODE_WORLD_READABLE);
		 String strUser = LoginData.getString("USER", "");
		 String strPassword = LoginData.getString("PASSWORD", "");
		 
		 String user=txtUser.getText().toString();
		 String pass=txtPassword.getText().toString();
		 
		 if ((user.matches(strUser))&&(pass.matches(strPassword))){
			 
				Intent i=new Intent(Login.this, SplashActivity.class);
				startActivity(i);		
		 }
		 else
		 {
			 Toast toast=Toast.makeText(getApplicationContext(),"Please enter valid data", Toast.LENGTH_SHORT);
			 toast.show();
		 }
			 

	}


}
