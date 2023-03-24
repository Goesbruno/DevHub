package br.com.alura.devhub.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.devhub.R
import br.com.alura.devhub.webclient.GitHubRepository
import br.com.alura.devhub.webclient.GitHubWebClient
import br.com.alura.devhub.webclient.ProfileUiState
import coil.compose.AsyncImage

@Composable
fun profileScreen(
    user: String,
    webClient: GitHubWebClient = GitHubWebClient()

) {
    val uiState = webClient.uiState
    LaunchedEffect(null) {
        webClient.findProfileBy(user)
    }
    Profile(uiState = uiState)
}


@Composable
private fun Profile(
    uiState: ProfileUiState
) {
    LazyColumn {
        item {
            ProfileHeader(uiState)
        }
        item {
            if (uiState.repositories.isNotEmpty()) {
                Text(
                    text = "Reposítórios", Modifier.padding(8.dp),
                    fontSize = 24.sp
                )
            }
        }
        items(uiState.repositories) { repo ->
            RepositoryItem(repo = repo)
        }
    }
}

@Composable
private fun ProfileHeader(state: ProfileUiState) {
    Column {
        val boxHeigth = remember {
            150.dp
        }
        val imageHeight = remember {
            boxHeigth
        }
        Box(
            Modifier
                .fillMaxWidth()
                .background(
                    color = Color.DarkGray,
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .height(boxHeigth)
        ) {
            AsyncImage(
                model = state.profilePic,
                placeholder = painterResource(R.drawable.imagem_padrao),
                contentDescription = "Github's profile pic",
                modifier = Modifier
                    .offset(y = imageHeight / 2)
                    .size(imageHeight)
                    .align(Alignment.BottomCenter)
                    .clip(CircleShape)
            )

        }
        Spacer(modifier = Modifier.height(imageHeight / 2))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = state.name,
                fontSize = 32.sp
            )
            Text(
                text = state.login,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

        }
        Text(
            text = state.bio,
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    bottom = 8.dp,
                    end = 8.dp
                )
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun RepositoryItem(repo: GitHubRepository) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp
    ) {
        Column {
            Text(
                repo.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2d333b))
                    .padding(8.dp),
                fontSize = 24.sp,
                color = Color.White
            )
            if (repo.description.isNotBlank()) {
                Text(
                    repo.description,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}




