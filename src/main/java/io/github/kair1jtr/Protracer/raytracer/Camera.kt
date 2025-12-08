package io.github.kair1jtr.Protracer.raytracer

import io.github.kair1jtr.Protracer.Point3
import io.github.kair1jtr.Protracer.Ray
import io.github.kair1jtr.Protracer.Vector3

class Camera(val width: Double,val height: Double) {
    val aspect_ratio = width/height
    val viewport_height = 2.0
    val viewport_width = aspect_ratio * viewport_height
    val focal_length = 1.0

    val origin = Point3(0.0,0.0,0.0)
    val horizontal = Vector3(viewport_width, 0.0, 0.0)
    val vertical = Vector3(0.0, viewport_height, 0.0)
    val lower_left_corner = origin - horizontal/2.0 - vertical/2.0 - Vector3(0.0, 0.0, focal_length)

    fun get_ray(u: Double,v: Double) : Ray = Ray(origin, lower_left_corner + horizontal * u + vertical * v - origin)
}