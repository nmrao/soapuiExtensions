package org.nmrao.soapuiext

class SoapUIGroovyScriptEngineHelperUtil {

    static SoapUIGroovyScriptEngineHelper getInstance(String key, def context, def runner, def log) {
        Properties prop = new Properties();
        ClassLoader loader = SoapUIGroovyScriptEngineHelperUtil.class.getClassLoader()
        //ClassLoader loader = Thread.currentThread().getContextClassLoader()
        InputStream stream = loader.getResourceAsStream('resources/script.properties')
        prop.load(stream)
        def pre_directory = System.getenv('SOAPUI_HOME')
        def directory = pre_directory + '/' + prop.getProperty('SCRIPTS_DIR')
        String script_path = directory+ '/' + prop.getProperty(key)
        File scriptFile = new File(script_path)
        def scriptText
        if (!scriptFile.exists() || scriptFile.isDirectory()) {
            InputStream stream1 = loader.getResourceAsStream('resources/Dummy.groovy')
            Scanner s = new Scanner(stream1).useDelimiter("\\A")
            scriptText = s.hasNext() ? s.next() : ""
        } else {
            scriptText = new File(script_path).text
        }
        SoapUIGroovyScriptEngineHelper scriptEngineHelper =  new SoapUIGroovyScriptEngineHelper(loader,scriptText,context, runner,log)
        return scriptEngineHelper
    }

}
