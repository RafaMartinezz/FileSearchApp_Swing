;Utilizamos Modern UI
!include "MUI2.nsh"

;Estructura General
Name "Creacion del instalador"; Título del instalador
OutFile "Fuentes.exe" ; Fichero que vamos a generar y utilizar como instalador
Unicode True
InstallDir "$PROGRAMFILES\fuentes";Carpeta donde se instalará la aplicación por defecto
InstallDirRegKey HKCU "1" "";Definimos la clave en el registro
RequestExecutionLevel admin ;Privilegios para el instalador. Admite los valores user o admin.
!define MUI_ABORTWARNING

;Definimos las Paginas o ventanas
!insertmacro MUI_PAGE_COMPONENTS
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_INSTFILES
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES

;Configuramos el idioma del instalador
!insertmacro MUI_LANGUAGE "Spanish"

;Definimos el componente que se va a poder señalar para instalar
Section "Fichero jar" SecDummy
SetOutPath $INSTDIR
File Fuentes_Tarea_U07.jar
SetOutPath $INSTDIR\lib ;Creamos la carpeta lib en el directorio en donde se va a instalar la aplicación.
File lib\swing-layout-1.0.4.jar ;copiamos el contenido
;Store installation folder
WriteRegStr HKCU "1" "" $INSTDIR
;Creamos el desinstalador
WriteUninstaller "$INSTDIR\Uninstall.exe"; Definimos la opción de desinstalar
SectionEnd


;Descripciones que aparecerán junto al componente cuando se selecciones. Aparece junto al componente.
;Language strings
LangString DESC_SecDummy ${LANG_SPANISH} "Instalación del fichero Tarea_U07-jar"
!insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
!insertmacro MUI_DESCRIPTION_TEXT ${SecDummy} $(DESC_SecDummy)
!insertmacro MUI_FUNCTION_DESCRIPTION_END
;Definimos la sección para desinstalar.
Section "Desinstalar"
 Delete "SINSTDIR\Uninstall.exe"
 RMDir "SINSTDIR"
 DeleteRegKey /ifempty HKCU "1"
SectionEnd


