/*
 *  Player Java Client - SpeechRecognitionInterface.java
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
 * $Id: SpeechRecognitionInterface.java 10 2005-05-10 12:10:24Z veedee $
 *
 */
package javaclient;

/**
 * The speech recognition interface provides access to a speech recognition server. 
 * @author Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v1.6.3 - Player 1.6.3 (all interfaces) supported
 *      <li>v1.6.2 - Player 1.6.2 supported, Javadoc documentation, several bugfixes  
 * </ul>
 */
public class SpeechRecognitionInterface extends PlayerDevice {
    
    /* speech recognition I/O */
    private final short PLAYER_SPEECH_RECOGNITION_CODE = PlayerClient.PLAYER_SPEECH_RECOGNITION_CODE;

    public static final short SPEECH_RECOGNITION_TEXT_LEN = 256; 
    
    /* the speech recognition data packet */
    private char[] text = new char[SPEECH_RECOGNITION_TEXT_LEN]; 
    
    /**
     * Constructor for SpeechRecognitionInterface.
     * @param pc a reference to the PlayerClient object
     * @param indexOfDevice the index of the device
     */
    public SpeechRecognitionInterface (PlayerClient pc, short indexOfDevice) {
        super(pc);
        device    = PLAYER_SPEECH_RECOGNITION_CODE;
        index     = indexOfDevice;
    }

    /**
     * Read the speech recognition data packet.
     */
    public synchronized void readData () {
        readHeader ();
        try {
            for (int i = 0; i < SPEECH_RECOGNITION_TEXT_LEN; i++)
                text[i] = is.readChar ();              /* the speech recognition data packet */
        } catch (Exception e) {
            System.err.println ("[SpeechRecognition] : Error when reading payload: " + e.toString ());
        }
    }

    /**
     * Returns the speech recognition data packet.
     * @return an array of up to SPEECH_RECOGNITION_TEXT_LEN chars filled with data
     */
    public synchronized char[] getText () { return this.text; }
}
