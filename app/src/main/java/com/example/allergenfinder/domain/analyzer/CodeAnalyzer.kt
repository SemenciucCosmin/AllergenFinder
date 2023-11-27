package com.example.allergenfinder.domain.analyzer

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class CodeAnalyzer(private val onScanned: (String) -> Unit) : ImageAnalysis.Analyzer {
    @OptIn(ExperimentalGetImage::class)
    override fun analyze(image: ImageProxy) {
        val imageToAnalyze = image.image ?: return
        val rotationDegrees = image.imageInfo.rotationDegrees
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .build()
        val barcodeScanner = BarcodeScanning.getClient(options)
        val imageToProcess = InputImage.fromMediaImage(imageToAnalyze, rotationDegrees)

        barcodeScanner
            .process(imageToProcess)
            .addOnSuccessListener { barcodes ->
                val firstValue = barcodes.firstOrNull()?.rawValue ?: return@addOnSuccessListener
                onScanned((firstValue))
            }
            .addOnFailureListener { exception ->
                exception.printStackTrace()
            }
            .addOnCompleteListener {
                image.close()
            }
    }
}
