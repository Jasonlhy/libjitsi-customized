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
package org.jitsi.impl.neomedia.codec.audio.silk;

import static org.jitsi.impl.neomedia.codec.audio.silk.Define.*;

/**
 *
 * @author Jing Dai
 * @author Dingxin Xu
 */
public class LBRRReset
{
    /**
     * Resets LBRR buffer, used if packet size changes.
     *
     * @param psEncC state
     */
    static void SKP_Silk_LBRR_reset(
        SKP_Silk_encoder_state      psEncC             /* I/O  state                                       */
    )
    {
        int i;

        for( i = 0; i < MAX_LBRR_DELAY; i++ ) {
            psEncC.LBRR_buffer[ i ].usage = SKP_SILK_NO_LBRR;
        }
    }
}
