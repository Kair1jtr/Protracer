package io.github.kair1jtr.Protracer.raytracer

import io.github.kair1jtr.Protracer.Color
import io.github.kair1jtr.Protracer.Ray

abstract class Material {
    abstract fun scatter(r_in: Ray, rec: hit_record) : Triple<Color, Ray, Boolean>
}