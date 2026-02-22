package org.lyrebird.spaceexplorer.ui.component.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import org.jetbrains.compose.resources.stringResource
import org.lyrebird.spaceexplorer.Black
import org.lyrebird.spaceexplorer.DarkGray
import org.lyrebird.spaceexplorer.FailedRed
import org.lyrebird.spaceexplorer.Gray
import org.lyrebird.spaceexplorer.LightGray
import org.lyrebird.spaceexplorer.SuccessGreen
import org.lyrebird.spaceexplorer.SurfaceLight
import org.lyrebird.spaceexplorer.White
import org.lyrebird.spaceexplorer.domain.model.uiModel.LaunchUIModel
import org.lyrebird.spaceexplorer.ui.component.common.StatusBadge
import org.lyrebird.spaceexplorer.ui.component.common.getShimmerBrush
import spaceexplorer.composeapp.generated.resources.Res
import spaceexplorer.composeapp.generated.resources.rocket


@Composable
fun LaunchCard(launch: LaunchUIModel, onClick: () -> Unit) {
    val statusColor = when (launch.isSuccess) {
        true -> SuccessGreen
        false -> FailedRed
        else -> Gray
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .border(
                width = 2.dp,
                color = statusColor.copy(alpha = 0.5f),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = statusColor.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {

                SubcomposeAsyncImage(
                    model = launch.patchUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    loading = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(brush = getShimmerBrush(), shape = CircleShape)
                        )
                    },
                    error = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.RocketLaunch,
                                contentDescription = null,
                                tint = LightGray,
                                modifier = Modifier.size(size = 32.dp)
                            )
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = launch.missionName,
                    style = MaterialTheme.typography.titleMedium,
                    color = Black
                )

                Text(
                    text = "${stringResource(Res.string.rocket)}: ${launch.rocketName}",
                    style = MaterialTheme.typography.bodySmall,
                    color = DarkGray
                )

                Text(
                    text = launch.dateText,
                    style = MaterialTheme.typography.labelSmall,
                    color = DarkGray
                )
            }

            StatusBadge(launch.isSuccess)
        }
    }
}

@Composable
fun LaunchListSkeleton() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceLight)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) {
                LaunchItemSkeleton(getShimmerBrush())
            }
        }
    }
}

@Composable
fun LaunchItemSkeleton(brush: Brush) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        border = BorderStroke(1.dp, LightGray.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(65.dp)
                    .background(brush, CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {

                Spacer(modifier = Modifier.width(120.dp).height(20.dp).background(brush, RoundedCornerShape(4.dp)))
                Spacer(modifier = Modifier.height(8.dp))

                Spacer(modifier = Modifier.width(80.dp).height(14.dp).background(brush, RoundedCornerShape(4.dp)))
                Spacer(modifier = Modifier.height(6.dp))

                Spacer(modifier = Modifier.width(60.dp).height(12.dp).background(brush, RoundedCornerShape(4.dp)))
            }

            Spacer(
                modifier = Modifier
                    .width(75.dp)
                    .height(28.dp)
                    .background(brush, RoundedCornerShape(12.dp))
            )
        }
    }
}

