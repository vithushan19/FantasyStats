package com.vithushan.fantasyv.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import oauth.signpost.signature.AuthorizationHeaderSigningStrategy;
import oauth.signpost.signature.HmacSha1MessageSigner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.net.Uri;
import android.util.Log;

public class NetworkUtil {

	private CommonsHttpOAuthConsumer mainConsumer;
	private CommonsHttpOAuthProvider mainProvider;
	private String oAuthVerifier;

	private static NetworkUtil mInstance;
	private static final String REQUEST_TOKEN_ENDPOINT_URL = "https://api.login.yahoo.com/oauth/v2/get_request_token";
	private static final String AUTHORIZE_WEBSITE_URL = "https://api.login.yahoo.com/oauth/v2/request_auth";
	private static final String ACCESS_TOKEN_ENDPOINT_URL = "https://api.login.yahoo.com/oauth/v2/get_token";

	private static final String YAHOO_CALLBACK_URL = "http://localhost";
	private static final String YAHOO_CONSUMER_KEY = "dj0yJmk9bTRFajFpSUN0bjdoJmQ9WVdrOWFsZGtUWEkzTldNbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD02Mg--";
	private static final String YAHOO_CONSUMER_SECRET = "075b41b4141886de3e99c4bf93565aa907e159fb";

	private static final String TAG = "NetworkUtil";

	private NetworkUtil() {

		// Configure the consumer and provider according to Yahoo documentation
		mainConsumer = new CommonsHttpOAuthConsumer(YAHOO_CONSUMER_KEY,
				YAHOO_CONSUMER_SECRET);
		mainConsumer.setMessageSigner(new HmacSha1MessageSigner());
		mainConsumer
				.setSigningStrategy(new AuthorizationHeaderSigningStrategy());
		mainProvider = new CommonsHttpOAuthProvider(REQUEST_TOKEN_ENDPOINT_URL,
				ACCESS_TOKEN_ENDPOINT_URL, AUTHORIZE_WEBSITE_URL);
		mainProvider.setOAuth10a(false);
	}

	public static NetworkUtil getInstance() {
		if (mInstance == null) {
			mInstance = new NetworkUtil();
		}
		return mInstance;
	}

	private void getAccessToken(String verifierUrl) throws OAuthMessageSignerException,
			OAuthNotAuthorizedException, OAuthExpectationFailedException,
			OAuthCommunicationException {
		Uri uriData = Uri.parse(verifierUrl);
		setVerifier(uriData.getQueryParameter("oauth_verifier"));
		mainProvider.retrieveAccessToken(mainConsumer, oAuthVerifier);

	}

	public void setVerifier(String verifier) {
		this.oAuthVerifier = verifier;
	}

	public void showToken() {
		// Log.d("SubPlurkV2", "Token = " + mainConsumer.getToken() +
		// " and secret = " + mainConsumer.getTokenSecret());
		String str = "verifier = "
				+ this.oAuthVerifier
				+ "<br>"
				+ "Token = "
				+ mainConsumer.getToken()
				+ "<br>"
				+ "secret = "
				+ mainConsumer.getTokenSecret()
				+ "<br>"
				+ "oauth_expires_in = "
				+ mainProvider.getResponseParameters().getFirst(
						"oauth_expires_in")
				+ "<br>"
				+ "oauth_session_handle = "
				+ mainProvider.getResponseParameters().getFirst(
						"oauth_session_handle")
				+ "<br>"
				+ "oauth_authorization_expires_in = "
				+ mainProvider.getResponseParameters().getFirst(
						"oauth_authorization_expires_in")
				+ "<br>"
				+ "xoauth_yahoo_guid = "
				+ mainProvider.getResponseParameters().getFirst(
						"xoauth_yahoo_guid") + "<br>";
		Log.i("YahooScreen", "str : " + str);
	}

	public String getScoreboard(String verifierUrl) throws OAuthMessageSignerException,
			OAuthExpectationFailedException, OAuthCommunicationException,
			ClientProtocolException, IOException, OAuthNotAuthorizedException {

		getAccessToken(verifierUrl);

		// String url =
		String url = "http://fantasysports.yahooapis.com/fantasy/v2/league/342.l.42669/scoreboard?format=json";
		OAuthConsumer consumer = mainConsumer;

		final HttpGet request = new HttpGet(url);
		Log.i("doGet", "Requesting URL : " + url);
		consumer.sign(request);

		Log.i("YahooScreen", "request url : " + request.getURI());
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;

		response = httpclient.execute((HttpUriRequest) request);
		Log.i("doGet", "Statusline : " + response.getStatusLine());
		InputStream data = response.getEntity().getContent();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(data));
		String responeLine;
		StringBuilder responseBuilder = new StringBuilder();
		while ((responeLine = bufferedReader.readLine()) != null) {
			responseBuilder.append(responeLine);
		}
		Log.i("doGet", "Response : " + responseBuilder.toString());
		return responseBuilder.toString();

	}

	public String getRequestToken() throws OAuthMessageSignerException,
			OAuthNotAuthorizedException, OAuthExpectationFailedException,
			OAuthCommunicationException {
		// TODO Auto-generated method stub
		Log.i(TAG, "Retrieving request token from Google servers");
		final String url = mainProvider.retrieveRequestToken(mainConsumer,
				YAHOO_CALLBACK_URL);
		Log.i(TAG, "Popping a browser with the authorize URL : " + url);

		return url;
	}
	

}
