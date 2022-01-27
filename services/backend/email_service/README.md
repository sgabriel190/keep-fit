# Email service

Email service implementation using Python3.

## Requirements

Included in the requirements.txt. Install those Python3 requirements by the following command.

```
    pip3 install -r requirements
```

## Installation 

The service is encapsulated into a Docker image. Please first build the image accordingly.

The script need two environmental variables:
* __EMAIL_NAME__ - the email name to be used for the SMTP service.
* __EMAIL_PASSWORD__ - the email password to be used for the SMTP service.

> In order to use this service please create an account on Gmail with this scope. The SMTP server used is by default gmail if you consider using something else please modify the __SMTPServer class__.

## Acknowledgment

Gmail account needs some settings in order to be successfully used as an SMTP email client. Refer to [this](https://github.com/matomo-org/matomo/issues/8613).