package kv.compose.newsapp.presentation.components

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kv.compose.newsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    bottomSheetState: SheetState,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
    containerColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit
) {
    ModalBottomSheet(
        modifier = modifier.testTag(stringResource(R.string.bottom_sheet)),
        containerColor = containerColor,
        onDismissRequest = onDismissRequest,
        sheetState = bottomSheetState,
        dragHandle = null,
        tonalElevation = 0.dp,
        scrimColor = MaterialTheme.colorScheme.onSurface.copy(.2f),
        shape = shape
    ) {
        content()
    }
}