soapuiExtensions
================
This soapuiExtensions library allows users to have some additional functionality in soapUI(free edition) tool.

<p>For eg:User may want to do something before running a test case or after running a test case etc by implementing appropriate groovy script as required. Allow me to add an example here. Usually user may want to add credentials for the request step automatically, see the script samples/scripts/TestSuiteTestStepAdded.groovy</p>

<p>
<b>How to use this library:</b><br>
    set <i>SOAPUI_HOME</i> environment variable.<br>
    copy lib/SoapUIExtListeners.jar file under $SOAPUI_HOME/bin/ext directory<br>
    copy samples/listeners/custom-listeners.xml file under $SOAPUI_HOME/bin/listeners directory<br>
    copy samples/scripts directory under $SOAPUI_HOME<br>
    And implement appropriate groovy script available under $SOAPUI_HOME/scripts<br>
    Note: for windows users, you may need to check %SOAPUI_HOME%\bin\soapui.bat which actually overwrites SOAPUI_HOME, need to fix soapui.bat script if requires.<br>
</p>