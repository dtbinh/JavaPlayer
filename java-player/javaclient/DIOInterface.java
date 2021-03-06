/*
 *  Player Java Client - DIOInterface.java
 *  Copyright (C) 2003-2005 Maxim A. Batalin & Radu Bogdan Rusu
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
 * $Id: DIOInterface.java 10 2005-05-10 12:10:24Z veedee $
 *
 */
package javaclient;

/**
 * The dio interface provides access to a digital I/O device.
 * @author Maxim A. Batalin & Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v1.6.3 - Player 1.6.3 (all interfaces) supported
 *      <li>v1.6.2 - Player 1.6.2 supported, Javadoc documentation, several bugfixes  
 *      <li>v1.5a &nbsp;- Player 1.5 supported (most popular devices)
 * </ul>
 */
public class DIOInterface extends PlayerDevice {
	
    private final short PLAYER_DIO_CODE = PlayerClient.PLAYER_DIO_CODE;   /* digital I/O */
    
    /* the player message types (see player.h) */
    private final short PLAYER_MSGTYPE_CMD = PlayerClient.PLAYER_MSGTYPE_CMD;
    
    private int count    = 0;       /* number of valid samples */
    private int bitField = 0;       /* bitfield of samples */ 
    /**
     * Constructor for DIOInterface.
     * @param pc a reference to the PlayerClient object
     * @param indexOfDevice the index of the device
     */
    public DIOInterface (PlayerClient pc, short indexOfDevice) {
        super(pc);
        device    = PLAYER_DIO_CODE;
        index     = indexOfDevice;
    }
    
    /**
     * Read the samples values.
     */
    public synchronized void readData () {
        readHeader ();
        try {
            count = is.readUnsignedByte ();           /* number of valid samples  */
            bitField = is.readInt ();                 /* bitfield of samples */
        } catch (Exception e) {
            System.err.println ("[DIO] : Error when reading payload: " + e.toString ());
        }
    }
    
    /**
     * Returns the number of valid samples
     * @return the number of valid samples as a byte
     */
    public synchronized byte getCount    () { return (byte)count; }
    
    /**
     * Returns the bitfield of samples.
     * @return the bitfield of samples as an integer
     */
    public synchronized int  getBitField () { return bitField;    }
    
    /**
     * The dio interface accepts 4-byte commands which consist of the ouput bitfield.
     * @param count the command
     * @param digout the output bitfield
     */
    public void setOutputBitfield (byte count, int digout) {
        try {
            sendHeader (PLAYER_MSGTYPE_CMD, 5);       /* 5 bytes payload */
            os.writeByte (count);
            os.writeInt  (digout);
            os.flush ();
        } catch (Exception e) {
            System.err.println ("[DIO] : Couldn't send output bitfield command request: " + 
                    e.toString ());
        }
    }
    
}
