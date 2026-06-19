package org.todo.task.pe.view.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAd(modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->
            AdView(context).apply {
                // Configuramos el tamaño estándar de Banner
                setAdSize(AdSize.BANNER)

                // IMPORTANTE: Este es el ID de tu bloque de anuncios que acabas de crear
                //adUnitId = "ca-app-pub-2231310509491104/2230047868"

                adUnitId = "ca-app-pub-3940256099942544/6300978111"
                // Cargamos el anuncio
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}