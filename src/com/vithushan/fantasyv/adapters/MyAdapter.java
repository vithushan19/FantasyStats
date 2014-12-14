package com.vithushan.fantasyv.adapters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.inqbarna.tablefixheaders.adapters.SampleTableAdapter;
import com.inqbarna.tablefixheaders.samples.R;
import com.vithushan.fantasyv.model.Fantasy;
import com.vithushan.fantasyv.model.Player;

public class MyAdapter extends SampleTableAdapter {

	private final int width;
	private final int nameWidth;
	private final int height;
	private ArrayList<Player> mPlayers;
	private Player mVithushan;

	public MyAdapter(Context context, String jsonString) {
		super(context);

		Fantasy f = new Fantasy();
		try {
			mPlayers = f.initializePlayers(jsonString);
			mVithushan = getVithushan();
			mPlayers.remove(mVithushan);
		} catch (JSONException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
		}
		Resources resources = context.getResources();

		width = resources.getDimensionPixelSize(R.dimen.table_width);
		nameWidth = resources.getDimensionPixelSize(R.dimen.table_name_width);
		height = resources.getDimensionPixelSize(R.dimen.table_height);
	}

	private Player getVithushan() {
		for (int i = 0; i < mPlayers.size(); i++) {
			if (mPlayers.get(i).getName().equals("Black and Melo")) {
				return mPlayers.get(i);
			}
		}
		return mPlayers.get(0);
	}

	@Override
	public int getRowCount() {
		return 23;
	}

	@Override
	public int getColumnCount() {
		return 10;
	}

	@Override
	public int getWidth(int column) {
		if (column == -1)
			return 0;
		if (column == 0)
			return nameWidth;
		return width;
	}

	@Override
	public int getHeight(int row) {
		return height;
	}

	@Override
	public String getCellString(int row, int column) {
		Player p = null;

		if (row >= 13) {
			return "";
		}
		if (row == -1) {
			p = mVithushan;
		} else {
			p = mPlayers.get(row);
		}
		if (column == -1) {
			return "";
		}
		if (column == 0) {
			return p.getName();
		}
		return p.getStats().get(column - 1).getStat().getValue();
	}

	@Override
	public int getLayoutResource(int row, int column) {
		final int layoutResource;
		switch (getItemViewType(row, column)) {
		case 0:
			layoutResource = R.layout.item_table1_header;
			break;
		case 1:
			layoutResource = R.layout.item_table1_lose;
			break;
		case 2:
			layoutResource = R.layout.item_table1_win;
			break;
		default:
			throw new RuntimeException("wtf?");
		}
		return layoutResource;
	}

	@Override
	public int getItemViewType(int row, int column) {
		if (row < 0) {
			return 0;
		} else if (column <= 0) {
			return 0;
		}

		try {
			Double myStat = Double.valueOf(getCellString(-1, column));
			Double hisStat = Double.valueOf(getCellString(row, column));
			if (column != 9) {
				if (myStat > hisStat) {
					return 2;
				} else {
					return 1;
				}
			} else {
				if (myStat < hisStat) {
					return 2;
				} else {
					return 1;
				}
			}
		} catch (NumberFormatException e) {
			return 0;
		}

	}

	@Override
	public int getViewTypeCount() {
		return 3;
	}

	private String readFromFile(Context context, String filename) {

		String ret = "";

		try {
			InputStream inputStream = context.getAssets().open(filename);

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null) {
					stringBuilder.append(receiveString);
				}

				inputStream.close();
				ret = stringBuilder.toString();
			}
		} catch (FileNotFoundException e) {
			Log.e("login activity", "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("login activity", "Can not read file: " + e.toString());
		}

		return ret;
	}
}
