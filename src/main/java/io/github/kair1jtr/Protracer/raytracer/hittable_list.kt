package io.github.kair1jtr.Protracer.raytracer

import io.github.kair1jtr.Protracer.raytracer.Ray

class hittable_list() : Hittable() {
    private var objects = mutableListOf<Hittable>()

    fun clear() = objects.clear()
    fun add(obj: Hittable) = objects.add( obj )

    override fun hit(r: Ray, t_min: Double, t_max: Double): hit_record {
        var closest_so_far = t_max
        var rec : hit_record = hit_record(
            t = 0.0,
            p = Vector3(0.0,0.0,0.0),
            normal = Vector3(0.0,0.0,0.0),
            j=false,
            front_face = false,
            mat_ptr = Lambertian(Color(0.0,0.0,0.0))
        )


        for (obj in objects) {
            var temp_rec = obj.hit(r,t_min,closest_so_far)
            if (temp_rec.j) {
                closest_so_far = temp_rec.t
                rec = temp_rec
            }
        }

        return rec
    }

}