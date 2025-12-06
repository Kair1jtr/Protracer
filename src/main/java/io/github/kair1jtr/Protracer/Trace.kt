package io.github.kair1jtr.Protracer

import processing.core.PApplet
import processing.core.PConstants
import processing.core.PImage
import kotlin.math.sqrt

import io.github.kair1jtr.Protracer.Vector3;

class Trace(var p: PApplet) {
    fun drawImage(): PImage {
        val image = p.createImage(p.width, p.height, PConstants.RGB)
        image.loadPixels()
        for (y in 0 until image.height) {
            val flippedY = image.height - 1 - y  // 上下反転
            for (x in 0 until image.width) {
                image.pixels[y * image.width + x] = calcPixelColor(x, flippedY)
            }
        }
        image.updatePixels()

        return image
    }

    fun calcPrimaryRay(x: Int, y: Int): Vector3 {
        val imagePlane: Double = p.height.toDouble()

        val dx: Double = x + 0.5 - p.width / 2
        val dy: Double = -(y + 0.5 - p.height / 2)
        val dz = -imagePlane

        return Vector3(dx, dy, dz).normalize()
    }

    fun calcPixelColor(x: Int, y: Int): Int {
        val eye: Vector3 = Vector3(0.0, 0.0, 5.0) // 視点の座標
        val sphereCenter: Vector3 = Vector3(0.0, 0.0, 0.0) // 球の中心座標
        val sphereRadius: Float = 1f

        val rayDir: Vector3 = calcPrimaryRay(x, y)

        if (intersectRaySphere(eye, rayDir, sphereCenter, sphereRadius)) {
            return p.color(255, 255, 255) // 白
        } else {
            return p.color(0, 0, 0) // 黒
        }
    }
    fun intersectRaySphere(
        rayOrigin: Vector3, rayDir: Vector3,
        sphereCenter: Vector3, sphereRadius: Float
    ): Boolean {
        val v: Vector3 = rayOrigin - sphereCenter
        val b: Double = dot(rayDir,v)
        val c: Double = dot(v,v) - sqrt(sphereRadius.toFloat())
        val d = b * b - c
        return d >= 0
    }
}