/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pulsar.client.impl.auth;

import java.io.IOException;
import java.util.Map;

import org.apache.pulsar.client.api.Authentication;
import org.apache.pulsar.client.api.AuthenticationDataProvider;
import org.apache.pulsar.client.api.AuthenticationUtil;
import org.apache.pulsar.client.api.EncodedAuthenticationParameterSupport;
import org.apache.pulsar.client.api.PulsarClientException;

/**
 *
 * This plugin requires these parameters
 *
 * tlsCertFile: A file path for a client certificate. tlsKeyFile: A file path for a client private key.
 *
 */
public class AuthenticationTls implements Authentication, EncodedAuthenticationParameterSupport {

    private static final long serialVersionUID = 1L;

    private String certFilePath;
    private String keyFilePath;

    @Override
    public void close() throws IOException {
        // noop
    }

    @Override
    public String getAuthMethodName() {
        return "tls";
    }

    @Override
    public AuthenticationDataProvider getAuthData() throws PulsarClientException {
        try {
            return new AuthenticationDataTls(certFilePath, keyFilePath);
        } catch (Exception e) {
            throw new PulsarClientException(e);
        }
    }

    @Override
    public void configure(String encodedAuthParamString) {
        setAuthParams(AuthenticationUtil.configureFromPulsar1AuthParamString(encodedAuthParamString));
    }

    @Override
    @Deprecated
    public void configure(Map<String, String> authParams) {
        setAuthParams(authParams);
    }

    @Override
    public void start() throws PulsarClientException {
        // noop
    }

    private void setAuthParams(Map<String, String> authParams) {
        certFilePath = authParams.get("tlsCertFile");
        keyFilePath = authParams.get("tlsKeyFile");
    }

}
