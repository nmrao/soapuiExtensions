soapuiExtensions
================
This soapuiExtensions library allows users to have some additional functionality in soapUI(free edition) tool, like soapui pro allows to do something before, after doing something.

For eg: User may want to do something before running a test case or after running a test case etc by implementing appropriate groovy script as required. Allow me to add an example here. Usually user may want to add credentials for the request step automatically, see the script [_samples/scripts/TestSuiteTestStepAdded.groovy_](https://github.com/nmrao/soapuiExtensions/blob/master/samples/scripts/TestSuiteTestStepAdded.groovy)

### How to use this library: 
set _SOAPUI_HOME_ environment variable. 
- copy _[lib/SoapUIExtListeners.jar](https://github.com/nmrao/soapuiExtensions/blob/master/lib/SoapUIExtListeners.jar)_ file under $SOAPUI_HOME/bin/ext directory 
- copy _[samples/listeners/custom-listeners.xml](https://github.com/nmrao/soapuiExtensions/blob/master/samples/listeners/custom-listeners.xml)_ file under $SOAPUI_HOME/bin/listeners directory 
- copy _[samples/scripts](https://github.com/nmrao/soapuiExtensions/tree/master/samples/scripts)_ directory under $SOAPUI_HOME 
- And implement appropriate groovy script available under $SOAPUI_HOME/scripts. Refer [_Mappings_](https://github.com/nmrao/soapuiExtensions/blob/master/src/main/groovy/resources/script.properties) file in order to implement respective groovy script. This mapping file is used internally, it is given here for user reference, so that he can implement his respective script with the right groovy file name.

Note: for windows users, you may need to check %SOAPUI_HOME%\bin\soapui.bat which actually overwrites SOAPUI_HOME, need to fix soapui.bat script if requires.

If you donot wish to make any changes to %SOAPUI_HOME%\bin\soapui.bat  file on windows, then you want to copy that groovy file under %SOAPUI_HOME%\bin\scripts instead of %SOAPUI_HOME%\scripts and retry. If your machine is linux then it should work if you copy under $SOAPUI_HOME/scripts directory as mentioned above.

uses jdk 7, soapUI 4.5.1, and groovy 1.8.9

Dependency
 - log4j
