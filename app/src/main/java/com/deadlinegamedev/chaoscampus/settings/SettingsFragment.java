/*
 * Copyright 2017-2018 Nikita Shakarun
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

package com.deadlinegamedev.chaoscampus.settings;

import android.content.Intent;
import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;
import com.deadlinegamedev.chaoscampus.R;
import com.deadlinegamedev.chaoscampus.config.ConfigActivity;

public class SettingsFragment extends PreferenceFragmentCompat {
	@Override
	public void onCreatePreferences(Bundle bundle, String s) {
		addPreferencesFromResource(R.xml.preferences);
		findPreference("pref_default_settings").setOnPreferenceClickListener(preference -> {
			Intent intent = new Intent(getActivity(), ConfigActivity.class);
			intent.putExtra(ConfigActivity.DEFAULT_CONFIG_KEY, true);
			startActivity(intent);
			return true;
		});
	}
}
