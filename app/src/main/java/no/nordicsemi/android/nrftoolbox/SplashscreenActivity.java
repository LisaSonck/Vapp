/*
 * Copyright (c) 2015, Nordic Semiconductor
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package no.nordicsemi.android.nrftoolbox;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class SplashscreenActivity extends AppCompatActivity implements FragmentChangeListener, NavigationView.OnNavigationItemSelectedListener {
	private DrawerLayout drawer;


	@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//zet aangemaakte toolbar als actionbar
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		drawer = findViewById(R.id.drawer_layout);

		//reference nr navigatieview --> kunnen gebruiken
		NavigationView navigationView = findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		//icon en drawer fixen dus de drie streepjes om naviagtie te openen
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
				R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		//wire up the button
		//get the button, what happens when clicked
		//starten met activity --> home openen
		if (savedInstanceState == null) { //enkel dit fragment tonen als opnieuw opgestart, niet als even gekilled en dan weer opstarten door device zelf
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
					new HomeFragment()).commit();
			navigationView.setCheckedItem(R.id.nav_home);
		}


	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			//openen van juiste fragment
			case R.id.nav_home:
				getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
						new HomeFragment()).commit();
				break;
			case R.id.nav_settings:
				getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
						new SettingsFragment()).commit();
				break;
			case R.id.nav_numbers:
				getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
						new NumbersFragment()).commit();
				break;
			case R.id.nav_graph:
				getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
						new GraphFragment()).commit();
				break;
			case R.id.nav_bluetooth:
				Intent i = new Intent(this, FeaturesActivity.class);
				startActivity(i);
				break;
		}

		drawer.closeDrawer(GravityCompat.START);
		return true; //item wordt selected na klikken

	}

	@Override
	//willen niet direct terug als navigatievenster open -> enkel terug nr gewone fragment; navigatievenster sluiten
	public void onBackPressed() {
		if (drawer.isDrawerOpen(GravityCompat.START)) { //als drawer langs rechterkan openen (=END) dan moet hier ook .END gebruikt worden, ipv .START
			drawer.closeDrawer(GravityCompat.START);
		} else {
			//anders wel algemene uitvoering functie
			super.onBackPressed();
		}
	}

	@Override
	public void replaceFragment(Fragment fragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_container, fragment);
		fragmentTransaction.commit();
	}
}