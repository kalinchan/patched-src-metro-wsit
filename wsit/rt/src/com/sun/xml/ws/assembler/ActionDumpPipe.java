/*
 The contents of this file are subject to the terms
 of the Common Development and Distribution License
 (the "License").  You may not use this file except
 in compliance with the License.

 You can obtain a copy of the license at
 https://jwsdp.dev.java.net/CDDLv1.0.html
 See the License for the specific language governing
 permissions and limitations under the License.

 When distributing Covered Code, include this CDDL
 HEADER in each file and include the License file at
 https://jwsdp.dev.java.net/CDDLv1.0.html  If applicable,
 add the following below this CDDL HEADER, with the
 fields enclosed by brackets "[]" replaced with your
 own identifying information: Portions Copyright [yyyy]
 [name of copyright owner]
*/
/*
 $Id: ActionDumpPipe.java,v 1.2 2006-10-05 00:09:21 arungupta Exp $

 Copyright (c) 2006 Sun Microsystems, Inc.
 All rights reserved.
*/

package com.sun.xml.ws.assembler;

import com.sun.xml.ws.api.WSBinding;
import com.sun.xml.ws.api.message.Message;
import com.sun.xml.ws.api.message.Packet;
import com.sun.xml.ws.api.pipe.Pipe;
import com.sun.xml.ws.api.pipe.PipeCloner;
import com.sun.xml.ws.api.pipe.helper.AbstractFilterPipeImpl;

/**
 * @author Arun Gupta
 */
public class ActionDumpPipe extends AbstractFilterPipeImpl {
    private final String name;
    private final WSBinding binding;

    public ActionDumpPipe(WSBinding binding, Pipe next) {
        this("ActionDumpPipe", binding, next);
    }

    public ActionDumpPipe(String name, WSBinding binding, Pipe next) {
        super(next);
        this.name = name;
        this.binding = binding;
    }

    /**
     * Copy constructor.
     */
    private ActionDumpPipe(ActionDumpPipe that, PipeCloner cloner) {
        super(that, cloner);
        this.name = that.name;
        this.binding = that.binding;
    }

    public Packet process(Packet packet) {
        dump(packet);
        Packet reply = next.process(packet);
        dump(reply);
        return reply;
    }

    protected void dump(Packet packet) {
        if (packet.getMessage() != null)
            dumpAction(packet);
    }

    protected void dumpAction(Packet packet) {
        try {
            Message m = packet.getMessage().copy();

            String to = m.getHeaders().getTo(binding.getAddressingVersion(), binding.getSOAPVersion());
            String action = m.getHeaders().getAction(binding.getAddressingVersion(), binding.getSOAPVersion());

            System.out.println("{To, Action}: {" + to + ", " + action + "}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pipe copy(PipeCloner cloner) {
        return new ActionDumpPipe(this, cloner);
    }
}
