package com.vithushan.fantasyv.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.inqbarna.tablefixheaders.samples.R;

public class WebViewActivity extends Activity {

	protected static final int GET_VERIFIER_REQUEST = 1;
	private WebView webView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);

		webView1 = (WebView) findViewById(R.id.webview);
		webView1.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub

				if (url.startsWith("localhost")
						|| url.startsWith("http://localhost")) {
					Intent verifierIntent = new Intent(getApplicationContext(), TableActivity.class);
					verifierIntent.putExtra("url", url);
					startActivity(verifierIntent);
					return true;
				} else {

					return false;
				}

			}
		});

		Intent intent = getIntent();
		if (intent != null) {
			webView1.loadUrl(intent.getData().toString());
		}

	}

}