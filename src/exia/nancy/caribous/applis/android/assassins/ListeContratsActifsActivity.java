package exia.nancy.caribous.applis.android.assassins;

import metier.all_purpose.PreferenceKeys;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Contract;
import exia.nancy.caribous.applis.android.assassins.metier.server_interacts.ContratHelper;

public class ListeContratsActifsActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	Contract[] targets;

	boolean loaded;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste_contrats_actifs);
		// Create the adapter that will return a fragment for each of the three
		// primary sections
		// of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		loaded = false;

	}

	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub

		View vivi = super.onCreateView(name, context, attrs);

		new AsyncTask<String, String, Contract[]>() {

			@Override
			protected Contract[] doInBackground(String... params) {
				// Récupèrer parties en cours
				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(getApplicationContext());
				return new ContratHelper().getContratsEnCoursForPlayer(prefs
						.getInt(PreferenceKeys.USER_ID, 0));
			}

			@Override
			protected void onPostExecute(Contract[] result) {
				targets = result;
				loaded = true;

				mSectionsPagerAdapter.notifyDataSetChanged();

				super.onPostExecute(result);
			}

		}.execute("");

		return vivi;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_liste_contrats_actifs, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the primary sections of the app.
	 */
	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

		int nbContracts;

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			Fragment fragment;

			if (loaded) {
				fragment = new ShowContractInfo();

				((ShowContractInfo) fragment).setTarget(targets[i]);

			} else {
				fragment = new WaitFragment();
			}

			return fragment;
		}

		@Override
		public int getCount() {
			return loaded ? nbContracts : 0;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			if (loaded) {
				return "PARTIE " + position;
			} else {
				return "LOADING";
			}
		}

		@Override
		public int getItemPosition(Object object) {
			if (object instanceof WaitFragment) {
				if (loaded) {
					return POSITION_NONE;
				} else {
					return 0;
				}
			} else if (object instanceof ShowContractInfo) {
				return super.getItemPosition(object);
			} else {
				return POSITION_NONE;
			}
		}
	}
}
