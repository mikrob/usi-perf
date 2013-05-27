package org.henri.cocktailfactory.mbean;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * @author Henri Tremblay
 */
@Component
@ManagedResource(objectName="bean:name=performanceBean", description="Used to toggle performance bottlenecks")
public class PerformanceBean {

    private boolean bottleneck = true;
    

    @ManagedAttribute(description="Is bottleneck on?")
    public boolean isBottleneck() {
        return bottleneck;
    }

    @ManagedAttribute(description="Is bottleneck on?")
    public void setBottleneck(boolean bottleneck) {
        this.bottleneck = bottleneck;
    }
}
