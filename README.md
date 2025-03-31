# Email Server Project

This project implements a fully functional email server that supports the following protocols:

- **POP3**: For receiving emails.
- **IMAP**: For email synchronization across devices.
- **SMTP**: For sending emails.

## Features

- **Security**: Incorporates SSL/TLS and STARTTLS for secure connections.
- **Directory Access**: Utilizes LDAP for retrieving email contacts.
- **Calendar and Contact Synchronization**: Implements CalDAV and CardDAV for managing calendar events and contact information.

## Project Structure

```
email-server
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── emailserver
│   │   │           ├── pop3
│   │   │           │   └── Pop3Server.java
│   │   │           ├── imap
│   │   │           │   └── ImapServer.java
│   │   │           ├── smtp
│   │   │           │   └── SmtpServer.java
│   │   │           ├── security
│   │   │           │   └── SecurityManager.java
│   │   │           ├── ldap
│   │   │           │   └── LdapManager.java
│   │   │           ├── caldav
│   │   │           │   └── CalDavManager.java
│   │   │           └── carddav
│   │   │               └── CardDavManager.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       ├── java
│       │   └── com
│       │       └── emailserver
│       │           ├── pop3
│       │           │   └── Pop3ServerTest.java
│       │           ├── imap
│       │           │   └── ImapServerTest.java
│       │           ├── smtp
│       │           │   └── SmtpServerTest.java
│       │           ├── security
│       │           │   └── SecurityManagerTest.java
│       │           ├── ldap
│       │           │   └── LdapManagerTest.java
│       │           ├── caldav
│       │           │   └── CalDavManagerTest.java
│       │           └── carddav
│       │               └── CardDavManagerTest.java
├── pom.xml
└── README.md
```

