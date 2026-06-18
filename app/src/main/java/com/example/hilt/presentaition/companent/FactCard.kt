package com.example.hilt.presentaition.companent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hilt.model.Category
import com.example.hilt.model.Fact


@Composable
fun FactCard(
    fact: Fact,
    isFavorite: Boolean,
    onFavoriteClick: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = fact.text,
                style = MaterialTheme.typography.bodyLarge
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Категория: ${fact.category}",
                    style = MaterialTheme.typography.labelMedium
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {onFavoriteClick(true)},

                ){
                    Icon(
                        imageVector = if (isFavorite){
                            Icons.Filled.Favorite
                        }else{
                            Icons.Outlined.FavoriteBorder
                        },
                        contentDescription = "favorite",
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun FanCardPreview() {
    val fact = Fact(1,"dffdf", Category.ANIMALS)
    MaterialTheme{
        FactCard(
            fact,
            isFavorite = false,
            onFavoriteClick = {}
        )
    }
}