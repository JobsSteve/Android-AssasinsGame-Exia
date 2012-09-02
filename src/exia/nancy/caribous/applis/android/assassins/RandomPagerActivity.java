package exia.nancy.caribous.applis.android.assassins;

import metier.all_purpose.PreferenceKeys;
import android.content.Context;
import android.content.Intent;
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
import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Partie;
import exia.nancy.caribous.applis.android.assassins.metier.server_interacts.PartiesHelper;

public class RandomPagerActivity extends FragmentActivity {

	boolean loaded;

	Partie[] parties;

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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_random_pager);

		loaded = false;

		// Create the adapter that will return a fragment for each of the three
		// primary sections
		// of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public View onCreateView(View parent, String name, Context context,
			AttributeSet attrs) {
		View vivi = super.onCreateView(parent, name, context, attrs);

		new AsyncTask<String, String, Partie[]>() {

			@Override
			protected Partie[] doInBackground(String... params) {
				// Récupèrer parties en cours
				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(getApplicationContext());
				return new PartiesHelper().getMesParties(prefs.getInt(
						PreferenceKeys.USER_ID, 0));
			}

			@Override
			protected void onPostExecute(Partie[] result) {
				parties = result;
				loaded = true;

				mSectionsPagerAdapter.loadedFragments = new Fragment[result.length];
				mSectionsPagerAdapter.notifyDataSetChanged();

				super.onPostExecute(result);
			}

		}.execute("");

		return vivi;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_random_pager, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the primary sections of the app.
	 */
	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

		public Fragment[] loadedFragments;

		public Fragment waitFrag;

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
			loadedFragments = new Fragment[1];
		}

		@Override
		public Fragment getItem(int i) {
			if (i < loadedFragments.length && loadedFragments[i] != null)
				return loadedFragments[i];
			Fragment fragment;
			if (loaded) {
				// Ici, on appelle le Fragment
				fragment = new PartieFragment();

				Bundle args = new Bundle();

				args.putParcelable("Partie", parties[i]);

				fragment.setArguments(args);

				loadedFragments[i] = fragment;
			} else {
				fragment = new WaitFragment();

				waitFrag = fragment;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return loaded ? parties.length : 1;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			if (loaded) {
				return parties[position].get_title().toUpperCase();
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
			} else if (object instanceof PartieFragment) {
				return super.getItemPosition(object);
			} else {
				return POSITION_NONE;
			}
		}

	}

	public void buttonCurrContractPress(View view) {
		// On appelle le détail du contrat
		Intent intent = new Intent(view.getContext(),
				ShowContractActivity.class);

		// TODO PartieFragment fragPartie = (PartieFragment)
		// mSectionsPagerAdapter
		// .getItem(mViewPager.getCurrentItem());

		startActivity(intent);
	}
}
