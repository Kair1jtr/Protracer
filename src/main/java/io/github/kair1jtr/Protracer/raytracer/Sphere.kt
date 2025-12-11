package io.github.kair1jtr.Protracer

import kotlin.math.sqrt

import io.github.kair1jtr.Protracer.raytracer.*


class Sphere(val center : Point3,val radius: Double,val mat_ptr: Material) : Hittable(){

    override fun hit(r: Ray, t_min: Double, t_max: Double): hit_record {
        val oc = r.orig - center
        val a = r.dir.length_squared()
        val half_b = Utils.dot(oc,r.dir)
        val c = oc.length_squared() - radius*radius
        val discriminant = half_b*half_b - a*c

        if (discriminant > 0) {
            val root = sqrt(discriminant)

            var temp = (-half_b - root)/a
            if (t_min < temp && temp < t_max) {
                val outward_normal : Vector3 = (r.at(temp )-center) / radius
                var rec = hit_record(
                    t = temp,
                    p = r.at(temp),
                    normal = (r.at(temp)-center)/radius,
                    j = true,
                    front_face = false,
                    mat_ptr = mat_ptr
                )

                rec.set_face_normal(r,outward_normal)

                return rec
            }
            temp = (-half_b + root)/a
            if (temp < t_max && temp > t_min) {
                val outward_normal : Vector3 = (r.at(temp )-center) / radius
                var rec = hit_record(
                    t = temp,
                    p = r.at(temp),
                    normal = (r.at(temp)-center)/radius,
                    j = true,
                    front_face = false,
                    mat_ptr = mat_ptr
                )

                rec.set_face_normal(r,outward_normal)

                return rec
            }
        }
        return hit_record(
            t = 0.0,
            p = r.at(0.0),
            normal = r.at(0.0),
            j=false,
            front_face = false,
            mat_ptr = mat_ptr
        )
    }
}