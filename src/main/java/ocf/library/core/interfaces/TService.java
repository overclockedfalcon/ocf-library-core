package ocf.library.core.interfaces;

import org.springframework.stereotype.Component;

@Component
public interface TService <T,U>{
	public U execute(T request);
}
