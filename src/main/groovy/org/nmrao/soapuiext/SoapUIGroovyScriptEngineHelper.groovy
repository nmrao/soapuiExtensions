package org.nmrao.soapuiext
import com.eviware.soapui.SoapUI
import com.eviware.soapui.support.GroovyUtils
import com.eviware.soapui.support.scripting.groovy.SoapUIGroovyScriptEngine

class SoapUIGroovyScriptEngineHelper {

    def context
    def testRunner
    def log
    String scriptText
    SoapUIGroovyScriptEngine scriptEngine

    protected SoapUIGroovyScriptEngineHelper(ClassLoader loader,String scriptText, def context, def testRunner, def log) {
        this.context = context
        this.log = log
        this.testRunner = testRunner
        this.scriptText = scriptText
        scriptEngine = new SoapUIGroovyScriptEngine(loader)
    }

    void run() {
        scriptEngine.setScript(scriptText)
        try {
            if (scriptText.trim().length() > 0) synchronized (this) {
                if (context) scriptEngine.setVariable("context", context)
                if (testRunner) scriptEngine.setVariable("testRunner", testRunner)
                scriptEngine.setVariable("log", log)
                scriptEngine.run()
            }
        } catch ( Throwable e ) {
            String errorLineNumber = GroovyUtils.extractErrorLineNumber( e )
            SoapUI.logError( e )
        } finally {
            if( scriptEngine != null )
                scriptEngine.clearVariables()
        }
    }
}
