package io.github.kair1jtr.Protracer

import java.util.Vector
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

object Utils {
    private val random = Random

    fun dot(v1: Vector3, v2: Vector3): Double =
        v1.x * v2.x + v1.y * v2.y + v1.z * v2.z

    fun cross(v1: Vector3, v2: Vector3): Vector3 = Vector3(
        v1.y * v2.z - v2.y * v1.z,
        v1.z * v2.x - v2.z * v1.x,
        v1.x * v2.y - v2.x * v1.y
    )

    fun unitVector(v: Vector3): Vector3 = v / v.length()

    fun degrees_to_radians(degrees: Double) : Double = degrees * pi / 180

    fun random_double(): Double = random.nextDouble()

    fun random_double(min: Double, max: Double): Double = min + (max - min) * random.nextDouble()

    fun random() : Vector3 = Vector3(random_double(),random_double(),random_double())

    fun random(min : Double,max : Double) : Vector3 = Vector3(random_double(min,max),random_double(min,max),random_double(min,max))

    fun random_in_unit_sphere() : Vector3 {
        while (true) {
            var p = Utils.random(-1.0,1.0)
            if (p.length_squared() >= 1) continue
            return p
        }
    }

    fun random_unit_vector() : Vector3 {
        val a = random_double(0.0,2*pi)
        val z = random_double(-1.0,1.0)
        val r = sqrt(1 - z*z)
        return Vector3(r* cos(a),r* sin(a),z)
    }

    fun random_in_hemisphere(normal : Vector3) : Vector3{
        var in_unit_sphere = random_in_unit_sphere()
        if (Utils.dot(in_unit_sphere,normal) > 0.0) {
            return in_unit_sphere
        }else {
            return -in_unit_sphere
        }
    }

    fun reflect(v : Vector3,n : Vector3) : Vector3 = v-2* Utils.dot(v,n)*n
}