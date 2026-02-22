package org.lyrebird.spaceexplorer.ui.component.launchDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.lyrebird.spaceexplorer.Black
import org.lyrebird.spaceexplorer.Blue
import org.lyrebird.spaceexplorer.DarkGray
import org.lyrebird.spaceexplorer.Gray
import org.lyrebird.spaceexplorer.IceBlue
import org.lyrebird.spaceexplorer.LightGray
import org.lyrebird.spaceexplorer.Red
import org.lyrebird.spaceexplorer.RocketBadge
import org.lyrebird.spaceexplorer.SurfaceLight
import org.lyrebird.spaceexplorer.White
import org.lyrebird.spaceexplorer.core.util.openBrowser
import org.lyrebird.spaceexplorer.domain.model.uiModel.LaunchUIModel
import org.lyrebird.spaceexplorer.ui.component.common.StatusBadge
import org.lyrebird.spaceexplorer.ui.component.common.getShimmerBrush
import spaceexplorer.composeapp.generated.resources.Res
import spaceexplorer.composeapp.generated.resources.height
import spaceexplorer.composeapp.generated.resources.mass
import spaceexplorer.composeapp.generated.resources.read_article
import spaceexplorer.composeapp.generated.resources.rocket
import spaceexplorer.composeapp.generated.resources.see_in_wikipedia_for
import spaceexplorer.composeapp.generated.resources.watch_it_on
import spaceexplorer.composeapp.generated.resources.wikipedia_logo
import spaceexplorer.composeapp.generated.resources.youtube


@Composable
fun LaunchOverview(title: String, launchDetails: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(20.dp)
                .background(RocketBadge, RoundedCornerShape(2.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            ),
            color = Black
        )
    }

    Text(
        text = launchDetails,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        style = MaterialTheme.typography.bodyMedium,
        color = DarkGray
    )
}

@Composable
fun LaunchHeaderCard(launch: LaunchUIModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = launch.missionName,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.5).sp
            ),
            color = Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = null,
                    tint = Gray,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = launch.dateTimeText,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            StatusBadge(success = launch.isSuccess)
        }
    }
}
@Composable
fun HeroSection(model: LaunchUIModel) {

    val images = model.heroImageUrl ?: emptyList()

    val hasImages = images.isNotEmpty()
    val hasPatch = model.patchUrl != null
    if (!hasImages && !hasPatch) return

    val pagerState = rememberPagerState(pageCount = { images.size })
    val coroutineScope = rememberCoroutineScope()


    Box(modifier = Modifier
        .fillMaxWidth()
        .then(if (hasImages) Modifier.height(400.dp) else Modifier.wrapContentHeight())
    ) {
        if (images.isNotEmpty()){

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                SubcomposeAsyncImage(
                    model = images[page],
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    loading = {
                        Box(modifier = Modifier.fillMaxSize().background(getShimmerBrush()))
                    },
                    success = { state ->
                        Image(
                            painter = state.painter,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    },
                    error = {
                        Box(modifier = Modifier.fillMaxSize().background(LightGray))
                    }
                )
            }

            if (images.size > 1) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                    enabled = pagerState.currentPage > 0,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(8.dp)
                        .background(Black.copy(0.3f), CircleShape)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBackIos, null, tint = White)
                }

                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    enabled = pagerState.currentPage < images.size - 1,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(8.dp)
                        .background(Black.copy(0.3f), CircleShape)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, null, tint = White)
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    repeat(images.size) { iteration ->
                        val color = if (pagerState.currentPage == iteration) White else White.copy(0.5f)
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(color, CircleShape)
                        )
                    }
                }
            }
        }
        model.patchUrl?.let {
            Surface(
                shape = CircleShape,
                color = RocketBadge.copy(alpha = 0.8f),
                border = BorderStroke(2.dp, White.copy(0.2f)),
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = (-16).dp, y = 30.dp)
            ) {
                SubcomposeAsyncImage(
                    model = model.patchUrl,
                    contentDescription = null,
                    modifier = Modifier.padding(12.dp),
                    loading = {
                        Box(modifier = Modifier.fillMaxSize().background(getShimmerBrush(), CircleShape))
                    }
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(40.dp))
}

@Composable
fun RocketDetailsCard(model: LaunchUIModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = IceBlue
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            model.rocketName?.let {
                Text(
                    text = "${stringResource(Res.string.rocket)}: $it",
                    style = MaterialTheme.typography.titleLarge)
            }

            model.rocketDescription?.let {
                Text(text = it, color = Gray, modifier = Modifier.padding(vertical = 8.dp))
            }

            model.wikipediaUrl?.let { url ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { openBrowser(url) }
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.wikipedia_logo),
                        contentDescription = "Wikipedia Logo",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "${stringResource(Res.string.see_in_wikipedia_for)} ${model.rocketName}",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                        color = RocketBadge
                    )


                }
            }

            Row(modifier = Modifier.padding(vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                model.height?.let { SpecTag(label = stringResource(Res.string.height), value = it) }
                model.mass?.let   { SpecTag(label = stringResource(Res.string.mass),   value = it) }
            }
        }
    }
}
@Composable
fun SpecTag(label: String, value: String) {
    Surface(
        color = RocketBadge.copy(alpha = 0.3f),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.height(40.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 8.dp)) {
            Text(text = "$label:", style = MaterialTheme.typography.labelSmall, color = RocketBadge)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = value, style = MaterialTheme.typography.bodySmall, color = White)
        }
    }
}

@Composable
fun DetailBottomBar(model: LaunchUIModel) {

    if (model.videoUrl == null && model.articleUrl == null) return
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = White,
        tonalElevation = 8.dp,
        shadowElevation = 16.dp
    ) {
        Row(
            modifier = Modifier
                .background(color = RocketBadge.copy(alpha = 0.2f))
                .navigationBarsPadding()
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            model.videoUrl?.let { url ->
                Button(
                    onClick = { openBrowser(url) },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SurfaceLight,
                        contentColor = Black
                    ),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(2.dp, Red),
                ) {

                    Text(
                        text = stringResource(Res.string.watch_it_on),
                        color = Black,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )

                    Spacer(Modifier.width(8.dp))

                    Image(
                        painter = painterResource(Res.drawable.youtube),
                        contentDescription = "YouTube Logo",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

            model.articleUrl?.let { url ->
                Button(
                    onClick = { openBrowser(url) },
                    modifier = Modifier.weight(1f).height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Description, contentDescription = null, tint = White)
                    Spacer(Modifier.width(8.dp))
                    Text(stringResource(Res.string.read_article), color = White)
                }
            }
        }
    }
}



@Composable
fun LaunchDetailSkeleton() {
    val brush = getShimmerBrush()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceLight)
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.height(280.dp).fillMaxWidth()) {
            Spacer(modifier = Modifier.fillMaxSize().background(brush))

            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .size(90.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = (-16).dp, y = 30.dp),
                color = White
            ) {
                Spacer(modifier = Modifier.fillMaxSize().background(brush))
            }
        }

        Spacer(modifier = Modifier.height(45.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Spacer(modifier = Modifier.width(150.dp).height(28.dp).background(brush, RoundedCornerShape(4.dp)))
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.width(100.dp).height(16.dp).background(brush, RoundedCornerShape(4.dp)))
            }
            Spacer(modifier = Modifier.width(80.dp).height(30.dp).background(brush, RoundedCornerShape(16.dp)))
        }

        Spacer(modifier = Modifier.padding(horizontal = 16.dp).width(140.dp).height(20.dp).background(brush, RoundedCornerShape(4.dp)))
        Spacer(modifier = Modifier.height(12.dp))
        repeat(3) {
            Spacer(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp).height(14.dp).background(brush, RoundedCornerShape(2.dp)))
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = IceBlue),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Spacer(modifier = Modifier.width(120.dp).height(24.dp).background(brush, RoundedCornerShape(4.dp)))
                repeat(2) {
                    Spacer(modifier = Modifier.fillMaxWidth().height(14.dp).background(brush, RoundedCornerShape(2.dp)))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(2) { Spacer(modifier = Modifier.width(80.dp).height(35.dp).background(brush, RoundedCornerShape(8.dp))) }
                }
            }
        }
    }
}