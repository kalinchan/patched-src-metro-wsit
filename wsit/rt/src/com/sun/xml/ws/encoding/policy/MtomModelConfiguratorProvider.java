/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2008 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.xml.ws.encoding.policy;

import com.sun.xml.ws.api.model.wsdl.WSDLModel;
import com.sun.xml.ws.api.model.wsdl.WSDLPort;
import com.sun.xml.ws.api.model.wsdl.WSDLService;
import com.sun.xml.ws.policy.AssertionSet;
import com.sun.xml.ws.policy.Policy;
import com.sun.xml.ws.policy.PolicyAssertion;
import com.sun.xml.ws.policy.PolicyException;
import com.sun.xml.ws.policy.PolicyMap;
import com.sun.xml.ws.policy.PolicyMapKey;
import com.sun.xml.ws.policy.jaxws.spi.ModelConfiguratorProvider;
import java.util.Iterator;
import javax.xml.ws.soap.MTOMFeature;

import static com.sun.xml.ws.encoding.policy.EncodingConstants.OPTIMIZED_MIME_SERIALIZATION_ASSERTION;

/**
 *
 * @author japod
 */
public class MtomModelConfiguratorProvider implements ModelConfiguratorProvider{
    /**
     * Creates a new instance of MtomModelConfiguratorProvider
     */
    public MtomModelConfiguratorProvider() {
    }
    
    /**
     * process Mtom policy assertions and if found and is not optional then mtom is enabled on the
     * {@link WSDLBoundPortType}
     *
     * @param model must be non-null
     * @param policyMap must be non-null
     */
    public void configure(WSDLModel model, PolicyMap policyMap) throws PolicyException {
        if ((null==model) ||(null==policyMap)) {
            return;
        }
        for (WSDLService service:model.getServices().values()) {
            for (WSDLPort port : service.getPorts()) {
                PolicyMapKey key = PolicyMap.createWsdlEndpointScopeKey(service.getName(),port.getName());
                Policy policy = policyMap.getEndpointEffectivePolicy(key);
                if (null!=policy && policy.contains(OPTIMIZED_MIME_SERIALIZATION_ASSERTION)) {
                    Iterator <AssertionSet> assertions = policy.iterator();
                    while(assertions.hasNext()){
                        AssertionSet assertionSet = assertions.next();
                        Iterator<PolicyAssertion> policyAssertion = assertionSet.iterator();
                        while(policyAssertion.hasNext()){
                            PolicyAssertion assertion = policyAssertion.next();
                            if(OPTIMIZED_MIME_SERIALIZATION_ASSERTION.equals(assertion.getName())){
                                port.getBinding().addFeature(new MTOMFeature(true));
                            } // end-if non optional mtom assertion found
                        } // next assertion
                    } // next alternative
                } // end-if policy contains mtom assertion
            } // end foreach port
        } // end foreach service
    }
}