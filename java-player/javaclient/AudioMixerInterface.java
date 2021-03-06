/*
 *  Player Java Client - AudioMixerInterface.java
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
 * $Id: AudioMixerInterface.java 33 2006-02-14 17:56:06Z veedee $
 *
 */
package javaclient;

/**
 * The audiomixer interface is used to control sound levels.
 * @author Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v1.6.3 - Player 1.6.3 (all interfaces) supported
 *      <li>v1.6.2 - Player 1.6.2 supported, Javadoc documentation, several bugfixes  
 * </ul>
 */
public class AudioMixerInterface extends PlayerDevice {

    private static final boolean isDebugging = PlayerClient.isDebugging;
    
    /* audio I/O */
    private final short PLAYER_AUDIOMIXER_CODE = PlayerClient.PLAYER_AUDIOMIXER_CODE;

    /* the player message types (see player.h) */
    private static final short PLAYER_MSGTYPE_CMD = PlayerClient.PLAYER_MSGTYPE_CMD;
    private static final short PLAYER_MSGTYPE_REQ = PlayerClient.PLAYER_MSGTYPE_REQ;

    /* configuration subtypes */
    protected static final short PLAYER_AUDIOMIXER_SET_MASTER = 1;
    protected static final short PLAYER_AUDIOMIXER_SET_PCM    = 2;
    protected static final short PLAYER_AUDIOMIXER_SET_LINE   = 3;
    protected static final short PLAYER_AUDIOMIXER_SET_MIC    = 4;
    protected static final short PLAYER_AUDIOMIXER_SET_IGAIN  = 5;
    protected static final short PLAYER_AUDIOMIXER_SET_OGAIN  = 6;

    private int masterLeft, masterRight, pcmLeft, pcmRight, lineLeft, lineRight;
    private int micLeft, micRight, iGain, oGain;
    
    /**
     * Constructor for AudioMixerInterface.
     * @param pc a reference to the PlayerClient object
     * @param indexOfDevice the index of the device
     */
    public AudioMixerInterface (PlayerClient pc, short indexOfDevice) {
        super(pc);
        device    = PLAYER_AUDIOMIXER_CODE;
        index     = indexOfDevice;
    }

    /**
     * The audiomixer interface accepts commands to set the left and right volume 
     * levels of various channels. The channel may be PLAYER_AUDIOMIXER_MASTER for 
     * the master volume, PLAYER_AUDIOMIXER_PCM for the PCM volume, 
     * PLAYER_AUDIOMIXER_LINE for the line in volume, PLAYER_AUDIOMIXER_MIC for the 
     * microphone volume, PLAYER_AUDIOMIXER_IGAIN for the input gain, and 
     * PLAYER_AUDIOMIXER_OGAIN for the output gain.<br /><br />
     * See the player_audiomixer_cmd structure from player.h
     * @param subtype one of the types above
     * @param left value for the left channel
     * @param right value for the right channel
     */
    public void setVolume (byte subtype, short left, short right) {
        try {
            sendHeader (PLAYER_MSGTYPE_CMD, 5); /* 5 byte payload */
            os.writeByte  (subtype);            /* the packet subtype */
            os.writeShort (left);               /* left channel */ 
            os.writeShort (right);              /* right channel */ 
            os.flush ();
        } catch (Exception e) {
            System.err.println ("[AudioMixer] : Couldn't send new command: " +
                    e.toString ());
        }
    }

    /**
     * Configuration request: Get levels.
     * <br><br>
     * The audiomixer interface provides accepts a configuration request which 
     * returns the current state of the mixer levels<br /><br />
     * See the player_audiomixer_config structure from player.h
     */
    public void getLevels (byte subtype) {
        try {
            sendHeader (PLAYER_MSGTYPE_REQ, 1);     /* 1 byte payload */
// Not yet implemented in player!
            os.writeByte (subtype);
            os.flush ();
        } catch (Exception e) {
            System.err.println ("[AudioMixer] : Couldn't send command: " + e.toString ());
        }
    }
    
    /**
     * Handle acknowledgement response messages (threaded mode).
     * @param size size of the payload
     */
    public void handleResponse (int size) {
        if (size == 0) {
            if (isDebugging)
            	System.err.println ("[AudioMixer][Debug] : Unexpected response of size 0!");
            return;
        }
        try {
            /* each reply begins with a uint8_t subtype field */
            byte subtype = is.readByte ();
            switch (subtype) {
                default:{
                    masterLeft  = is.readUnsignedShort ();
                    masterRight = is.readUnsignedShort ();
                    pcmLeft     = is.readUnsignedShort ();
                    pcmRight    = is.readUnsignedShort ();
                    lineLeft    = is.readUnsignedShort ();
                    lineRight   = is.readUnsignedShort ();
                    micLeft     = is.readUnsignedShort ();
                    micRight    = is.readUnsignedShort ();
                    iGain       = is.readUnsignedShort ();
                    oGain       = is.readUnsignedShort ();
                	break;
                }
/*                default:{
                    System.err.println ("[AudioMixer] : Unexpected response " + subtype + 
                            " of size = " + size);
                    break;
                }*/
            }
        } catch (Exception e) {
            System.err.println ("[AudioMixer] : Error when reading payload " + e.toString ());
        }
    }

    public synchronized int getMasterLeft  () { return masterLeft;  }
    public synchronized int getMasterRight () { return masterRight; }
    public synchronized int getPCMLeft     () { return pcmLeft;     }
    public synchronized int getPCMRight    () { return pcmRight;    }
    public synchronized int getLineLeft    () { return lineLeft;    }
    public synchronized int getLineRight   () { return lineRight;   }
    public synchronized int getMicLeft     () { return micLeft;     }
    public synchronized int getMicRight    () { return micRight;    }
    public synchronized int getIGain       () { return iGain;       }
    public synchronized int getOGain       () { return oGain;       }
}
