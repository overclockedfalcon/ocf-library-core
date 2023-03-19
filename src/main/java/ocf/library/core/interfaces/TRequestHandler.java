package ocf.library.core.interfaces;

import org.springframework.stereotype.Component;

@Component
public interface TRequestHandler<T, U> {
	public U handle(T request);

}
