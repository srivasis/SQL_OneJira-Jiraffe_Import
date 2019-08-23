
TO RUN THIS PROGRAM YOU MUST FIRST SETUP THE PROPERTIES FILE.

**IMP**
ENSURE THAT A SPACE IS PLACED BETWEEN THE ":" AFTER THE LABEL AND BEFORE THE INPUT SETTING
FOR EXAMPLE: Username: Batman

Username: [Active Directory Username for the Person Running this application]
OneJira_UNC_FilePath: \\TLPFILER\sharpregr\GAMIFI-SHARE\DAT\V01\ONEJIRA
Jiraffe_UNC_FilePath: \\TLPFILER\sharpregr\GAMIFI-SHARE\DAT\V01\JIRAFFE
OneJira_StartDate__FilePath: [There exists a OneJira_StartDate.txt in this folder, you need to provide the address to this file for example -> C:\MongoImport\SQL_Database_Setup\src\OneJira_StartDate.txt]
Jiraffe_StartDate_FilePath: [There exists a Jiraffe_StartDate.txt in this folder, you need to provide the address to this file for example -> C:\MongoImport\SQL_Database_Setup\src\Jiraffe_StartDate.txt]
SQL_Server_Driver: com.microsoft.sqlserver.jdbc.SQLServerDriver
SQL_Server_URL: jdbc:sqlserver://CRDBDATPORTD01\DEV01;Database=TTV01_GAMIFICATION;Integrated Security=SSIP;
SQL_Server_Username: [Username to connect to sql server]
SQL_Server_Password: [Password to connect to sql server]
Create_Tables: [Answer only in "true" or "false" if you wish to create tables in the Database]
Insert_Rows: [Answer only in "true" or "false" if you wish to insert rows into tables existing on the database]


ONCE THE PROPERTIES FILE IS COMPLETE RUN THE PROGRAM WITH A SINGLE PARAMETER 
THE PARAMETER IS THE PATH TO THE properties file FOR EXAMPLE -> C:\MongoImport\SQL_Database_Setup\src\properties