@ECHO ON
set arg1=%1
set arg2=%2
cd %arg2%
instaloader --fast-update profile %arg1%