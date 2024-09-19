package com.navercorp.pinpoint.plugin.template;

import com.navercorp.pinpoint.bootstrap.instrument.InstrumentClass;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentException;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentMethod;
import com.navercorp.pinpoint.bootstrap.instrument.Instrumentor;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformCallback;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplate;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplateAware;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPlugin;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPluginSetupContext;

import java.security.ProtectionDomain;

public class MyPlugin implements ProfilerPlugin, TransformTemplateAware {
    private TransformTemplate transformTemplate;

    @Override
    public void setup(ProfilerPluginSetupContext context) {
        addTransformers();
    }

    private void addTransformers() {
        transformTemplate.transform("com.example.MyTargetClass", new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass targetClass = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
                InstrumentMethod targetMethod = targetClass.getDeclaredMethod("myTargetMethod", "java.lang.String"); // Adjust method signature as needed
                targetMethod.addInterceptor(MySimpleInterceptor.class);
                return targetClass.toBytecode();
            }
        });
    }

    @Override
    public void setTransformTemplate(TransformTemplate transformTemplate) {
        this.transformTemplate = transformTemplate;
    }
}