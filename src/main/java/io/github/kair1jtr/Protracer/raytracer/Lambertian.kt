package io.github.kair1jtr.Protracer.raytracer

import io.github.kair1jtr.Protracer.raytracer.Ray
import io.github.kair1jtr.Protracer.raytracer.Utils

class Lambertian(val albedo: Color) : Material() {
    override fun scatter(r_in: Ray, rec: hit_record): Triple<Color, Ray, Boolean> {
        var scatter_direction = rec.normal + Utils.random_unit_vector()

        val scattered = Ray(rec.p, scatter_direction)
        return Triple(albedo, scattered,true)
    }
}