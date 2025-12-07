package io.github.kair1jtr.Protracer.raytracer;

import io.github.kair1jtr.Protracer.Vector3;
import org.jocl.*;

import static org.jocl.CL.*;

public class gTrace {
    static String src = """
            
            
            
            
            
            
            
            
            """;

    public gTrace() {

    }

    void setup() {
        CL.setExceptionsEnabled(true);

        cl_platform_id[] platforms = new cl_platform_id[1];
        clGetPlatformIDs(1, platforms, null);

        cl_device_id[] devices = new cl_device_id[1];
        clGetDeviceIDs(platforms[0], CL_DEVICE_TYPE_GPU, 1, devices, null);

        cl_context context = clCreateContext(
                null, 1, devices,
                null, null, null
        );

        cl_command_queue queue = clCreateCommandQueue(
                context,
                devices[0],
                CL_QUEUE_PROFILING_ENABLE,
                null
        );
    }

    void runKernel(Vector3 v) {

    }
}
