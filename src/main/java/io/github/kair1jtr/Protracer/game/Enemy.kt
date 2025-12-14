package io.github.kair1jtr.Protracer.game

import io.github.kair1jtr.Protracer.raytracer.Point3
import io.github.kair1jtr.Protracer.raytracer.Utils
import io.github.kair1jtr.Protracer.raytracer.Vector3

abstract class Enemy(point : Point3, var dir : Vector3,size : Double) : GameObject(point,size){
    var alive = true
    var ballets = mutableListOf<Ballet>()

    abstract override fun move()

    abstract fun attack(player : Point3)

    abstract override fun hit_box(center : Vector3,size : Double) : Boolean
}