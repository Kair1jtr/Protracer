package io.github.kair1jtr.Protracer.raytracer

import io.github.kair1jtr.Protracer.Point3
import io.github.kair1jtr.Protracer.Ray
import io.github.kair1jtr.Protracer.Utils
import io.github.kair1jtr.Protracer.Vector3
import kotlin.math.tan

class Camera(val lookfrom : Point3,
             val lookat : Point3,
             val vup : Vector3,
             val vfov: Double,
             val aspect_ratio: Double
) {

    var origin = Point3(0.0, 0.0, 0.0)
    var horizontal: Vector3
    var vertical: Vector3
    var lower_left_corner: Point3

    init {
        val theta = vfov  // vfovは既に弧度法
        val h = tan(theta / 2.0)
        val viewport_height = 2.0 * h
        val viewport_width = aspect_ratio * viewport_height

        // カメラ座標系の基底ベクトルを計算
        val w = Utils.unit_vector(lookfrom - lookat)  // 視線方向の逆（正規化）
        val u = Utils.unit_vector(Utils.cross(vup,w))
        val v = Utils.cross(w,u)                         // 垂直方向ベクトル（既に正規化されている）

        origin = lookfrom
        horizontal = u*viewport_width
        vertical = v*viewport_height
        lower_left_corner = origin - horizontal / 2.0 - vertical / 2.0 - w
    }

    fun get_ray(u: Double, v: Double): Ray = Ray(origin, lower_left_corner + horizontal * u + vertical * v - origin)
}