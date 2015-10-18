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

    private SoapUIGroovyScriptEngineHelper(ClassLoader loader,String scriptText, def context, def testRunner, def log) {
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

    static SoapUIGroovyScriptEngineHelper getInstance(String key, def context, def runner, def log) {
        def prop = new Properties()
        def loader = SoapUIGroovyScriptEngineHelper.class.getClassLoader()
        def stream = loader.getResourceAsStream('resources/script.properties')
        prop.load(stream)
        def pre_directory = System.getenv('SOAPUI_HOME')
        def directory = pre_directory + '/' + prop.getProperty('SCRIPTS_DIR')
        def script_path = directory+ '/' + prop.getProperty(key)
        def scriptFile = new File(script_path)
        def scriptText
        if (!scriptFile.exists() || scriptFile.isDirectory()) {
            def dummyScriptFile = prop.getProperty('DUMMY_SCRIPT')
            def stream1 = loader.getResourceAsStream("resources/${dummyScriptFile}")
            def scanner = new Scanner(stream1).useDelimiter("\\A")
            scriptText = scanner.hasNext() ? scanner.next() : ""
        } else {
            scriptText = new File(script_path).text
        }
        def scriptEngineHelper =  new SoapUIGroovyScriptEngineHelper(loader,scriptText,context, runner,log)
        scriptEngineHelper
    }
}
