package com.example.myusers.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myusers.viemodels.UserViewModel

@Composable
fun UserDetailsScreen(viewModel: UserViewModel, email: String?) {
    val user = email?.let { viewModel.findUser(it) }
    val activity = LocalContext.current as Activity

    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = user?.picture?.large,
                contentDescription = "picture",
                modifier = Modifier
                    .height(128.dp)
                    .width(128.dp)
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(10))
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "User: ${user?.name?.first} ${user?.name?.last}")
            Text(text = "Age: ${user?.dob?.age}")
            Text(text = "Birth date: ${user?.dob?.date?.take(10)}")
            Text(text = "Gender: ${user?.gender}")
            Text(text = "Address: " +
                    "${user?.location?.country}, " +
                    "${user?.location?.city}, " +
                    "${user?.location?.street?.name} " +
                    "${user?.location?.street?.number} ",
                maxLines = 3,
                modifier = Modifier.clickable {
                    val lat = user?.location?.coordinates?.latitude
                    val long = user?.location?.coordinates?.longitude
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$lat, $long"))
                    activity.startActivity(intent)
                }
            )
            Text(modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${user?.phone}"))
                activity.startActivity(intent)
            }, text = "Phone: ${user?.phone}")
            Text(modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${user?.cell}"))
                activity.startActivity(intent)
            }, text = "Cell: ${user?.cell}")
            Text(
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:")
                        putExtra(Intent.EXTRA_EMAIL, arrayOf(user?.email))
                    }
                    activity.startActivity(intent)

                },
                text = "Email: ${user?.email}"
            )
        }


    }

}