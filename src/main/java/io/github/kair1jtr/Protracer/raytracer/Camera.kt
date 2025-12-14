package io.github.kair1jtr.Protracer.raytracer

import io.github.kair1jtr.Protracer.raytracer.Ray
import io.github.kair1jtr.Protracer.raytracer.Utils
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class Camera(
    val lookfrom: Point3,
    val x_rot: Double,
    val y_rot: Double,
    val vup: Vector3 = Vector3(0.0, 1.0, 0.0),  // デフォルトでY軸上向き
    val vfov: Double,
    val aspect_ratio: Double
) {
    var origin: Point3
    var horizontal: Vector3
    var vertical: Vector3
    var lower_left_corner: Point3
    var lookat: Point3

    init {
        val direction = Vector3(
            cos(y_rot) * sin(x_rot),
            sin(y_rot),
            cos(y_rot) * cos(x_rot)
        )

        lookat = lookfrom + direction

        // カメラ設定の計算
        val theta = vfov
        val h = tan(theta / 2.0)
        val viewport_height = 2.0 * h
        val viewport_width = aspect_ratio * viewport_height

        val w = Utils.unit_vector(lookfrom - lookat)
        val u = Utils.unit_vector(Utils.cross(vup, w))
        val v = Utils.cross(w, u)

        origin = lookfrom
        horizontal = u * viewport_width
        vertical = v * viewport_height
        lower_left_corner = origin - horizontal / 2.0 - vertical / 2.0 - w
    }

    fun get_ray(u: Double, v: Double): Ray = Ray(origin, lower_left_corner + horizontal * u + vertical * v - origin)
}