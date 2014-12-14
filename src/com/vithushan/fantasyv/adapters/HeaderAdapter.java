package com.vithushan.fantasyv.adapters;

import android.content.Context;
import android.content.res.Resources;

import com.inqbarna.tablefixheaders.adapters.SampleTableAdapter;
import com.inqbarna.tablefixheaders.samples.R;

public class HeaderAdapter extends SampleTableAdapter {

	private final int width;
	private final int nameWidth;
	private final int height;
	private final String[] headers = { "", "NAME", "FG%", "FT%", "3PTM", "PTS",
			"REB", "AST", "ST", "BLK", "TO" };

	public HeaderAdapter(Context context) {
		super(context);

		Resources resources = context.getResources();

		width = resources.getDimensionPixelSize(R.dimen.table_width);
		nameWidth = resources.getDimensionPixelSize(R.dimen.table_name_width);
		height = resources.getDimensionPixelSize(R.dimen.table_height);
	}

	@Override
	public int getRowCount() {
		return 0;
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
		return headers[column + 1];
	}

	@Override
	public int getLayoutResource(int row, int column) {
		final int layoutResource;
		switch (getItemViewType(row, column)) {
		case 0:
			layoutResource = R.layout.item_table1_header;
			break;
		case 1:
			layoutResource = R.layout.item_table1;
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
		} else {
			return 1;
		}
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}
}
