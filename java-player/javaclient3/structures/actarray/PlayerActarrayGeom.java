/*
 *  Player Java Client 3 - PlayerActarrayGeom.java
 *  Copyright (C) 2006 Radu Bogdan Rusu
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
 * $Id: PlayerActarrayGeom.java 90 2010-05-02 18:09:04Z corot $
 *
 */

package javaclient3.structures.actarray;

import javaclient3.structures.*;

/**
 * Request/reply: get geometry
 * Send a null PLAYER_ACTARRAY_GET_GEOM_REQ request to receive the geometry in
 * this form. 
 * @author Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v2.0 - Player 2.0 supported
 * </ul>
 */
public class PlayerActarrayGeom implements PlayerConstants {

    // The number of actuators in the array. 
    private int actuators_count;
    // The geometry information for each actuator in the array. 
    private PlayerActarrayActuatorgeom[] actuators = new PlayerActarrayActuatorgeom[PLAYER_ACTARRAY_NUM_ACTUATORS];


    /**
     * @return  The number of actuators in the array. 
     **/
    public synchronized int getActuators_count () {
        return this.actuators_count;
    }

    /**
     * @param newActuators_count  The number of actuators in the array. 
     *
     */
    public synchronized void setActuators_count (int newActuators_count) {
        this.actuators_count = newActuators_count;
    }
    /**
     * @return  The geometry information for each actuator in the array. 
     **/
    public synchronized PlayerActarrayActuatorgeom[] getActuators () {
        return this.actuators;
    }

    /**
     * @param newActuators  The geometry information for each actuator in the array. 
     *
     */
    public synchronized void setActuators (PlayerActarrayActuatorgeom[] newActuators) {
        this.actuators = newActuators;
    }

}