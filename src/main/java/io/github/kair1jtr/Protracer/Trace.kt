package io.github.kair1jtr.Protracer

import processing.core.PApplet
import processing.core.PConstants
import processing.core.PImage

class Trace(var p: PApplet) {
    var aspect_ratio: Int

    var eye: Vector3 = Vector3(0.0, 0.0, 5.0)
    var sphereCenter: Vector3 = Vector3(0.0, 0.0, 0.0)
    var sphereRadius: Double = 1.0

    init {
        this.aspect_ratio = p.width / p.height
    }

    fun drawImage(): PImage {
        val viewport_height = 2.0
        val viewport_width = aspect_ratio * viewport_height
        val focal_length = 1.0

        val origin = Vector3(0.0, 0.0, 0.0)
        val horizontal = Vector3(viewport_width, 0.0, 0.0)
        val vertical = Vector3(0.0, viewport_height, 0.0)
        val lower_left_corner =
            origin - horizontal / 2.0 - vertical / 2.0 - Vector3(0.0, 0.0, focal_length)

        val colorTable = p.createImage(p.width, p.height, PConstants.RGB)
        colorTable.loadPixels()

        for (y in 0 until p.height) {
            println("Scanlines remaining: ${p.height - 1 - y}")

            val yy = p.height - 1 - y   // ★ 上下反転（Processing→RayTracing座標）

            for (x in 0 until p.width) {

                // ★ x はそのまま / y は反転して使う
                val u = x.toDouble() / (p.width - 1)
                val v = yy.toDouble() / (p.height - 1)

                val direction =
                    lower_left_corner + horizontal * u + vertical * v - origin

                val r = Ray(origin, direction)

                val pixel_color = ray_color(r)

                colorTable.pixels[y * p.width + x] =
                    p.color(
                        (pixel_color.e[0] * 255).toInt(),
                        (pixel_color.e[1] * 255).toInt(),
                        (pixel_color.e[2] * 255).toInt()
                    )
            }
        }

        colorTable.updatePixels()

        return colorTable
    }

    fun ray_color(r: Ray): Vector3 {
        val unit_direction = unit_vector(r.direction());
        val t = 0.5 * (+ 1)

        val i = Vector3(1.0,1.0,1.0);
        val k = Vector3(0.5,0.7,1.0);

        return Vector3(1.0, 1.0, 1.0)*(1.0-t) + Vector3(0.5, 0.7, 1.0)*t;
    }
}