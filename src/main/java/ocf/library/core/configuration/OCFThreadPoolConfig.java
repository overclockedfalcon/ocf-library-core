package ocf.library.core.configuration;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import ocf.library.core.constant.OCFConstants;

@Configuration
@EnableAsync
public class OCFThreadPoolConfig {

	@Value("${threadpool.default.executor.default.size:20}")
	private int defaultThreadpoolDefaultSize;
	@Value("${threadpool.default.executor.max.size:40}")
	private int defaultThreadpoolMaxSize;
	@Value("${threadpool.default.executor.queue.size:40}")
	private int defaultThreadpoolQueueSize;
	@Value("${threadpool.default.executor.timeout.allow:true}")
	private Boolean defaultThreadpoolCoreThreadTimeoutAllow;

	@Bean(name = OCFConstants.OcfThreadPool.DEFAULT)
	public Executor defaultExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		if (defaultThreadpoolDefaultSize > 0)
			executor.setCorePoolSize(defaultThreadpoolDefaultSize);
		if (defaultThreadpoolMaxSize > 0)
			executor.setMaxPoolSize(defaultThreadpoolMaxSize);
		if (defaultThreadpoolQueueSize > 0)
			executor.setQueueCapacity(defaultThreadpoolQueueSize);
		executor.setAllowCoreThreadTimeOut(defaultThreadpoolCoreThreadTimeoutAllow);
		executor.setThreadNamePrefix(OCFConstants.OcfThreadPool.DEFAULT + "Executor-");
		executor.initialize();
		return executor;
	}

}
