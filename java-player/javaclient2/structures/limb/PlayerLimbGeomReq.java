/*
 *  Player Java Client 2 - PlayerLimbGeomReq.java
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
 * $Id: PlayerLimbGeomReq.java 40 2006-02-28 21:23:58Z veedee $
 *
 */

package javaclient2.structures.limb;

import javaclient2.structures.*;

/**
 * Request/reply: get geometry
 * Query geometry by sending a null PLAYER_LIMB_GEOM_REQ reqest.
 * @author Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v2.0 - Player 2.0 supported
 * </ul>
 */
public class PlayerLimbGeomReq implements PlayerConstants {

    // The base position of the end-effector in robot coordinates.
    private PlayerPoint3d basePos;

    /**
     * @return  The base position of the end-effector in robot coordinates. 
     **/
    public synchronized PlayerPoint3d getX () {
        return this.basePos;
    }

    /**
     * @param newBasePos The base position of the end-effector in robot coordinates. 
     *
     */
    public synchronized void setBasePos (PlayerPoint3d newBasePos) {
        this.basePos = newBasePos;
    }

}