package Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CallStageWhenEnterAreaRectangle {
    int[] stageToCall();
    String worldName();
    double x();
    double y();
    double z();
    double dx();
    double dy();
    double dz();
}
