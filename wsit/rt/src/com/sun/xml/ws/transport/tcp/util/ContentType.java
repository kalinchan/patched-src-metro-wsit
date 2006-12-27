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
import com.sun.xml.ws.transport.tcp.resources.MessagesMessages;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alexey Stashok
 */
public final class ContentType {
    private final MimeType mimeType;
    private final Map<String, String> parameters;
    
    private ContentType(@NotNull final MimeType mimeType, @NotNull final Map<String, String> parameters) {
        this.mimeType = mimeType;
        this.parameters = parameters;
    }
    public MimeType getMimeType() {
        return mimeType;
    }
    
    public Map<String, String> getParameters() {
        return parameters;
    }
    
    public static ContentType createContentType(final String contentType) {
        final String[] entities = contentType.split(";");
        final String mimeTypeS = entities[0].trim().toLowerCase();
        final List<MimeType> mimeTypeList = MimeType.mimeName2mime.get(mimeTypeS);
        assert mimeTypeList != null;
        
        final Map<String, String> parameters = new HashMap<String, String>(4);
        for(MimeType mime : mimeTypeList) {
            int ctEmbedParamsAmount = mime.getEmbeddedParams().size();
            
            for(int i=1; i<entities.length; i++) {
                final String[] keyVal = entities[i].split("=");
                assert keyVal.length == 2;
                
                final String key = keyVal[0].trim();
                final String value = keyVal[1].trim();
                final String valToCompare = mime.getEmbeddedParams().get(key);
                if (valToCompare != null) {
                    if (valToCompare.equals(value)) {
                        ctEmbedParamsAmount--;
                    }
                } else {
                    parameters.put(key, value);
                }
            }
            
            if (ctEmbedParamsAmount == 0) {
                return new ContentType(mime, parameters);
            }
            
        }
        
        throw new AssertionError(MessagesMessages.WSTCP_0011_UNKNOWN_CONTENT_TYPE(contentType));
    }
    
    public static final class EncodedContentType {
        public final int mimeId;
        public final Map<Integer, String> params;
        
        public EncodedContentType(final int mimeId, final Map<Integer, String> params) {
            this.mimeId = mimeId;
            this.params = params;
        }
    }
}
