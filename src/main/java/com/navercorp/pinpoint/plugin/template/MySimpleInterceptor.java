package com.navercorp.pinpoint.plugin.template;

import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.SpanRecorder;
import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.interceptor.SpanSimpleAroundInterceptor;

public class MySimpleInterceptor extends SpanSimpleAroundInterceptor {

    protected MySimpleInterceptor(TraceContext traceContext, MethodDescriptor methodDescriptor, Class<? extends SpanSimpleAroundInterceptor> childClazz) {
        super(traceContext, methodDescriptor, childClazz);
    }

    @Override
    protected void doInBeforeTrace(SpanRecorder spanRecorder, Object o, Object[] objects) {
        System.out.println("Before method execution");
    }

    @Override
    protected Trace createTrace(Object o, Object[] objects) {
        return null;
    }

    @Override
    protected void doInAfterTrace(SpanRecorder spanRecorder, Object o, Object[] objects, Object o1, Throwable throwable) {
        System.out.println("After method execution");
    }
}
