:: ȫ�����´��
:: ���´��jar

cd /d %~dp0/lss-admin
call mvn clean install

:: ��war�����Ƶ������Ŀ¼
cd /d %~dp0/dist 
:: del %~dp0\dist\*.war
rd /S/Q target
md target\
copy  %~dp0\lss-admin\target\lss-admin.war %~dp0\dist\
pause 
