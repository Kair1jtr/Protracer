package io.github.kair1jtr.Protracer.game

import io.github.kair1jtr.Protracer.raytracer.Point3
import io.github.kair1jtr.Protracer.raytracer.Vector3
import kotlin.math.sqrt

class Ballet(point : Point3,size : Double,var dir : Vector3) : GameObject(point,size) {
    public var lifetime : Double = 0.0

    override fun move() {
        point += dir.normalize()*0.3
        lifetime++
    }

    override fun hit_box(center : Vector3,player_size: Double) : Boolean{
        val dx = center.x - point.x
        val dy = center.y - point.y
        val dz = center.z - point.z

        val distance = sqrt(dx * dx + dy * dy + dz * dz)
        return distance <= size
    }
}