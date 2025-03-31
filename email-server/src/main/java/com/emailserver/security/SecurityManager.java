package com.emailserver.security;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;
import java.security.KeyStore;

public class SecurityManager {

    private SSLContext sslContext;

    public SecurityManager(String keystorePath, String keystorePassword) throws Exception {
        initializeSSLContext(keystorePath, keystorePassword);
    }

    private void initializeSSLContext(String keystorePath, String keystorePassword) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (FileInputStream keyStoreStream = new FileInputStream(keystorePath)) {
            keyStore.load(keyStoreStream, keystorePassword.toCharArray());
        }

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

        sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagers, null);
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return sslContext.getSocketFactory();
    }

    public SSLSocket createSecureSocket(String host, int port) throws Exception {
        SSLSocketFactory factory = getSSLSocketFactory();
        return (SSLSocket) factory.createSocket(host, port);
    }

    public void enableStartTLS(SSLSocket socket) throws Exception {
        socket.startHandshake();
    }
}