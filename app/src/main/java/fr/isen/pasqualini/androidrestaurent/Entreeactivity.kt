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
import com.google.gson.Gson


class Entreeactivity(private val context: Context) {
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
            com.android.volley.Request.Method.POST,
            url,
            jsonObject,
            { response ->
                // Traitement de la réponse JSON
                listener.onMenuItemsReceived(response)

                // Filtrer les éléments selon la catégorie "entrée" et les afficher dans la console
                val filteredItems = filterItems(response)
                filteredItems.forEach { item -> println("Nom: ${item.name}") }
            },
            { error ->
                Log.e(TAG, "Erreur lors de la récupération du menu : ${error.message}")
                listener.onError(error)
            }
        )

        requestQueue.add(jsonObjectRequest)
    }

    private fun filterItems(response: JSONObject): List<MenuItem> {
        // Ici, vous devez implémenter votre logique de filtrage en fonction de la structure de votre JSON
        // Pour cet exemple, je vais simplement renvoyer une liste vide
        return emptyList()
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

data class MenuItem(val category: String, val name: String)

data class Menu(val menu: List<MenuItem>)



// Votre JSON
val json = "{ \"menu\": [ { \"category\": \"entrée\", \"name\": \"Salade\" }, { \"category\": \"plat\", \"name\": \"Steak\" }, { \"category\": \"dessert\", \"name\": \"Gâteau\" } ] }"

// Désérialisation du JSON en objets Java à l'aide de GSON
val menu = Gson().fromJson(json, Menu::class.java)

// Filtrer les éléments selon la catégorie sélectionnée (par exemple, "entrée")
val filteredItems = menu.menu.filter { it.category == "entrée" }

// Afficher les éléments filtrés


