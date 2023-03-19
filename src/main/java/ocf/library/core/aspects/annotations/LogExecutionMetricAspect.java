package ocf.library.core.aspects.annotations;

import java.text.MessageFormat;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogExecutionMetricAspect {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("@annotation(ocf.library.core.annotations.LogExecutionMetric)")
	public void executionMetricPointcut() {
	};

	@Around(value = "executionMetricPointcut()")
	public Object calculateExecutionMetric(ProceedingJoinPoint proceedingJoinpoint) throws Throwable {
		Long triggeredTime = System.currentTimeMillis();
		Object object = proceedingJoinpoint.proceed();
		logger.debug(MessageFormat.format(
				proceedingJoinpoint.getTarget().getClass().getSimpleName() + " -> "
						+ proceedingJoinpoint.getSignature().getName() + " method took: {0} ms",
				System.currentTimeMillis() - triggeredTime));
		return object;

	}

}
