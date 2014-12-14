package com.vithushan.fantasyv.activity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import org.apache.http.client.ClientProtocolException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.inqbarna.tablefixheaders.samples.R;
import com.vithushan.fantasyv.adapters.HeaderAdapter;
import com.vithushan.fantasyv.adapters.MyAdapter;
import com.vithushan.fantasyv.network.NetworkUtil;

public class TableActivity extends Activity {

	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table);

		final TableFixHeaders mainTable = (TableFixHeaders) findViewById(R.id.table);
		final TableFixHeaders headerTable = (TableFixHeaders) findViewById(R.id.headerTable);
		mainTable.setSecondaryTable(headerTable);

		mProgressDialog = ProgressDialog.show(this, "Loading...",
				"Loading stats", true, false);

		new AsyncTask<Void, Void, String>() {

			@Override
			protected void onPreExecute() {

			};

			@Override
			protected String doInBackground(Void... params) {
				// TODO Auto-generated method stub
				Intent intent = getIntent();
				String verifierUrl = intent.getStringExtra("url");
				String jsonString = null;
				try {
					jsonString = NetworkUtil.getInstance().getScoreboard(
							verifierUrl);
				} catch (OAuthMessageSignerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OAuthExpectationFailedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OAuthCommunicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OAuthNotAuthorizedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				writeStringAsFile(jsonString, "output2.txt");
				return jsonString;
			}

			@SuppressLint("NewApi")
			public void writeStringAsFile(final String fileContents,
					String fileName) {
				try {
					File file = new File(
							Environment
									.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
							fileName);
					Log.i("TAG", file.getAbsolutePath());
					FileWriter out = new FileWriter(file);
					out.write(fileContents);
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			protected void onPostExecute(String result) {
				mProgressDialog.dismiss();
				mainTable.setAdapter(new MyAdapter(getApplicationContext(),
						result));
				headerTable.setAdapter(new HeaderAdapter(
						getApplicationContext()));
			};
		}.execute();

	}

}
