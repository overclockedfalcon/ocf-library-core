package ocf.library.core.interfaces;

import org.springframework.stereotype.Component;

@Component
public interface TValidator<T> {

	public void validate(T request);

}
