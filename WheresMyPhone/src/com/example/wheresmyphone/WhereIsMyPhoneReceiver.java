package com.example.wheresmyphone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.*;
import android.widget.Toast;
import android.location.*;
import android.media.MediaPlayer;


public class WhereIsMyPhoneReceiver extends BroadcastReceiver implements LocationListener {
	String loc = "Location unkown";
	LocationManager mgr;
	String provider;
	public WhereIsMyPhoneReceiver() {
		
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		SharedPreferences passwdfile = context.getSharedPreferences("passwd", 0);
		String password = passwdfile.getString("passwd", null);		
		mgr = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		criteria.setCostAllowed(true);
		provider = mgr.getBestProvider(criteria,  true);
		mgr.requestLocationUpdates(provider,  0,  0,  this);
		Location lastLocation = mgr.getLastKnownLocation(provider);
		if(lastLocation!=null)
			loc = "Findme Latitude: " + lastLocation.getLatitude() + "Longitude: " + lastLocation.getLongitude();
		
		// Get the messages - PDU = protocol description unit
		Bundle bundle = intent.getExtras();
		Object[] pdusObj = (Object[]) bundle.get("pdus");
		SmsMessage[] messages = new SmsMessage[pdusObj.length];
		for (int i = 0; i<pdusObj.length; i++){
			messages[i] = SmsMessage.createFromPdu((byte[])pdusObj[i]);
		}
		for (SmsMessage msg : messages) {
			// Make sure it's a findme message
			if (msg.getMessageBody().contains("findme: " + password)){
				String to = msg.getOriginatingAddress();
				SmsManager sm = SmsManager.getDefault();
				sm.sendTextMessage(to,  null,  loc,  null,  null);
				Toast.makeText(context,  "Location sent to: " + to + " using provider: " + provider, Toast.LENGTH_LONG).show();
				MediaPlayer mp = MediaPlayer.create(context,  R.raw.keys1);
				mp.start();
			}
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		// Get location and store in loc
		if(location == null){
			location = mgr.getLastKnownLocation(provider);
		}
		loc = "Findme Latitude:" + location.getLatitude() + "\nLongitude: " 
				+ location.getLongitude();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
