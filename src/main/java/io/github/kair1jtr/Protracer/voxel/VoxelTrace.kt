package io.github.kair1jtr.Protracer.voxel

import io.github.kair1jtr.Protracer.Color
import io.github.kair1jtr.Protracer.Point3
import io.github.kair1jtr.Protracer.Vector3
import io.github.kair1jtr.Protracer.Ray
import processing.core.PApplet
import processing.core.PConstants
import processing.core.PImage
import kotlin.math.max
import kotlin.math.min

class VoxelTrace(var p: PApplet) {

    fun drawImage(): PImage {
        val image = p.createImage(p.width, p.height, PConstants.RGB)
        image.loadPixels()

        val aspect_ratio = p.width.toDouble() / p.height.toDouble()
        val viewport_height = 2.0
        val viewport_width = aspect_ratio * viewport_height
        val focal_length = 1.0

        val origin = Point3(0.0, 0.0, 0.0)
        val horizontal = Vector3(viewport_width, 0.0, 0.0)
        val vertical = Vector3(0.0, viewport_height, 0.0)
        val lower_left_corner = origin - horizontal/2.0 - vertical/2.0 - Vector3(0.0, 0.0, focal_length)

        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val u = x.toDouble() / (p.width - 1)
                val v = y.toDouble() / (p.height - 1)

                val direction = lower_left_corner + horizontal * u + vertical * v - origin
                val r = Ray(origin, direction)

                val c = ray_color(r)
                val red = (c.x * 255).toInt().coerceIn(0, 255)
                val green = (c.y * 255).toInt().coerceIn(0, 255)
                val blue = (c.z * 255).toInt().coerceIn(0, 255)

                // 上下反転して表示
                p.set(x,p.height-1-y,p.color(red, green, blue))
            }
        }

        image.updatePixels()
        return image
    }

    fun ray_color(r: Ray): Color {
        val grid = VoxelGrid()

        grid.addCube(0.0, 0.0, -3.0, 1.0, Color(1.0, 0.0, 0.0))   // 中央の赤い立方体
        grid.addCube(1.5, 0.0, -3.0, 0.5, Color(0.0, 1.0, 0.0))   // 右の緑の立方体
        grid.addCube(-1.5, 0.0, -3.0, 0.5, Color(0.0, 0.0, 1.0))  // 左の青の立方体
        grid.addCube(0.0, 1.5, -3.0, 0.5, Color(1.0, 1.0, 0.0))   // 上の黄色の立方体
        grid.addCube(0.0, -1.5, -3.0, 0.5, Color(1.0, 0.0, 1.0))  // 下の紫の立方体

        val hit = grid.hit(r)
        if (hit != null) {
            val (t, color) = hit
            return color
        }

        val unit_direction = r.dir.normalize()
        val t = (unit_direction.y + 1.0) * 0.5
        return Color(1.0, 1.0, 1.0) * (1.0 - t) + Color(0.5, 0.7, 1.0) * t
    }
}