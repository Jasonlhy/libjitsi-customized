/*
 * Copyright @ 2015 Atlassian Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jitsi.impl.neomedia.rtcp;

import java.io.*;

import net.sf.fmj.media.rtp.*;

import org.jitsi.impl.neomedia.*;

/**
 * Created by gp on 6/27/14.
 *
 * 0                   1                   2                   3
 * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |V=2|P|   FMT   |       PT      |          length               |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                  SSRC of packet sender                        |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                  SSRC of media source                         |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * :            Feedback Control Information (FCI)                 :
 * :                                                               :
 */
public class RTCPFBPacket
    extends RTCPPacket
{
    public static final int RTPFB = 205;

    public static final int PSFB = 206;

    public byte[] fci;

    /**
     * Feedback message type (FMT).
     */
    public int fmt;

    /**
     * SSRC of packet sender.
     */
    public long senderSSRC;

    /**
     * SSRC of media source.
     */
    public long sourceSSRC;

    public RTCPFBPacket(int fmt, int type, long senderSSRC, long sourceSSRC)
    {
        super.type = type;

        this.fmt = fmt;
        this.senderSSRC = senderSSRC;
        this.sourceSSRC = sourceSSRC;
    }

    public RTCPFBPacket(RTCPCompoundPacket base)
    {
        super(base);
    }

    @Override
    public void assemble(DataOutputStream dataoutputstream) throws IOException
    {
        int len = this.calcLength();
        byte[] buf = new byte[len];
        int off = 0;

        /*
         * version (V): (2 bits):   This field identifies the RTP version.  The
         *     current version is 2.
         * padding (P) (1 bit):   If set, the padding bit indicates that the
         *     packet contains additional padding octets at the end that
         *     are not part of the control information but are included
         *     in the length field.  Always 0.
         * Feedback message type (FMT) (5 bits):  This field identifies the type
         *     of the FB message and is interpreted relative to the type
         *     (transport layer, payload- specific, or application layer
         *     feedback).  Always 15, application layer feedback
         *     message.
         */
        buf[off++] = (byte) (0x80 | fmt);

        /*
         * Payload type (PT) (8 bits):   This is the RTCP packet type that
         *     identifies the packet as being an RTCP FB message.
         *     Always PSFB (206), Payload-specific FB message.
         */
        buf[off++] = (byte) (0xCE);

        // Length (16 bits):  The length of this packet in 32-bit words minus
        // one, including the header and any padding. This is in
        // line with the definition of the length field used in RTCP
        // sender and receiver reports
        int rtcpPacketLength = len / 4 - 1;
        buf[off++] = (byte) ((rtcpPacketLength & 0xFF00) >> 8);
        buf[off++] = (byte) (rtcpPacketLength & 0x00FF);

        // SSRC of packet sender: 32 bits
        RTCPFeedbackMessagePacket.writeSSRC(senderSSRC, buf, off);
        off += 4;

        //  SSRC of media source: 32 bits
        RTCPFeedbackMessagePacket.writeSSRC(sourceSSRC, buf, off);
        off += 4;

        if (fci != null && fci.length != 0)
        {
            // Feedback Control Information (FCI): variable length
            System.arraycopy(fci, 0, buf, off, fci.length);
        }

        dataoutputstream.write(buf, 0, len);
    }

    @Override
    public int calcLength()
    {
        // Length (16 bits):  The length of this packet in 32-bit words minus
        // one, including the header and any padding.

        int len = 12; // header+ssrc+ssrc
        if (fci != null && fci.length != 0)
            len += fci.length;

        return len;
    }

    @Override
    public String toString()
    {
        return "\tRTCP FB packet from sync source " + senderSSRC;
    }
}
