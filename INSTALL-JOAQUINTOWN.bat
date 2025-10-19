@echo off
REM Joaquintown Mod - Windows Installer Launcher
REM This batch file launches the PowerShell installer with proper permissions

title Joaquintown Mod Installer

echo.
echo ========================================
echo   Joaquintown Mod Installer
echo ========================================
echo.
echo Starting installation...
echo.

REM Check if PowerShell is available
where powershell >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: PowerShell not found!
    echo PowerShell is required to run this installer.
    echo.
    echo Please install PowerShell or run the installer manually.
    pause
    exit /b 1
)

REM Run PowerShell script with execution policy bypass
REM This allows the script to run without changing system-wide PowerShell settings
powershell.exe -ExecutionPolicy Bypass -NoProfile -File "%~dp0install-joaquintown.ps1"

REM Check if the script ran successfully
if %ERRORLEVEL% EQU 0 (
    echo.
    echo Installation completed successfully!
) else (
    echo.
    echo Installation encountered an error.
    echo Please check the output above for details.
)

echo.
pause
