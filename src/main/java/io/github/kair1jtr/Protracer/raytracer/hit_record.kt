package io.github.kair1jtr.Protracer.raytracer

import io.github.kair1jtr.Protracer.Point3
import io.github.kair1jtr.Protracer.Ray
import io.github.kair1jtr.Protracer.Utils
import io.github.kair1jtr.Protracer.Vector3

data class hit_record(
    var p: Point3,
    var normal : Vector3,
    var mat_ptr : Material,
    var t : Double,
    var j : Boolean,
    var front_face : Boolean
) {
    //物体の内側から発生したレイの場合に法線を内向きにする
    fun set_face_normal(r: Ray, outward_normal: Vector3) {
        front_face = Utils.dot(r.dir , outward_normal) < 0
        normal = if (front_face) {
            outward_normal
        }else {
            -outward_normal
        }
    }
}