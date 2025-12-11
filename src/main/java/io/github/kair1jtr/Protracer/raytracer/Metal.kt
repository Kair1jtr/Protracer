package io.github.kair1jtr.Protracer.raytracer

import io.github.kair1jtr.Protracer.Color
import io.github.kair1jtr.Protracer.Ray
import io.github.kair1jtr.Protracer.Utils

import io.github.kair1jtr.Protracer.*

class Metal(val albedo: Color,val fuzz : Double) : Material() {
    override fun scatter(r_in: Ray, rec: hit_record): Triple<Color, Ray, Boolean> {
        val reflected = Utils.reflect(Utils.unit_vector(r_in.dir),rec.normal)
        val scattered = Ray(rec.p,reflected + Utils.random_in_unit_sphere() * fuzz)
        val atteuation = albedo

        return Triple(atteuation,scattered, Utils.dot(scattered.dir,rec.normal) > 0)
    }
}