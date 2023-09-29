package com.wagnod.core_ui.ui.search_bar

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wagnod.core_ui.theme.SampleTheme
import com.wagnod.core_ui.theme.mainBackgroundColor
import com.wagnod.core_ui.theme.textColorSecondary
import com.wagnod.core_ui.theme.textPrimary

@Composable
fun ExpandableSearchView(
    searchDisplay: String,
    text: String,
    onSearchStateChanged: (String) -> Unit,
    onSearchClosed: () -> Unit,
    onSearchExpand: () -> Unit,
    modifier: Modifier = Modifier,
    expandedInitially: Boolean = false,
    showElevation: Boolean = false,
) {
    val (expanded, onExpandedChanged) = remember {
        mutableStateOf(expandedInitially)
    }

    Surface(
        modifier = modifier,
        elevation = if (showElevation) 4.dp else 0.dp
    ) {
        Crossfade(targetState = expanded) { isSearchFieldVisible ->
            when (isSearchFieldVisible) {
                true -> ExpandedSearchView(
                    searchDisplay = searchDisplay,
                    onSearchDisplayChanged = onSearchStateChanged,
                    onSearchDisplayClosed = onSearchClosed,
                    onExpandedChanged = onExpandedChanged,
                    text = text,
                    modifier = modifier,
                )

                false -> CollapsedSearchView(
                    onExpandedChanged = onExpandedChanged,
                    onSearchExpand = onSearchExpand,
                    text = text,
                    modifier = modifier,
                )
            }
        }
    }
}

@Composable
fun SearchIcon() {
    Icon(
        painter = rememberVectorPainter(Icons.Default.Search),
        contentDescription = "search icon",
        tint = MaterialTheme.colors.textPrimary
    )
}

@Composable
fun CollapsedSearchView(
    onExpandedChanged: (Boolean) -> Unit,
    onSearchExpand: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colors.textPrimary
        )
        IconButton(onClick = {
            onSearchExpand.invoke()
            onExpandedChanged(true)
        }) {
            SearchIcon()
        }
    }
}

@Composable
fun ExpandedSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    onExpandedChanged: (Boolean) -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    val textFieldFocusRequester = remember { FocusRequester() }

    SideEffect {
        textFieldFocusRequester.requestFocus()
    }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(searchDisplay, TextRange(searchDisplay.length)))
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            onExpandedChanged(false)
            onSearchDisplayClosed()
        }) {
            Icon(
                painter = rememberVectorPainter(Icons.Default.ArrowBack),
                contentDescription = "back icon",
                tint = MaterialTheme.colors.textPrimary
            )
        }
        TextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onSearchDisplayChanged(it.text)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .padding(end = 8.dp)
                .clip(RoundedCornerShape(10.dp))
                .focusRequester(textFieldFocusRequester),
            label = {
                Text(text = "Search in $text", color = MaterialTheme.colors.textColorSecondary)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
    }
}

@Preview
@Composable
fun CollapsedSearchViewPreview() {
    SampleTheme {
        Surface(
            color = MaterialTheme.colors.mainBackgroundColor
        ) {
            ExpandableSearchView(
                searchDisplay = "",
                onSearchStateChanged = {},
                onSearchClosed = {},
                onSearchExpand = {},
                text = "Shop"
            )
        }
    }
}

@Preview
@Composable
fun ExpandedSearchViewPreview() = SampleTheme {
    ExpandableSearchView(
        searchDisplay = "",
        onSearchStateChanged = {},
        expandedInitially = true,
        onSearchClosed = {},
        onSearchExpand = {},
        text = "Shop"
    )
}