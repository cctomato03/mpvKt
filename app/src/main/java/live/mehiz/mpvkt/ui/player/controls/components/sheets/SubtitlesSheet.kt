package live.mehiz.mpvkt.ui.player.controls.components.sheets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import live.mehiz.mpvkt.R
import live.mehiz.mpvkt.ui.player.Track
import me.zhanghai.compose.preference.FooterPreference
import me.zhanghai.compose.preference.ProvidePreferenceLocals

@Composable
fun SubtitlesSheet(
  tracks: ImmutableList<Track>,
  selectedTracks: ImmutableList<Int>,
  onSelect: (Int) -> Unit,
  onAddSubtitle: () -> Unit,
  onDismissRequest: () -> Unit,
  modifier: Modifier = Modifier
) {
  GenericTracksSheet(
    tracks,
    onDismissRequest = onDismissRequest,
    header = { AddTrackRow(stringResource(R.string.player_sheets_add_ext_sub), onAddSubtitle) },
    track = { track ->
      SubtitleTrackRow(
        title = getTrackTitle(track),
        selected = selectedTracks.indexOf(track.id),
        onClick = { onSelect(track.id) },
      )
    },
    footer = {
      ProvidePreferenceLocals {
        FooterPreference(
          summary = {
            Text(stringResource(R.string.player_sheets_subtitles_footer_secondary_sid_no_styles))
          },
        )
      }
    },
    modifier = modifier,
  )
}

@Composable
fun SubtitleTrackRow(
  title: String,
  selected: Int, // -1 unselected, otherwise return 0 and 1 for the selected indices
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .clickable(onClick = onClick)
      .padding(start = 8.dp, end = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Checkbox(
      selected > -1,
      onCheckedChange = { _ -> onClick() },
    )
    Text(
      title,
      fontStyle = if (selected > -1) FontStyle.Italic else FontStyle.Normal,
      fontWeight = if (selected > -1) FontWeight.ExtraBold else FontWeight.Normal,
    )
    Spacer(modifier = Modifier.weight(1f))
    if (selected != -1) {
      Text(
        "#${selected + 1}",
        fontStyle = if (selected > -1) FontStyle.Italic else FontStyle.Normal,
        fontWeight = if (selected > -1) FontWeight.ExtraBold else FontWeight.Normal,
      )
    }
  }
}

/*
@Preview
@Composable
fun PreviewSubtitlesSheet() {
  SubtitlesSheet(Tracks(mutableListOf(1), mutableListOf(Track(1, "hello", "en"))), {}) {}
}
*/
