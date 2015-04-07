@echo off
chcp 936
:begin
echo.
echo 1. 批量生成
echo.
echo 2. 单条生成

set /p c=请输入（1、2）
if "%c%"=="1" (
java -cp codeMaker_fat.jar  org.gaofeng.main.RunBatch
)
if "%c%"=="2" (
java -cp codeMaker_fat.jar  org.gaofeng.main.RunSingle
)
goto begin
