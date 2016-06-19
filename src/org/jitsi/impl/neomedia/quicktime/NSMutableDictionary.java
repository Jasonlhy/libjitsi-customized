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
package org.jitsi.impl.neomedia.quicktime;

/**
 * Represents an Objective-C <tt>NSMutableDictionary</tt> object.
 *
 * @author Lyubomir Marinov
 */
public class NSMutableDictionary
    extends NSDictionary
{

    /**
     * Initializes a new <tt>NSMutableDictionary</tt> instance which is to
     * represent a new Objective-C <tt>NSMutableDictionary</tt> object.
     */
    public NSMutableDictionary()
    {
        this(allocAndInit());
    }

    /**
     * Initializes a new <tt>NSMutableDictionary</tt> instance which is to
     * represent a specific Objective-C <tt>NSMutableDictionary</tt> object.
     *
     * @param ptr the pointer to the Objective-C <tt>NSMutableDictionary</tt>
     * object to be represented by the new instance
     */
    public NSMutableDictionary(long ptr)
    {
        super(ptr);
    }

    private static native long allocAndInit();

    public void setIntForKey(int value, long key)
    {
        setIntForKey(getPtr(), value, key);
    }

    private static native void setIntForKey(long ptr, int value, long key);
}
