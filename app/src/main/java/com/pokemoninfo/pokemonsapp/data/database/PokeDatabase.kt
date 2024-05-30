package com.pokemoninfo.pokemonsapp.data.database

import android.content.Context
import android.util.Log
import com.pokemoninfo.pokemonsapp.R
import javax.inject.Inject

class PokeDatabase @Inject constructor(
    private val context: Context
) {

    fun method() {
        Log.d(LOG_TAG, "ExampleDatabase ${context.getString(R.string.app_name)}")
    }

    companion object {

        private const val LOG_TAG = "EXAMPLE_TEST"
    }
}