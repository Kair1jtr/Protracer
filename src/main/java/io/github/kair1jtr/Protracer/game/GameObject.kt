package io.github.kair1jtr.Protracer.game

import io.github.kair1jtr.Protracer.raytracer.Point3
import io.github.kair1jtr.Protracer.raytracer.Vector3

abstract class GameObject(var point : Point3,var size : Double) {
    abstract fun move()

    abstract fun hit_box(center : Vector3,player : Double) : Boolean
}