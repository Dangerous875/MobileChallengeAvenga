package com.barzabaldevs.mobilechallengeuala.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel

@Composable
fun SearchCountry(
    query: String,
    onQueryChange: (String) -> Unit,
    countries: List<CountryModel>,
    onFilteredCountriesChange: (List<CountryModel>) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(query) {
        val filtered = countries.filter { country ->
            country.name.replace("'", "").startsWith(query, ignoreCase = true)
        }
        onFilteredCountriesChange(filtered)
    }

    TextField(
        value = query,
        onValueChange = { newQuery ->
            onQueryChange(newQuery)
        },
        placeholder = { Text(text = "Filter...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Black
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
            },
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}