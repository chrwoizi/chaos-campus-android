/*
 * Copyright 2018 Nikita Shakarun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.deadlinegamedev.chaoscampus.filepicker;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nononsenseapps.filepicker.FilePickerFragment;
import com.nononsenseapps.filepicker.LogicHandler;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.deadlinegamedev.chaoscampus.R;

public class FilteredFilePickerFragment extends FilePickerFragment {

	private static String lastPath = Environment.getExternalStorageDirectory().getPath();
	private static final List<String> extList = Arrays.asList(".jad", ".jar");

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v;
		switch (viewType) {
			case LogicHandler.VIEWTYPE_HEADER:
				v = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_dir,
						parent, false);
				return new HeaderViewHolder(v);
			case LogicHandler.VIEWTYPE_CHECKABLE:
				v = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_checkable,
						parent, false);
				return new CheckableViewHolder(v);
			case LogicHandler.VIEWTYPE_DIR:
			default:
				v = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_dir,
						parent, false);
				return new DirViewHolder(v);
		}
	}

	private String getExtension(@NonNull File file) {
		String path = file.getPath();
		int i = path.lastIndexOf(".");
		if (i < 0) {
			return null;
		} else {
			return path.substring(i);
		}
	}

	@Override
	protected boolean isItemVisible(final File file) {
		if (!isDir(file) && (mode == MODE_FILE || mode == MODE_FILE_AND_DIR)) {
			String ext = getExtension(file);
			return ext != null && extList.contains(ext.toLowerCase());
		}
		return isDir(file);
	}

	@Override
	public void goToDir(@NonNull File file) {
		lastPath = file.getPath();
		super.goToDir(file);
	}

	public static String getLastPath() {
		return lastPath;
	}

	public File getBackTop() {
		return getPath(getArguments().getString(KEY_START_PATH, "/"));
	}

	public boolean isBackTop() {
		return 0 == compareFiles(mCurrentPath, getBackTop()) ||
				0 == compareFiles(mCurrentPath, new File("/"));
	}
}