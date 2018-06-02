# Android-Save-Data-into-SQLite-Database-Server
 Android insert string data of Edittext into SQLite Database (Server) using Java and PHP.

 In this project, you need to make table inside database with 4 columns (id: INT & AUTO_INCREMENT, name: TEXT, email: TEXT, message: TEXT).
 
 Then, change some lines in 'submit.php'
 https://github.com/krdevdory/Android-Save-Data-into-SQLite-Database-Server/blob/master/submit.php
 
 In line 4, change 'Username'.
 In line 5, change 'Password'.
 In line 6, change 'Database name'.
 
 In line 14, change TABLE_NAME.
 
 And you need to add some codes in build.gradle(Module: app).
 Check build.gradle(Module: app) line 17.
 https://github.com/krdevdory/Android-Save-Data-into-SQLite-Database-Server/blob/master/app/build.gradle
 
 
 
 Finally change 'String server_url' in MainActivity(line 36).
 https://github.com/krdevdory/Android-Save-Data-into-SQLite-Database-Server/blob/master/app/src/main/java/com/devdory/dataintosql/MainActivity.java
 
