package io.github.kair1jtr.Protracer.raytracer

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object Utils {
    private val seed = ThreadLocal.withInitial {
        (System.nanoTime().toULong() xor 0x9E3779B97F4A7C15uL)
    }

    fun dot(v1: Vector3, v2: Vector3): Double =
        v1.x * v2.x + v1.y * v2.y + v1.z * v2.z

    fun cross(v1: Vector3, v2: Vector3): Vector3 = Vector3(
        v1.y * v2.z - v2.y * v1.z,
        v1.z * v2.x - v2.z * v1.x,
        v1.x * v2.y - v2.x * v1.y
    )

    fun unit_vector(v: Vector3): Vector3 = v / v.length()

    fun degrees_to_radians(degrees: Double) : Double = degrees * pi / 180

    fun random_double(): Double {
        var z = seed.get() + 0x9E3779B97F4A7C15uL
        seed.set(z)

        var x = z
        x = (x xor (x shr 30)) * 0xBF58476D1CE4E5B9uL
        x = (x xor (x shr 27)) * 0x94D049BB133111EBuL
        x = x xor (x shr 31)

        return ((x shr 11).toLong().toDouble() * (1.0 / (1L shl 53)))
    }

    fun random_double(min: Double, max: Double): Double = min + (max - min) * random_double()

    fun random() : Vector3 = Vector3(random_double(), random_double(), random_double())

    fun random(min : Double,max : Double) : Vector3 =
        Vector3(random_double(min, max), random_double(min, max), random_double(min, max))

    fun random_in_unit_sphere() : Vector3 {
        while (true) {
            var p = random(-1.0,1.0)
            if (p.length_squared() >= 1) continue
            return p
        }
    }

    fun random_unit_vector() : Vector3 {
        val a = random_double(0.0,2* pi)
        val z = random_double(-1.0,1.0)
        val r = sqrt(1 - z * z)
        return Vector3(r * cos(a), r * sin(a), z)
    }

    fun random_in_hemisphere(normal : Vector3) : Vector3 {
        var in_unit_sphere = random_in_unit_sphere()
        if (dot(in_unit_sphere,normal) > 0.0) {
            return in_unit_sphere
        }else {
            return -in_unit_sphere
        }
    }

    fun reflect(v : Vector3, n : Vector3) : Vector3 = v- (n*(2* dot(v,n)))

    fun rotation_to_vector(x_rot : Double,y_rot :Double) : Vector3 = Vector3(
            cos(y_rot) * sin(x_rot),
            sin(y_rot),
            cos(y_rot) * cos(x_rot)
        )

}