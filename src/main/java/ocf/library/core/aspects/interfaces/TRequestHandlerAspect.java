package ocf.library.core.aspects.interfaces;

import java.text.MessageFormat;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TRequestHandlerAspect {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(* ocf.library.core.interfaces.TRequestHandler.handle(..))")
	public void tRequestHandlerHandlePointcut() {
	};

	@Around(value = "tRequestHandlerHandlePointcut()")
	public Object tRequestHandlerExecutionTimeAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Long triggeredTime = System.currentTimeMillis();
		Object object = proceedingJoinPoint.proceed();
		logger.debug(MessageFormat.format(
				proceedingJoinPoint.getTarget().getClass().getSimpleName() + " -> "
						+ proceedingJoinPoint.getSignature().getName() + " method took: {0} ms",
				System.currentTimeMillis() - triggeredTime));
		return object;
	}

}
