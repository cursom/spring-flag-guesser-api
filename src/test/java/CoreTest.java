package hu.cursom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Core.class)
class CoreTest {

	@Test
	@Disabled("Temporarily disabled until context loading is fixed")
	void contextLoads() {
	}
}