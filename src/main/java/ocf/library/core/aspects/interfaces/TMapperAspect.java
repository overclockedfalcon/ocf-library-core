package ocf.library.core.aspects.interfaces;

import java.text.MessageFormat;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TMapperAspect {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(* ocf.library.core.interfaces.TMapper.map(..))")
	public void tMapperMapPointcut() {
	};

	@Around(value = "tMapperMapPointcut()")
	public Object tMapperExecutionTimeAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Long triggeredTime = System.currentTimeMillis();
		Object object = proceedingJoinPoint.proceed();
		logger.debug(MessageFormat.format(
				proceedingJoinPoint.getTarget().getClass().getSimpleName() + " -> "
						+ proceedingJoinPoint.getSignature().getName() + " method took: {0} ms",
				System.currentTimeMillis() - triggeredTime));
		return object;
	}

}
