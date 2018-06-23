This is a console whois-client. It connects to a whois-server and requests information about the domain you specified.  
It uses the NICNAME/WHOIS protocol according to RFC 954.  
How to use: `java -cp [classpath] by.iyalchyk.Run [whois-server] [domain_request]`  
Example: `java -cp target/WhoisClient-1.0-SNAPSHOT.jar by.iyalchyk.Run whois.nic.ru yandex.ru`  
Example output:
```
domain:       YANDEX.RU
nserver:      ns1.yandex.ru.
nserver:      ns2.yandex.ru.
nserver:      ns9.z5h64q92x9.net
state:        REGISTERED, DELEGATED
admin-contact:https://www.nic.ru/cgi/whois_webmail.cgi?domain=YANDEX.RU
org:          YANDEX, LLC.
registrar:    RU-CENTER-RU
created:      1997.09.23
paid-till:    2018.10.01
source:       RU-CENTER

>>> Last update of WHOIS database: 2018.06.23T21:24:02Z <<<

```