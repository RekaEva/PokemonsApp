package com.pokemoninfo.pokemonsapp.uiutils

import java.util.Locale

fun String.formatName() : String{
    val name = this.replaceFirstChar {
        if (it.isLowerCase()) {
            it.titlecase(Locale.getDefault())
        } else it.toString() }
    return name
}