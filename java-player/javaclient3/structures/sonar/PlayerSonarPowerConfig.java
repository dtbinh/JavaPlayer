/*
 *  Player Java Client 3 - PlayerSonarPowerConfig.java
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
 * $Id: PlayerSonarPowerConfig.java 90 2010-05-02 18:09:04Z corot $
 *
 */

package javaclient3.structures.sonar;

import javaclient3.structures.*;

/**
 * Request/reply: Sonar power.
 * On some robots, the sonars can be turned on and off from software.
 * To do so, send a PLAYER_SONAR_REQ_POWER request.  Null response. 
 * @author Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v2.0 - Player 2.0 supported
 * </ul>
 */
public class PlayerSonarPowerConfig implements PlayerConstants {

    // Turn power off TRUE or FALSE 
    private byte state;


    /**
     * @return  Turn power off TRUE or FALSE 
     **/
    public synchronized byte getState () {
        return this.state;
    }

    /**
     * @param newState  Turn power off TRUE or FALSE 
     *
     */
    public synchronized void setState (byte newState) {
        this.state = newState;
    }

}