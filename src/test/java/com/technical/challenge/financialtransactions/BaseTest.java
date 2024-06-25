package com.technical.challenge.financialtransactions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.mockito.MockitoAnnotations.openMocks;

public class BaseTest {

    private AutoCloseable closeable;

    @BeforeAll
    public static void setUp() {
        loadTemplates("com.technical.challenge.financialtransactions.fixture");
    }

    @BeforeEach
    public void init() {
        closeable = openMocks(this);
    }

    @AfterEach
    public void closhEach() throws Exception {
        closeable.close();
    }

}
