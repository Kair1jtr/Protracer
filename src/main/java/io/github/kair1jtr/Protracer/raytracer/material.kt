package io.github.kair1jtr.Protracer.raytracer

import io.github.kair1jtr.Protracer.Color
import io.github.kair1jtr.Protracer.Ray

abstract class material {
    abstract fun scatter(r_in: Ray, rec: hit_record) : Pair<Color, Ray>
}