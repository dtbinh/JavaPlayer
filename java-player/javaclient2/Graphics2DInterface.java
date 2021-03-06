/*
 *  Player Java Client 2 - Graphics2DInterface.java
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
 * $Id: Graphics2DInterface.java 70 2006-06-30 10:18:07Z veedee $
 *
 */
package javaclient2;

import java.io.IOException;

import javaclient2.xdr.OncRpcException;
import javaclient2.xdr.XdrBufferEncodingStream;
import javaclient2.structures.PlayerMsgHdr;
import javaclient2.structures.graphics2d.PlayerGraphics2dCmdPoints;
import javaclient2.structures.graphics2d.PlayerGraphics2dCmdPolyline;
import javaclient2.structures.graphics2d.PlayerGraphics2dCmdPolygon;

/**
 * The graphics2d interface provides an interface to graphics devices. Drivers 
 * can implement this interface to provide clients and other drivers with 
 * graphics output. For example, Stage models present this interface to allow 
 * clients to draw in the Stage window.
 * @author Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v2.0 - Player 2.0 supported
 * </ul>
 */
public class Graphics2DInterface extends PlayerDevice {

    /**
     * Constructor for Graphics2DInterface.
     * @param pc a reference to the PlayerClient object
     */
    public Graphics2DInterface (PlayerClient pc) { super(pc); }
    
    /**
     * This interface produces no data.
     */
    public synchronized void readData (PlayerMsgHdr header) { }
    
    /**
     * Requests: This interface accepts no requests.
     * @param header Player header
     */
    public void handleResponse (PlayerMsgHdr header) { }
    
    
    /**
     * Command: Clear screen (PLAYER_GRAPHICS2D_CMD_CLEAR).
     * <br><br>
     */
    public void clearScreen () {
        try {
        	sendHeader (PLAYER_MSGTYPE_CMD, PLAYER_GRAPHICS2D_CMD_CLEAR, 0);
        	os.flush ();
        } catch (IOException e) {
        	throw new PlayerException 
        		("[Graphics2D] : Couldn't send clear screen command: " + 
        				e.toString(), e);
        }
    }
    
    /**
     * Command: Draw points (PLAYER_GRAPHICS2D_CMD_POINTS).
     * <br><br>
     * Draw some points.
     * @param pgcp a PlayerGraphics2dCmdPoints structure holding the required 
     * data
     */
    public void drawPoints (PlayerGraphics2dCmdPoints pgcp) {
        try {
        	int points = pgcp.getCount ();
        	if (points > 64) points = 64;
        	int size = 4 + 4 + (points * 8) + 16; 
        	sendHeader 
        		(PLAYER_MSGTYPE_CMD, PLAYER_GRAPHICS2D_CMD_POINTS, size);
        	XdrBufferEncodingStream xdr = new XdrBufferEncodingStream (size);
        	xdr.beginEncoding (null, 0);
        	xdr.xdrEncodeInt   (points);;
        	xdr.xdrEncodeInt   (points);;
        	for (int i = 0; i < points; i++) {
        		xdr.xdrEncodeFloat (pgcp.getPoints ()[i].getPx ());
        		xdr.xdrEncodeFloat (pgcp.getPoints ()[i].getPy ());
        	}
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getColor ().getAlpha () & 0x000000FF));
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getColor ().getRed   () & 0x000000FF));
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getColor ().getGreen () & 0x000000FF));
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getColor ().getBlue  () & 0x000000FF));
        	xdr.endEncoding ();
        	
        	os.write (xdr.getXdrData (), 0, xdr.getXdrLength ());
        	xdr.close ();
        	os.flush ();
        } catch (IOException e) {
        	throw new PlayerException 
        		("[Graphics2D] : Couldn't send draw command: " + 
        				e.toString(), e);
        } catch (OncRpcException e) {
        	throw new PlayerException 
        		("[Graphics2D] : Error while XDR-encoding draw command: " + 
        				e.toString(), e);
        }
    }

    /**
     * Command: Draw polyline (PLAYER_GRAPHICS2D_CMD_POLYLINE).
     * <br><br>
     * Draw a series of straight line segments between a set of points.
     * @param pgcp a PlayerGraphics2dCmdPolyline structure holding the required 
     * data
     */
    public void drawPolyline (PlayerGraphics2dCmdPolyline pgcp) {
        try {
        	int points = pgcp.getCount ();
        	if (points > 64) points = 64;
        	int size = 4 + 4 + (points * 8) + 16;
        	sendHeader 
        		(PLAYER_MSGTYPE_CMD, PLAYER_GRAPHICS2D_CMD_POLYLINE, size);
        	XdrBufferEncodingStream xdr = new XdrBufferEncodingStream (size);
        	xdr.beginEncoding (null, 0);
        	xdr.xdrEncodeInt   (points);
        	xdr.xdrEncodeInt   (points);
        	for (int i = 0; i < points; i++) {
        		xdr.xdrEncodeFloat (pgcp.getPoints ()[i].getPx ());
        		xdr.xdrEncodeFloat (pgcp.getPoints ()[i].getPy ());
        	}
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getColor ().getAlpha () & 0x000000FF));
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getColor ().getRed   () & 0x000000FF));
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getColor ().getGreen () & 0x000000FF));
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getColor ().getBlue  () & 0x000000FF));
        	xdr.endEncoding ();
        	
        	os.write (xdr.getXdrData (), 0, xdr.getXdrLength ());
        	xdr.close ();
        	os.flush ();
        } catch (IOException e) {
        	throw new PlayerException 
        		("[Graphics2D] : Couldn't send draw command: " + 
        				e.toString(), e);
        } catch (OncRpcException e) {
        	throw new PlayerException 
        		("[Graphics2D] : Error while XDR-encoding draw command: " + 
        				e.toString(), e);
        }
    }
    
    /**
     * Command: Draw polygon (PLAYER_GRAPHICS2D_CMD_POLYGON)
     * <br><br>
     * Draw a polygon.
     * @param pgcp a PlayerGraphics2dCmdPolygon structure holding the required 
     * data
     */
    public void drawPolygon (PlayerGraphics2dCmdPolygon pgcp) {
        try {
        	int points = pgcp.getCount ();
        	if (points > 64) points = 64;
        	int size = 4 + 4 + (points * 8) + 16 + 16 + 4; 
        	sendHeader 
        		(PLAYER_MSGTYPE_CMD, PLAYER_GRAPHICS2D_CMD_POLYGON, size);
        	XdrBufferEncodingStream xdr = new XdrBufferEncodingStream (size);
        	xdr.beginEncoding (null, 0);
        	xdr.xdrEncodeInt   (points);;
        	xdr.xdrEncodeInt   (points);;
        	for (int i = 0; i < points; i++) {
        		xdr.xdrEncodeFloat (pgcp.getPoints ()[i].getPx ());
        		xdr.xdrEncodeFloat (pgcp.getPoints ()[i].getPy ());
        	}
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getColor ().getAlpha () & 0x000000FF));
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getColor ().getRed   () & 0x000000FF));
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getColor ().getGreen () & 0x000000FF));
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getColor ().getBlue  () & 0x000000FF));
        	
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getFill_color ().getAlpha () & 0x000000FF));
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getFill_color ().getRed   () & 0x000000FF));
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getFill_color ().getGreen () & 0x000000FF));
        	xdr.xdrEncodeByte 
        		((byte)(pgcp.getFill_color ().getBlue  () & 0x000000FF));
        	
        	xdr.xdrEncodeByte (pgcp.getFilled ());
        	xdr.endEncoding ();
        	
        	os.write (xdr.getXdrData (), 0, xdr.getXdrLength ());
        	xdr.close ();
        	os.flush ();
        } catch (IOException e) {
        	throw new PlayerException 
        		("[Graphics2D] : Couldn't send draw command: " + 
        				e.toString(), e);
        } catch (OncRpcException e) {
        	throw new PlayerException 
        		("[Graphics2D] : Error while XDR-encoding draw command: " + 
        				e.toString(), e);
        }
    }
}
