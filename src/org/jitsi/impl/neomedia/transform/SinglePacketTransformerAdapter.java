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
package org.jitsi.impl.neomedia.transform;

import org.jitsi.impl.neomedia.*;
import org.jitsi.util.function.*;

/**
 *
 * @author Lyubomir Marinov
 */
public class SinglePacketTransformerAdapter
    extends SinglePacketTransformer
{
    /**
     * Ctor.
     */
    public SinglePacketTransformerAdapter()
    {
        super();
    }

    /**
     * Ctor.
     *
     * @param packetPredicate the <tt>PacketPredicate</tt> to use to match
     * packets to (reverse) transform.
     */
    public SinglePacketTransformerAdapter(Predicate<RawPacket> packetPredicate)
    {
        super(packetPredicate);
    }

    @Override
    public RawPacket reverseTransform(RawPacket pkt)
    {
        return pkt;
    }

    @Override
    public RawPacket transform(RawPacket pkt)
    {
        return pkt;
    }
}
