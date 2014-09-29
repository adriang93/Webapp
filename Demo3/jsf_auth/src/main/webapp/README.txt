    
*** README ***

This uses the "official" JEE style for authentication but ....
 ... we use the file realm (internal to GlassFish).
Simple for development and testing but should use a jdbc realm (database).
For a jdbc realm example see jpa/jpa_auth

Users and roles handled by container
------------------------------------
(users and roles in application will be mapped to these)

Start GlassFish. Start Admin console (Servers > GlassFish, right click > 
View Domain Admin Console) or go to http://localhost:4848

Goto: Configurations > server-config > Security > Realms > File > Manage users 
>  New (then OK)

UserId  Group      Passwd
pelle   userGroup  111
fia     adminGroup 111

Config files
-------------
A lot in web.xml, glassfish-web.xml and faces-config.xml

GlassFish settings 
------------------
See folder glassfish


