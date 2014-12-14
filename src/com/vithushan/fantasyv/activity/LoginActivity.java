package com.vithushan.fantasyv.activity;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.inqbarna.tablefixheaders.samples.R;
import com.vithushan.fantasyv.network.NetworkUtil;

public class LoginActivity extends Activity {

	private ImageButton button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Login
		button1 = (ImageButton) this.findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AsyncTask<Void, Void, String>() {

					@Override
					protected String doInBackground(Void... params) {
						try {
							return NetworkUtil.getInstance().getRequestToken();
						} catch (OAuthMessageSignerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (OAuthNotAuthorizedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (OAuthExpectationFailedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (OAuthCommunicationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}

					@Override
					protected void onPostExecute(String result) {
						Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
						intent.setData(Uri.parse(result));
						startActivity(intent);
					};

				}.execute();
			}
		});

	}

}