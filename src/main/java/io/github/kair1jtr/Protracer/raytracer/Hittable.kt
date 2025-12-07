package io.github.kair1jtr.Protracer.raytracer

import io.github.kair1jtr.Protracer.Ray

abstract class Hittable {
    abstract fun hit(
        r: Ray, t_min: Double, t_max: Double
    ) : hit_record
}