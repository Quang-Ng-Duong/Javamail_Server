package com.emailserver.ldap;

import javax.naming.directory.*;
import javax.naming.*;
import java.util.Hashtable;

public class LdapManager {
    private String ldapUrl;
    private String baseDn;
    private String userDn;
    private String password;
    private DirContext context;

    public LdapManager(String ldapUrl, String baseDn, String userDn, String password) {
        this.ldapUrl = ldapUrl;
        this.baseDn = baseDn;
        this.userDn = userDn;
        this.password = password;
        connect();
    }

    private void connect() {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapUrl);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, userDn);
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            context = new InitialDirContext(env);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public NamingEnumeration<SearchResult> search(String searchFilter) {
        try {
            return context.search(baseDn, searchFilter, new SearchControls());
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            if (context != null) {
                context.close();
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}