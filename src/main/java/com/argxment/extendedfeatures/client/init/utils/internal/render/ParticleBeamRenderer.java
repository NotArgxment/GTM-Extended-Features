package com.argxment.extendedfeatures.client.init.utils.internal.render;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

public class ParticleBeamRenderer {

    private static final double DEFAULT_PARTICLES_PER_BLOCK = 3.0;

    private ParticleBeamRenderer() {}

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

    // Draws the 12-edge wireframe of a cube of the given half-extent
    public static void emitCubeOutline(ServerLevel level, Vec3 center, double halfExtent, ParticleOptions particle) {
        emitCubeOutline(level, center, halfExtent, particle, DEFAULT_PARTICLES_PER_BLOCK);
    }

    public static void emitCubeOutline(ServerLevel level, Vec3 center, double halfExtent,
                                       ParticleOptions particle, double particlesPerBlock) {
        Vec3[] corners = new Vec3[8];
        int i = 0;
        for (int dx = -1; dx <= 1; dx += 2) {
            for (int dy = -1; dy <= 1; dy += 2) {
                for (int dz = -1; dz <= 1; dz += 2) {
                    corners[i++] = center.add(dx * halfExtent, dy * halfExtent, dz * halfExtent);
                }
            }
        }
        // corner indices: 0=(---) 1=(--+) 2=(-+-) 3=(-++) 4=(+--) 5=(+-+) 6=(++-) 7=(+++)
        int[][] edges = {
                { 0, 1 }, { 0, 2 }, { 0, 4 }, { 1, 3 }, { 1, 5 }, { 2, 3 },
                { 2, 6 }, { 3, 7 }, { 4, 5 }, { 4, 6 }, { 5, 7 }, { 6, 7 }
        };

        for (int[] edge : edges) {
            emitLine(level, corners[edge[0]], corners[edge[1]], particle, particlesPerBlock);
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