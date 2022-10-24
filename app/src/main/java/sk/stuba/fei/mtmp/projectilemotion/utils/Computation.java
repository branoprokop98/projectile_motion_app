package sk.stuba.fei.mtmp.projectilemotion.utils;

import java.util.ArrayList;
import java.util.List;

import sk.stuba.fei.mtmp.projectilemotion.models.Motion;

public class Computation {
    private double angle;
    private final double speed;
    private final double GRAVITY = 9.81;

    public Computation(double speed, double angle) {
        this.speed = speed;
        this.angle = angle;
    }

    public List<Motion> getResult() {
        List<Motion> results = new ArrayList<>();
        results.add(new Motion(0, 0, 0.000));
        this.angle = Math.toRadians(this.angle);
        double x;
        double y;
        double time = 0.001;
        do {
            x = speed * time * Math.cos(angle);
            y = (speed * time * Math.sin(angle)) - ((GRAVITY * Math.pow(time, 2)) / 2);
            results.add(new Motion(x, y, time));
            time += 0.01;
        } while (y >= 0);
        results.remove(results.size() - 1);
        return results;
    }

    public double distance() {
        this.angle = Math.toRadians(this.angle);
        return (Math.pow(speed, 2) / GRAVITY) * Math.sin(2 * angle);
    }
}
