package com.argxment.extendedfeatures.client.init.utils.internal.render;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

public class ParticleBeamRenderer {

    private static final double DEFAULT_PARTICLES_PER_BLOCK = 1.5;

    private ParticleBeamRenderer() {}

    public static Vec3 faceCenterTowards(BlockPos from, BlockPos to) {
        Vec3 fromCenter = Vec3.atCenterOf(from);
        Vec3 toCenter = Vec3.atCenterOf(to);
        Vec3 dir = toCenter.subtract(fromCenter);

        double ax = Math.abs(dir.x);
        double ay = Math.abs(dir.y);
        double az = Math.abs(dir.z);

        if (ax >= ay && ax >= az) {
            return fromCenter.add(Math.signum(dir.x) * 0.5, 0, 0);
        } else if (ay >= ax && ay >= az) {
            return fromCenter.add(0, Math.signum(dir.y) * 0.5, 0);
        } else {
            return fromCenter.add(0, 0, Math.signum(dir.z) * 0.5);
        }
    }

    // Draws a single straight line of particles between two points
    public static void emitLine(ServerLevel level, Vec3 start, Vec3 end, ParticleOptions particle) {
        emitLine(level, start, end, particle, DEFAULT_PARTICLES_PER_BLOCK);
    }

    public static void emitLine(ServerLevel level, Vec3 start, Vec3 end,
                                ParticleOptions particle, double particlesPerBlock) {
        double distance = start.distanceTo(end);
        int steps = Math.max(1, (int) (distance * particlesPerBlock));

        for (int i = 0; i <= steps; i++) {
            double t = (double) i / steps;
            Vec3 point = start.lerp(end, t);
            level.sendParticles(particle, point.x, point.y, point.z, 1, 0, 0, 0, 0);
        }
    }

    // Draws the 4-edge wireframe of a horizontal (XZ-plane) square of the given half-extent,centered on the given point at the same Y level
    public static void emitSquareOutline(ServerLevel level, Vec3 center, double halfExtent, ParticleOptions particle) {
        emitSquareOutline(level, center, halfExtent, particle, DEFAULT_PARTICLES_PER_BLOCK);
    }

    public static void emitSquareOutline(ServerLevel level, Vec3 center, double halfExtent,
                                         ParticleOptions particle, double particlesPerBlock) {
        double edgeExtent = halfExtent - 0.5;
        Vec3[] corners = new Vec3[4];
        int i = 0;
        for (int dx = -1; dx <= 1; dx += 2) {
            for (int dz = -1; dz <= 1; dz += 2) {
                corners[i++] = center.add(dx * edgeExtent, 0, dz * edgeExtent);
            }
        }
        // corner indices: 0=(-,-) 1=(-,+) 2=(+,-) 3=(+,+)
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 } };

        for (int[] edge : edges) {
            emitLine(level, corners[edge[0]], corners[edge[1]], particle, particlesPerBlock);
        }
    }
}