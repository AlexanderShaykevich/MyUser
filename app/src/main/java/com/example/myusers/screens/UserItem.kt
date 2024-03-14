package com.example.myusers.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.myusers.R
import com.example.myusers.data.usermodel.User
import com.example.myusers.navigation.Screens


@Composable
fun UserItem(user: User, navHostController: NavHostController) {
    val email = user.email
    val activity = LocalContext.current as Activity

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navHostController.navigate(Screens.UserDetails.passArg(email)) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Spacer(modifier = Modifier.height(16.dp))
                AsyncImage(
                    modifier = Modifier
                        .height(72.dp)
                        .width(72.dp)
                        .clip(CircleShape),
                    model = user.picture.medium,
                    contentDescription = "picture"
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(3f)) {
                val modifier = Modifier.padding(end = 8.dp).size(18.dp)

                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = "name",
                        modifier = modifier
                    )
                    Text(
                        text = "${user.name.first} ${user.name.last}",
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_home_24),
                        contentDescription = "name",
                        modifier = modifier
                    )
                    Text(
                        text = "${user.location.country}, " + "${user.location.city}, " +
                                "${user.location.street.name} " + "${user.location.street.number} ",
                        modifier = Modifier.clickable {
                            val lat = user.location.coordinates.latitude
                            val long = user.location.coordinates.longitude
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$lat, $long"))
                            activity.startActivity(intent)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_call_24),
                        contentDescription = "name",
                        modifier = modifier
                    )
                    Text(
                        text = user.cell,
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${user.cell}"))
                            activity.startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}
