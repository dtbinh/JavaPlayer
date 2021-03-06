/*
 *  Player Java Client - PlayerPosition3DGeomT.java
 *  Copyright (C) 2005 Radu Bogdan Rusu
 *
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * $Id: PlayerPosition3DGeomT.java 10 2005-05-10 12:10:24Z veedee $
 *
 */
package javaclient.structures;

/**
 * To request robot geometry, set the subtype to PLAYER_POSITION3D_GET_GEOM_REQ 
 * and leave the other fields empty. The server will reply with the pose and 
 * size fields filled in. <br />
 * (see the player_position3d_geom structure from player.h)
 * @author Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v1.6.3 - Player 1.6.3 (all interfaces) supported
 *      <li>v1.6.2 - Player 1.6.2 supported, Javadoc documentation, several bugfixes  
 *      <li>v1.5a &nbsp;- Player 1.5 supported (most popular devices)
 * </ul>
 */
public class PlayerPosition3DGeomT {
    /* pose of the robot base, in the robot cs (mm, mm, mm, mrad, mrad, mrad) */
    private short[] pose = new short[6];
    /* dimensions of the base (mm, mm, mm) */
    private short[] size = new short[3];
    
    /**
     * 
     * @return the pose of the robot base, in the robot cs (mm, mm, mrad)
     */
    public synchronized short[] getPose () {
        return this.pose;
    }
    
    /**
     * 
     * @param newpose pose of the robot base, in the robot cs (mm, mm, mrad) 
     */
    public synchronized void setPose (short[] newpose) {
        this.pose = newpose;
    }
    
    /**
     * 
     * @return the dimensions of the base (mm, mm) 
     */
    public synchronized short[] getSize () {
        return this.size;
    }
    
    /**
     * 
     * @param newsize dimensions of the base (mm, mm) 
     */
    public synchronized void setSize (short[] newsize) {
        this.size = newsize;
    }
}
