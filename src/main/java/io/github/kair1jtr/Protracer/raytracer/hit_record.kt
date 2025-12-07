package io.github.kair1jtr.Protracer.raytracer

import io.github.kair1jtr.Protracer.Point3
import io.github.kair1jtr.Protracer.Ray
import io.github.kair1jtr.Protracer.Utils
import io.github.kair1jtr.Protracer.Vector3

data class hit_record(
    var p: Point3,
    var normal : Vector3,
    var t : Double,
    var j : Boolean,
    var front_face : Boolean
) {
    fun set_face_normal(r: Ray, outward_normal: Vector3) {
        front_face = Utils.dot(r.dir , outward_normal) < 0
        normal = if (front_face) {
            outward_normal
        }else {
            -outward_normal
        }
    }
    fun set_none() {
        t = 0.0
        p = Vector3(0.0,0.0,0.0)
        normal = Vector3(0.0,0.0,0.0)
        j=false
        front_face = false
    }
}