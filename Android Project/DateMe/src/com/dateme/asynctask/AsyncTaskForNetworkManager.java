package com.dateme.asynctask;

import java.util.List;

import org.apache.http.NameValuePair;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.dateme.callback.ParseListener;
import com.dateme.config.AppConstants;
import com.dateme.fragment.network.NetworkManager;

public class AsyncTaskForNetworkManager extends
		AsyncTask<String, String, Object> {
	private Context context;
	private NetworkManager networkManager = NetworkManager.getInstance();
	private List<NameValuePair> nameValuePair;
	private String url;
	int flag = 0;
	Object object;
	ParseListener parseListener;

	public AsyncTaskForNetworkManager(Context context,
			List<NameValuePair> nameValuePair, String url, int flag,
			ParseListener parseListener) {
		this.context = context;
		this.nameValuePair = nameValuePair;
		this.url = url;
		this.flag = flag;
		this.parseListener = parseListener;
	}

	public AsyncTaskForNetworkManager(Context context, String url, int flag,
			ParseListener parseListener) {
		this.context = context;
		this.url = url;
		this.flag = flag;
		this.parseListener = parseListener;
	}

	@Override
	protected Object doInBackground(String... params) {

		Object object = new Object();
		String jsonString = "";
		try {
			jsonString = networkManager.sendPostRequest(context,
					nameValuePair, url);
			object = parseListener.onParse(jsonString);

		} catch (Exception e) {
			Log.e(AppConstants.EXCEPTION, e.toString(), e);
		}
		return object;
	}

}