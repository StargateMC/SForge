/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmocore.constants;

import com.mmocore.MMOCore;
import com.mmocore.api.UniverseAPI;
import com.mmocore.module.AbstractObjectCore;
import com.mmocore.module.Dimension.RegisterableDimension;
import com.mmocore.module.Galaxy.RegisterableGalaxy;

/**
 *
 * @author draks
 */
public class uPosition extends AbstractObjectCore<uPosition> {

    private double uPosX;
    private double uPosZ;
    private double dPosX;
    private double dPosZ;
    private RegisterableDimension dimension;
    private double dPosY;
    
    public uPosition(double dPosX, double dPosY, double dPosZ, RegisterableDimension dimension) {
        this.dimension = dimension;
        this.dPosX = dPosX;
        this.dPosY = dPosY;
        this.dPosZ = dPosZ;
        if (dimension.getType().equals(DimensionType.Hyperspace) || dimension.getType().equals(DimensionType.Unknown)) {
            this.uPosX = this.dPosX;
            this.uPosZ = this.dPosZ;
        } else {
            if (this.dimension.getType().equals(DimensionType.Planet)) {
                uPosX = this.getDimension().getParent().getX();
                uPosZ = this.getDimension().getParent().getZ();
                if (this.getDimension().getParent().getSpawnX() > this.getDimension().getX()) uPosX -= (this.getDimension().getParent().getSpawnX() - this.getDimension().getX());
                if (this.getDimension().getParent().getSpawnX() < this.getDimension().getX()) uPosZ += (this.getDimension().getX() - this.getDimension().getParent().getSpawnX());
                if (this.getDimension().getSpawnX() > this.getDPosX()) uPosX -= (this.getDimension().getSpawnX() - this.getDPosX());
                if (this.getDimension().getSpawnX() < this.getDPosX()) uPosZ += (this.getDPosX() - this.getDimension().getSpawnX());                
            }
            if (this.dimension.getType().equals(DimensionType.StarSystem)) {
                uPosX = this.getDimension().getX();
                uPosZ = this.getDimension().getZ();
                if (this.getDimension().getSpawnX() > this.getDPosX()) uPosX -= (this.getDimension().getSpawnX() - this.getDPosX());
                if (this.getDimension().getSpawnX() < this.getDPosX()) uPosZ += (this.getDPosX() - this.getDimension().getSpawnX());    
            }            
        }
    }
    
    public boolean isInSpace() {
        return (this.dimension.getType().equals(DimensionType.StarSystem));
    }
    
    public RegisterableDimension getDimension() {
        return this.dimension;
    }
    
    public boolean isInHyperSpace() {
        return (this.dimension.getType().equals(DimensionType.Hyperspace));
    }
    
    public boolean isInUniverse() {
        return (this.dimension != null);
    }
    
    public double getDPosX() {
        return this.dPosX;
    }
    public double getDPosY() {
        return this.dPosY;
    }
    public double getDPosZ() {
        return this.dPosZ;
    }
    public double getUPosX() {
        return this.uPosX;
    }
    
    public String getDisplayString() {
        return (this.getDPosX() + "," + this.getDPosY() + ", " + this.getDPosZ() + " on dimension: " + this.getDimension().getDisplayName());
    }
    
    public double getUPosZ() {
        return this.uPosZ;
    }
    
    public RegisterableGalaxy getGalaxy() {
        return UniverseAPI.getGalaxy(this);
    }
    
    @Override
    public void initialise() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finalise() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
