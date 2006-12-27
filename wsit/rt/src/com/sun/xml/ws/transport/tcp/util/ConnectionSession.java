/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License).  You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the license at
 * https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * you own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2006 Sun Microsystems Inc. All Rights Reserved
 */

package com.sun.xml.ws.transport.tcp.util;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import com.sun.xml.ws.transport.tcp.io.Connection;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexey Stashok
 */
public final class ConnectionSession {
    private static final ChannelSettings zeroChannelSettings = new ChannelSettings();
    
    private Map<Integer, ChannelContext> channelId2context;
    
    private Map<String, Object> attributes;
    
    private int channelCounter;
    
    private Connection connection;
    private final int dstAddressHashKey;
    
    private boolean isClosed;
    
    public ConnectionSession(final Connection connection) {
        this(-1, connection);
    }
    
    public ConnectionSession(final int dstAddressHashKey, final Connection connection) {
        this.dstAddressHashKey = dstAddressHashKey;
        this.connection = connection;
        channelId2context = new HashMap<Integer, ChannelContext>();
        attributes = new HashMap<String, Object>(1);
        channelCounter = 1;
        initServiceChannel();
    }
    
    private void initServiceChannel() {
        final ChannelContext zeroChannelContext = new ChannelContext(this, zeroChannelSettings);
        channelId2context.put(0, zeroChannelContext);
    }
    
    public void registerChannel(final int channelId, @NotNull final ChannelContext context) {
        channelId2context.put(channelId, context);
    }
    
    public ChannelContext findWSServiceContextByChannelId(final int channelId) {
        return channelId2context.get(channelId);
    }
    
    public void deregisterChannel(final int channelId) {
        channelId2context.remove(channelId);
    }
    
    public void abort() {
        synchronized(this) {
            if (isClosed) return;
            isClosed = true;
        }

        try {
            connection.close();
        } catch (IOException ex) {
        }
        close();
    }
    
    public void setAttribute(@NotNull final String name, final Object value) {
        attributes.put(name, value);
    }
    
    public @Nullable Object getAttribute(@NotNull final String name) {
        return attributes.get(name);
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public int getDstAddressHashKey() {
        return dstAddressHashKey;
    }
    
    public int getChannelsAmount() {
        return channelId2context.size();
    }
    
    public synchronized int getNextAvailChannelId() {
        return channelCounter++;
    }
    
    private void close() {
        attributes = null;
        channelId2context = null;
        connection = null;
    }
}
