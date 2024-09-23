package id.hae.cache

import androidx.datastore.preferences.core.stringPreferencesKey

object AppDataStoreKeys {
    const val APP_DATA_STORE_NAME = "atlassian-design-cache"
    val KEY_TOKEN = stringPreferencesKey("theme")
}