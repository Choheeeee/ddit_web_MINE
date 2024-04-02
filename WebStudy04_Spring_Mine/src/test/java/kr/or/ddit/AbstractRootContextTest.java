package kr.or.ddit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig(locations = "file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
public abstract class AbstractRootContextTest {

}
