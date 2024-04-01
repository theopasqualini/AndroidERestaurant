package fr.isen.pasqualini.androidrestaurent

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import fr.isen.pasqualini.androidrestaurent.ui.theme.AndroidREstaurentTheme
import org.json.JSONObject
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.Response


class MenuApiClient(private val context: Context) {
    private val TAG = "MenuApiClient"
    private val url = "http://test.api.catering.bluecodegames.com/menu"
    private val idShop = "1"

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun fetchMenuItems(listener: MenuListener) {
        val jsonObject = JSONObject().apply {
            put("id_shop", idShop)
        }

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonObject,
            Response.Listener { response ->
                // Traitement de la réponse JSON
                listener.onMenuItemsReceived(response)
            },
            Response.ErrorListener { error ->
                Log.e(TAG, "Erreur lors de la récupération du menu : ${error.message}")
                listener.onError(error)
            }
        )

        requestQueue.add(jsonObjectRequest)
    }

    interface MenuListener {
        fun onMenuItemsReceived(response: JSONObject)
        fun onError(error: Exception)
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    AndroidREstaurentTheme {
        Greeting2("Android")
    }
}