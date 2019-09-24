:: 全部重新打包
:: 重新打包jar

cd /d %~dp0/lss-admin
call mvn clean install

:: 将war包复制到部署包目录
cd /d %~dp0/dist 
:: del %~dp0\dist\*.war
rd /S/Q target
md target\
copy  %~dp0\lss-admin\target\lss-admin.war %~dp0\dist\
pause 
