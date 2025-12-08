package io.github.kair1jtr.Protracer.raytracer

import io.github.kair1jtr.Protracer.Color
import io.github.kair1jtr.Protracer.Ray
import io.github.kair1jtr.Protracer.Utils

abstract class lambertian(val albedo: Color) : material() {
    override fun scatter(r_in: Ray, rec: hit_record): Pair<Color, Ray> {
        var scatter_direction = rec.normal + Utils.random_unit_vector()

        val scattered = Ray(rec.p, scatter_direction)
        return Pair(albedo, scattered)
    }
}