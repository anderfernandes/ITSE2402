package com.example.wheresmyphone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.*;
import android.location.*;
import android.media.MediaPlayer;

public class WhereIsMyPhoneReceiver extends BroadcastReceiver {
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
		Criteria.criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		criteria.setCostAllowed(true);
		provider = mgr.getBestProvider(criteria,  true);
		mgr.requestLocationUpdates(provider,  0,  0,  this);
		Location lastlocation = mgr.getLastKnownLocation(provider);
		if(lastLocation!=null)
			loc = "Findme Latitude: " + lastLocation.getLatitude() + "Longitude: " + lastLocation.getLongitude();
	}
}
